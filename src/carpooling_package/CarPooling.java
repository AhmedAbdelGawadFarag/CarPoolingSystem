package carpooling_package;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * route that has a start Location and end Location
 * 
 * @author Beeka
 */
class Route {
	private String startLocation;
	private String endLocation;

	/**
	 * initialize route object with start location and end loaction
	 * 
	 * @param Start start location of the route
	 * @param End   end location of the route
	 */
	public Route(String Start, String End) {
		this.startLocation = Start;
		this.endLocation = End;
	}

	/**
	 * set start location of the route
	 * 
	 * @param startLocation start location of the route
	 */
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	/**
	 * set end location of the route
	 * 
	 * @param endLocation end location of the route
	 */
	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	/**
	 * @return start location of the route
	 */
	public String getStartLocation() {
		return this.startLocation;
	}

	/**
	 * @return end location of the route
	 */
	public String getEndLocation() {
		return this.endLocation;
	}

}

/**
 * the reason for this interface that i want the ticket class to take the
 * carcode but i dont want to send the whole car class i just want to send the
 * car code only not the driver name and all other stuff that ticket doesnt need
 * so this interface make this
 * 
 * @author Beeka
 *
 */
interface Icode {
	/**
	 * @return Car code number
	 */
	public String getCarCode();
}

/**
 * car that has codenumber , drivername ,capacity ,trips in each day ,
 * passengers
 * 
 * @author Beeka
 */
class Car implements Icode {
	private final String Codenumber;
	private final String driverName;
	private final int capacity;
	private int TripsPerDay;

	/**
	 * initialize car object with code number , drivername , capacity , trips in day
	 * 
	 * @param Codenumber  car code number
	 * @param driverName  driver name
	 * @param capacity    capacity of the car
	 * @param TripsPerDay number of trips in a day
	 */
	public Car(String Codenumber, String driverName, int capacity, int TripsPerDay) {
		this.Codenumber = Codenumber;
		this.capacity = capacity;
		this.driverName = driverName;
		this.TripsPerDay = TripsPerDay;
	}

	/**
	 * 
	 * @return return car code number
	 */
	@Override
	public String getCarCode() {
		return Codenumber;
	}

	/**
	 * 
	 * @return trips in a day
	 */

	public int getTripsPerDay() {
		return TripsPerDay;
	}

	/**
	 * 
	 * @return capacity of the car
	 */
	public int getCapacity() {
		return capacity;
	}

}

/**
 * Ticket that has a price and carCode
 * 
 * @author Beeka
 *
 */
class Ticket {
	private double price;
	private final Icode carCode;// interface refrence

	/**
	 * intalize ticket with price , car code
	 * 
	 * @param price   ticket price
	 * @param carCode car code
	 */
	public Ticket(double price, Icode carCode) {
		this.price = price;
		this.carCode = carCode;
	}

	/**
	 * 
	 * @return ticket price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @return car code
	 */
	public String CarCode() {
		return this.carCode.getCarCode();
	}

	/**
	 * set ticket price
	 * 
	 * @param price ticket price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}

/**
 * a ride is just what customers pay for to use carpooling system any trip must
 * have a rout and a car and available tickets that passengers can reserve it
 * and passengers for the trip
 * 
 * @author Beeka
 *
 */
class Ride {
	private Route route;
	private Car car;
	private ArrayList<Ticket> availTickets;// the availabe tickets for the ride
	private ArrayList<Passenger> passngers;// array list of passengers
	private int price;// price of ride not the ticket

	/**
	 * initialize a ride with route , car , price of the ride (note that price of
	 * the ride is different between ticket price as ticket price can be less than
	 * ride price due to subscription or it can equall ride price if passenger
	 * doesnt have subscription) , available tickets is just calculated from the car
	 * object capacity
	 * 
	 * @param route route of the ride
	 * @param car   car of the ride
	 * @param price price of the ride
	 */
	public Ride(Route route, Car car, int price) {
		this.route = route;
		this.car = car;
		this.price = price;
		availTickets = new ArrayList<Ticket>(car.getCapacity());
		passngers = new ArrayList<Passenger>();
	}

