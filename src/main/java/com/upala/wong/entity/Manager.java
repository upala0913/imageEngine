package com.upala.wong.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-06 19:18
 *  @description
 ********************************/

@Data
public class Manager {

    @ApiModelProperty("用户ID，必须唯一")
    private Integer id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户登录密码")
    private String userPass;
    @ApiModelProperty("用户电话")
    private String mobile;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户头像")
    private String photo;
    @ApiModelProperty("用户真实姓名")
    private String reName;

}
