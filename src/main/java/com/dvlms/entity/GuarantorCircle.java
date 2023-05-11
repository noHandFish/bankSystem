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
@TableName("GuarantorCircle")
@ApiModel(value = "GuarantorCircle对象", description = "")
@AllArgsConstructor

public class GuarantorCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("gua_idnum")
    private String guaIdnum;

    @TableField("gua_ship")
    private String guaShip;

    @TableField("gua_loan_balance")
    private Integer guaLoanBalance;

    @TableField("gua_total_amount")
    private Integer guaTotalAmount;

    @TableField("gua_total_balance")
    private Integer guaTotalBalance;

    @TableField("con_id")
    private String conId;


}
