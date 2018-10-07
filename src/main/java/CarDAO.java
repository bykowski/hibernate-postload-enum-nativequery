import entity.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public void add(Car car) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(car);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public Car read(long id) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try {
            Car car = session.get(Car.class, id);
            return car;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public void update(long id, Car newCar) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Car car = session.find(Car.class, id);
            car.setProductionDate(newCar.getProductionDate());
            car.setMark(newCar.getMark());
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public void delete(long id) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Car car = session.find(Car.class, id);
            session.delete(car);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public List<Car> readManyMank(String mark) {
        List<Car> cars = new ArrayList<>();
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try {
            Query query = session.createNativeQuery("SELECT id FROM car WHERE car.mark LIKE ?");
            query.setParameter(1, mark);
            List<BigInteger> resultList = query.getResultList();
            for (BigInteger bigInteger : resultList) {
                Car car = read(bigInteger.longValue());
                cars.add(car);
            }
            return cars;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }
}
