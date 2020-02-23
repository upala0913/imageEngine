package com.upala.wong.service.image.impl;

import com.upala.wong.common.ResponseCommon;
import com.upala.wong.entity.DocumentSpace;
import com.upala.wong.mapper.image.ImageSpaceSettingMapper;
import com.upala.wong.service.image.ImageSpaceSettingService;
import com.upala.wong.utils.ExceptionUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/23 19:56
 *  @description
 *  @version 0.0.1
 ********************************/

@Service
@Log4j2
public class ImageSpaceSettingServiceImpl implements ImageSpaceSettingService {

    @Resource
    private ImageSpaceSettingMapper imageSpaceSettingMapper;

    @Override
    public ResponseCommon queryDocumentFolder(Integer delFlag, Integer index, Integer limit) {
        List<DocumentSpace> spaceList = null;
        Integer start = (index - 1) * limit;
        Integer total = null;
        try {
            spaceList = imageSpaceSettingMapper.queryDocumentFolder(delFlag, start, limit);
            total = imageSpaceSettingMapper.getTotal(delFlag);
        } catch (ExceptionUtils e) {
            log.error("查询数据出现异常：{}", e.getMessage());
            return ResponseCommon.responseFail("查询数据竖线异常");
        }
        return ResponseCommon.responseSuccess("查询数据成功", spaceList, total);
    }

}
