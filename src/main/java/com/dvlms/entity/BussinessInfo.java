package com.dvlms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
@TableName("BussinessInfo")
@ApiModel(value = "BussinessInfo∂‘œÛ", description = "")
@AllArgsConstructor
public class BussinessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("con_id")
    private String conId;

    @TableField("con_account")
    private String conAccount;

    @TableField("con_start")
    private LocalDate conStart;

    @TableField("con_end")
    private LocalDate conEnd;

    @TableField("con_duration")
    private Integer conDuration;

    @TableField("con_amount")
    private Integer conAmount;

    @TableField("con_balance")
    private Integer conBalance;

    @TableField("con_type")
    private String conType;

    @TableField("con_rate")
    private BigDecimal conRate;

    @TableField("con_rate_type")
    private String conRateType;

    @TableField("con_auto_amount")
    private Integer conAutoAmount;

    @TableField("con_pass_amount")
    private Integer conPassAmount;

    @TableField("con_direct")
    private String conDirect;

    @TableField("con_use")
    private String conUse;

    @TableField("con_date")
    private LocalDate conDate;

    @TableField("con_way")
    private String conWay;

    @TableField("con_nature")
    private String conNature;

    @TableField("con_return")
    private Integer conReturn;

    @TableField("con_return_plan")
    private String conReturnPlan;

    @TableField("con_buss_class")
    private String conBussClass;

    @TableField("con_com_class")
    private String conComClass;

    @TableField("con_class")
    private String conClass;

    @TableField("con_loan_class")
    private String conLoanClass;


}
