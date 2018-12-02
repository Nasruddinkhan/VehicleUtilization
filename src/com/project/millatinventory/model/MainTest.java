package com.project.millatinventory.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class MainTest
{
	public static void main(String[] args) {
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
System.out.println(sessionFactory);
FileHeader f = new FileHeader();
f.setFileId(1);
f.setAmount(11);
//Create session factory object
//getting session object from session factory
Session session = sessionFactory.getCurrentSession();
//getting transaction object from session object
//session.beginTransaction();

session.save(f);
System.out.println("Inserted Successfully");
//session.getTransaction().commit();
//session.close();
//sessionFactory.close();

	}
}