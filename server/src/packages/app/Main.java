package packages.app;

import packages.util.Request;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.*;
import java.util.concurrent.*;

/**
 * Main class
 */
public class Main {

    /**
     * Program entry point
     *
     * @param args the input arguments
     */
    public static void main(String args[]) throws IOException {

        CollectionManager collectionManager = new CollectionManager();
        CommandsList commandsList = new CommandsList(collectionManager);
        ExecutorService clientPool = Executors.newCachedThreadPool();
        Server server = new Server();

        collectionManager.collection_initialization();


        while(true) {
            server.getSelector().select(3000);
            Set<SelectionKey> keys = server.getSelector().selectedKeys();

            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SelectionKey resultKey = server.register();
                    clientPool.submit(() -> {
                        while (true) {
                            Request request = server.readRequest(resultKey);
                            if (request.getCommand() != null) {
                                request.setKey(resultKey);
                                commandsList.execute(request);
                            }
                        }
                    });
                }
            }
        }
    }
}