package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.GuarantorInfo;
import com.dvlms.entity.LoanInfo;
import com.dvlms.service.GuarantorInfoService;
import com.dvlms.service.LoanInfoService;
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
@RequestMapping("/loan-info")
public class LoanInfoController<T> {
    @Autowired
    private LoanInfoService loanInfoService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(loanInfoService.list());
    }

    //查询id
    @GetMapping("/info/{loan_idnum}")
    public Object info(@PathVariable("loan_idnum") String loan_idnum) {
        return new Result<>(loanInfoService.getById(loan_idnum));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody LoanInfo loanInfo) {
        loanInfoService.save(loanInfo);
        return new Result<>(loanInfo);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody LoanInfo loanInfo) {
        loanInfoService.updateById(loanInfo);
        return new Result<>(loanInfo);
    }

    //删除 根据id
    @PostMapping("/delete/{loan_idnum}")
    public Object deleteUsers(@PathVariable("loan_idnum") String loan_idnum) {
        loanInfoService.removeById(loan_idnum);
        return new Result<>(loanInfoService.list());
    }
}
