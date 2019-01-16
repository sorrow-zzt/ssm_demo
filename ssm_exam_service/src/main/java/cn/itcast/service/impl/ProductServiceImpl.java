package cn.itcast.service.impl;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Ids;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(List<Ids> idsList) {
        List<String> list = new ArrayList<>();
        for (Ids ids : idsList) {
            if(ids.getIds()!=null&&!"null".equals(ids.getIds())){
                list.add(ids.getIds());
            }
        }
        System.out.println(list);
        productDao.delete(list);
    }
}
