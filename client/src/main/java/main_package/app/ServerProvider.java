package main_package.app;

import main_package.exceptions.ConnectException;
import main_package.util.Request;
import main_package.util.Response;
import main_package.util.SenderResult;

import java.io.IOException;

public class ServerProvider {
    private Client client = new Client();
    private static ServerProvider serverProvider;

    private ServerProvider() {
        client.connect();
        /*int attempt = 0;
        while(!client.connect()) {
            attempt++;
            if(attempt ==  5) {
                System.exit(0);
            }
        }*/

    }

    public static ServerProvider getServerProvider() {
        if (serverProvider == null) serverProvider = new ServerProvider();
        return serverProvider;
    }
    public Client getClient() {
        return client;
    }

    public SenderResult send(Request request) {
        return client.sendRequest(request);
    }

    public Response getResponse() throws NullPointerException, IOException {
        while (true) {
            Response response = client.getResponse();
            if (response != null) return response;
        }
    }

    public void reconnect() {
        client.reconnect();
    }

}
