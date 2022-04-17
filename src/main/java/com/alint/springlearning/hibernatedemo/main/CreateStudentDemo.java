package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Alin","Tudose","alintudose126@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            factory.close();
        }
    }
}
