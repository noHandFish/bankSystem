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
@TableName("ClientInfo")
@ApiModel(value = "ClientInfo对象", description = "")
@AllArgsConstructor

public class ClientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("cli_id")
    private Long cliId;

    @TableField("cli_name")
    private String cliName;

    @TableId("cli_idnum")
    private String cliIdnum;

    @TableField("cli_gender")
    private String cliGender;

    @TableField("cli_village")
    private String cliVillage;

    @TableField("cli_address")
    private String cliAddress;

    @TableField("cli_worplace")
    private String cliWorplace;

    @TableField("cli_phone")
    private String cliPhone;

    @TableField("cli_marriage")
    private String cliMarriage;

    @TableField("cli_spouse")
    private String cliSpouse;

    @TableField("cli_spouse_idnum")
    private String cliSpouseIdnum;

    @TableField("cli_spouse_phone")
    private String cliSpousePhone;

    @TableField("cli_spouse_workspace")
    private String cliSpouseWorkspace;

    @TableField("cli_account")
    private String cliAccount;

    @TableField("cli_outlets")
    private String cliOutlets;

    @TableField("cli_deposit")
    private Integer cliDeposit;

    @TableField("cli_amount")
    private Integer cliAmount;

    @TableField("cli_balance")
    private Integer cliBalance;

    @TableField("cli_subbranch")
    private String cliSubbranch;

    @TableField("cli_manager")
    private String cliManager;


}
