package com.api.carapi.service;


import com.api.carapi.model.Car;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CarService {

    /**
     * 查询所有汽车信息
     * @return 汽车信息集合
     */
    List<Car> findAll() ;

    /**
     * 按照类型查询车辆
     * @param type 车辆类型
     * @return 车辆信息
     */
    List<Car> findAll(int type);

    /**
     * 查询名下车辆
     * @param userId 用户id
     * @return 车辆
     */
    List<Car> findAllCar(String userId);

    /**
     * 查询所有待审核车辆
     * @return 待审核车辆
     */
    List<Car> findAllCar();
    /**
     *
     * 根据id查询汽车信息
     * @param carId 汽车ID
     * @return 汽车信息
     */
    Car findOne(String carId)  ;

    /**
     * 创建汽车
     * @param car 汽车信息
     * @return 受影响行
     */
    int createCar(Car car) ;

    /**
     * 更新汽车信息
     * @param car 汽车信息
     * @return 受影响行
     */
    int  updateCar(Car car) ;

    /**
     * 审核车辆
     * @param carId 车辆id
     * @param remark 审核信息
     * @param type 审核类型
     * @return 审核状态
     */
    int updateCar(String carId,int type ,String remark);

    /**
     * 删除汽车
     * @param carId 汽车id
     * @return 受影响行
     */
    int  deleteCar(String carId);

    /**
     * 接收图片
     * @param file 文件
     * @param request 请求信息
     * @return 接收状态
     */
    int uploadImg(MultipartFile file, HttpServletRequest request);
}
