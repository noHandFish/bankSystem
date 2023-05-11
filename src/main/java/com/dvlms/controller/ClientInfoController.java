package com.dvlms.controller;


import com.dvlms.common.lang.Result;
import com.dvlms.entity.ClientContract;
import com.dvlms.entity.ClientInfo;
import com.dvlms.service.ClientContractService;
import com.dvlms.service.ClientInfoService;
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
@RequestMapping("/client-info")
public class ClientInfoController<T> {
    @Autowired
    private ClientInfoService clientInfoService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(clientInfoService.list());
    }

    //查询id
    @GetMapping("/info/{cli_idnum}")
    public Object info(@PathVariable("cli_idnum") String cli_idnum) {
        return new Result<>(clientInfoService.getById(cli_idnum));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody ClientInfo clientInfo) {
        clientInfoService.save(clientInfo);
        return new Result<>(clientInfo);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody ClientInfo clientInfo) {
        clientInfoService.updateById(clientInfo);
        return new Result<>(clientInfo);
    }

    //删除 根据id
    @PostMapping("/delete/{cli_idnum}")
    public Object deleteUsers(@PathVariable("cli_idnum") String cli_idnum) {
        clientInfoService.removeById(cli_idnum);
        return new Result<>(clientInfoService.list());
    }
}
