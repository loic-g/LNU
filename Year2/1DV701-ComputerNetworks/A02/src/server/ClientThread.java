package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class takes care of each client and make sure to receive and send the correct data to the client.
 */

public class ClientThread implements Runnable {

    private Socket socket;
    private int id;
    private byte[] buf; //array from browser
    private OutputStream os;
    protected String directoryRoot;
    private HttpProtocolHandler http = new HttpProtocolHandler();




    public ClientThread(Socket socket, byte[] bufferSize, int id, String directoryRoot) {
        this.socket = socket;
        this.id = id;
        this.buf = bufferSize;
        this.directoryRoot = directoryRoot;
    }

    /**
     *
     * The run method will take the data sent by the client with the InputStream.
     * Then it will call the method readHttpGetRequestLine from HttpProtocolHandler class.
     * This one will take care of all the data sent by the client and split it into multiple chunks
     * to make sure that later on it can be used. For instance, after doing that, we need to check,
     * if the client sent a GET, POST or PUT method. And from there, we decide what method to call and so on.
     * If something wrong happens it will close the client with the method closeClient().
     *
     */

    @Override
    public void run() {
        try {

            InputStream is = socket.getInputStream();
            os = socket.getOutputStream();

            System.out.println(Thread.currentThread().getName());
            System.out.printf("TCP echo request from %s", socket.getInetAddress().getHostAddress());
            System.out.printf(" using port %d\n", socket.getPort());
            System.out.println("----------------------------------------------------------------");


            while (true) {

                /* Create Input Stream for receiving message */

                /* Receiving message */
                int br = is.read(buf); //Reads some number of bytes coming from the browser and stores them into the buffer array b.
                //System.out.println(new String(buf));

                /* Compare sent and received message */
                String receivedString = new String(buf, 0, br);

                ArrayList<Byte> byteArray = new ArrayList<>();

                for (int i=0;i<buf.length;i++){
                    byteArray.add(buf[i]);
                }

                //Allows to check if there are still data into the stream
                while(is.available()>0) {

                 br = is.read(buf);

                 for (int i=0;i<buf.length;i++){
                     byteArray.add(buf[i]);
                 }

                receivedString += new String(buf, 0, br);

                }

                byte[] result = new byte[byteArray.size()];
                for(int i = 0; i < byteArray.size(); i++) {
                    result[i] = byteArray.get(i);
                }

                http.readHttpGetRequestLine(receivedString,result); //Will get all the data

                if(http.requestMethodName.equals("GET")){
                    sendToBrowser(directoryRoot);
                } else if (http.requestMethodName.equals("POST")){
                    http.addFileToServer(directoryRoot);
                    sendToBrowser(directoryRoot);
                } else if (http.requestMethodName.equals("PUT")){
                    http.updateCreateFile(directoryRoot);
                    sendToBrowser(directoryRoot);
                } else {
                    internalError();
                }



           }


        } catch (Exception se){
            System.err.println("Connection has been lost or the client " +id+ " closed it...");
            closeClient();
            internalError();
        } finally {
            closeClient();

        }

        System.out.printf("TCP echo request from %s", socket.getInetAddress().getHostAddress());
        System.out.printf(" using port %d has been closed\n", socket.getPort());



    }

    private void closeClient(){
        try {
            socket.close();
            Thread.currentThread().interrupt();

        }catch (IOException ie){
        }
    }

    private void internalError(){
        try {
            byte[] l = http.get500();
            /* Send message*/
            os.write(l);
            os.flush();
            os.close();
        }catch (IOException ie){
            closeClient();
        }

    }

    /**
     * This method will call readAll() method from HttpProtocolHandler and getResponseHeader().
     * After receiving all the data needed from HttpProtocolHandler, it will combine both bytes arrays.
     * After combining them, it will send them through OutputStream and the close.
     *
     * The byte array "data" will usually contains the HTML page (optional).
     * The byte array "headerData" will contain the response header.
     *
     * The combined array called "concatenated" will contain the whole Server response Message
     * (Response Header + Response Body (optional)).
     * @param getRootDirectory Takes the Directory where HTML files are located.
     *
     */

    private void sendToBrowser(String getRootDirectory){
        try {
            byte[] data = null;
            try {
                data = http.readAll(getRootDirectory);
            }catch (Exception e){

            }

            System.out.println(http.header);
            byte[] headerData = http.getResponseHeader().getBytes();

            ByteArrayOutputStream combined = new ByteArrayOutputStream();
            combined.write(headerData);

            try {
            combined.write(data);
            }catch (Exception e){

            }
            byte[] concatenated = combined.toByteArray();

            /* Send message*/
            os.write(concatenated);
            os.flush();
            os.close();
        }catch (IOException ie){
            closeClient();
        }
    }
}
