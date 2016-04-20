/*
 *Michael Spearing
 *Thomas Leahy tpl335 Niraj Th 2:00-3:30
 */

package assignment6;

import static org.junit.Assert.*;

import org.junit.Test;
//import static org.junit.Assert.fail;

public class TestTicketOffice
{

    public static int score = 0;

    /*
    @Test
    public void basicServerTest()
    {
	Theater myTheater = new Theater();
	try
	{
	    TicketServer.start(16790, myTheater);
	} catch (Exception e)
	{
	    fail();
	}
	String customerName = "Customer";
	for (int i = 0; i < 100; i ++)
	{
	    TicketClient client = new TicketClient();
	    client.requestTicket(customerName.concat(Integer.toString(i)));
	}

    }
*/
    
    @Test
    public void testServerCachedHardInstance()
    {
	Theater myTheater = new Theater();
	int port1 = 16790;
	int port2 = 17721;
	try
	{
	    TicketServer.start(port1, myTheater);
	    TicketServer.start(port2, myTheater);
	} catch (Exception e)
	{
	    fail();
	}
	String customerName = "Customer";
	for (int i = 0; i < 100; i = i + 2)
	{
	    TicketClient client1 = new TicketClient("localhost", "c1", port1);
	    client1.requestTicket(customerName.concat(Integer.toString(i)));
	    
	    TicketClient client2 = new TicketClient("localhost", "c2", port2);
	    client2.requestTicket(customerName.concat(Integer.toString(i+1)));
	}
    }

    /*
    @Test
    public void twoNonConcurrentServerTest()
    {
	Theater myTheater = new Theater();
	try
	{
	    TicketServer.start(16791, myTheater);
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
*/
}
