package tnsif;

class Vehicle {
 String modelNo;
 String modelName;
 int speed;
 String engine;

 
 Vehicle(String modelNo, String modelName, int speed, String engine) {
     this.modelNo = modelNo;
     this.modelName = modelName;
     this.speed = speed;
     this.engine = engine;
 }

 void showVehicleDetails() {
     System.out.println("Model No: " + modelNo);
     System.out.println("Model Name: " + modelName);
     System.out.println("Top Speed: " + speed + " km/h");
     System.out.println("Engine Type: " + engine);
 }
}


class Car extends Vehicle {
 

 
 Car(String modelNo, String modelName, int speed, String engine, int numberOfSeats) {
     super(modelNo, modelName, speed, engine); // Call parent constructor
    
 }

 void showCarDetails() {
     showVehicleDetails();
     
 }
}


public class InheritanceTest {
 public static void main(String[] args) {
     Car myCar = new Car("C2025", "Honda City", 180, "i-VTEC", 5);
     myCar.showCarDetails();
 }
}
