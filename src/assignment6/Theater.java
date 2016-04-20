package assignment6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Theater
{
    public int rows = 26;
    public int seatsPerRow = 50;
    private Seat[] tSeats = new Seat[rows * seatsPerRow];
    private Lock myLock;
    int iter = 0;

    /**
     * Theater Constructor
     */
    public Theater()
    {
	String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	for (int i = 0; i < 26; i++)
	{
	    String rowString = s.substring(i, i + 1);
	    for (int x = 1; x < 51; x++)
	    {
		tSeats[(i * 50) + x - 1] = new Seat(rowString, x);
	    }
	}
	System.out.println("Theater Initialized! Let's get this show on the road!");

	myLock = new ReentrantLock();
    }

    /**
     * Finds best available seat remaining
     * @return
     */
    public Seat bestAvailableSeat()
    {
	// This thing needs to have a lock
	// Maybe needs a queue?

	try
	{
	    for (int i = 0; i < tSeats.length; i++)
	    {
		if (tSeats[i].available())
		{
		    return tSeats[i];
		}
	    }
	} finally
	{

	}
	return null;
    }

    /**
     * Marks hte best available seat remaining as taken (sells it)
     * @return
     */
    public Seat markBestAvailableSeatTaken()
    {
	myLock.lock();
	Seat takenSeat = this.bestAvailableSeat();
	takenSeat.Taken();
	myLock.unlock();
	return takenSeat;
    }
}
