/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 *
 * @author Tobal
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        Client c = Client.create(cc);
        WebResource resource = c.resource("http://localhost:7001/Second/webresources/Items");
        WebResource resource2 = c.resource("http://localhost:7001/MyFirstREST/webresources/MyPath");
        String response = resource.get(String.class);
        String response2 = resource2.get(String.class);
        System.out.println(response);
        System.out.println(response2);
    }
    
}
