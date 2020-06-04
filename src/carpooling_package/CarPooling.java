package carpooling_package;

import java.util.Scanner;

class route {
	private String startDest;
	private String EndDest;

	public route(String Start, String End) {
		this.startDest = Start;
		this.EndDest = End;
	}

	public void setStartDest(String startDest) {
		this.startDest = startDest;
	}

	public void setEndDest(String endDest) {
		EndDest = endDest;
	}

	public String getStartDest() {
		return startDest;
	}

	public String getEndDest() {
		return EndDest;
	}

}

class Car {
	final String Codenumber, driverName;
	final int capacity;
	int TripsPerDay;

	public Car(String Codenumber, int capicaty, String driverName, int capacity, int TripsPerDay) {
		this.Codenumber = Codenumber;
		this.capacity = capacity;
		this.driverName = driverName;
		this.TripsPerDay = TripsPerDay;
	}
}

class Ride {
	route carRoute;
	Car car;

}	

class Ticket {
	private double price;

	public Ticket(double price) {

	}

}

abstract class passenger {
	Ticket t;
	Ride ride;
	public passenger() {
		// TODO Auto-generated constructor stub
	}

}

interface iprice{
	
	
}

class nonSubscriber extends passenger implements iprice{
	
	
}

class subscriber extends passenger implements iprice{
	
	
}


public class CarPooling {

	public static void main(String[] args) {

		System.out.println("hello world");
		System.out.println("hello world");

	}

}
