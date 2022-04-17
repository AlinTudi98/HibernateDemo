package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy","Duck","daffy@dcti.ro");

            session.beginTransaction();

            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            session.getTransaction().commit();
            System.out.println("Saved student. Generated id: " + tempStudent.getId());
            System.out.println("Done!");

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with id: 0"+ tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + myStudent);

        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            session.close();
            factory.close();
        }
    }
}
