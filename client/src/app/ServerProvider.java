package app;

import util.Request;
import util.Response;
import util.SenderResult;

public class ServerProvider {
    private static Client client = new Client();
    private static ServerProvider serverProvider = new ServerProvider();

    private ServerProvider() {
        client.connect();
    }

    public static ServerProvider getServerProvider() {
        return serverProvider;
    }
    public static Client getClient() {
        return client;
    }

    public SenderResult send(Request request) {
        return client.sendRequest(request);
    }

    public Response getResponse() {
        while (true) {
            Response response = client.getResponse();
            if (response != null) return response;
            System.out.println("kek");
        }
    }

}
