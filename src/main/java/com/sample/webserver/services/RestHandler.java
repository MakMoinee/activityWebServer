package com.sample.webserver.services;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class RestHandler {

	HttpClient client;

	public RestHandler() {
		client = HttpClient.newHttpClient();
	}

	public void getWeapons() {
		HttpRequest request = HttpRequest.newBuilder(URI.create("https://valorant-api.com/v1/weapons"))
				.method("GET", null).header("Content-Type", "application/json").build();
		try {
			var response = client.send(request, null);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
