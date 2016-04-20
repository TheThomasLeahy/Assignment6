/*
 *Michael Spearing
 *Thomas Leahy tpl335 Niraj Th 2:00-3:30
 */

package assignment6;

import static org.junit.Assert.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
//import static org.junit.Assert.fail;

public class TestTicketOffice
{

    public static int score = 0;

    @Test
    public void basicServerTest()
    {
	Theater myTheater = new Theater();
	Lock myLock = new ReentrantLock();
	try
	{
	    TicketServer.start(16789, myTheater, myLock);
	} catch (Exception e)
	{
	    fail();
	}
	
	TicketClient client = new TicketClient();
	for(int i = 0; i < 100; i ++)
	{
	client.requestTicket();
	}
	client = null;
    }

    @Test
    public void testServerCachedHardInstance()
    {
	Theater myTheater = new Theater();
	Lock myLock = new ReentrantLock();
	try
	{
	    TicketServer.start(16790, myTheater, myLock);

	} catch (Exception e)
	{
	    fail();

	}
	TicketClient client1 = new TicketClient("localhost", "c1");
	TicketClient client2 = new TicketClient("localhost", "c2");
	client1.requestTicket();
	client2.requestTicket();
    }

    @Test
    public void twoNonConcurrentServerTest()
    {
	Theater myTheater = new Theater();
	Lock myLock = new ReentrantLock();
	try
	{
	    TicketServer.start(16791, myTheater, myLock);
	} catch (Exception e)
	{
	    fail();
	}
	TicketClient c1 = new TicketClient("nonconc1");
	TicketClient c2 = new TicketClient("nonconc2");
	TicketClient c3 = new TicketClient("nonconc3");
	c1.requestTicket();
	c2.requestTicket();
	c3.requestTicket();
    }

    @Test
    public void twoConcurrentServerTest()
    {
	Theater myTheater = new Theater();
	Lock myLock = new ReentrantLock();
	try
	{
	    TicketServer.start(16792, myTheater, myLock);
	} catch (Exception e)
	{
	    fail();
	}
	final TicketClient c1 = new TicketClient("conc1");
	final TicketClient c2 = new TicketClient("conc2");
	final TicketClient c3 = new TicketClient("conc3");
	Thread t1 = new Thread()
	{
	    public void run()
	    {
		c1.requestTicket();
	    }
	};
	Thread t2 = new Thread()
	{
	    public void run()
	    {
		c2.requestTicket();
	    }
	};
	Thread t3 = new Thread()
	{
	    public void run()
	    {
		c3.requestTicket();
	    }
	};
	t1.start();
	t2.start();
	t3.start();
	try
	{
	    t1.join();
	    t2.join();
	    t3.join();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}

    }
}
