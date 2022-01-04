/*
This class is the Echo Client using UDP and it contains 5 methods:
    * main class
            Setup the Socket and connect it, then run the program until manual cancellation

    * void oneSecondMessage(int transferRate,DatagramSocket socket)
            This method needs an int value for the transfer Rate of how many messages per second.
            And a an Object of type DatagramSocket.
            This method will run for one second and will only send n amount of packets to the server
            where n is the transfer rate value.

    * void checkArguments(String[] args)
            This method check the different Arguments of the client to see if they
            are all correct. All conditions below needs to be true in order for the program to continues
            It checks:
            * The IP:
                    - Check that each number of the IP is in fact a number
                    - Check if the numbers are between 0 and 255
            * The Port:
                    - Check if argument is a number
                    - Check if number is between 0 and 65535
            * Buffer Rate:
                    - Check if argument is a number
                    - Check if number is between 1 and 2048
            * Buffer Rate:
                    - Check if argument is a number
                    - Check if number is more than/equal to 0

    * void sendAndReceivePackets(DatagramSocket socket)
            Method used to send and receive packets to the server.
            Needs to be given an Object of type DatagramSocket.

    * void closeSocket()
            Closes socket and exit program.
 */

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPEchoClient {
    private  static String MSG = "Hello, this is a message";
    private static InputStream input;
    private static OutputStream output;
    private  static Socket socket = new Socket();

    private static int PORT;
    private static String IP;
    private static int bufferSize;
    private static int transferRate;


    public static void main(String[] args)  {
        checkArguments(args);

        try {
            //to connect to the server
            InetAddress inetaddr = InetAddress.getByName(IP);
            InetSocketAddress addr = new InetSocketAddress(inetaddr,PORT);
            socket.connect(addr,2000);

            while(true){
                try{
                    oneSecondMessage(transferRate);
                }catch (Exception e){
                    closeSocket();
                }
            }

        }catch (Exception e){
            closeSocket();
        }
    }

    public static void oneSecondMessage(int transferRate){
        int tr = transferRate;
        long time = System.currentTimeMillis();
        System.out.println("******************************");
        while((System.currentTimeMillis()-time)<1000){
            if(transferRate==0){
                try {
                    sendAndReceivePackets();
                    socket.close();
                    System.exit(1);
                } catch (IOException e) {
                    closeSocket();
                }
            }else if(tr>0){
                try{
                    sendAndReceivePackets();
                    tr--;
                }catch(IOException ioe){
                    closeSocket();
                }
            }
        }
    }
    public static void sendAndReceivePackets() throws IOException{
        byte[] buffer = new byte[bufferSize];
        output = socket.getOutputStream();
        output.write(MSG.getBytes());

        input = socket.getInputStream();

       int read =  input.read(buffer);//TODO
        String receivedMessage = new String(buffer,0,read);
        //System.out.println(input.available());
        while(input.available()>0){

            read = input.read(buffer);
            receivedMessage += new String(buffer,0,read);
        }
        //To remove all spaces
       receivedMessage = receivedMessage.trim();

        if (receivedMessage.compareTo(MSG) == 0)
            System.out.printf("%d bytes sent and received\n", receivedMessage.length());
        else
            System.out.printf("Sent and received msg not equal! " + receivedMessage + " \n");
    }

    public static void checkArguments(String[] args){
        if (args.length != 4) {
            System.err.printf("usage: %s server_name port\n", args[3]);
            System.exit(1);
        }

        String[] check = args[0].split("\\.");

        if(check.length==4){
            boolean isValid = true;
            for(int i=0;i<check.length;i++){
                if(Integer.valueOf(check[i])<0 && Integer.valueOf(check[i])>255){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                IP = args[0]; //0-255
            }else{
                System.out.println("The IP address is not correct");
                System.exit(1);
            }
        }else {
            System.out.println("The IP address is not correct");
            System.exit(1);
        }
        if(Integer.parseInt(args[1])>=0 && Integer.parseInt(args[1])<65535){
            PORT = Integer.parseInt(args[1]); //65535 0
        }else{
            System.out.println("The PORT is not correct");
            System.exit(1);
        }
        if(Integer.parseInt(args[2])>0 && Integer.parseInt(args[2])<2048) {
            bufferSize = Integer.parseInt(args[2]); //1 2048
        }else{
            System.out.println("The Buffer size is not correct");
            System.exit(1);
        }
        if(Integer.parseInt(args[3])>=0){
            transferRate = Integer.parseInt(args[3]); // !<0
        }else{
            System.out.println("The Transfer rate is not correct");
            System.exit(1);
        }
    }

    private static void closeSocket(){
        System.out.println("Servers are shutting down.....");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

}
