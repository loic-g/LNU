package runServer;
import server.TCPServer;

/**
 * This web-server follows HTTP/1.1 protocol for GET, POST, and PUT methods.
 * It consists of 4 classes (TCPServer, ClientThread, HttpProtocolHandler, and ResponseStatusCode)
 * @see TCPServer
 * @see server.ClientThread
 * @see server.HttpProtocolHandler
 * @see server.ResponseStatusCode
 *
 * @author Anas Kwefati & Loïc Galland
 * @version 0.1
 *
 */

public class MainServer {
    public static void main(String[] args) {

        /**
         *
         * Arguments that the user will have to put to run the server [PORT] [ROOT DIRECTORY HTML FILE].
         *
         */

        TCPServer server = new TCPServer(args);
        server.runServer();
    }
}
