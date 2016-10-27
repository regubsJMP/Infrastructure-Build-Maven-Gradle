package com.epam.jmp.springmvc.persontask.entity;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String address;
	private int salary;

	public Person() {
	}

	public Person(String address, int age, String firstName, int id, String lastName, int salary) {
		this.address = address;
		this.age = age;
		this.firstName = firstName;
		this.id = id;
		this.lastName = lastName;
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Person{" +
				"address='" + address + '\'' +
				", id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", salary=" + salary +
				'}';
	}
}
