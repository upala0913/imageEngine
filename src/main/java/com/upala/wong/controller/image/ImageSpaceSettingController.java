package com.upala.wong.controller.image;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.service.image.ImageSpaceSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/23 20:16
 *  @description
 *  @version 0.0.1
 ********************************/

@RestController
@RequestMapping("/image")
@Log4j2
@Api(value = "图片空间设置API", tags = "图片空间设置API")
@ApiResponses({
    @ApiResponse(code = 200, message = "请求成功"),
    @ApiResponse(code = 3426, message = "请求失败")
})
public class ImageSpaceSettingController {

    @Resource
    private ImageSpaceSettingService imageSpaceSettingService;

    @RequestMapping(value = "queryDocumentFolder", method = RequestMethod.POST)
    @ApiOperation(value = "查询图片目录文件夹", notes = "查询图片目录文件夹")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(name = "param", properties = {
            @DynamicParameter(name = "delFlag", value = "文件夹删除标记", dataTypeClass = Integer.class)
        })
    )
    public ResponseCommon queryDocumentFolder(@RequestBody Map<String, Object> param) {
        Object data = param.get("delFlag");
        if (data == null)
            return ResponseCommon.responseFail("目录文件夹删除标记为空");
        int delFlag = Integer.parseInt(data.toString());
        return imageSpaceSettingService.queryDocumentFolder(delFlag);
    }

}
