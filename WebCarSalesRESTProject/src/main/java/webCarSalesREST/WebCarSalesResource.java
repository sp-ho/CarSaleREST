package webCarSalesREST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/WebCarSales") // URL mapping
public class WebCarSalesResource {

	  private static HashMap<String, Car> carHashMap = new HashMap<>(); // Store car information

	  // Set it as static so that the methods can access to the HashMap 
	    static {
			// Array of Car data
		    Car[] carData = {
		        new Car("F1", "Ford", "Mustang", 2022, "New", 50000.0, "https://content.homenetiol.com/2002954/2203049/0x0/2bb4283be6284cf9b82922b67d110df9.jpg"),
		        new Car("H1", "Honda", "CR-V", 2023, "New", 30000.0, "https://content.homenetiol.com/2002954/2203049/0x0/fdfb923c12b94544868eb6c050a964f3.jpg"),
		        new Car("HY1", "Hyundai", "Ioniq", 2019, "Used", 36000.0, "https://content.homenetiol.com/2002954/2203049/0x0/ad2305691263487dafe9da2195787761.jpg"),
		        new Car("N1", "Nissan", "Kicks", 2021, "Used", 30000.0, "https://content.homenetiol.com/2002954/2203049/0x0/f218f41d67104a5fadb328cf488b407c.jpg"),
		        new Car("F2", "Ford", "Maverick", 2023, "New", 31100.0, "https://content.homenetiol.com/2002954/2203049/0x0/26295b762abf48ee9874fa0fbd91188e.jpg"),
		        new Car("H2", "Honda", "Civic", 2018, "Used", 25800.0, "https://content.homenetiol.com/2002954/2203049/0x0/bcd5b6fd9f2c47dda3522ef569ff7183.jpg"),
		        new Car("HY2", "Hyundai", "Accent", 2020, "Used", 21995.0, "https://content.homenetiol.com/2002954/2203049/0x0/988f957c6ccd41af96bbe18d21776766.jpg"),
		        new Car("N2", "Nissan", "Murano", 2023, "New", 39808.0, "https://content.homenetiol.com/2002954/2203049/0x0/54968e2c007e431b8b33cf84f5249cac.jpg"),
		        new Car("N3", "Nissan", "Murano", 2022, "Used", 36000.0, "https://content.homenetiol.com/2002954/2203049/0x0/529438fc13984bc18ec0c65dc0d81803.jpg"),
		        new Car("HY3", "Hyundai", "Accent", 2021, "Used", 22005.0, "https://content.homenetiol.com/2002954/2203049/0x0/85724c96c3be4e37b2c74e086eb9f9fd.jpg")
		    };

		    // Create Car objects and add them to the carHashMap 
		    for (Car car : carData) {
		        carHashMap.put(car.getCarId(), car);
		    }
	    }
	
	// Display car catalog in HTML
	@GET // specify HTTP request with GET
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String displayHTMLCarCatalog() {
        
        // Generate HTML table 
     	String html = "<html><title>Car Sales</title><body>"
     					+ "<h2>Car Catalog</h2>"
     					+ "<table border=\"1\"><tr>" 	// table
		     			+ "<th>ID</th>" 				// table headers
     					+ "<th>Make</th>" 
     					+ "<th>Model</th>" 
     					+ "<th>Year</th>" 
     					+ "<th>Description</th>" 
						+ "<th>Price ($)</th>"
     					+ "<th>Image</th></tr>";
     	
        // Loop over the values using a for-each loop
        for (Car car : carHashMap.values()) {
        	html += "<tr>" 									// table row
        			+ "<td>" + car.getCarId() + "</td>"  	// table cells
        			+ "<td>" + car.getCarMake() + "</td>"
        			+ "<td>" + car.getCarModel() + "</td>"
        			+ "<td>" + car.getCarYear() + "</td>"
                	+ "<td>" + car.getCarDesc() + "</td>"
        			+ "<td>" + String.format("%.2f", car.getCarPrice()) + "$</td>"
        			+ "<td><img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'></td>" 
        			+ "</tr>";
        }    
        html += "</table></body></html>";
        return html;
	}
	
