package com.dvlms.controller;


import ch.qos.logback.core.net.server.Client;
import com.dvlms.common.lang.Result;
import com.dvlms.entity.BussinessInfo;
import com.dvlms.entity.ClientContract;
import com.dvlms.service.BussinessInfoService;
import com.dvlms.service.ClientContractService;
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
@RequestMapping("/client-contract")
public class ClientContractController<T> {
    @Autowired
    private ClientContractService clientContractService;

    //查询所有
    @GetMapping("/list")
    public Object list() {
        return new Result<>(clientContractService.list());
    }

    //查询id
    @GetMapping("/info/{cli_idnum}")
    public Object info(@PathVariable("cli_idnum") String cli_idnum) {
        return new Result<>(clientContractService.getById(cli_idnum));
    }

    //添加
    @PostMapping("/save")
    public Object save(@Validated @RequestBody ClientContract clientContract) {
        clientContractService.save(clientContract);
        return new Result<>(clientContract);
    }

    //修改 根据id
    @PostMapping("/update")
    public Object update(@Validated @RequestBody ClientContract clientContract) {
        clientContractService.updateById(clientContract);
        return new Result<>(clientContract);
    }

    //删除 根据id
    @PostMapping("/delete/{cli_idnum}")
    public Object deleteUsers(@PathVariable("cli_idnum") String cli_idnum) {
        clientContractService.removeById(cli_idnum);
        return new Result<>(clientContractService.list());
    }
}
