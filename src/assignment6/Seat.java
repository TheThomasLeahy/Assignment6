package assignment6;

public class Seat
{
    String row;
    int seatNumber;
    private SeatStatus status;

    /**
     * Seat constructor
     * @param rowName
     * @param seatNum
     */
    Seat(String rowName, int seatNum)
    {
	this.row = rowName;
	this.seatNumber = seatNum;
	this.status = SeatStatus.NotTaken;
    }

    /**
     * toString() for a object of type seat
     */
    public String toString()
    {
	return (this.row.concat(Integer.toString(this.seatNumber)));
    }

    /**
     * Marks a seat as taken
     */
    public void Taken()
    {
	this.status = SeatStatus.Taken;
    }

    /**
     * Returns if the seat is available
     */
    public boolean available()
    {
	if (this.status == SeatStatus.NotTaken)
	{
	    return true;
	} else
	{
	    return false;
	}
    }

}

/**
 * Enum for SeatStatus - Taken or not?!
 * @author thoma_000
 *
 */
enum SeatStatus {
    Taken, NotTaken
}
