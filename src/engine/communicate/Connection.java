package engine.communicate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Connection  implements Serializable {

    public static ArrayList<PacketListener> packetListeners = new ArrayList<>();

    public static void start(UserClient userClient, ConnectionListener listener) {
        new Thread(new ClientConnectionListener(userClient, listener)).start();
    }

    public static class ClientConnectionListener implements Runnable {
        UserClient client;
        ConnectionListener listener;

        public ClientConnectionListener(UserClient client, ConnectionListener listener) {
            this.client = client;
            this.listener = listener;
        }

        public void run() {
            while (true) {
                try {
                    Object obj = client.getObj();
                    for (PacketListener e : Connection.packetListeners) {
                        if (e.instanceOf(obj)) {
                            e.onPacketReceived(obj);
                        }
                    }
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Connection Error");
                    listener.onConnectionLost(client);
                    try {
                        client.disconnect();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    public static void login(String ip, int port, String username, String password, ClientListener clientListener) {
        try {
            Socket socket = new Socket(ip, port);
            UserClient server = new UserClient();
            server.setSocket(socket);
            server.setSender(new ObjectOutputStream(socket.getOutputStream()));
            server.getSender().flush();
            server.setGetter(new ObjectInputStream(socket.getInputStream()));
            server.sendObj(new Account(username, password));
            new Thread(new ServerListener(server, clientListener)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createAccount(String ip, int port, String username, String password, ClientListener clientListener) {
        try {
            Socket socket = new Socket(ip, port);
            UserClient server = new UserClient();
            server.setSocket(socket);
            server.setSender(new ObjectOutputStream(socket.getOutputStream()));
            server.getSender().flush();
            server.setGetter(new ObjectInputStream(socket.getInputStream()));
            server.sendObj(Chirp.CreateUserAccount);
            server.sendObj(new Account(username, password));
            new Thread(new ServerListener(server, clientListener)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void join(String ip, int port, ClientListener clientListener) {
        try {
            Socket socket = new Socket(ip, port);
            UserClient server = new UserClient();
            server.setSocket(socket);
            server.setSender(new ObjectOutputStream(socket.getOutputStream()));
            server.getSender().flush();
            server.setGetter(new ObjectInputStream(socket.getInputStream()));
            new Thread(new ServerListener(server, clientListener)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ServerListener implements Runnable {
        UserClient server;
        ClientListener clientListener;

        public ServerListener(UserClient server, ClientListener clientListener) {
            this.server = server;
            this.clientListener = clientListener;
        }

        public void run() {
            try {
                Object obj = server.getObj();
                if (obj instanceof Chirp) {
                    Chirp c = (Chirp) obj;
                    switch (c) {
                        case Connected:
                            clientListener.onConnect(server);
                            System.out.println("Connected");
                            break;
                        case IncorrectPassword:
                            clientListener.onError(Chirp.IncorrectPassword);
                            break;
                        case UnknownUserAccount:
                            clientListener.onError(Chirp.UnknownUserAccount);
                            break;
                        case UserAccountTaken:
                            clientListener.onError(Chirp.UserAccountTaken);
                            break;
                        default:
                            break;
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
