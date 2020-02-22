package com.upala.wong.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/22 11:59
 *  @description 省份城市实体类
 *  @version 0.0.1
 ********************************/

@Data
@ApiModel
public class Province {

    @ApiModelProperty(value = "城市标识码", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(value = "城市名称", dataType = "String")
    private String name;
    @ApiModelProperty(value = "父级标识码", dataType = "Integer")
    private Integer parentId;
    @ApiModelProperty(value = "父级名称", dataType = "String")
    private String parentName;
    @ApiModelProperty(value = "区域标识码", dataType = "String")
    private String areaCode;
    @ApiModelProperty(value = "邮编", dataType = "String")
    private String zipCode;
    @ApiModelProperty(value = "区域等级", dataType = "Integer")
    private Integer depth;
    @ApiModelProperty(value = "标识码", dataType = "Integer")
    private Integer ident;

}
