
package serverudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ServerUDP {
 private static int PORT  = 9091;
 public static void main(String[] args) throws IOException{
	 DatagramSocket serverSocket =new DatagramSocket (PORT);//CREA UN SOCKET
	 System.err.println("Server listening on port " + PORT +" using UDP connection \n");
	 long initialTime = System.currentTimeMillis();
	 System.out.println("Tiempo inicial "+ initialTime +"\n");
         
	 try {
		 while (true){//BUCLE QUE SIEMPRE VA A DEJAR AL SERVER EN ESCUCHA 
			 byte bufferReceive[] = new byte[128]; //CREA UN ARRAY PARA RECIVIR DATOS
			 DatagramPacket receivePacket=new DatagramPacket(bufferReceive,bufferReceive.length);
			 serverSocket.receive(receivePacket);
			 InetAddress clientAddress=receivePacket.getAddress();
			 int clientPort= receivePacket.getPort();
			 System.out.println("Client port: "+clientPort+"\n");//MUESTRA EL PUERTO 
			 
                         //ENVIA LA INFORMACIO 
                         final String user = System.getProperty("user.name");//OBTIENE EL NOMBRE DEL USUARIO LOGEADO EN EL SERVIDOR
                         //OBTIENE EL NOMBRE DEL SERVIDOR 
                         InetAddress addr = InetAddress.getLocalHost();
                          String hostname = addr.getHostName();
			 //String msg ="Message from server";
                         String msg ="username: "+user +"\n"+"Servername: " + hostname;//CONSTRUIR EL MENSAJE
			 //ENVIA EL MENSAJE 
                         byte bufferSend[]=msg.getBytes();
			 DatagramPacket sendPacket= new DatagramPacket(bufferSend,bufferSend.length,clientAddress,clientPort);
			 serverSocket.send(sendPacket);
		 	}
	 	}
	 finally{
		 serverSocket.close();
	 	}
	 }
}