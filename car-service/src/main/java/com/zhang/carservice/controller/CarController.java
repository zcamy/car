package com.zhang.carservice.controller;


import com.api.carapi.annotations.Log;
import com.api.carapi.model.Car;
import com.api.carapi.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Log
@RestController
@RequestMapping("/car")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    CarService carService;

    @RequestMapping(path="/createCar",method = RequestMethod.POST)
    public int createCar( @Valid @NotNull Car car) {
        return  carService.createCar(car);
    }

    @RequestMapping(path="/findAllCar",method = RequestMethod.POST)
    public List<Car> findAllCar(@NotNull int type)  {
        return carService.findAll(type);
    }

    @RequestMapping(path="/findAllCarS",method = RequestMethod.POST)
    public List<Car> findAllCarS()  {
        return carService.findAllCar();
    }
    @RequestMapping(path="/findAll",method = RequestMethod.POST)
    public List<Car> findAllCar(@NotNull String userId)  {
        return carService.findAllCar(userId);
    }

    @RequestMapping(path="/findOneCar",method = RequestMethod.POST)
    public Car findOneCar(@NotNull String carId) {
        return carService.findOne(carId);
    }

    @RequestMapping(path="/deleteCar",method = RequestMethod.POST)
    public int deleteCar(@NotNull String carId) {
        return carService.deleteCar(carId);
    }

    @RequestMapping(path="/updateCar",method = RequestMethod.POST)
    public int updateCar( @Valid @NotNull Car car)  {
        return carService.updateCar(car);
    }

    @RequestMapping(path="/updateCarS",method = RequestMethod.POST)
    public int updateCarS(@NotNull String carId ,int type, @NotNull String remark)  {
        return carService.updateCar(carId,type,remark);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public int uploadImg( @RequestParam("file") MultipartFile file, HttpServletRequest request){
        return carService.uploadImg(file,request);
    }
}
