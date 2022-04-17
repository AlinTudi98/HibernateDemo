package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PublicKeyDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            System.out.println("Creating a new student object...");
            Student tempStudent1 = new Student("Cosmin","Onutu","onutuc@gmail.com");
            Student tempStudent2 = new Student("Catalina","Mihailescu","catalinamihailescu@yahoo.com");
            Student tempStudent3 = new Student("Emilian","Dumitru","d_emilian@yahoo.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            session.close();
            factory.close();
        }
    }
}
