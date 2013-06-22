package com.recluit.lab.restclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RFCRestClient {
	public String findRFC(String rfc){
		Client client = Client.create();
		String url = "http://localhost:8080/RESTServer/rest/hello/" + rfc.replace("|", "$").replace("/", "-");
		WebResource resource = client.resource(url);
		ClientResponse response = resource.accept("text/plain").get(ClientResponse.class);
		if(response.getStatus() == 200){
			return response.getEntity(String.class);
		}
		else
			return null;
	}

}

