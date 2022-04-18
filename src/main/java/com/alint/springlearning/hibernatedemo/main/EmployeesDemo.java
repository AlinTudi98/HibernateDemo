package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.Practice.DBEmployeeEngine;
import com.alint.springlearning.hibernatedemo.entities.Employee;

import java.text.SimpleDateFormat;
import java.util.List;

public class EmployeesDemo {
    public static void main(String[] args) {
        try{
        DBEmployeeEngine engine = new DBEmployeeEngine();
        String configFileName = "hibernateOracle.cfg.xml";

        System.out.println("------------------------------------------------------------");
        System.out.println("Saving objects test...");
        Employee employee = new Employee("Dani","Mocanu","MANEA");
        System.out.println("Saving employee: " + employee);
        engine.saveEmployee(employee,configFileName);
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------------------------------------------------");
        System.out.println("Getting employee with id: " + employee.getId());
        Employee employee1 = engine.getEmployee(employee.getId(), configFileName);
        System.out.println("Got employee: " + employee1);
        System.out.println("------------------------------------------------------------");

        engine.saveEmployee(new Employee("Florin","Salam","MANEA"),configFileName);
        engine.saveEmployee(new Employee("Tzanca","Uraganu","MANEA"),configFileName);
        engine.saveEmployee(new Employee("Nicolae","Guta","MANEA"),configFileName);
        engine.saveEmployee(new Employee("","Susanu","MANEA"),configFileName);
        engine.saveEmployee(new Employee("Mr","Juve","MANEA"),configFileName);

        System.out.println("------------------------------------------------------------");
        String companyName = "MANEA";
        System.out.println("Getting employees that work for company: " + companyName);
        List<Employee> employees = engine.getEmployeesForCompany(companyName,configFileName);
        System.out.println("Got employees: ");
        for(Employee employee_iter: employees)
            System.out.println(">>\t Employee: " + employee_iter);
        System.out.println("\n");
        System.out.println("------------------------------------------------------------");

        System.out.println("------------------------------------------------------------");
        System.out.println("Deleting employee: " + employee);
        int employeeId = employee.getId();
        engine.deleteEmployee(employee.getId(), configFileName);
        System.out.println("\nTrying to get the same employee id...");
        Employee employee2 = engine.getEmployee(employeeId,configFileName);
        if (employee2 == null){
            System.out.println("Null. Correct!");
        } else {
            System.out.println("Somehow got employee: " + employee2);
            System.out.println("Check!!!");
        }
        System.out.println("------------------------------------------------------------");


    } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
