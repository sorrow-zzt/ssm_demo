package cn.itcast.service;

import cn.itcast.domain.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(Integer page, Integer pageSize);

    Orders findById(String id);
}
