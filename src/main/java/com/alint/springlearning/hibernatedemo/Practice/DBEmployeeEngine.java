package com.alint.springlearning.hibernatedemo.Practice;

import com.alint.springlearning.hibernatedemo.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBEmployeeEngine {
    private SessionFactory factory;

    public DBEmployeeEngine() {

    }

    private void initFactory(String configFileName){
        if (factory == null){
            factory = new Configuration().configure(configFileName).addAnnotatedClass(Employee.class).buildSessionFactory();
        }
    }

    public void saveEmployee(Employee employee, String configFileName){
        initFactory(configFileName);
        try (Session session = factory.getCurrentSession()) {

            session.beginTransaction();

            System.out.println(">>DBEmployeeEngine:\tSaving employee: " + employee);
            session.save(employee);

            session.getTransaction().commit();
            System.out.println(">>DBEmployeeEngine:\tDone!");

        } catch (Exception e) {
            System.out.println(">>DBEmployeeEngine:\tFailed to save employee: " + employee);
            System.out.println(">>DBEmployeeEngine:\tCause: " + e.getMessage());
        } finally {
            factory.close();
            factory = null;
        }
    }

    public Employee getEmployee(int employeeId, String configFileName){
        initFactory(configFileName);
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            System.out.println(">>DBEmployeeEngine:\tGetting employee with id: " + employeeId);
            Employee employee = session.get(Employee.class, employeeId);
            session.getTransaction().commit();
            System.out.println(">>DBEmployeeEngine:\tDone!");
            return employee;
        } catch (Exception e){
            System.out.println(">>DBEmployeeEngine:\tFailed to get employee for id: " + employeeId);
            System.out.println(">>DBEmployeeEngine:\tCause: " + e.getMessage());
        } finally {
            factory.close();
            factory = null;
        }
        return null;
    }

    public List<Employee> getEmployeesForCompany(String companyName,String configFileName){
        initFactory(configFileName);
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            System.out.println(">>DBEmployeeEngine:\tGetting employees that work for company: " + companyName);
            List<Employee> employees = session.createQuery("from Employee where company = '" + companyName + "'")
                    .getResultList();
            System.out.println(">>DBEmployeeEngine:\tDone!");
            return employees;
        } catch (Exception e){
            System.out.println(">>DBEmployeeEngine:\tFailed to get employees for company:" + companyName);
            System.out.println(">>DBEmployeeEngine:\tCause: " + e.getMessage());
        } finally {
            factory.close();
            factory = null;
        }
        return null;
    }

    public void deleteEmployee(int employeeId,String configFileName){
        initFactory(configFileName);
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            System.out.println(">>DBEmployeeEngine:\tDeleting employee with id: " + employeeId);
            session.createQuery("delete from Employee where id = " + employeeId).executeUpdate();
            System.out.println(">>DBEmployeeEngine:\tDone!");
        } catch (Exception e){
            System.out.println(">>DBEmployeeEngine:\tFailed to delete employee with id: " + employeeId);
            System.out.println(">>DBEmployeeEngine:\tCause: " + e.getMessage());
        } finally {
            factory.close();
            factory = null;
        }
    }

}
