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
	int index = 0;
	for (int i = 0; i < 26; i++)
	{
	    String rowString = s.substring(i, i + 1);
	    int x;
	    for (x = 108; x <= 121; x++)
	    {
		tSeats[index] = new Seat(rowString, x);
		index++;
	    }
	    for (x = 101; x <= 107; x++)
	    {
		tSeats[index] = new Seat(rowString, x);
		index++;
	    }
	    for (x = 122; x <= 128; x++)
	    {
		tSeats[index] = new Seat(rowString, x);
		index++;
	    }
	}
	myLock = new ReentrantLock();
    }

    /**
     * Finds best available seat remaining
     * 
     * @return
     */
    public Seat bestAvailableSeat()
    {
	// This thing needs to have a lock
	// Maybe needs a queue?

	for (int i = 0; i < tSeats.length; i++)
	{
	    if (tSeats[i].available())
	    {
		return tSeats[i];
	    }
	}

	return null;
    }

    /**
     * Marks hte best available seat remaining as taken (sells it)
     * 
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
