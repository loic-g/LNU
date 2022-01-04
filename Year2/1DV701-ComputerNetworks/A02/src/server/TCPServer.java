package server;

import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * This class creates the thread and implements the class ClientThread.
 * @see ClientThread
 */

public class TCPServer {

    private  final int BUFSIZE= 10000;
    private int PORT;
    private String ROOT;
    private  int id = 0;

    private  Socket socket;

    public TCPServer(String[] args){
        isArgumentValid(args);
    }

    /**
     *
     * This method listens for connections and whenever it receives a client it will create a thread
     * and will call the run method in ClientThread.
     * @see ClientThread
     *
     */
    public void runServer(){

        try {

            byte[] buf = new byte[BUFSIZE];
            /* Create socket */
            //Creates a socket address where the IP address is the wildcard address and the port number a specified value.
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("Server has been started...\n");

            while(true){
                socket = serverSocket.accept(); // Listen for connection
                //socket.setSoTimeout(1000);

                try {

                    ClientThread cl = new ClientThread(socket,buf,id, ROOT); //runnable
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("Client connected "+ id+ " has been connected");
                    Thread newClient = new Thread(cl, "Client "+id + " :");
                    newClient.start(); //run the thread



                    id+=1;
                } catch (Exception e){
                    e.printStackTrace();

                    socket.close();
                    Thread.currentThread().interrupt();
                    //System.out.println("here2");

                    break;
                }


            }

        } catch (IOException ie){
            try {
                socket.close();
            } catch (IOException io){
                //System.out.println("here");
            }

            //ie.printStackTrace();
            //Thread.currentThread().interrupt();
        }

    }

    /**
     * This method takes the arguments and verify that they are correct. Otherwise it will print an error message
     * and shutdown the program. Arguments available : [PORT] [ROOT DIRECTORY]
     *
     * @param args Takes the arguments PORT and Directory to the html files.
     */

    private void isArgumentValid(String[] args){
        //Need to put [PORT] [ROOT DIRECTORY]
        if (args.length != 2) {
            System.err.printf("Error: Incorrect number of arguments. It should be 2: " +
                    "[PORT] [ROOT DIRECTORY HTML FILES]\n");
            System.exit(1);
        }

        PORT = convertArgsStringToInt(args[0]);
        ROOT = args[1];

        File checkPath = new File(ROOT);
        if (!checkPath.isDirectory()){
            System.err.print("Error : Invalid Path. Path does not exist");
            System.exit(1);
        }

        //Check MAX PORT RANGE
        if(PORT>65535 || PORT < 0){
            System.err.print("Error : Invalid Port Number. Must be between [1;65535]");
            System.exit(1);
        }

    }


    /**
     *
     * @param nbrInput Takes the string that needs to be converted into an integer.
     * @return the String converted into an integer.
     */

    private int convertArgsStringToInt(String nbrInput){

        int finalNumber = 0;

        try {
            finalNumber = Integer.parseInt(nbrInput);

        } catch (NumberFormatException e){
            System.err.println("Argument" + nbrInput + " must be an integer.");
            System.exit(1);
        }
        return finalNumber;
    }



}



