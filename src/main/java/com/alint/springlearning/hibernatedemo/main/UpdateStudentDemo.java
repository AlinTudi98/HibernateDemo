package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration().configure("hibernateOracle.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            int studentId=1;
            session.beginTransaction();

//            System.out.println("\nGetting student with id: " +  studentId);
//            Student student = session.get(Student.class,studentId);
//
//            System.out.println("Updating student...");
//
//            student.setEmail("onutuc@dcti.ro");

            System.out.println("Update email for all students");
            session.createQuery("update Student set email='foo@bar.com'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
