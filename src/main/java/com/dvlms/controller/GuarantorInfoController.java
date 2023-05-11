package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.GuarantorCircle;
import com.dvlms.entity.GuarantorInfo;
import com.dvlms.service.GuarantorCircleService;
import com.dvlms.service.GuarantorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhb
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/guarantor-info")
public class GuarantorInfoController<T> {
    @Autowired
    private GuarantorInfoService guarantorInfoService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(guarantorInfoService.list());
    }

    //查询id
    @GetMapping("/info/{con_id}")
    public Object info(@PathVariable("con_id") String con_id) {
        return new Result<>(guarantorInfoService.getById(con_id));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody GuarantorInfo guarantorInfo) {
        guarantorInfoService.save(guarantorInfo);
        return new Result<>(guarantorInfo);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody GuarantorInfo guarantorInfo) {
        guarantorInfoService.updateById(guarantorInfo);
        return new Result<>(guarantorInfo);
    }

    //删除 根据id
    @PostMapping("/delete/{con_id}")
    public Object deleteUsers(@PathVariable("con_id") String con_id) {
        guarantorInfoService.removeById(con_id);
        return new Result<>(guarantorInfoService.list());
    }
}
