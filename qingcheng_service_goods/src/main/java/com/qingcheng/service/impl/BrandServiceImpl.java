package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    //品牌展示功能
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    //分页展示功能
    @Override
    public PageResult<Brand> findPage(int pag, int size) {
        PageHelper.startPage(pag, size);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectAll();
        System.out.println("测试git");
        return new PageResult<>(brands.getTotal(), brands.getResult());//brands.getResult()=rows分页所有的信息
    }

    //条件查询
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return brandMapper.selectByExample(example);//根据条件查询
    }


    //条件查询+分页
    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int pag, int size) {
        PageHelper.startPage(pag, size);
        Example example = createExample(searchMap);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);//根据条件查询；类型强转为Page<Brand>为了给下面封装的结果集使用
        return new PageResult<>(brands.getTotal(), brands.getResult());
    }

    /**
     *  构建查询条件       
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//criteria给查询条件Example设置值

        if (searchMap != null) {
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andEqualTo("letter", searchMap.get("letter"));
            }
        }
        return example;
    }

    //根据id查询
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    //新增
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    //修改方法
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    //根据id删除方法
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }


}
