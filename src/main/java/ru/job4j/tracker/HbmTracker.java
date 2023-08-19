package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        Session session = this.sf.openSession();
        List<Item> resultList = new ArrayList<>();
        try {
            Query<Item> query = session.createQuery("from Item", Item.class);
            resultList = query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return resultList;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = this.sf.openSession();
        List<Item> resultList = new ArrayList<>();
        try {
            Query<Item> query = session.createQuery("from Item where name = :fName", Item.class)
                    .setParameter("fName", key);
            resultList = query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return resultList;
    }

    @Override
    public Item findById(int id) {
        Item resultItem = null;
        Session session = this.sf.openSession();
        try {
            Query<Item> query = session.createQuery(
                    "from Item as i where i.id = :fId", Item.class);
            query.setParameter("fId", id);
            resultItem = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return resultItem;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}