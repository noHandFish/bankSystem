package com.dvlms.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PassDto implements Serializable {

    @NotBlank(message = "�˻�������Ϊ��")
    private String username;

    @NotBlank(message = "�����벻��Ϊ��")
    private String password;

    @NotBlank(message = "�����벻��Ϊ��")
    private String currentPass;

    @NotBlank(message = "�����벻��Ϊ��")
    private String passwordAgainInput;
}
