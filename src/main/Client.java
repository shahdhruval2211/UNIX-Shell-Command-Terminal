package main;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	
	 private final Socket clientSocket;
	  
     // Constructor
     public Client(Socket socket)
     {
         this.clientSocket = socket;
     }

	
	  public static void main(String[] args) throws Exception, IOException {
	  
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Enter the IP address of the server or type Localhost to connect locally:");
		  String ip = sc.nextLine();
		  Socket s = new Socket(ip,3333);
		  
		  DataInputStream din = new DataInputStream(s.getInputStream());
		  DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  
		  System.out.println("******************************Welcome to Neel And Dhruval's Unix Shell******************************");
		  System.out.println("Enter Unix Command to get output or Type Quit to exit");
		  String str="",str2=""; 
		  while(!str.equals("quit")){
			  System.out.print(">>");
			  str=br.readLine(); 
			  dout.writeUTF(str);
			  dout.flush();
			  str2=din.readUTF();
			  System.out.println("\n"+str2);
		  }
		  System.out.println("Good Bye!! Have a Good Day");
		  dout.close(); 
		  s.close();
		  sc.close();
	  }
	 
}
