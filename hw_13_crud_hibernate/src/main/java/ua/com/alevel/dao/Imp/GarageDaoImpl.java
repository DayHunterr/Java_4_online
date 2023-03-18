package ua.com.alevel.dao.Imp;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.GarageDao;
import ua.com.alevel.entity.Garage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GarageDaoImpl implements GarageDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Garage garage) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(garage);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Garage garage) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(garage);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Garage garage) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(garage);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Garage> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Garage c where c.id = :id")
                    .setParameter("id", id);
            Garage garage = (Garage) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(garage);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Garage> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Garage ");
            List<Garage> garages = query.getResultList();
            transaction.commit();
            return garages;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}
