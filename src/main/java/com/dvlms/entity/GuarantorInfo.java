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
@TableName("GuarantorInfo")
@ApiModel(value = "GuarantorInfo对象", description = "")
@AllArgsConstructor

public class GuarantorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("con_id")
    private String conId;

    @TableField("gua1_name")
    private String gua1Name;

    @TableField("gua1_idnum")
    private String gua1Idnum;

    @TableField("gua1_ship")
    private String gua1Ship;

    @TableField("gua2_name")
    private String gua2Name;

    @TableField("gua2_idnum")
    private String gua2Idnum;

    @TableField("gau2_ship")
    private String gau2Ship;

    @TableField("sub1_name")
    private String sub1Name;

    @TableField("sub1_idnum")
    private String sub1Idnum;

    @TableField("sub1_is_rela")
    private String sub1IsRela;

    @TableField("sub2_name")
    private String sub2Name;

    @TableField("sub2_idnum")
    private String sub2Idnum;

    @TableField("sub2_is_rela")
    private String sub2IsRela;

    @TableField("sub3_name")
    private String sub3Name;

    @TableField("sub3_idnum")
    private String sub3Idnum;

    @TableField("sub3_is_rela")
    private String sub3IsRela;


}
