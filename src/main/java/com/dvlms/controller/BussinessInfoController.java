package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.BussinessInfo;
import com.dvlms.mapper.BussinessInfoMapper;
import com.dvlms.service.BussinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhb
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/bussiness-info")
public class BussinessInfoController<T> {
    @Autowired
    private BussinessInfoService bussinessInfoService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(bussinessInfoService.list());
    }

    //查询id
    @GetMapping("/info/{con_id}")
    public Object info(@PathVariable("con_id") String con_id) {
        return new Result<>(bussinessInfoService.getById(con_id));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody BussinessInfo bussinessInfo) {
        bussinessInfoService.save(bussinessInfo);
        return new Result<>(bussinessInfo);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody BussinessInfo bussinessInfo) {
        bussinessInfoService.updateById(bussinessInfo);
        return new Result<>(bussinessInfo);
    }

    //删除 根据id
    @PostMapping("/delete/{con_id}")
    public Object deleteUsers(@PathVariable("con_id") String con_id) {
        bussinessInfoService.removeById(con_id);
        return new Result<>(bussinessInfoService.list());
    }
}
