package web.dao;

import web.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> carsCount(List<Car> list, int number);
}
