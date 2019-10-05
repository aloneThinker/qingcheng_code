package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@RequestMapping("/brand")
public class BrandController {
    @Reference//远程注入接口
    private BrandService brandService;

    //品牌展示
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
       // System.out.println("");
        return brandService.findAll();
    }

    //分页展示
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page, size);
    }

    //条件查询
    @RequestMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String, Object> searchMap) {
        return brandService.findList(searchMap);
    }

    //条件+分页
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap, int pag, int size) {

        return brandService.findPage(searchMap, pag, size);
    }

    //根据ID查询
    @RequestMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result();
    }

    //修改方法
    @PostMapping("/update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return new Result();
    }

    @RequestMapping("delete")
    //根据id删除方法
    public Result delete(Integer id){
        brandService.delete(id);
        return new Result();
    }
}
