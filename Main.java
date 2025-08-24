package tnsif;

//Parent Class (Base Class)
//Parent class
class Person {
 String name;
 int age;

 void displayDetails() {
     System.out.println("Name: " + name);
     System.out.println("Age: " + age);
 }
}

//Child class
class Student extends Person {
 int rollNo;

 void showStudentInfo() {
     System.out.println("Roll Number: " + rollNo);
 }
}

//Main class
public class Main {
 public static void main(String[] args) {
     Student s = new Student();

     // Setting values
     s.name = "Sakshi";
     s.age = 21;
     s.rollNo = 101;

     // Accessing methods
     s.displayDetails();       // From Person class
     s.showStudentInfo();
 }
}
