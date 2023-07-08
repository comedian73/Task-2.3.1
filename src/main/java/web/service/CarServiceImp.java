package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.dao.CarDaoImp;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    private CarDao carDao = new CarDaoImp();
    @Override
    public List<Car> carsCount(List<Car> list, int number) {
        list = this.list();
        return carDao.carsCount(list, number);
    }
    private List<Car> list() {
        List<Car> list = new ArrayList<>();
        list.add(new Car("Ford", "Mustang", 20000));
        list.add(new Car("KIA", "Sportage", 13000));
        list.add(new Car("Lamborghini", "350GT", 50000));
        list.add(new Car("Renault", "LOGAN", 5000));
        list.add(new Car("VAZ Lada", "2106", 3000));
        return list;
    }
}
