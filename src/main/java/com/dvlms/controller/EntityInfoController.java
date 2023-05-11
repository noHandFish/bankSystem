package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.ClientInfo;
import com.dvlms.entity.EntityInfo;
import com.dvlms.service.ClientInfoService;
import com.dvlms.service.EntityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhb
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/entity-info")
public class EntityInfoController<T> {
    @Autowired
    private EntityInfoService entityInfoService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(entityInfoService.list());
    }

    //查询id
    @GetMapping("/info/{en_idnum}")
    public Object info(@PathVariable("en_idnum") String en_idnum) {
        return new Result<>(entityInfoService.getById(en_idnum));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody EntityInfo entityInfo) {
        entityInfoService.save(entityInfo);
        return new Result<>(entityInfo);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody EntityInfo entityInfo) {
        entityInfoService.updateById(entityInfo);
        return new Result<>(entityInfo);
    }

    //删除 根据id
    @PostMapping("/delete/{en_idnum}")
    public Object deleteUsers(@PathVariable("en_idnum") String en_idnum) {
        entityInfoService.removeById(en_idnum);
        return new Result<>(entityInfoService.list());
    }
}
