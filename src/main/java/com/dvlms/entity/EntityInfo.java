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
@TableName("EntityInfo")
@ApiModel(value = "EntityInfo对象", description = "")
@AllArgsConstructor

public class EntityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("en_name")
    private String enName;

    @TableId("en_idnum")
    private String enIdnum;

    @TableField("en_identify")
    private String enIdentify;

    @TableField("en_status")
    private String enStatus;

    @TableField("en_region")
    private String enRegion;

    @TableField("en_street")
    private String enStreet;

    @TableField("en_village")
    private String enVillage;

    @TableField("en_annual_income")
    private Integer enAnnualIncome;


}
