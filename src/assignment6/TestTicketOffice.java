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

    /**
     * Simply Tests one server
     */
     @Test
     public void basicServerTest()
     {
     System.out.println("Start BasicServerTest");
     Theater myTheater = new Theater();
     int port = 16666;
     try
     {
     TicketServer.start(port, myTheater);
     } catch (Exception e)
     {
     fail();
     }
     String customerName = "Customer";
     for (int i = 0; i < 100; i++)
     {
     TicketClient client = new TicketClient("localhost", "c1", port);
     client.requestTicket(customerName.concat(Integer.toString(i)));
     }
     System.out.println("End BasicServerTest");
     System.out.println("******************************************");
     }
    
     /**
      * Tests two servers
      */
     @Test
     public void twoServerTest()
     {
     System.out.println("Start twoServerTest");
     Theater myTheater = new Theater();
     int port1 = 16795;
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
     client2.requestTicket(customerName.concat(Integer.toString(i + 1)));
     }
     System.out.println("End twoServerTest");
     System.out.println("******************************************");
     }
    
     /**
      * Tests three servers
      */
     @Test
     public void threeServerTest()
     {
     System.out.println("Start threeServerTest");
     Theater myTheater = new Theater();
     int port1 = 16796;
     int port2 = 17722;
     int port3 = 12345;
     try
     {
     TicketServer.start(port1, myTheater);
     TicketServer.start(port2, myTheater);
     TicketServer.start(port3, myTheater);
     } catch (Exception e)
     {
     fail();
     }
     String customerName = "Customer";
     for (int i = 0; i < 100; i = i + 3)
     {
     TicketClient client1 = new TicketClient("localhost", "c1", port1);
     client1.requestTicket(customerName.concat(Integer.toString(i)));
    
     TicketClient client2 = new TicketClient("localhost", "c2", port2);
     client2.requestTicket(customerName.concat(Integer.toString(i + 1)));
    
     TicketClient client3 = new TicketClient("localhost", "c2", port3);
     client3.requestTicket(customerName.concat(Integer.toString(i + 2)));
     }
     System.out.println("End threeServerTest");
     System.out.println("******************************************");
     }
    
     /**
      * Tests the ability of the theater object to correctly know
      * if the theater is sold out
      */
     @Test
     public void sellOutTest()
     {
     System.out.println("Start sellOutTest");
     Theater myTheater = new Theater();

     int port = 16798;
     try
     {
     TicketServer.start(port, myTheater);
     } catch (Exception e)
     {
     fail();
     }
     String customerName = "Customer";
     for (int i = 0; i < 500; i++)
     {
     TicketClient c1 = new TicketClient("localhost", "c1", port);
     c1.requestTicket(customerName.concat(Integer.toString(i)));
     }
     System.out.println("End sellOutTest");
     System.out.println("******************************************");
     }

    /**
     * Shows the ability of two clients to talk to the same server
     */
    @Test
    public void testServerCachedHardInstance()
    {
	System.out.println("Start testServerCachedHardInstance");
	Theater myTheater = new Theater();
	try
	{
	    TicketServer.start(16790, myTheater);
	} catch (Exception e)
	{
	    fail();
	}

	TicketClient client1 = new TicketClient("localhost", "c1", 16790);
	TicketClient client2 = new TicketClient("localhost", "c2", 16790);
	client1.requestTicket("Customer Bob:");
	client2.requestTicket("Customer Bill");
	System.out.println("End testServerCachedHardInstance");
	System.out.println("******************************************");
    }

    /**
     * This tests embedded calls to requestTicket We don't know what order the
     * customers will be processed but we need to ensure no two copies of the
     * same ticket will be sold
     */
    @Test
    public void threeConcurrentServerTest()
    {
	System.out.println("Start twoConcurrentServerTest");
	int port1 = 16793;
	int port2 = 17727;
	int port3 = 12349;
	Theater myTheater = new Theater();

	try
	{
	    TicketServer.start(port1, myTheater);
	    TicketServer.start(port2, myTheater);
	    TicketServer.start(port3, myTheater);
	} catch (Exception e)
	{
	    fail();
	}
	final TicketClient c1 = new TicketClient("localhost", "c1", port1);
	final TicketClient c2 = new TicketClient("localhost", "c2", port2);
	final TicketClient c3 = new TicketClient("localhost", "c3", port3);
	Thread t1 = new Thread()
	{
	    public void run()
	    {
		c1.requestTicket("Customer Bob");
	    }
	};
	Thread t2 = new Thread()
	{
	    public void run()
	    {
		c2.requestTicket("Customer Sally");
	    }
	};
	Thread t3 = new Thread()
	{
	    public void run()
	    {
		c3.requestTicket("Customer Bill");
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
	System.out.println("End twoConcurrentServerTest");
	System.out.println("******************************************");
    }

    /**
     * Per niraj's suggestion, this test requests tickets using multiple servers
     * with each one connected to multiple clients
     */
    @Test
    public void multServMultClientTest()
    {
	System.out.println("Start multServMultClientTest");
	int port1 = 16853;
	int port2 = 17888;
	int port3 = 19808;
	Theater myTheater = new Theater();

	try
	{
	    TicketServer.start(port1, myTheater);
	    TicketServer.start(port2, myTheater);
	    TicketServer.start(port3, myTheater);
	} catch (Exception e)
	{
	    fail();
	}

	for (int i = 0; i < 300; i = i + 9)
	{
	    final TicketClient cP11 = new TicketClient("localhost", "cP11", port1);
	    final TicketClient cP12 = new TicketClient("localhost", "cP12", port1);
	    final TicketClient cP13 = new TicketClient("localhost", "cP13", port1);

	    final TicketClient cP21 = new TicketClient("localhost", "cP21", port2);
	    final TicketClient cP22 = new TicketClient("localhost", "cP22", port2);
	    final TicketClient cP23 = new TicketClient("localhost", "cP23", port2);

	    final TicketClient cP31 = new TicketClient("localhost", "cP31", port3);
	    final TicketClient cP32 = new TicketClient("localhost", "cP32", port3);
	    final TicketClient cP33 = new TicketClient("localhost", "cP33", port3);

	    cP11.requestTicket("Customer" + Integer.toString(i));
	    cP12.requestTicket("Customer" + Integer.toString(i + 1));
	    cP13.requestTicket("Customer" + Integer.toString(i + 2));

	    cP21.requestTicket("Customer" + Integer.toString(i + 3));
	    cP22.requestTicket("Customer" + Integer.toString(i + 4));
	    cP23.requestTicket("Customer" + Integer.toString(i + 5));

	    cP31.requestTicket("Customer" + Integer.toString(i + 6));
	    cP32.requestTicket("Customer" + Integer.toString(i + 7));
	    cP33.requestTicket("Customer" + Integer.toString(i + 8));
	}
	System.out.println("End multServMultClientTest");
	System.out.println("******************************************");
    }
}
