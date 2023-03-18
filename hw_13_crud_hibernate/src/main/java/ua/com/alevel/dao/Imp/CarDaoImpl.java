package ua.com.alevel.dao.Imp;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.CarDao;
import ua.com.alevel.entity.Car;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Car> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car c where c.id = :id")
                    .setParameter("id", id);
            Car car = (Car) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(car);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Car> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car ");
            List<Car> cars = query.getResultList();
            transaction.commit();
            return cars;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}
