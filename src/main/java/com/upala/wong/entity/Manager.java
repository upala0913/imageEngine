package com.upala.wong.entity;

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

    private Integer id;
    private String userName;
    private String userPass;
    private String mobile;
    private String email;
    private String photo;
    private String reName;

}
