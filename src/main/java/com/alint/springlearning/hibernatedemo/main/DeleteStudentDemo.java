package com.alint.springlearning.hibernatedemo.main;

import com.alint.springlearning.hibernatedemo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration().configure("hibernateOracle.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            int studentId = 4;
            session.beginTransaction();

            //Student student = session.get(Student.class, studentId);

            //session.delete(student);

            session.createQuery("delete from Student where id=1").executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

    }
}
