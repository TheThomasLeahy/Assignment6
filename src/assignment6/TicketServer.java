/*
 *Michael Spearing
 *Thomas Leahy tpl335 Niraj Th 2:00-3:30
 */

package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.*;

public class TicketServer
{
    static int PORT = 2222;
    // EE422C: no matter how many concurrent requests you get,
    // do not have more than three servers running concurrently
    final static int MAXPARALLELTHREADS = 3;

    // Data Structure for the theater
    public static Theater myTheater = null;
    public static Lock myLock;

    public static void start(int portNumber, Theater theater, Lock thisLock) throws IOException
    {
	myTheater = theater;
	myLock = thisLock;
	PORT = portNumber;
	Runnable serverThread = new ThreadedTicketServer();
	Thread t = new Thread(serverThread);
	t.start();
    }
}

class ThreadedTicketServer implements Runnable
{

    String hostname = "127.0.0.1";
    String threadname = "X";
    String testcase;
    TicketClient sc;

    public void run()
    {
	System.out.println("TTS - RUN");
	ServerSocket serverSocket;
	try
	{
	    serverSocket = new ServerSocket(TicketServer.PORT);
	    // ServerSocket makes this server 'listen' for client requests on
	    // port 2222 on this machine
	    while (bestAvailableSeat() != null)
	    {
		Socket clientSocket = serverSocket.accept();
		// accept method waits until request arrives. returns a socket
		// for client communication.
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		// Socket connection to sent a string message
		
		TicketServer.myLock.lock();
		Seat thisSeat = bestAvailableSeat();
		markAvailableSeatTaken(thisSeat);
		out.println(thisSeat.toString()); // returns data
		out.close();
		TicketServer.myLock.unlock();

		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(clientSocket.getInputStream()));
		// IDK what this does
	    }

	} catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    Seat bestAvailableSeat()
    {
	Seat thisIsTheBS = TicketServer.myTheater.bestAvailableSeat();
	return thisIsTheBS;
    }

    void markAvailableSeatTaken(Seat thisSeat)
    {
	thisSeat.Taken();
    }
}
