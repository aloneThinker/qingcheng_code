package com.qingcheng.service.goods;


import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    //品牌展示功能
    public List<Brand> findAll();

    //分页展示功能
    public PageResult<Brand> findPage(int pag, int size);

    //条件查询功能
    public List<Brand> findList(Map<String, Object> searchMap);

    //条件+分页查询
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int pag, int size);

    //根据id查询
    public Brand findById(Integer id);

    //新增方法
    public void add(Brand brand);

    //修改方法
    public void update(Brand brand);

    //删除方法
    public void delete(Integer id);
}
