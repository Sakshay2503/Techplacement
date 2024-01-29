package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class Employeee {
	private int empId;
	private String ename;
	private double salary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employeee(int empId, String ename, double salary) {
		super();
		this.empId = empId;
		this.ename = ename;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return " empId= " + empId + ", ename= " + ename + ", salary= " + salary;
	}

}

class EmployeeManagementSystem {
	private static final String FILE_NAME = "employee_data.txt";
	private static Scanner s = new Scanner(System.in);
	private static Scanner s1 = new Scanner(System.in);
	private static List<Employeee> c = new ArrayList<Employeee>();

	public static void main(String args[]) {

		loadEmployeeDataFromFile();
		while (true) {
			System.out.println("Employee Management System");
			System.out.println("1. Add Employee");
			System.out.println("2. View Employees");
			System.out.println("3. Search Employee");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			int ch = s.nextInt();
			s.nextLine();

			switch (ch) {
			case 1:
				addEmployee();
				break;
			case 2:
				viewEmployees();
				break;
			case 3:
				searchEmployee();
				break;
			case 4:
				updateEmployee();
				break;
			case 5:
				deleteEmployee();
				break;
			case 6:
				 saveEmployeeDataToFile();
				System.out.println("Exiting program.....");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice.....Please enter a valid option.....");

			}
		}

	}

	

	private static void addEmployee() {
		System.out.print("Enter EId : ");
		int eId = s.nextInt();
		System.out.print("Enter Empname : ");
		String ename = s1.nextLine();
		System.out.print("Enter Salary : ");
		int salary = s.nextInt();
		c.add(new Employeee(eId, ename, salary));

		System.out.println("Record Inserted Sucessfully");

	}

	private static void viewEmployees() {
		Iterator<Employeee> i = c.iterator();
		while (i.hasNext()) {
			Employeee e = i.next();
			System.out.println(e);
		}

	}

	private static void searchEmployee() {
		boolean flag = false;
		System.out.print("Enter EId to Search : ");
		int eId = s.nextInt();

		Iterator<Employeee> ii = c.iterator();
		while (ii.hasNext()) {
			Employeee e = ii.next();
			if (e.getEmpId() == eId) {
				System.out.println(e);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("Record Not Found");
		}

	}

	private static void updateEmployee() {
		boolean flag = false;
		System.out.print("Enter EId to Update : ");
		int eId = s.nextInt();
		ListIterator<Employeee> li = c.listIterator();
		while (li.hasNext()) {
			Employeee e = li.next();
			if (e.getEmpId() == eId) {
				System.out.print("Enter new Name : ");
				String ename = s1.nextLine();
				System.out.print("Enter new Salary : ");
				Double salary = s.nextDouble();
				li.set(new Employeee(eId, ename, salary));
				flag = true;
			}
		}

		if (!flag) {
			System.out.println("Record Not Found");
		} else {
			System.out.println("Record is Updated Sucessfully");
		}

	}

	private static void deleteEmployee() {
		boolean flag = false;
		System.out.print("Enter EId to Delete : ");
		int eId = s.nextInt();

		Iterator<Employeee> iii = c.iterator();
		while (iii.hasNext()) {
			Employeee e = iii.next();
			if (e.getEmpId() == eId) {
				iii.remove();
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("Record Not Found");
		} else {
			System.out.println("Record is Deleted Sucessfully");
		}

	}

	private static void loadEmployeeDataFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				double salary = Double.parseDouble(parts[2]);
				Employeee employee = new Employeee(id, name, salary);
				c.add(employee);
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void saveEmployeeDataToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Employeee employee : c) {
				writer.write(employee.getEmpId() + "," + employee.getEname() + "," + employee.getSalary());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
