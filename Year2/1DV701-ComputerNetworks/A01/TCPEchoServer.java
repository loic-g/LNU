/*
This class is the Echo Server using TCP and it contains 1 method:
    * main class
            Setup the Service Socket and connect it, then create an instance of ClientClassThread puts it into a thread and starts the Thread.

 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class  TCPEchoServer {
    public static final int MYPORT= 4950;
    private static int UID = 0;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            System.out.println("Starting the server...");
            ServerSocket welcomeSocket = new ServerSocket(MYPORT);
            byte[] buf = new byte[1024];

            while (true) {
                socket = welcomeSocket.accept();
                try {
                    ClientClassThread server = new ClientClassThread(socket, buf, ++UID); //type runnable

                    Thread client = new Thread(server, "Client " + UID);

                    client.start();
                }catch (Exception e){
                    socket.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
/*
This class is named ClientClassThread and implements Runnable, needs a Socket, byte[] and int. it contains 2 methods:
    * run()
            Infinite loop to wait for message from client and read it. then sending it back to client

    * void closeClient()
            Close the socket.

 */
class ClientClassThread implements Runnable{

    private final int userID;
    private byte[] buf;

    private Socket socket;
    private InputStream input;
    private  OutputStream output;

    public ClientClassThread(Socket socket,byte[] buf,int userID) {
        this.socket = socket;
        this.userID = userID;
        this.buf = buf;
    }


    @Override
    public void run() {
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
            //infinite loop to wait for message from client and read it. then sending it back to client

           while(true){
               try {
                   input.read(buf);//TODO

                   output.write(buf, 0, new String(buf).trim().length());

                   System.out.println("ID: " + userID + " IP: " + socket.getInetAddress().getHostAddress() + " PORT: " + socket.getPort());
               }catch (Exception e){
                   closeClient();
               }
           }
        }catch (Exception e){
            closeClient();
        }finally {
            closeClient();
        }
    }

    private void closeClient(){
        try{
            socket.close();
            Thread.currentThread().interrupt();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



