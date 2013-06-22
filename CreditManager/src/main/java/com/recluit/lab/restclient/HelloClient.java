package com.recluit.lab.restclient;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HelloClient {
	
	public String getMessage(){
		Client client = Client.create();
		WebResource webResourse = client.resource("http://localhost:8080/RESTServer/rest/hello");
		ClientResponse reponse = webResourse.accept("text/plain").get(ClientResponse.class);
		if(reponse.getStatus() != 200){
			throw new RuntimeException("Failed!!!: " + reponse.getStatus());
		}
		String output = reponse.getEntity(String.class);
		return output;
	}

}
