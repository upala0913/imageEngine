package com.upala.wong.mapper.image;

import com.upala.wong.entity.DocumentSpace;
import com.upala.wong.utils.ExceptionUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/23 19:34
 *  @description 图片空间设置
 *  @version 0.0.1
 ********************************/

public interface ImageSpaceSettingMapper {

    List<DocumentSpace> queryDocumentFolder(@Param("delFlag") Integer delFlag) throws ExceptionUtils;

}
