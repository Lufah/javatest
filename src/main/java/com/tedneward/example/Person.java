package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
   private int age;
   private String name;
   private double salary;
   private String ssn;
   private boolean propertyChangeFired = false;
   private static int numOfPeople = 0;
  
   public Person() {
      this("", 0, 0.0d);
   }
     
   // Added exception for age < 0 and n == null (is this needed?)
   public Person(String n, int a, double s) {
      if (n == null || a < 0 ) {
       	throw new IllegalArgumentException();
      }

      name = n;
      age = a;
      salary = s;
      ssn = "";

      numOfPeople++;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int a) {
     	if (a < 0) {
     		throw new IllegalArgumentException();  
     	}
     	age = a;
   }
     
   public String getName() {
      return name;
   }

   public void setName(String n) {
     	if (n == null) {
     		throw new IllegalArgumentException();
     	}
     	name = n;
   }
     
   public double getSalary() {
      return salary;
   }

   public void setSalary(double s) {
     	salary = s;
   }

   public String getSSN() {
      return ssn;
   }

   public void setSSN(String value) {
      String old = ssn;
      ssn = value;
       
      this.pcs.firePropertyChange("ssn", old, value);
      propertyChangeFired = true;
   }

   public int count() {
     	return numOfPeople;
   }

   public boolean getPropertyChangeFired() {
      return propertyChangeFired;
   }

   public double calculateBonus() {
      return salary * 1.10;
   }
     
   public String becomeJudge() {
      return "The Honorable " + name;
   }
  
   public int timeWarp() {
      return age + 10;
   }
     
   // Added instanceof for comparison
   @Override
   public boolean equals(Object other) {
     	if (other instanceof Person) {
     		Person p = (Person)other;
     		return this.name == p.name && this.age == p.age;
     	}
     	return false;
   }

   // Nested class that compares age and arranges by youngest to oldet
   public static class AgeComparator implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int ageDiff = p1.age - p2.age;
    		if (ageDiff > 0) {
    			return 1;
    		} else if (ageDiff < 0) {
    			return -1;
    		} else {
    			return 0;
    		}
    	}
   }

   // Compares salaries and arranges by highest to lowest salary
   @Override
   public int compareTo(Person other) {
     	double salaryDiff = this.salary - other.getSalary();
     	if (salaryDiff > 0) {
   		return -1;
   	} else if (salaryDiff < 0) {
   		return 1;
   	} else {
   		return 0;
    	}
   }

   // Returns ArrayList with family members 
   public static ArrayList<Person> getNewardFamily() {
     	Person p1 = new Person("Ted", 41, 250000.00);
     	Person p2 = new Person("Charlotte", 43, 150000.00);
     	Person p3 = new Person("Michael", 22, 10000.00);
     	Person p4 = new Person("Matthew", 15, 0.00);

     	ArrayList<Person> family = new ArrayList<Person>();
     	family.add(p1);
     	family.add(p2);
     	family.add(p3);
     	family.add(p4);

     	return family;
   }

   // Overrides toString
   @Override
   public String toString() {
      return "[Person name:" + getName() + " age:" + getAge() + " salary:" + getSalary() + "]";
   }

   // PropertyChangeListener support; you shouldn't need to change any of
   // these two methods or the field
   //
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
   }
   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
   }
}
