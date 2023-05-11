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
@TableName("LoanInfo")
@ApiModel(value = "LoanInfo对象", description = "")
@AllArgsConstructor

public class LoanInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("loan_idnum")
    private String loanIdnum;

    @TableField("loan_identify")
    private String loanIdentify;

    @TableField("loan_is_bonus")
    private String loanIsBonus;

    @TableField("loan_property")
    private Integer loanProperty;

    @TableField("loan_rela1_info")
    private String loanRela1Info;

    @TableField("loan_rela1")
    private String loanRela1;

    @TableField("loan_rela2_info")
    private String loanRela2Info;

    @TableField("loan_rela2")
    private String loanRela2;

    @TableField("loan_rela3_info")
    private String loanRela3Info;

    @TableField("loan_rela3")
    private String loanRela3;

    @TableField("loan_rela4_info")
    private String loanRela4Info;

    @TableField("loan_rela4")
    private String loanRela4;

    @TableField("loan_rela5_info")
    private String loanRela5Info;

    @TableField("loan_rela5")
    private String loanRela5;


}
