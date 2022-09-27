package app;

import util.Request;
import util.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int SERVER_PORT = 8081;
    private static final int BUFFER_SIZE = 1024 * 1024;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static ExecutorService sendResponse = Executors.newFixedThreadPool(10);


    public Server() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(SERVER_PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Сервер запущен");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public Selector getSelector() {
        return selector;
    }

    public SelectionKey register() {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            return client.register(selector, SelectionKey.OP_READ);
        }
        catch (IOException e) {
            System.out.println("Ошибка в регистрации канала");
        }
        return null;
    }


    public Request readRequest(SelectionKey key) {
        Request request = new Request();
        request.setKey(key);
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            socketChannel.read(buffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream input = new ObjectInputStream(bais);
            request = (Request) input.readObject();
        }
        catch (IOException e) {
            key.cancel();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Ошибка получения запроса от клиента - " + key);
        }
        return request;
    }

    public static void sendResponse(Response response, SelectionKey key) {
        sendResponse.submit(() -> {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(response);
                socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
            }
            catch (IOException e) {
                System.out.println("что - то");
            }
        });
    }
}
