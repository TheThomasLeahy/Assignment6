/*
 *Michael Spearing
 *Thomas Leahy tpl335 Niraj Th 2:00-3:30
 */

package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable
{
    String hostname = "127.0.0.1";
    String threadname = "X";
    TicketClient sc;
    String customerName;
    int portName;

    public ThreadedTicketClient(TicketClient sc, String hostname, String threadname, int portname)
    {
	this.sc = sc;
	this.hostname = hostname;
	this.threadname = threadname;
	this.portName = portname;
    }

    public void run()
    {
	System.out.flush();
	try
	{
	    Socket echoSocket = new Socket(hostname, portName);
	    //Need to change this

	    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
	    // Request medium/stream to server
	    out.println(this.customerName);

	    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
	    // BufferedReader stdIn = new BufferedReader(new
	    // InputStreamReader(System.in));
	    while (!in.ready())
	    {
		// Do nothing while we wait
	    }
	    String serverString = in.readLine();
	    // Server sends us our ticket here

	    // COME BACK AND FIX FORMATTING HERE
	    System.out.println("Server " + this.portName + "\t sold " + this.customerName + "\t" + serverString);

	    // What is stdIn?

	    echoSocket.close();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

}

public class TicketClient
{
    ThreadedTicketClient tc;
    String result = "dummy";
    String hostName = "";
    String threadName = "";
    int portName = 2222;

    TicketClient(String hostname, String threadname, int portname)
    {
	tc = new ThreadedTicketClient(this, hostname, threadname, portname);
	hostName = hostname;
	threadName = threadname;
	portName = portname;
    }
/*
    TicketClient(String name)
    {
	this("localhost", name, );
    }

    TicketClient()
    {
	this("localhost", "unnamed client", 2222);
    }
*/
    void requestTicket(String customerName)
    {
	tc.customerName = customerName;
	tc.run(); // Sells ticket. Prints ticket to console.
    }

    void printTicketSeat(String soldSeat)
    {
	System.out.println(hostName + "," + threadName + " got one ticket");

    }

    void sleep()
    {
	try
	{
	    Thread.sleep(100);
	} catch (InterruptedException e)
	{
	    e.printStackTrace();
	}
    }

}
