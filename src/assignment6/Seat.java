package assignment6;

public class Seat
{
    String row;
    int seatNumber;
    SeatStatus status;
    
    Seat(String rowName, int seatNum)
    {
	this.row = rowName;
	this.seatNumber = seatNum;
	this.status = SeatStatus.NotTaken;
    }
    
}

enum SeatStatus
{
    Taken, NotTaken
}