	public void addPassenger(Passenger p) {
		double ticketPrice = Discount.getDiscount(this, p);
		Ticket newTicket = new Ticket(ticketPrice, this.car);
		p.addTicket(newTicket);// add last ticket from the avail tickets

		passngers.add(p);

		availTickets.remove(availTickets.size() - 1);// remove ticket from avail tickets object
	}

	/**
	 * @return car object
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @return route object
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * @return the Avail places in this ride
	 */
	public int getAvailTickets() {
		return availTickets.size();
	}

	public int getPrice() {
		return price;
	}
}

/**
 * abstract class that represents passenger , each passenger can have one ticket
 * or multiple tickets
 * 
 * @author Beeka
 */
abstract class Passenger {

	protected ArrayList<Ticket> tickets;

	/**
	 * initialize passenger object with empty array of tickets
	 */
	public Passenger() {
		tickets = new ArrayList<Ticket>();
	}

	/**
	 * @return ticket discount for the classes that extends that abstract class
	 */
	abstract public double discount();

	/**
	 * add a ticket to this passenger
	 * 
	 * @param ticket ticket of the passenger
	 */
	protected void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

}

/**
 * interface resposible for determining ticket price
 * 
 * @author Beeka
 */

/**
 * class that is responsible for calculating the discount for passengers
 * depending on their types this class calculate the discount manually by just
 * taking an object form passenger abstract
 * 
 * @author Beeka
 */
abstract class Discount {
	// this is abstract because i dont want to make an object from this Discount
	// class

	/**
	 * function to getDiscount by taking object of the passenger to know its type
	 * automatically by calling discount in each passenger class if he subscribe or
	 * he didn't subscribe then takes the ticket
	 * 
	 * @param T         ticket object
	 * @param passenger object of the type of passenger
	 * @return discount of the ticket
	 */
	public static double getDiscount(Ride r, Passenger passenger) {
		return (r.getPrice() - passenger.discount() * r.getPrice());
	}
}

/**
 * interface that are resposible for detremining if you can subscribe or not
 * 
 * @author Beeka
 */
interface Isubscribable {
	int age = 35; // minimum age to subscibe;
	int trips = 10; // minmum trips you can subsribe to

	/**
	 * check if the passenger can subscribe or not
	 * 
	 * @param age   age of the passenger
	 * @param Trips number of trips of the passenger
	 * @return true if he can subscribe , false otherwise
	 */
	public boolean cansubscribe(int age, int Trips);
}

/**
 * non subscriber passenger class
 * 
 * @author Beeka
 */
class NonSubscriber extends Passenger implements Isubscribable {
	/**
	 * initialize non subscriber object with empty array of rides that can buy
	 */
	public NonSubscriber() {
		super();
	}

	/**
	 * discount for the non subscriber passenger class
	 */
	@Override
	public double discount() {
		// there is no discount for the nonsubscriber class so the discount will be 0
		return 0;
	}

	/**
	 * check if this passenger can subscribe or not
	 */
	@Override
	public boolean cansubscribe(int age, int Trips) {
		if (this.age >= age && this.trips >= Trips) {
			return true;
		}
		return false;
	}

}

/**
 * subscriber passenger class
 * 
 * @author Beeka
 */
class Subscriber extends Passenger {
	int reservedTrips;
	public final String name;
	public int age;

	/**
	 * initialize subscriber object that has name and age with empty array of rides
	 * that can buy
	 * 
	 * @param name name of the passenger
	 * @param age  age of the passenger
	 */
	public Subscriber(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * get ticket discount for the subscriber passenger
	 */
	@Override
	public double discount() {
		return ((double) 50.0) / 100;
	}

}

class hardcoded {
	public hardcoded() {

	}

}

public class CarPooling {

	public static void main(String[] args) {
		Route r = new Route("city", "ASd");
		Car c = new Car("12", "asd", 2, 10);

		Ride m = new Ride(r, c, 13);
		Passenger p = new Subscriber("ASd", 12);
		m.addPassenger(p);
		System.out.println(p.tickets.get(0).getPrice());
	}
}