	// Display car catalog in plain text
	@GET // specify HTTP request with GET
	@Produces(MediaType.TEXT_PLAIN) // return output in plain text
	public String displayTextCarCatalog() {
		        
        // Generate HTML table 
     	String text = "";
     	
        // Loop over the values using a for-each loop
        for (Car car : carHashMap.values()) {
        	text += "Car ID: " + car.getCarId()
        			+ ", Make: " + car.getCarMake() 
        			+ ", Model: " + car.getCarModel()
        			+ ", Year: " + car.getCarYear()
                	+ ", Description: " + car.getCarDesc()
        			+ ", Price: " + String.format("%.2f", car.getCarPrice()) + "\n";        	
        }    
        return text;
	}
	
	// Display car catalog in JSON
	@GET // specify HTTP request with GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> displayJSONCarCatalog() {
		        
        // Retrieve all cars from the HashMap and store them into an ArrayList
        List<Car> cars = new ArrayList<>(carHashMap.values());
        return cars;
	}
	
	// Search a car by carId using @PathParam
	@GET // specify HTTP request with GET
	@Path("/searchCarId/{carId}") // pass in parameter
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public List<Car> searchJSONCarId(@PathParam("carId") String carId) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // Iterate through the HashMap to find the matching car
        for (Car car : carHashMap.values()) {
        	if (car.getCarId().equals(carId)) {
        		matchingCars.add(car);
        		break;
        	}
        }   
		return matchingCars;
	}
	
	// Search a car by carId using @QueryParam
	@GET // specify HTTP request with GET
	@Path("searchCarId") 
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String searchQueryHTMLCarId(@QueryParam("carId") String carId) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // Iterate through the HashMap to find the matching car
        for (Car car : carHashMap.values()) {
        	if (car.getCarId().equals(carId)) {
        		matchingCars.add(car);
        		break;
        	}
        }   

        // Create an HTML grid to display the search results
        String grid = "<h1>" + carId + "</h1>";
        grid += "<div class='grid-container'>";

        // Iterate through list of the matching cars and display them
        for (Car car : matchingCars) {
	        grid += "<div class='grid-item'>"; 																
	        grid += "<img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'>"; // image with certain size
	        grid += "<div class='car-info'>";  
	        grid += "<p class='centered-text'>Make: " + car.getCarMake() + "</p>"; 		// paragraphs
	        grid += "<p class='centered-text'>Model: " + car.getCarModel() + "</p>";
	        grid += "<p class='centered-text'>Year: " + car.getCarYear() + "</p>";
	        grid += "<p class='centered-text'>Description: " + car.getCarDesc() + "</p>";
	        grid += "<p class='centered-text'>Price: $" + car.getCarPrice() + "</p>";
	        grid += "</div>";
	        grid += "</div>";
        }
        grid += "</div>";

        // Apply CSS styles to the grid
        String styledGrid = "<style>" +
        "body {" +
        " background: #E5E4E2;" +
        " background-repeat: no-repeat;" +
        " background-size: cover;" +
        " font-family: Arial, sans-serif;" +
        "}" +
        ".header {" +
        " text-align: center;" +
        " background-color: #333;" +
        " color: #fff;" +
        " padding: 10px;" +
        "}" +
        ".grid-container {" +
        " display: grid;" +
        " grid-template-columns: repeat(4, 1fr);" +
        " grid-gap: 20px;" +
        " justify-items: center;" +
        "}" +
        ".grid-item {" +
        " border: 1px solid #ccc;" +
        " padding: 10px;" +
        " text-align: center;" +
        " background-color: rgba(255, 255, 255, 0.8);" +
        "}" +
        ".car-image {" +
        " width: 100%;" +
        " height: auto;" +
        "}" +
        ".centered-text {" +
        " text-align: center;" +
        "}" +
        "</style>" + grid;

        return styledGrid;
	}
	
	// Search cars by car make using @PathParam
	@GET // specify HTTP request with GET
	@Path("/searchCarMake/{carMake}") // pass in parameter
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public List<Car> searchJSONCarMake(@PathParam("carMake") String carMake) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // Iterate through the HashMap to find the matching cars
        for (Car car : carHashMap.values()) {
        	if (car.getCarMake().equals(carMake)) {
        		matchingCars.add(car);
        	}
        }   
		return matchingCars;
	}
	
	// Search cars by car make using @QueryParam
	@GET // specify HTTP request with GET
	@Path("searchCarMake") 
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String searchQueryHTMLCarMake(@QueryParam("carMake") String carMake) {
               
        // Create an HTML grid to display the search results
        String grid = "<h1>" + carMake + "</h1>";
        grid += "<div class='grid-container'>";

        // Iterate through the HashMap to find the matching cars and add them to the grid
        for (Car car : carHashMap.values()) {
            if (car.getCarMake().equals(carMake)) {
                grid += "<div class='grid-item'>";
                grid += "<img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'>";
                grid += "<div class='car-info'>";
                grid += "<p class='centered-text'>Make: " + car.getCarMake() + "</p>";
                grid += "<p class='centered-text'>Model: " + car.getCarModel() + "</p>";
                grid += "<p class='centered-text'>Year: " + car.getCarYear() + "</p>";
                grid += "<p class='centered-text'>Description: " + car.getCarDesc() + "</p>";
                grid += "<p class='centered-text'>Price: $" + car.getCarPrice() + "</p>";                
                grid += "</div>";
                grid += "</div>";
            }
        }
        grid += "</div>";

        // Apply CSS styles to the grid
        String styledGrid = "<style>" +
                "body {" +
                "   background: #E5E4E2;" +
                "   background-repeat: no-repeat;" +
                "   background-size: cover;" +
                " 	font-family: Arial, sans-serif;" +
                "}" +
                ".header {" +
                " text-align: center;" +
                " background-color: #333;" +
                " color: #fff;" +
                " padding: 10px;" +
                "}" +
                ".grid-container {" +
                "   display: grid;" +
                "   grid-template-columns: repeat(4, 1fr);" +
                "   grid-gap: 20px;" +
                "   justify-items: center;" +
                "}" +
                ".grid-item {" +
                "   border: 1px solid #ccc;" +
                "   padding: 10px;" +
                "   text-align: center;" +
                "   background-color: rgba(255, 255, 255, 0.8);" +
                "}" +
                ".car-image {" +
                "   width: 100%;" +
                "   height: auto;" +
                "}" +
                ".centered-text {" +
                "   text-align: center;" +
                "}" +
                "</style>" + grid;

        return styledGrid;
	}
	
	// Search cars by car model using @PathParam
	@GET // specify HTTP request with GET
	@Path("/searchCarModel/{carModel}") // pass in parameter
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public List<Car> searchJSONCarModel(@PathParam("carModel") String carModel) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // iterate through the HashMap to find the matching cars
        for (Car car : carHashMap.values()) {
        	if (car.getCarModel().equals(carModel)) {
        		matchingCars.add(car);
        	}
        }   
		return matchingCars;
	}
	
	// Search cars by car model using @QueryParam
	@GET // specify HTTP request with GET
	@Path("searchCarModel") 
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String searchQueryHTMLCarModel(@QueryParam("carModel") String carModel) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // iterate through the HashMap to find the matching cars
        for (Car car : carHashMap.values()) {
        	if (car.getCarModel().equals(carModel)) {
        		matchingCars.add(car);
        	}
        }   
        
        // Create an HTML grid to display the search results
        String grid = "<h1>" + carModel + "</h1>";
        grid += "<div class='grid-container'>";
        
        // Iterate through the list of the matching cars and display them
        for (Car car : matchingCars) {
	        grid += "<div class='grid-item'>";
	        grid += "<img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'>";
	        grid += "<div class='car-info'>";
	        grid += "<p class='centered-text'>Make: " + car.getCarMake() + "</p>";
	        grid += "<p class='centered-text'>Model: " + car.getCarModel() + "</p>";
	        grid += "<p class='centered-text'>Year: " + car.getCarYear() + "</p>";
	        grid += "<p class='centered-text'>Description: " + car.getCarDesc() + "</p>";
	        grid += "<p class='centered-text'>Price: $" + car.getCarPrice() + "</p>";
	        grid += "</div>";
	        grid += "</div>";
        }
        grid += "</div>";

        // Apply CSS styles to the grid
        String styledGrid = "<style>" +
        "body {" +
        " background: #E5E4E2;" +
        " background-repeat: no-repeat;" +
        " background-size: cover;" +
        " font-family: Arial, sans-serif;" +
        "}" +
        ".header {" +
        " text-align: center;" +
        " background-color: #333;" +
        " color: #fff;" +
        " padding: 10px;" +
        "}" +
        ".grid-container {" +
        " display: grid;" +
        " grid-template-columns: repeat(4, 1fr);" +
        " grid-gap: 20px;" +
        " justify-items: center;" +
        "}" +
        ".grid-item {" +
        " border: 1px solid #ccc;" +
        " padding: 10px;" +
        " text-align: center;" +
        " background-color: rgba(255, 255, 255, 0.8);" +
        "}" +
        ".car-image {" +
        " width: 100%;" +
        " height: auto;" +
        "}" +
        ".centered-text {" +
        " text-align: center;" +
        "}" +
        "</style>" + grid;

        return styledGrid;
	}
	
	// Search cars by a range of price (low: price1, high: price2) using @PathParam
	@GET // specify HTTP request with GET
	@Path("/searchCarPrice/{price1}/{price2}") // pass in parameter
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public List<Car> searchJSONCarPrice(@PathParam("price1") double price1, @PathParam("price2") double price2) {
        
        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // Iterate through the HashMap to find the matching cars
        for (Car car : carHashMap.values()) {
        	if (car.getCarPrice() >= price1 && car.getCarPrice() <= price2) {
        		matchingCars.add(car);
        	}
        }   
        
        // Sort the matchingCars list based on car price in ascending order
        Collections.sort(matchingCars, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) { // Create an anonymous class because it is only used here
            	// matchingCars list is arranged in ascending order based on price
            	return Double.compare(car1.getCarPrice(), car2.getCarPrice());
            }
        });
		return matchingCars;
	}
	
	// Search cars by a range of price (low: price1, high: price2) using @PathParam
	@GET // specify HTTP request with GET
	@Path("searchCarPrice") 
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String searchQueryJSONCarPrice(
			@QueryParam("price1") double price1, 
			@QueryParam("price2") double price2) {

        // Create an ArrayList to store matching cars
        List<Car> matchingCars = new ArrayList<>();
        
        // Iterate through the HashMap to find the matching cars
        for (Car car : carHashMap.values()) {
        	if (car.getCarPrice() >= price1 && car.getCarPrice() <= price2) {
        		matchingCars.add(car); // add matching car to the list
        	}
        }   
        
        // Sort the matchingCars list based on car price in ascending order
        Collections.sort(matchingCars, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) { // Create an anonymous class because it is only used here
            	// matchingCars list is arranged in ascending order based on price
            	return Double.compare(car1.getCarPrice(), car2.getCarPrice());
            }
        });
        
        // Create an HTML grid to display the search results
        String grid = "<h1>Price range: $" + String.format("%.2f", price1) + " - $" + String.format("%.2f", price2) + "</h1>";
        grid += "<div class='grid-container'>";

        // Iterate through the HashMap to find the matching cars and add them to the grid
        for (Car car : matchingCars) {
	        grid += "<div class='grid-item'>";
	        grid += "<img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'>";
	        grid += "<div class='car-info'>";
	        grid += "<p class='centered-text'>Make: " + car.getCarMake() + "</p>";
	        grid += "<p class='centered-text'>Model: " + car.getCarModel() + "</p>";
	        grid += "<p class='centered-text'>Year: " + car.getCarYear() + "</p>";
	        grid += "<p class='centered-text'>Description: " + car.getCarDesc() + "</p>";
	        grid += "<p class='centered-text'>Price: $" + String.format("%.2f", car.getCarPrice()) + "</p>";
	        grid += "</div>";
	        grid += "</div>";
        }
        grid += "</div>";

        // Apply CSS styles to the grid
        String styledGrid = "<style>" +
        "body {" +
        " background: #E5E4E2;" +
        " background-repeat: no-repeat;" +
        " background-size: cover;" +
        " font-family: Arial, sans-serif;" +
        "}" +
        ".header {" +
        " text-align: center;" +
        " background-color: #333;" +
        " color: #fff;" +
        " padding: 10px;" +
        "}" +
        ".grid-container {" +
        " display: grid;" +
        " grid-template-columns: repeat(4, 1fr);" +
        " grid-gap: 20px;" +
        " justify-items: center;" +
        "}" +
        ".grid-item {" +
        " border: 1px solid #ccc;" +
        " padding: 10px;" +
        " text-align: center;" +
        " background-color: rgba(255, 255, 255, 0.8);" +
        "}" +
        ".car-image {" +
        " width: 100%;" +
        " height: auto;" +
        "}" +
        ".centered-text {" +
        " text-align: center;" +
        "}" +
        "</style>" + grid;

        return styledGrid;
	}
	
	
	// Add a car using @QueryParam
	@POST // specify HTTP request with POST
	@Path("addJSONQueryCar") 
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public HashMap<String, Car> addQueryJSONCar(
			@QueryParam("car_id") String carId, 
			@QueryParam("car_make") String carMake,
			@QueryParam("car_model") String carModel,
			@QueryParam("car_year") int carYear,
			@QueryParam("car_desc") String carDesc,
			@QueryParam("car_price") double carPrice,
			@QueryParam("car_image_url") String carImageURL) {
		        
		// Create a new Car object with the provided data
	    Car newCar = new Car(carId, carMake, carModel, carYear, carDesc, carPrice, carImageURL);
	    
	    // Add the new car to the carHashMap
	    carHashMap.put(carId, newCar);
	    
		return carHashMap;
	}
	
	// Add a car using @FormParam
	@POST // specify HTTP request with POST
	@Path("addJSONFormCar") 
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public HashMap<String, Car> addJSONCar(
			@FormParam("car_id") String carId, 
			@FormParam("car_make") String carMake,
			@FormParam("car_model") String carModel,
			@FormParam("car_year") int carYear,
			@FormParam("car_desc") String carDesc,
			@FormParam("car_price") double carPrice,
			@FormParam("car_image_url") String carImageURL) {
		        
		// Create a new Car object with the provided data
	    Car newCar = new Car(carId, carMake, carModel, carYear, carDesc, carPrice, carImageURL);
	    
	    // Add the new car to the carHashMap
	    carHashMap.put(carId, newCar);
	    
		return carHashMap;
	}
	
	// Add a car using @FormParam
	@POST // specify HTTP request with POST
	@Path("addCar") 
	@Produces(MediaType.TEXT_HTML) // return output in HTML
	public String addCarHTML(
	        @FormParam("car_id") String carId,
	        @FormParam("car_make") String carMake,
	        @FormParam("car_model") String carModel,
	        @FormParam("car_year") int carYear,
	        @FormParam("car_desc") String carDesc,
	        @FormParam("car_price") double carPrice,
	        @FormParam("car_image_url") String carImageURL) {

	    // Create a new car object
	    Car newCar = new Car(carId, carMake, carModel, carYear, carDesc, carPrice, carImageURL);

	    // Add the new car to the carHashMap
	    carHashMap.put(newCar.getCarId(), newCar);
	    
	    // Sort the carHashMap by car ID
	    List<Car> sortedCars = new ArrayList<>(carHashMap.values());
	    Collections.sort(sortedCars, Comparator.comparing(Car::getCarId));
	    
	    // Generate the HTML page with the car list
	    String html = "<html>" +
	            "<head>" +
	            "<title>Car Catalog</title>" +
	            "<style>" +
	            "body {" +
	            "    font-family: Arial, sans-serif;" +
	            "    background-color: #f2f2f2;" +
	            "}" +
	            ".container {" +
	            "    max-width: 800px;" +
	            "    margin: 0 auto;" +
	            "    padding: 20px;" +
	            "    background-color: #ffffff;" +
	            "    border-radius: 5px;" +
	            "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);" +
	            "}" +
	            "h1 {" +
	            "    text-align: center;" +
	            "    color: #333333;" +
	            "    margin-bottom: 20px;" +
	            "}" +
	            "table {" +
	            "    width: 100%;" +
	            "    border-collapse: collapse;" +
	            "}" +
	            "th, td {" +
	            "    padding: 10px 20px;" +
	            "    text-align: left;" +
	            "}" +
	            "th {" +
	            "    background-color: #f2f2f2;" +
	            "}" +
	            "tr:nth-child(even) {" +
	            "    background-color: #f9f9f9;" +
	            "}" +
	            "tr:hover {" +
	            "    background-color: #eaeaea;" +
	            "}" +
	            "img {" +
	            "    width: 100px;" +
	            "    height: auto;" +
	            "}" +
	            "</style>" +
	            "</head>" +
	            "<body>" +
	            "<div class='container'>" +
	            "<h1>Car Catalog</h1>" +
	            "<table>" +
	            "<tr>" +
	            "<th>Car ID</th>" +
	            "<th>Make</th>" +
	            "<th>Model</th>" +
	            "<th>Year</th>" +
	            "<th>Description</th>" +
	            "<th>Price ($)</th>" +
	            "<th>Image</th>" +
	            "</tr>";

	    // Iterate through the carHashMap to generate table rows for each car
	    for (Car car : sortedCars) {
	        html += "<tr>" +
	                "<td>" + car.getCarId() + "</td>" +
	                "<td>" + car.getCarMake() + "</td>" +
	                "<td>" + car.getCarModel() + "</td>" +
	                "<td>" + car.getCarYear() + "</td>" +
	                "<td>" + car.getCarDesc() + "</td>" +
	                "<td>" + String.format("%.2f", car.getCarPrice()) + "</td>" +
	                "<td><img src='" + car.getCarImageURL() + "' alt='Car Image' width='150' height='auto'></td>" +
	                "</tr>";
	    }

	    html += "</table>" +
	            "</div>" +
	            "</body>" +
	            "</html>";
	
	    return html;
	}
	
	
	// Delete a car by car ID using @QueryParam
	@DELETE // specify HTTP request with DELETE
	@Path("removeJSONCar") 
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public HashMap<String, Car> removeJSONClientCar(
			@QueryParam("car_id") String carId) {
		        
        // Find the key of the car that matches the given carId
        String carToRemove = null;
        for (Map.Entry<String, Car> entry : carHashMap.entrySet()) {
            if (entry.getValue().getCarId().equals(carId)) {
                carToRemove = entry.getKey(); // get the key of the car to be removed
                break;
            }
        }

        // Remove the entry with the matching carId key from the carHashMap
        if (carToRemove != null) {
            carHashMap.remove(carToRemove);
        }
        return carHashMap;
    }
	
	// Delete a car by car ID using @QueryParam
	@DELETE // specify HTTP request with DELETE
	@Path("removeCar") 
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public HashMap<String, Car> removeJSONCar(
			@QueryParam("car_id") String carId) {
		        
        // Find the key of the car that matches the given carId
        String carToRemove = null;
        for (Map.Entry<String, Car> entry : carHashMap.entrySet()) {
            if (entry.getValue().getCarId().equals(carId)) {
                carToRemove = entry.getKey(); // get the key of the car to be removed
                break;
            }
        }

        // Remove the entry with the matching carId key from the carHashMap
        if (carToRemove != null) {
            carHashMap.remove(carToRemove);
        }
        return carHashMap;
    }
	
	// Update price of a car using @QueryParam
	@PUT // specify HTTP request with PUT
	@Path("updateCar") 
	@Produces(MediaType.APPLICATION_JSON) // return output in JSON
	public HashMap<String, Car> updateJSONCar(
			@QueryParam("car_id") String carId, 
			@QueryParam("car_price") double carPrice) {
        
        for (Car car: carHashMap.values()) {
        	if (car.getCarId().equals(carId)) {
        		car.setCarPrice(carPrice); // update the price
        		break;
        	}
        }  
		return carHashMap;
	}
}
