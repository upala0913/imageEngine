package com.upala.wong.entity;

import lombok.Data;

import java.util.Date;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/23 12:41
 *  @description 目录空间实体类
 *  @version 0.0.1
 ********************************/

@Data
public class DocumentSpace {

    // 目录标识码
    private String id;
    // 目录名称
    private String name;
    // 目录的index
    private Integer index;
    // 目录类型：0-图片，1-文件，2-视频，3-其他
    private Integer type;
    // 目录创建时间
    private Date createTime;
    // 删除标识符：0-未删除，1-删除
    private Integer delFlag;
    // 删除类型：0-逻辑删除，1-物理删除（彻底删除）
    private Integer delFlagType;

}
