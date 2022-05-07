package com.zhang.carservice.dao;



import com.zhang.carservice.entity.CarEntity;
import com.zhang.carservice.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 汽车信息表
 */

@Repository
public class CarDao {

    @Autowired
    CarMapper carMapper;

    /**
     * 查询所有汽车信息
     * null 代表查询所有
     * @return 汽车信息集合
     */
    public List<CarEntity> findAllCar(){
        return carMapper.selectList(null);
    }

    /**
     * 查询单个汽车信息
     * @param carId 汽车id
     * @return 汽车信息
     */
    public CarEntity findOneCar(String carId){
        return carMapper.selectById(carId);
    }

    /**
     * 创建汽车
     * @param carEntity 汽车信息
     * @return 执行状态
     */
    public int createCar(CarEntity carEntity){
        return  carMapper.insert(carEntity);
    }

    /**
     * 更新汽车信息
     * @param carEntity 汽车信息
     * @return 执行状态
     */
    public int updateCar(CarEntity carEntity){
        return  carMapper.updateById(carEntity);
    }

    /**
     * 删除汽车
     * @param carId 汽车id
     * @return 执行状态
     */
    public int deleteCar(String carId){
        return  carMapper.deleteById(carId);
    }
}
