
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
            * Transfer Rate:
                    - Check if argument is a number
                    - Check if number is more than/equal to 0

    * void sendAndReceivePackets(DatagramSocket socket)
            Method used to send and receive packets to the server.
            Needs to be given an Object of type DatagramSocket.

    * boolean isNumeric(final String str)
            Returns "true" if the string contains a number
            Otherwise returns "false".
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class UDPEchoClient {
    private static int PORT;
    private static String IP;
    private static int bufferSize;
    private static int transferRate;
    private static int count =0;


    public static void main(String[] args)  {
        checkArguments(args);
        final int MYPORT= 0;

    try {
        /* Create socket */
        DatagramSocket socket = new DatagramSocket(null);
        /* Create local endpoint using bind() */
        SocketAddress localBindPoint = new InetSocketAddress(MYPORT);
        socket.bind(localBindPoint);

        while(true){
            System.out.println("transfer rate:  " + transferRate + " Second passed: "+ ++count );
            oneSecondMessage(transferRate,socket);
        }
    }catch (IOException ioe){
        System.out.println("Connection unsuccessful. Please retry again");
        System.exit(1);
    }

    }

    public static void oneSecondMessage(int transferRate,DatagramSocket socket){
        int tr = transferRate;
        long time = System.currentTimeMillis();
        while((System.currentTimeMillis()-time)<1000){
            if(transferRate==0){
                sendAndReceivePackets(socket);
                socket.close();
                System.exit(1);
            }else if(tr>0){
                sendAndReceivePackets(socket);
                tr--;
            }
        }
    }
    /*
    Check if the arguments are correct
     */
    public static void checkArguments(String[] args){

        if (args.length != 4) {
            System.err.printf("usage: %s server_name port\n", args[3]);
            System.exit(1);
        }
        /*Split at each "." to get all the numbers in IP to test them*/
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
        }if(isNumeric(args[1])){
            if(Integer.parseInt(args[1])>=0 && Integer.parseInt(args[1])<65535){
                PORT = Integer.parseInt(args[1]); //65535 0
            }else{
                System.out.println("The PORT is not correct");
                System.exit(1);
            }
        }else{
            System.out.println("The PORT is not number");
            System.exit(1);
        }
        //Buffer Size
        if(isNumeric(args[2])){
            if(Integer.parseInt(args[2])>0 && Integer.parseInt(args[2])<2048) {
                bufferSize = Integer.parseInt(args[2]); //1 2048
            }else{
                System.out.println("The Buffer size is not between 0 and 2048");
                System.exit(1);
            }
        }else{
            System.out.println("The Buffer size is not a number");
            System.exit(1);
        }
        //Transfer Rate
        if(isNumeric(args[3])){
            if(Integer.parseInt(args[3])>=0){
                transferRate = Integer.parseInt(args[3]); // !<0
            }else{
                System.out.println("The Transfer rate is not correct");
                System.exit(1);
            }
        }else{
            System.out.println("The Transfer rate is not a number");
            System.exit(1);
        }






    }
    /*Used to Send and Receive packets to/from the server.
    *
    * */
    public static void sendAndReceivePackets(DatagramSocket socket) {
        String MSG= "An Echo Message!";

        byte[] buf= new byte[bufferSize];
        /* Create remote endpoint */
        SocketAddress remoteBindPoint =
                new InetSocketAddress(IP, PORT);

        /* Create datagram packet for sending message */
        DatagramPacket sendPacket = new DatagramPacket(MSG.getBytes(), MSG.length(), remoteBindPoint);

        /* Create datagram packet for receiving echoed message */
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
       try {
           /* Send and receive message*/
           socket.send(sendPacket);
           socket.receive(receivePacket);
       }catch(IOException ioe){
           System.out.println("Error while sending/receiving a packet");
       }

        /* Compare sent and received message */
        String receivedString= new String(receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());




        if (receivedString.compareTo(MSG) == 0)
            System.out.printf("%d bytes sent and received\n", receivePacket.getLength());
        else
            System.out.printf("Sent and received msg not equal!\n");

    }
    /*Used to make sure that string contains a numeric number. */
    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);

    }



}
