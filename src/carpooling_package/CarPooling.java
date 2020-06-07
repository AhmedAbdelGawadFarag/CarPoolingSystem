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
 * and passengers and a price of course ( the ride price is different between
 * ticket price as ticket price can be less than ride price due to subscription
 * or it can equall ride price if passenger doesnt have subscription)
 * 
 * @author Beeka
 *
 */
class Ride {
	private Route route;
	private Car car;
	private int availTickets;// the availabe tickets for the ride
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

		availTickets = car.getCapacity();

		passngers = new ArrayList<Passenger>();
	}

	public void addPassenger(Passenger p) {
		double ticketPrice = Discount.getDiscount(this, p);
		Ticket newTicket = new Ticket(ticketPrice, this.car);
		p.addTicket(newTicket);// add last ticket from the avail tickets

		passngers.add(p);

		availTickets--;// remove ticket from avail tickets object
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
		return availTickets;
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
	int age = 20; // minimum age to subscibe;
	int trips = 5; // minmum trips you can subsribe to

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

public class CarPooling {
	static ArrayList<Passenger> passengers;
	static ArrayList<Route> routes;
	static ArrayList<Car> cars;
	static ArrayList<Ride> rides;

	public static void getallroute(ArrayList<Route> routes) {
		System.out.println("choose the route you want to reverse a car in it ");
		for (int i = 0; i < 5; i++) {
			System.out.printf("%d: start location: %s       end location: %s \n", i + 1,
					routes.get(i).getStartLocation(), routes.get(i).getEndLocation());

		}

	}

	public static void searchForroute(Route r) {
		for (int i = 0; i < rides.size(); i++) {
			System.out.print(rides.get(i).getRoute() == r);
		}
	}

	public static void main(String[] args) {

		passengers = new ArrayList<Passenger>();
		passengers.add(new Subscriber("ahmed", 23));
		passengers.add(new Subscriber("Mohamed", 26));
		passengers.add(new Subscriber("isaac", 14));

		passengers.add(new NonSubscriber());
		passengers.add(new NonSubscriber());

		routes = new ArrayList<Route>();
		routes.add(new Route("nasr city", "shoubra"));
		routes.add(new Route("abbasia", "october city"));
		routes.add(new Route("alex", "mansoura"));
		routes.add(new Route("shoubra", "nasr city"));
		routes.add(new Route("october city", "abbasia"));

		cars = new ArrayList<Car>();
		cars.add(new Car("2020198465", "abdelwahab", 4, 2));
		cars.add(new Car("2014356879", "mahmoud", 5, 2));
		cars.add(new Car("2013789456", "abanoub", 4, 2));
		cars.add(new Car("2010354987", "ibrahim", 5, 2));

		rides = new ArrayList<Ride>();
		rides.add(new Ride(routes.get(0), cars.get(0), 125));
		rides.add(new Ride(routes.get(1), cars.get(0), 75));
		rides.add(new Ride(routes.get(0), cars.get(1), 100));
		rides.add(new Ride(routes.get(1), cars.get(3), 110));
		rides.add(new Ride(routes.get(3), cars.get(2), 60));

		getallroute(routes);

		Scanner input = new Scanner(System.in);

		System.out.print("Enter route number : ");
		String routeNumber = input.next();

		searchForroute(routes.get(Integer.parseInt(routeNumber)));

	}
}