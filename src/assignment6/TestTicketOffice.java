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
	System.out.println("Start BasicServerTest");
	Theater myTheater = new Theater();
	int port = 16790;
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
	for (int i = 0; i < 1305; i++)
	{
	    TicketClient c1 = new TicketClient("localhost", "c1", port);
	    c1.requestTicket(customerName.concat(Integer.toString(i)));
	}
	System.out.println("End sellOutTest");
	System.out.println("******************************************");
    }

    /*
     * @Test public void twoConcurrentServerTest() { Theater myTheater = new
     * Theater(); try { TicketServer.start(16792, myTheater, myLock); } catch
     * (Exception e) { fail(); } final TicketClient c1 = new
     * TicketClient("conc1"); final TicketClient c2 = new TicketClient("conc2");
     * final TicketClient c3 = new TicketClient("conc3"); Thread t1 = new
     * Thread() { public void run() { c1.requestTicket(); } }; Thread t2 = new
     * Thread() { public void run() { c2.requestTicket(); } }; Thread t3 = new
     * Thread() { public void run() { c3.requestTicket(); } }; t1.start();
     * t2.start(); t3.start(); try { t1.join(); t2.join(); t3.join(); } catch
     * (Exception e) { e.printStackTrace(); }
     * 
     * }
     */
}
