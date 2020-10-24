package com.yucel.mavenhibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	private static SessionFactory factory;

	public static void main(String[] args) {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();

		getCityIdName();
		plateSearch(68);
//		deleteCity(10);
//		updateCity("New City Name", 10);
//		addNewCity("Aksaray", 61);
 
	}

	public static void getCityName() {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery("from City").getResultList();
			for (City city : cities) {
				System.out.println(city.getName());
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}

	public static void getCityId() {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery("from City").getResultList();
			for (City city : cities) {
				System.out.println(city.getId());
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}

	public static void getCityIdName() {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery("from City").getResultList();
			for (City city : cities) {
				System.out.println(city.getId() + " - " + city.getName());
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}

	public static void plateSearch(int plate) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery("from City c where c.plate='" + plate + "'").getResultList();
			for (City city : cities) {
				System.out.println("plate : " + plate + " => " + city.getName());
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}

	public static void addNewCity(String cityName, int plate) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			City city = new City();
			city.setName(cityName);
			city.setPlate(plate);
			session.save(city);
			session.getTransaction().commit();
			System.out.println("Saved");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}

	public static void updateCity(String cityName, int id) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			City city = session.get(City.class, id);
			city.setName(cityName);
			session.save(city);

			session.getTransaction().commit();
			System.out.println("Update");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}

	}

	public static void deleteCity(int id) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			City city = session.get(City.class, id);
			session.delete(city);

			session.getTransaction().commit();
			System.out.println("Update");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// factory.close();
		}
	}
}
