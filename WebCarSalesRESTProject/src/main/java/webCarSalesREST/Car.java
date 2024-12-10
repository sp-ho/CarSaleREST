package webCarSalesREST;

import java.util.Comparator;
import java.util.Map;

public class Car {

	// Instance variables
	private String carId;
	private String carMake;
	private String carModel;
	private int carYear;
	private String carDesc;
	private double carPrice;
	private String carImageURL;
	
	// Default constructor	
	public Car() {
	}

	// Constructor with parameters
	public Car(String carId, String carMake, String carModel, int carYear, String carDesc, double carPrice, String carImageURL) {
		super();
		this.carId = carId;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		this.carDesc = carDesc;
		this.carPrice = carPrice;
		this.carImageURL = carImageURL;
	}

	// Getters and setters
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

	public String getCarDesc() {
		return carDesc;
	}

	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
	
	public String getCarImageURL() {
		return carImageURL;
	}

	public void setCarImageURL(String carImageURL) {
		this.carImageURL = carImageURL;
	}
	
//	public static class CarDiscountComparator implements Comparator<Map.Entry<String, Car>> {
//        public int compare(Map.Entry<String, Car> entry1, Map.Entry<String, Car> entry2) {
//            double price1 = entry1.getValue().getCarPrice();
//            double price2 = entry2.getValue().getCarPrice();
//
//            if (price1 < price2) {
//                return -1;
//            } else if (price1 > price2) {
//                return 1;
//            } else {
//                return 0;
//            }
//        }
//    }
		
}
