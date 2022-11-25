package com.rahul.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.entity.Product;

public class HibernateUtility {
	
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionfactory = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure().addAnnotatedClass(Product.class);
			 sessionfactory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionfactory;
	}

}
