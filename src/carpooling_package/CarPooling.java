package carpooling_package;

import java.util.ArrayList;
import java.util.Scanner;

class Route {
	private String startLocation;
	private String endLocation;

	public Route(String Start, String End) {
		this.startLocation = Start;
		this.endLocation = End;
	}

	public void setStartDest(String startLocation) {
		this.startLocation = startLocation;
	}

	public void setEndDest(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getStartDest() {
		return this.startLocation;
	}

	public String getEndDest() {
		return this.endLocation;
	}

}

interface Icode {
	public String getCarCode();
}

class Car implements Icode {
	final String Codenumber, driverName;
	final int capacity;
	int TripsPerDay;
	ArrayList<Passenger> passengers;

	public Car(String Codenumber, int capicaty, String driverName, int capacity, int TripsPerDay) {
		this.Codenumber = Codenumber;
		this.capacity = capacity;
		this.driverName = driverName;
		this.TripsPerDay = TripsPerDay;
		passengers = new ArrayList<Passenger>();
	}

	public boolean canReverse() {
		return (this.passengers.size() <= capacity);
	}

	public void addPassenger(Passenger passenger) {
		this.passengers.add(passenger);
	}
	
	@Override
	public String getCarCode() {
		return Codenumber;
	}

}

class Ticket {
	private double price;
	private Icode carCode;// interface refrence

	public Ticket(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

}

class Ride {
	private Route route;
	private Car car;
	private Ticket ticket;

	public Ride(Route route, Car car, Ticket ticket) {
		this.route = route;
		this.car = car;
		this.ticket = ticket;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setCarRoute(Route route) {
		this.route = route;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Car getCar() {
		return car;
	}

	public Route getRoute() {
		return route;
	}

	public Ticket getTicket() {
		return ticket;
	}

}

abstract class Passenger {

	protected ArrayList<Ride> rides;

	public Passenger() {
		rides = new ArrayList<Ride>();
	}

	protected void addride(Ride ride) {
		rides.add(ride);
	}

}

interface Iprice {

	double TicketPrice(Ride ride);

	double TicketPrice(Ticket ticket);

}

interface Isubscribable {
	int age = 35; // minimum age to subscibe;
	int trips = 10; // minmum trips you can subsribe to

	public boolean cansubscribe(int age, int Trips);
}

class NonSubscriber extends Passenger implements Iprice, Isubscribable {

	public NonSubscriber() {
		super();
	}

	@Override
	public double TicketPrice(Ticket ticket) {
		return ticket.getPrice();
	}

	@Override
	public double TicketPrice(Ride ride) {
		Ticket t = ride.getTicket();
		return this.TicketPrice(t);
	}

	@Override
	public boolean cansubscribe(int age, int Trips) {
		if (this.age >= age && this.trips >= Trips) {
			return true;
		}
		return false;
	}

}

class Subscriber extends Passenger implements Iprice {
	int reservedTrips;
	public final String name;
	public int age;

	public Subscriber(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public double TicketPrice(Ticket ticket) {
		return ((ticket.getPrice() * 50.0) / 100);
	}

	@Override
	public double TicketPrice(Ride ride) {
		Ticket t = ride.getTicket();
		return this.TicketPrice(t);
	}

}

public class CarPooling {

	public static void main(String[] args) {

		System.out.println("hello world");
		System.out.println("hello world");

	}

}
