import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class TFTPServer {
    public static final int TFTPPORT = 4970;
    public static final int BUFSIZE = 516;
    public static final String READDIR = ""; //INSERT YOUR ADDRESS TO READ FILE
    public static final String WRITEDIR = ""; //INSERT YOUR ADDRESS TO READ FILE

    /* Different OP codes  */
    public static final int OP_RRQ = 1;
    public static final int OP_WRQ = 2;
    public static final int OP_DAT = 3;
    public static final int OP_ACK = 4;
    public static final int OP_ERR = 5;

    private static int opcodeParse; /* Is updated when parsing to get the opcode of the last request */

    public static void main(String[] args) {
        if (args.length > 0)
        {
            System.err.printf("usage: java %s%n", TFTPServer.class.getCanonicalName());
            System.exit(1);
        }
        //Starting the server
        try
        {
            TFTPServer server= new TFTPServer();
            server.start();
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
    }

    private void start() throws SocketException
    {
        byte[] buf= new byte[BUFSIZE];

        // Create socket
        DatagramSocket socket= new DatagramSocket(null);

        // Create local bind point
        SocketAddress localBindPoint= new InetSocketAddress(TFTPPORT);
        socket.bind(localBindPoint);

        System.out.printf("Listening at port %d for new requests%n", TFTPPORT);

        // Loop to handle client requests
        while (true)
        {
            final InetSocketAddress clientAddress = receiveFrom(socket, buf);
            // If clientAddress is null, an error occurred in receiveFrom()
            if (clientAddress == null)
                continue;

            final StringBuffer requestedFile= new StringBuffer();
            final int reqtype = ParseRQ(buf, requestedFile);

            new Thread()
            {
                public void run()
                {
                    try
                    {
                        DatagramSocket sendSocket= new DatagramSocket(0);

                        // Connect to client
                        sendSocket.connect(clientAddress);

                        System.out.printf("%s request for %s from %s using port %d%n",
                                (reqtype == OP_RRQ)?"Read":"Write", clientAddress.getAddress(),
                                clientAddress.getHostName(), clientAddress.getPort());

                        // Read request
                        if (reqtype == OP_RRQ)
                        {
                            requestedFile.insert(0, READDIR);
                            HandleRQ(sendSocket, requestedFile.toString(), OP_RRQ);
                        }
                        // Write request
                        else
                        {
                            requestedFile.insert(0, WRITEDIR);
                            HandleRQ(sendSocket,requestedFile.toString(),OP_WRQ);
                        }
                        sendSocket.close();
                    }
                    catch (SocketException e)
                    {e.printStackTrace();}
                }
            }.start();
        }
    }

    /**
     * Get the Socket address and Port of Client and then return it.
     * @param socket (socket to read from)
     * @param buf (where to store the read data)
     * @return socketAddress (the socket address of the client)
     */
    private InetSocketAddress receiveFrom(DatagramSocket socket, byte[] buf)
    {
        // Create datagram packet
        DatagramPacket receiveRequest = new DatagramPacket(buf, buf.length);

        try {
            socket.receive(receiveRequest); // Receive packet

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        /* Get client address and port from the packet, create an socket Address Object and return it */
        return new InetSocketAddress(receiveRequest.getAddress(),receiveRequest.getPort());
    }

    /**
     * Parses the request in buf to retrieve the type of request and requestedFile
     *
     * @param buf (received request)
     * @param requestedFile (name of file to read/write)
     * @return opcode (request type: RRQ or WRQ)
     */
    private int ParseRQ(byte[] buf, StringBuffer requestedFile)
    {
        /* See "TFTP Formats" in TFTP specification for the RRQ/WRQ request contents */
        ByteBuffer wrap= ByteBuffer.wrap(buf);
        short opcode = wrap.getShort();

        opcodeParse = opcode; //To check the opcode for bad request later on.

        /* Check the bytes of the request from after the opcode until it finds the next 0 and saves its index.
         * This will allow us to get the filename of the file*/
        int indexEnd = 0;
        for (int i=2;i<buf.length;i++){
            if(buf[i]==0){
                indexEnd = i;
                break;
            }
        }
        /* Get the bytes from after the opcode to the index found above and append it into the Stringbuffer */
        byte[] filenameBytes = Arrays.copyOfRange(buf,2,indexEnd);
        requestedFile.append(new String(filenameBytes));

        return opcode;
    }

    /**
     * Handles RRQ and WRQ requests
     *
     * @param sendSocket (socket used to send/receive packets)
     * @param requestedFile (name of file to read/write)
     * @param opcode (RRQ or WRQ)
     */
    private void HandleRQ(DatagramSocket sendSocket, String requestedFile, int opcode)
    {
        if(opcode == OP_RRQ)
        {
            /* See "TFTP Formats" in TFTP specification for the DATA and ACK packet contents */

            boolean result = send_DATA_receive_ACK(sendSocket,requestedFile);
        }
        else if (opcode == OP_WRQ)
        {

            boolean result = receive_DATA_send_ACK(sendSocket,requestedFile);
        }
        else
        {
            System.err.println("Invalid request. Sending an error packet.");

            /* See "TFTP Formats" in TFTP specification for the ERROR packet contents */
            send_ERR(sendSocket,4,"");
        }
    }
    /**
     * Sends the first ACK with block number 0, when answering a write request for the first time
     * @param socket (socket used to send/receive packets)
     *
     */
    private void sendACK0(DatagramSocket socket){

        ByteBuffer ACKtoSend = ByteBuffer.allocate(OP_ACK);
        ACKtoSend.putShort((short)OP_ACK);
        ACKtoSend.putShort((short)0);

        DatagramPacket send = new DatagramPacket(ACKtoSend.array(),ACKtoSend.array().length);
        try{
            socket.send(send);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
    /**
     * This method send Data and receives ACK. It is the READ method.
     * It sends the data and then wait to receive the ACK for it. If the ACK doesnt come after 5s,
     * the socket timeout and then resent the previous packet until we get the corresponding ACK or if
     * it fails more than 10 times. If the ACK block number match with the one sent, then the 512 bytes
     * of data will be sent. This will reproduce until all the file is sent or it catches an error.
     *
     * @param sendSocket (socket used to send/receive packets)
     * @param filename (String with address of the file that need to be sent)
     * @return boolean (return true if the transfer was a success and no if any problems arise
     */
    private boolean send_DATA_receive_ACK(DatagramSocket sendSocket, String filename)
    {
            File fileToSend = new File(filename);

            System.out.println("File Length: " +fileToSend.length() + " Bytes");
            try{
            if(fileToSend.isFile()){
               if(fileToSend.canRead()) {
                   try {
                       FileInputStream fin = new FileInputStream(fileToSend);

                       boolean check = true;
                       int blockNumber = 1; //Keeps track of the amount of block that was sent.
                       while (check) {

                           //Get from file
                           byte[] dataFile = new byte[BUFSIZE - 4];//-4 to get only 512 bytes of the File.
                           int amountRead = fin.read(dataFile);

                            /* Create the data packet inside of the ByteBuffer */
                           ByteBuffer toSend = ByteBuffer.allocate(BUFSIZE);
                           toSend.putShort((short) OP_DAT);// Opcode / short = 2 bytes
                           toSend.putShort((short) blockNumber);// to write the block Number
                           toSend.put(dataFile); //Data from the file that need to be "copied"
                           DatagramPacket dataToSend = new DatagramPacket(toSend.array(), amountRead + 4);

                           int countTrySendData = 0; // Variable that keep track of how many times the sending fails
                           int blockNumberACK = 0; //Block number of the ACK tobe able to compare
                           while (countTrySendData < 10) {
                               try {
                                   sendSocket.send(dataToSend);//Send data

                                   //To check the response (ACK) after sending
                                   byte[] responseBytes = new byte[BUFSIZE];
                                   DatagramPacket response = new DatagramPacket(responseBytes, responseBytes.length);
                                   sendSocket.setSoTimeout(5000);//Set timeout after 5s
                                   sendSocket.receive(response);

                                   ByteBuffer wrap = ByteBuffer.wrap(responseBytes);
                                   short opcode = wrap.getShort();
                                   blockNumberACK = wrap.getShort();

                                   if (opcode == OP_ACK) { //ACK Packet
                                       break;
                                   } else if (opcode == OP_ERR) { //ERROR Packet
                                       System.err.println("Error code " + wrap.getShort());
                                       send_ERR(sendSocket, 0, "Error packet from client");
                                       check = false;
                                       return false;
                                   }
                                   countTrySendData++;

                               } catch (SocketTimeoutException ste) {
                                   System.err.println("Socket timeout / B#" + blockNumber);
                               }
                           }
                           /* Throw exception after 10 trials to send the same data packet without ACK success */
                           if (countTrySendData == 10) {
                               throw new SocketTimeoutException();
                           }
                           /* if the amount of bytes of data is Less than 512 that means that this is the last data packet */
                           if (amountRead < 512) {
                               break;
                           }
                            /* To make sure that the block number from the ACK is the corresponding to the one sent with the data packet.
                             * If it is corresponding then we can send the next block of data */
                           if (blockNumber == blockNumberACK) {
                               blockNumber++;
                           }
                       }
                   }catch (FileNotFoundException ioe){
                       System.err.println("The file cannot be read");
                       send_ERR(sendSocket, 2, ioe.getMessage());
                       return false;
                   }
                   }else{
                       System.err.println("The file cannot be read");
                       send_ERR(sendSocket, 2, "");
                       return false;
                   }


            }else{
                System.err.println("The file "+filename+" was not found");
                send_ERR(sendSocket,1,"");
                return false;

            }

        }catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Transfer Timeout - ACK Not received");
            send_ERR(sendSocket,0,"Transfer has timed out");
            return false;
        }

      return true;
    }

    /**
     * This method receives Data and send ACK. It is the WRITE method.
     * It receive the data by buffer of 512 bytes. Everytime that data is received, the data is written to the file
     * and an ACK is sent with the corresponding Block Number.
     *
     * @param socket (socket used to send/receive packets)
     * @param filename (String with address of the file that need to be sent)
     * @return boolean (return true if the transfer was a success and no if any problems arise
     */
    private boolean receive_DATA_send_ACK(DatagramSocket socket, String filename)
    {
        /* To check if the request is actually a WRITE request, otherwise sends Error packet with error code 4*/
        if(opcodeParse!=OP_WRQ){
            send_ERR(socket,4,"");
            return false;
        }

        File toWrite = new File(filename);
        if(toWrite.exists()){
            System.err.println("The file "+ toWrite.getName() + " Already exists");
            send_ERR(socket,6,"");
            return false;
        }else {
            try {
                FileOutputStream send = new FileOutputStream(toWrite);
                sendACK0(socket); //Sends the first ACK to confirm the request.
                boolean check = true;
                int blockNB = 1; //Block number
                while (check) {
                    byte[] data = new byte[BUFSIZE];
                    DatagramPacket dataPacket = new DatagramPacket(data, data.length);
                    socket.receive(dataPacket); //Receive the data

                    ByteBuffer wrap = ByteBuffer.wrap(dataPacket.getData());
                    short opcode = wrap.getShort();

                    if (opcode == OP_DAT) {// Check if opcode is for a data packet
                        byte[] dataToWrite = Arrays.copyOfRange(dataPacket.getData(),4,dataPacket.getLength()); //Starts at 4 to not take the opcode and Block#

                        //Write the data to the file
                        send.write(dataToWrite);

                        //Send ACK back
                        ByteBuffer responseToSend = ByteBuffer.allocate(OP_ACK); //to create ACK packet.
                        responseToSend.putShort((short) OP_ACK); //Adding the Opcode to byte buffer
                        responseToSend.putShort((short)blockNB); //Same with block number
                        DatagramPacket ACKresponse = new DatagramPacket(responseToSend.array(),responseToSend.array().length);
                        socket.send(ACKresponse);

                    }else if(opcode == OP_ERR){
                        System.err.println("Error packet received from Client");
                        send_ERR(socket,0,"Error packet received");
                        return false;
                    }else{
                        System.err.println("The Opcode "+opcode+" is not handled by this server");
                        send_ERR(socket,4,"");
                        return false;
                    }
                    /* If the data is less than 512 bytes, therefore this the last data packet that we have received and acknowledge for.
                    * Close the FileOutputStream and stop the while loop */
                    if(data.length<512){
                        send.close();
                        check = false;
                    }
                    blockNB++;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
            return true;

    }
    /**
     * This method sends Error packets
     *
     * @param socket (socket used to send/receive packets)
     * @param errorCode (integer with the error code that need to be sent)
     * @param errorMessage (String with the Error message,if needed )
     */
    private void send_ERR(DatagramSocket socket,int errorCode, String errorMessage)
    {
        try {
            String error = "";
            if (errorCode == 0) {
                error += errorMessage;
            } else if (errorCode == 1) {
                error = "File not Found";
            } else if (errorCode == 2) {
                error = "Access violation";
            }else if (errorCode ==3){
                error = "Disk full or allocation exceeded";
            }else if(errorCode ==4){
                error = "Illegal TFTP operation";
            }else if (errorCode==5){
                error = "Unknown transfer ID";
            }else if (errorCode == 6) {
                error = "File already exists";
            }
            ByteBuffer errorByte = ByteBuffer.allocate(BUFSIZE);
            errorByte.putShort((short) OP_ERR);
            errorByte.putShort((short) errorCode);
            errorByte.put(error.getBytes());

            DatagramPacket sendError = new DatagramPacket(errorByte.array(), errorByte.array().length);
            socket.send(sendError);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
