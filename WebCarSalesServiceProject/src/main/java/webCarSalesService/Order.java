package webCarSalesService;

public class Order {
	
	// instance variables
	private int orderId;
	private String carId;
	private String carModel;
	private double carPrice;
	
	// Default constructor
	public Order() {}
	
	// Constructor with parameters
	public Order(int orderId, String carId, String carModel, double carPrice) {
		super();
		this.orderId = orderId;
		this.carId = carId;
		this.carModel = carModel;
		this.carPrice = carPrice;
	}

	// Getter and setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
	
}
