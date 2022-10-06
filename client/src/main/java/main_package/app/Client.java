package main_package.app;

import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import main_package.util.Response;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import main_package.util.Request;
import main_package.util.SenderResult;

import java.time.DateTimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private final int SERVER_PORT = 8081;
    private final int BUFFER_SIZE = 1048576;
    private Socket socket = new Socket();
    private InetAddress address;
    private InputStream inputStream;
    private OutputStream outputStream;



    public boolean connect() {
        try {
            address = InetAddress.getLoopbackAddress();
            socket = new Socket(address, SERVER_PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("Успешно подключились к серверу");
            return true;
        }
        catch (IOException e) {
            System.out.println("Сервер недоступен. \n");
            return false;
        }
    }

//    public boolean reconnect() {
//        while (!connect()) {
//            System.out.println("Переподключение к серверу...");
//        }
//        return true;
//    }
//
//    public boolean isConnected() {
//        return socket.isConnected();
//    }

    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Не удалось корректно завершить работу с сервером");
        }
    }

    public SenderResult sendRequest(Request request) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream)) {
            output.writeObject(request);
            outputStream.write(byteArrayOutputStream.toByteArray());
        }
        catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            return new SenderResult(false, "Не удалось отправить данные на сервер");
        }
        return new SenderResult();
    }

    public Response getResponse() {
        Response response = null;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            inputStream.read(buffer.array());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream input = new ObjectInputStream(byteArrayInputStream);
            response = (Response)input.readObject();
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Ошибка получения данных с сервера");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Некорреткные данные с сервера");
        }
        catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }




}