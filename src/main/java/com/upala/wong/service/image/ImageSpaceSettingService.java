package com.upala.wong.service.image;

import com.upala.wong.common.ResponseCommon;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/23 19:55
 *  @description 查询文件目录
 *  @version 0.0.1
 ********************************/

public interface ImageSpaceSettingService {

    ResponseCommon queryDocumentFolder(Integer delFlag);

}
