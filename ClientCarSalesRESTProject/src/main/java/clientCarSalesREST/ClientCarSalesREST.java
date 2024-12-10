package clientCarSalesREST;

import java.util.Scanner;

import org.glassfish.jersey.client.ClientConfig;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class ClientCarSalesREST {

	public static void main(String[] args) {
		// URL of REST service resource 
		String url = "http://localhost:8080/WebCarSalesRESTProject/rest/WebCarSales";
		
		// Instantiate an object from ClientConfig 
		ClientConfig config = new ClientConfig();
		
		// Create a new Client object with config configuration using static class ClientBuilder
		Client client = ClientBuilder.newClient(config);
		
		// Use the Client object to target the URL of the REST service
		WebTarget target = client.target(url);

		// The response will be a string no matter retrieved response is JSON, XML or HTML.
		// The response is retrieved using target and by specifying the method and header parameter of the REST resource.
		String response = target.request() // request the target
							.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
							.get(String.class); // specify the GET method of the REST resource and the whole thing will be represented by a string
		
		// Display the Car objects list in JSON 
		System.out.println("Car JSON Output:");
		System.out.println(response);
		
		// Use Scanner to let the user enter the input
		Scanner input = new Scanner(System.in);
		System.out.println("\n--- Search Car by ID ---");
		System.out.print("\nPlease Enter Car ID: "); // get Car ID from user
		String carID = input.nextLine();
		
		// Search car by ID
		target = client.target(url).path("/searchCarId/" + carID);

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.get(String.class); // specify the GET method of the REST resource and the whole thing will be represented by a string
		
		// Display the response in JSON
		System.out.println("\nSearch Car by ID (" + carID + "): ");
		System.out.println(response);
		
		// Search Car by make
		System.out.println("\n--- Search Car by Make ---");
		System.out.print("\nPlease Enter Car Make: "); // get Car make from user
		String carMake = input.nextLine();
		
		target = client.target(url).path("/searchCarMake/" + carMake);

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.get(String.class); // specify the GET method of the REST resource and the whole thing will be represented by a string
		
		// Display the response in JSON
		System.out.println("\nSearch Car by Make (" + carMake + "): ");
		System.out.println(response);

		// Search Car by model
		System.out.println("\n--- Search Car by Model ---");
		System.out.print("\nPlease Enter Car Model: "); // get Car model from user
		String carModel = input.nextLine();
		
		target = client.target(url).path("/searchCarModel/" + carModel);

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.get(String.class); // specify the GET method of the REST resource and the whole thing will be represented by a string
		
		// Display the response in JSON
		System.out.println("\nSearch Car by Make (" + carModel + "): ");
		System.out.println(response);
		
		// Search Car by price range
		System.out.println("\n--- Search Car by Price Range ---");
		System.out.print("\nPlease Enter Minimum Price: "); // get lower bound of price from user
		double price1 = input.nextDouble();
		System.out.print("\nPlease Enter Maximum Price: "); // get higher bound of price from user
		double price2 = input.nextDouble();
		
		target = client.target(url).path("/searchCarPrice/" + price1 + "/" + price2);

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.get(String.class); // specify the GET method of the REST resource and the whole thing will be represented by a string
		
		// Display the response in JSON
		System.out.println("\nSearch Car by Make (between $" + price1 + " and $" + price2 + "): ");
		System.out.println(response);	
		
		// Add a Car 
		System.out.println("\n--- Add a Car ---");
		System.out.print("\nPlease Enter Car ID: "); 
		String car_id = input.next();
		System.out.print("\nPlease Enter Car Make: "); 
		String car_make = input.next();
		System.out.print("\nPlease Enter Car Model: "); 
		String car_model = input.next();
		System.out.print("\nPlease Enter Car Year: "); 
		int car_year = Integer.parseInt(input.next());
		System.out.print("\nPlease Enter Car Description (New/Used): "); 
		String car_desc = input.next();
		System.out.print("\nPlease Enter Car Price: "); 
		double car_price = Double.parseDouble(input.next());
		System.out.print("\nPlease Enter Car Image URL: "); 
		String car_image_url = input.next();
		
		// Consume the newline character
		input.nextLine();

		// HTTP POST
		// Target the URL and pass query paramaters to the REST resource path
		target = client.target(url).path("addJSONQueryCar")
				.queryParam("car_id", car_id)
				.queryParam("car_make", car_make)
				.queryParam("car_model", car_model)
				.queryParam("car_year", car_year)
				.queryParam("car_desc", car_desc)
				.queryParam("car_price", car_price)
				.queryParam("car_image_url", car_image_url);
		
		response = target.request()  // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.post(Entity.text(""))  // use the same @PUT method as updateCourse method in the resource
					.readEntity(String.class);
		
		System.out.println(response);

		
		// HTTP DELETE
		System.out.println("\n--- Delete a Car ---");
		System.out.print("\nPlease Enter Car ID for removal: ");
		String car_id_del = input.nextLine();
		
		// Target the URL and pass query paramater to the REST resource path
		target = client.target(url).path("removeCar")
				.queryParam("car_id", car_id_del); 

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.delete() // use the same @DELETE method as removeCourse method in the resource
					.readEntity(String.class);
		
		System.out.println(response);
		
		// HTTP PUT
		System.out.println("\n--- Update a Car ---");
		System.out.print("\nPlease Enter Car ID for update: ");
		String car_id_up = input.nextLine();
		System.out.print("\nPlease Enter the new price for update: ");
		String car_price_up = input.nextLine();
		
		// Target the URL and pass query paramater to the REST resource path
		target = client.target(url).path("updateCar")
				.queryParam("car_id", car_id_up)
				.queryParam("car_price", car_price_up); 

		response = target.request() // request the target
					.accept(MediaType.APPLICATION_JSON)  // specify the Accept parameters with appropriate MediaType
					.put(Entity.text(""))  // use the same @DELETE method as removeCourse method in the resource
					.readEntity(String.class);
		
		System.out.println(response);
	}
}
