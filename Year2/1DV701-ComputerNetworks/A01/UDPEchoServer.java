/*
This class is the Echo Server using UDP and it contains 1 method:
    * main class
		- Bind the socket
		- In the infinite loop, it waits for a call from someone
		- When it receive a call from the client, print IP address and Port of the client
		- Sends back the packet to the client for ACK.
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class UDPEchoServer {
    public static final int MYPORT= 4950;
	public static DatagramSocket socket;

    public static void main(String[] args)  {
	
	if (args.length != 1) {
	    System.err.printf("usage: %s server_name port\n", args[0]);
	    System.exit(1);
	}
	System.out.println("Starting server .... ");
	byte[] buf= new byte[Integer.valueOf(args[0])];
	try {
		/* Create socket */
		 socket= new DatagramSocket(null);

		/* Create local bind point */
		SocketAddress localBindPoint = new InetSocketAddress(MYPORT);
		socket.bind(localBindPoint);
		while (true) {
			/* Create datagram packet for receiving message */
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

			/* Receiving message */
			socket.receive(receivePacket);

			/* Create datagram packet for sending message */
			DatagramPacket sendPacket =
					new DatagramPacket(receivePacket.getData(),
							receivePacket.getLength(),
							receivePacket.getAddress(),
							receivePacket.getPort());

			/* Send message*/
			socket.send(sendPacket);
			System.out.printf("UDP echo request from %s", receivePacket.getAddress().getHostAddress());
			System.out.printf(" using port %d\n", receivePacket.getPort());
		}
	}catch (Exception e){
		System.out.println("Server is shutting down.....");
		socket.close();
	}
    } 
}