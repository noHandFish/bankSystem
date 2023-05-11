package com.dvlms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhb
 * @since 2022-03-05
 */
@Getter
@Setter
@AllArgsConstructor
@TableName("ClientContract")
@ApiModel(value = "ClientContract对象", description = "")
public class ClientContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("cli_name")
    private String cliName;

    @TableId("cli_idnum")
    private String cliIdnum;

    @TableField("con_id")
    private String conId;


}
