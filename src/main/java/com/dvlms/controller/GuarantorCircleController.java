package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.EntityInfo;
import com.dvlms.entity.GuarantorCircle;
import com.dvlms.service.EntityInfoService;
import com.dvlms.service.GuarantorCircleService;
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
@RequestMapping("/guarantor-circle")
public class GuarantorCircleController<T> {
    @Autowired
    private GuarantorCircleService guarantorCircleService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(guarantorCircleService.list());
    }

    //查询id
    @GetMapping("/info/{gua_idnum}")
    public Object info(@PathVariable("gua_idnum") String gua_idnum) {
        return new Result<>(guarantorCircleService.getById(gua_idnum));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody GuarantorCircle guarantorCircle) {
        guarantorCircleService.save(guarantorCircle);
        return new Result<>(guarantorCircle);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody GuarantorCircle guarantorCircle) {
        guarantorCircleService.updateById(guarantorCircle);
        return new Result<>(guarantorCircle);
    }

    //删除 根据id
    @PostMapping("/delete/{gua_idnum}")
    public Object deleteUsers(@PathVariable("gua_idnum") String gua_idnum) {
        guarantorCircleService.removeById(gua_idnum);
        return new Result<>(guarantorCircleService.list());
    }
}
