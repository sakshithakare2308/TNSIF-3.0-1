package tnsif;

public class Encat {
	

	    // Encapsulated Student class
	    static class Student {
	        // private data members
	        private String name;
	        private int age;

	        // public getter for name
	        public String getName() {
	            return name;
	        }

	        // public setter for name
	        public void setName(String name) {
	            this.name = name;
	        }

	        // public getter for age
	        public int getAge() {
	            return age;
	        }

	        // public setter for age with validation
	        public void setAge(int age) {
	            if (age > 0) {
	                this.age = age;
	            } else {
	                System.out.println("Age must be positive.");
	            }
	        }
	    }

	    public static void main(String[] args) {
	        // Create object of Student
	        Student s = new Student();

	        // Set values using setters
	        s.setName("Sakshi");
	        s.setAge(21);

	        // Get values using getters
	        System.out.println("Name: " + s.getName());
	        System.out.println("Age: " + s.getAge());
	    }
	}


