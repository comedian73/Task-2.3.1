package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCar() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public void dropCarTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("DROP TABLE IF EXISTS cars CASCADE ").addEntity(Car.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getUser(String model, int series) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Car as car where car.model =:model and car.series = :series", Car.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<Car> cars = query.getResultList();

        transaction.commit();
        session.close();

        List<User> user = new LinkedList<>();
        Iterator iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = (Car) iterator.next();
            TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("from User where id =: idUser", User.class);
            query1.setParameter("idUser", car.getId());
            user.add(query1.getSingleResult());
        }

        return user;
    }

}
