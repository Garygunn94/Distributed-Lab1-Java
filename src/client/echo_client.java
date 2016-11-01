package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

public class echo_client {
	public static String host;
	public static int port;
	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		if (args.length > 0) {
		    try {
		        host = args[0];
		        port = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[1] + " must be an integer.");
		        System.exit(1);
		    }
		}
		else{
			System.err.println("Arguments Host and Port needed");
	        System.exit(1);
		}
		echo_client http = new echo_client();
		
		System.out.println("Testing 1 - Send Http GET request");
		String message = "hello world";
		while(message.compareTo("xxxxxx") != 0){
			http.sendGet(message, host, port);
			System.out.println("Enter a new string to send to server. Enter 'xxxxxx' to quit\n");
			Scanner scanner = new Scanner(System. in); 
			message = scanner.nextLine();
		}
		System.out.println("Terminated Connection");
	}

	private void sendGet(String message, String httphost, int httpport) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientBuilder.create().build();
		URI uri = new URIBuilder(httphost)
				.setPort(httpport)
				.addParameter("message", message)
				.build();
	
		HttpGet httpGet = new HttpGet(uri);
		
		HttpResponse response = client.execute(httpGet);
		
		System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
	System.out.println(result);
	}
}
