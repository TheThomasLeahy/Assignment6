package assignment6;

public class Seat
{
    String row;
    int seatNumber;
    private SeatStatus status;
    
    Seat(String rowName, int seatNum)
    {
	this.row = rowName;
	this.seatNumber = seatNum;
	this.status = SeatStatus.NotTaken;
    }
    
    public String toString()
    {
	return( this.row.concat(Integer.toString(this.seatNumber)));
    }

    public void Taken()
    {
	this.status = SeatStatus.Taken;
    }
    
}

enum SeatStatus
{
    Taken, NotTaken
}

