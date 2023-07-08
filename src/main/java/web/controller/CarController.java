package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String cars(
            @RequestParam(value = "count", defaultValue = "5")
            int allCars, Model model) {

        CarService carService = new CarServiceImp();
        List<Car> list = new ArrayList<>();
        list = carService.carsCount(list, allCars);
        model.addAttribute("list", list);

        return "cars";
    }
}
