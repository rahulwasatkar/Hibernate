package com.rahul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.rahul.config.HibernateUtility;
import com.rahul.entity.Product;

public class ProductDao {
	SessionFactory sessionfactory = HibernateUtility.getSessionFactory();

	public boolean configureProduct(Product product) {

		Session session = sessionfactory.openSession();
		boolean isSave = false;
		try {
			Transaction tr = session.beginTransaction();
			session.save(product);
			tr.commit();
			isSave = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return isSave;

	}

	public Product getProductByid(int ProductId) {
		Session session = sessionfactory.openSession();
		Product product = null;
		try {
			product = session.get(Product.class, ProductId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		Session session = sessionfactory.openSession();
		List list = null;
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public Boolean UpdateProduct(Product product) {
		Session session = sessionfactory.openSession();
		Transaction tr = session.beginTransaction();
		boolean isUpdated = false;
		try {
			Product product1 = session.get(Product.class, product.getProductId());
			session.evict(product1);
			if (product1 != null) {
				session.update(product);
				tr.commit();
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isUpdated;
	}

	public boolean deleteProduct(int id) {
		Session session = sessionfactory.openSession();
		Transaction tr = session.beginTransaction();
		boolean isDeleted = false;
		try {
			Product product = session.get(Product.class, id);
			if (product != null) {
				session.delete(product);
				tr.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isDeleted;

	}

	public Product getProductByName(String productname) {
		Session session = sessionfactory.openSession();
		List<Product> list = null;
		Product product = null;
		try {

			Criteria cr = session.createCriteria(Product.class);
			cr.add(Restrictions.eq("productName", productname));
			list = cr.list();
			product = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return product;

	}

	public double getproductSum() {
		Session session = sessionfactory.openSession();
		double sum = 0;
		try {
			Criteria cr = session.createCriteria(Product.class);
			cr.setProjection(Projections.groupProperty("productPrice"));
			List<Double> list = cr.list();
			if (!list.isEmpty()) {
				sum = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sum;

	}

	public int deleteProductQuery(int id) {

		Session session = sessionfactory.openSession();
		Transaction tr = session.beginTransaction();
		int row = 0;
		try {
			Query query = session.createQuery("delete from Product where productId =:pId");
			query.setParameter("pId", id);
			row = query.executeUpdate();
			tr.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return row;

	}

}
