package app;

import util.Request;
import util.Response;
import util.SenderResult;

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

    public Response getResponse() {
        while (true) {
            Response response = client.getResponse();
            if (response != null) return response;
        }
    }

}
