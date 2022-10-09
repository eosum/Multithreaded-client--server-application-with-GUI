package packages.connection;

import packages.util.Request;
import packages.util.Response;
import packages.util.SenderResult;

import java.io.IOException;

public class ServerProvider {
    private Client client = new Client();
    private static ServerProvider serverProvider;

    private ServerProvider() {
        client.connect();
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
