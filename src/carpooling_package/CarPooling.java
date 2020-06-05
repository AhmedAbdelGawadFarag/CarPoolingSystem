package carpooling_package;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * route that has a start Location and end Location
 * 
 * @author Beeka
 *
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
	 * 
	 * @return start location of the route
	 */
	public String getStartLocation() {
		return this.startLocation;
	}

	/**
	 * 
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
	 * 
	 * @return Car code number
	 */
	public String getCarCode();
}

/**
 * car that has codenumber , drivername ,capacity ,trips in each day ,
 * passengers
 * 
 * @author Beeka
 *
 */
class Car implements Icode {
	private final String Codenumber;
	private final String driverName;
	private final int capacity;
	private int TripsPerDay;
	private ArrayList<Passenger> passengers;

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
		passengers = new ArrayList<Passenger>();
	}

	/**
	 * initialize car object with code number , drivername , capacity , trips in day
	 * 
	 * @param Codenumber  Codenumber car code number
	 * @param driverName  driver name
	 * @param capacity    capacity of the car
	 * @param TripsPerDay number of trips in a day
	 * @param passengers  passengers that will be in the car
	 */
	public Car(String Codenumber, String driverName, int capacity, int TripsPerDay, ArrayList<Passenger> passengers) {
		this(Codenumber, driverName, capacity, TripsPerDay);
		this.passengers = passengers;

	}

	/**
	 * 
	 * @return true if there is a place in the car to be reversed , false otherwise
	 */
	public boolean canReverse() {
		return (this.passengers.size() <= capacity);
	}

	/**
	 * add a passenger to the car
	 * 
	 * @param passenger passenger object
	 */

	public void addPassenger(Passenger passenger) {
		this.passengers.add(passenger);
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
	 * @return passengers of the car
	 */

	public ArrayList<Passenger> getPassengers() {
		return passengers;
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
 * ride class is the class that holds the data for each passenger as each
 * passenger take a trip must have a rout and a car and ticket for the trip
 * 
 * @author Beeka
 *
 */
class Ride {
	private Route route;
	private Car car;
	private Ticket ticket;

	/**
	 * initialize a ride with route , car , ticket objects
	 * 
	 * @param route  route of the ride
	 * @param car    car of the ride
	 * @param ticket ticket of the ride
	 */
	public Ride(Route route, Car car, Ticket ticket) {
		this.route = route;
		this.car = car;
		this.ticket = ticket;
	}

	/**
	 * set car of the ride
	 * 
	 * @param car car of the ride
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * set ride route
	 * 
	 * @param route route of the ride
	 */
	public void setCarRoute(Route route) {
		this.route = route;
	}

	/**
	 * set ride ticket
	 * 
	 * @param ticket ticket of the ride
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	/**
	 * 
	 * @return car object
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * 
	 * @return route object
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * 
	 * @return ticket object
	 */
	public Ticket getTicket() {
		return ticket;
	}

}

/**
 * abstract class that represents passenger , each passenger must have one ride
 * or multiple rides
 * 
 * @author Beeka
 *
 */
abstract class Passenger {

	protected ArrayList<Ride> rides;

	/**
	 * initialize passenger object with empty array of rides that can buy
	 * 
	 * 
	 */
	public Passenger() {
		rides = new ArrayList<Ride>();
	}

	/**
	 * add a ride to this passenger
	 * 
	 * @param ride ride of the passenger
	 */
	protected void addride(Ride ride) {
		rides.add(ride);
	}

}

/**
 * interface resposible for determining ticket price
 * 
 * @author Beeka
 *
 */
interface Iprice {
	/**
	 * @return ticket discount for the classes that implement that interface
	 */
	public double discount();
}

/**
 * class that is responsible for calculating the discount for passengers
 * depending on their types this class calculate the discount manually by just
 * taking an object form Iprice interface
 * 
 * @author Beeka
 *
 */
class Discount {

	private Discount() {

	}

	public double getDiscount(Ticket T, Iprice passenger) {
		return (T.getPrice() - passenger.discount() * T.getPrice());
	}

}

/**
 * interface that are resposible for detremining if you can subscribe or not
 * 
 * @author Beeka
 *
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
 *
 */
class NonSubscriber extends Passenger implements Iprice, Isubscribable {
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
class Subscriber extends Passenger implements Iprice {
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

public class CarPooling {

	public static void main(String[] args) {

	}

}