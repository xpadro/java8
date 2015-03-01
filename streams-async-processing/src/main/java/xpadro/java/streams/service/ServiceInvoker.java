package xpadro.java.streams.service;

import org.springframework.web.client.RestTemplate;
import xpadro.java.streams.model.Client;

public class ServiceInvoker {
	private static final String URI = "http://localhost:8081/spring-rest-simple/clients/{clientId}";
	private RestTemplate restTemplate = new RestTemplate();
	
	public Client invoke(String id) {
		return restTemplate.getForObject(URI, Client.class, id);
	}
}
