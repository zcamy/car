package com.zhang.carservice.service;

import com.api.carapi.model.Car;
import com.api.carapi.service.CarService;
import com.zhang.carservice.config.BeanMapper.BeanMapper;
import com.zhang.carservice.config.handle.GlobalExceptionHandle;
import com.zhang.carservice.dao.CarDao;
import com.zhang.carservice.entity.CarEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDao CarDao;

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public List<Car> findAll() {
        List<CarEntity> carDaoList = CarDao.findAllCar();
        List<Car> carList = new ArrayList<>();
        for (CarEntity carEntity : carDaoList) {
            Car map = BeanMapper.map(carEntity, Car.class);
            carList.add(map);
        }
        return carList;
    }
    @Override
    public List<Car> findAll(int type) {
        List<Car> all = findAll();
        List<Car> carList = new ArrayList<>();
        if (all.size()!= 0){
            for (Car car : all) {
                if (car.getCarType() == type){
                    if (car.getCarState() == 0){
                        continue;
                    }
                    carList.add(car);
                }
            }
            return carList;
        }
        return null;
    }

    @Override
    public List<Car> findAllCar(String userId) {
        List<Car> all = findAll();
        List<Car> carList = new ArrayList<>();
        if (all.size()!= 0){
            for (Car car : all) {
                if (car.getUserId() != null) {
                    if (car.getUserId().equals(userId)){
                        carList.add(car);
                    }
                }
            }
            return carList;
        }
        return null;
    }


    @Override
    public Car findOne(String carId) {
        CarEntity carEntity = CarDao.findOneCar(carId);
        return BeanMapper.map(carEntity, Car.class);
    }



    @Override
    public int createCar(Car car){
        CarEntity carEntity = BeanMapper.map(car, CarEntity.class);
        return CarDao.createCar(carEntity);
    }

    @Override
    public int updateCar(Car car){
        CarEntity carEntity = BeanMapper.map(car, CarEntity.class);
        return CarDao.updateCar(carEntity);
    }

    @Override
    public int updateCar(String carId,int type ,String remark){
        Car car = findOne(carId);
        car.setCarState(type);
        car.setCarRemark(remark);
        return updateCar(car);
    }


    @Override
    public int deleteCar(String carId) {
        return CarDao.deleteCar(carId);
    }

    /**
     * 接受图片
     * @param file 文件
     * @param request 请求信息
     * @return 接收状态
     */
    public int uploadImg(MultipartFile file, HttpServletRequest request){
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = "D:/IDEA/毕业设计/car/img";
        logger.info(filePath+"/"+fileName);
        if (file.isEmpty()) {
            return 0;
        }
        try {
            uploadFile(file.getBytes(), filePath, fileName);
        } catch (IOException e) {
            //new GlobalExceptionHandle().errorHandler(e);
            return 0;
        }
        return 1;
    }

    public List<Car> findAllCar(){
        List<Car> all = findAll();
        List<Car> cars = new ArrayList<>();
        if (all.size() != 0){
            for (Car car : all) {
                if (car.getCarState() == 0){
                    cars.add(car);
                }
            }
            return cars;
        }
        return null;
    }

    public int update(String carId ,String remake ){
        return 0;
    }

    /**
     * 转换二进制
     * @param file 文件流
     * @param filePath 文件地址
     * @param fileName 文件名
     * @throws IOException 转换异常
     */
    public void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            boolean mkdirs = targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath +"/"+ fileName);
            out.write(file);
            out.flush();
            out.close();
    }
}
