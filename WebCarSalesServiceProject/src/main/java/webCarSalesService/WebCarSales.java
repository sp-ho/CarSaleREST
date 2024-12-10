package webCarSalesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding (style=Style.RPC) // remove procedure call
public class WebCarSales {
	
	// Create a HashMap to store Car objects
	private static HashMap<String, Car> carHashMap = new HashMap<>(); // Store car information
	
	// Create an Array List to store Order objects
	private static List<Order> OrderList = new ArrayList<>();

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
	    
		// Array of Order data
		Object[][] orderData = {
				{1, "F1", "Mustang", 50000.0},
				{2, "H1", "CR-V", 30000.0},
				{3, "N1", "Kicks", 30000.0}
		};
		
		// Create Order objects and add them to the orderList using the array data
		for (int i = 0; i < orderData.length; i++) {
			Order course = new Order();
			course.setOrderId((int) orderData[i][0]);
			course.setCarId((String) orderData[i][1]);
			course.setCarModel((String) orderData[i][2]);
			course.setCarPrice((double) orderData[i][3]);
			
			OrderList.add(course);
		}
	}
	
	@WebMethod
	public String displayCarCatalog() {
        
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
	
	@WebMethod
	public String displayOrderInfo() {
     	
     	String result = "";
     	
        for (int i = 0; i < OrderList.size(); i++) {
        	result += "\nOrder ID: " + OrderList.get(i).getOrderId() 
        			+ ", Car ID: " + OrderList.get(i).getCarId()
        			+ ", Car Model: " + OrderList.get(i).getCarModel()
        			+ ", Car Price: $" + OrderList.get(i).getCarPrice();
        }       
		return result;
	}
	
	@WebMethod
	public Order searchOrderInfo(int orderId){
		
		Order matchingOrder = null;
		
		for (int i = 0; i < OrderList.size(); i++) {
			if(OrderList.get(i).getOrderId() == orderId) {
				matchingOrder = OrderList.get(i);
				break;
			}
		}
		return matchingOrder;
	}
	
	@WebMethod
	public Order addOrder(int orderId, String carId, String carModel, double carPrice){
		
		// Create a Order object 
		Order newOrder = new Order(orderId, carId, carModel, carPrice);
		
		OrderList.add(newOrder); // add new order to Order list
		carHashMap.remove(newOrder.getCarId()); // remove the ordered car from car catalog
		
		return newOrder;
	}
}
