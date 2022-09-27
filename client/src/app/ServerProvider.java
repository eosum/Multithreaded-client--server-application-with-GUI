package app;

import util.Request;
import util.Response;

public class ServerProvider {
    private Client client = new Client();
    private static ServerProvider serverProvider = new ServerProvider();

    private ServerProvider() {
        client.connect();
    }

    public static ServerProvider getServerProvider() {
        return serverProvider;
    }

    public void send(Request request) {
        client.sendRequest(request);
    }

    public Response getResponse() {
        while (true) {
            Response response = client.getResponse();
            if (response != null) return response;
        }
    }

}
