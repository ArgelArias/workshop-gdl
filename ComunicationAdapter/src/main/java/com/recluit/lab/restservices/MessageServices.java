package com.recluit.lab.restservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class MessageServices {

	@GET
	@Path("/{rfc}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String sayHello(@PathParam("rfc") String rfc){
//		return "Hello from RESTServer";
		try{
//			Socket s = new Socket("127.0.0.1", 50000);
			Socket s = new Socket("127.0.0.1", 3550);
			StringBuilder strb = new StringBuilder("");
			PrintWriter writer = new PrintWriter(s.getOutputStream());
			InputStreamReader stream = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(stream);
			//close loan, payment, get a new loan, display
			writer.println(rfc);
			//writer.println("34567ZXCVB");
			writer.flush();
			String str = reader.readLine();
			String[] result = str.split("@");
			System.out.println("Text received from server; " + str);
			for(int i=0;i<result.length-1;i++){
				//System.out.println(result[i]);
				strb.append(result[i]).append("\n");
			}
			writer.close();
			reader.close();
			return str.replace("|", "@");
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public String sayXMLHello(){
//		return "<?xml version=\"1.0\"?>" +
//				"<hello>Hello from RESTServer!!!</hello>";
//	}
	
}
