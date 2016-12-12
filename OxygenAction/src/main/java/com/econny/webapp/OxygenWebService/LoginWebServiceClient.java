package com.econny.webapp.OxygenWebService;

import java.io.StringReader;

import javax.jws.WebService;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

@WebService
public class LoginWebServiceClient {

	@Autowired
	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	
	private static final String MESSAGE =
	        "<message xmlns=\"http://tempuri.org\">Hello World</message>";
	
	public LoginWebServiceClient(){

		webServiceTemplate.setDefaultUri("http://localhost:8090/WebService");
	}

    // send to the configured default URI
    public void simpleSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    // send to an explicit URI
    public void customSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/AnotherWebService",
            source, result);
    }
}
