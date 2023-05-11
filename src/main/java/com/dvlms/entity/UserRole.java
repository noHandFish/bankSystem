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
 * @since 2022-03-07
 */
@Getter
@Setter
@TableName("UserRole")
@ApiModel(value = "UserRole对象", description = "")
@AllArgsConstructor
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("username")
    private String userName;

    @TableField("password")
    private String password;

    @ApiModelProperty("权限授权(senior-manager,middle-manager,marketer,extension-worker)")
    @TableField("perms")
    private String perms;


}
