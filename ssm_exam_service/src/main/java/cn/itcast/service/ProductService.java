package cn.itcast.service;

import cn.itcast.domain.Ids;
import cn.itcast.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Integer page, Integer pageSize);

    void save(Product product);

    void delete(List<Ids> idsList);
}
