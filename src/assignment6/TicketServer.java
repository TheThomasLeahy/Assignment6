/*
 *Michael Spearing MSS3627	Mehtaab F 2:00 - 3:30
 *Thomas Leahy tpl335 Niraj Th 2:00-3:30
 */

package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer
{
    static int PORT;
    // EE422C: no matter how many concurrent requests you get,
    // do not have more than three servers running concurrently
    final static int MAXPARALLELTHREADS = 3;

    // Data Structure for the theater
    public static Theater myTheater = null;

    public static void start(int portNumber, Theater theater) throws IOException
    {
	myTheater = theater;
	PORT = portNumber;
	Runnable serverThread = new ThreadedTicketServer(portNumber);
	Thread t = new Thread(serverThread);
	t.start();
    }
}

class ThreadedTicketServer implements Runnable
{

    String hostname = "127.0.0.1";
    String threadname = "X";
    String testcase;
    int portNumber;
    TicketClient sc;

    /**
     * Initializes a server with a portnumber
     * @param portnumber
     */
    public ThreadedTicketServer(int portnumber)
    {
	// TODO Auto-generated constructor stub
	this.portNumber = portnumber;
    }

    /**
     * Run method. Purchases tickets for each request it receives from the
     * clients.
     */
    public void run()
    {
	ServerSocket serverSocket;
	try
	{
	    serverSocket = new ServerSocket(this.portNumber);
	    // ServerSocket makes this server 'listen' for client requests on
	    // port 2222 on this machine

	    while (bestAvailableSeat() != null)
	    {
		Socket clientSocket = serverSocket.accept();
		// accept method waits until request arrives. returns a socket
		// for client communication.

		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Listen for ticket request
		while (in.readLine() == null)
		{
		    // Do nothing while we wait
		}
		// Reads client request off of the buffered reader
		
		// Ticket Processing
		
//		Seat thisSeat = bestAvailableSeat();
//		markAvailableSeatTaken(thisSeat);
		
		Seat thisSeat =  TicketServer.myTheater.markBestAvailableSeatTaken();
		
		// Transmit String back to client
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		out.println(thisSeat.toString()); // returns data
		out.close();
	    }
	    while(true)
	    {
		Socket clientSocket = serverSocket.accept();
		// accept method waits until request arrives. returns a socket
		// for client communication.
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		while (in.readLine() == null)
		{
		    // Do nothing while we wait
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		out.println("NSL"); // returns data
		out.close();
		
	    }
	    //serverSocket.close();
	    
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Finds the best available seats and reserves them
     * @return
     */
    Seat bestAvailableSeat()
    {
	Seat thisIsTheBS = TicketServer.myTheater.bestAvailableSeat();
	return thisIsTheBS;
    }

    /**
     * Marks the seat called as taken
     * @param thisSeat
     */
    void markAvailableSeatTaken(Seat thisSeat)
    {
	thisSeat.Taken();
    }
}
