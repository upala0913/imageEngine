package com.upala.wong.controller.common;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.upala.wong.common.ResponseCommon;
import com.upala.wong.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/22 11:04
 *  @description 城市相关的类
 *  @version 0.0.1
 ********************************/

@RestController
@RequestMapping("/city")
@Log4j2
@Api(value = "城市信息的API", tags = "城市信息的API")
@ApiResponses({
    @ApiResponse(code = 200, message = "请求成功"),
    @ApiResponse(code = 3426, message = "请求失败")
})
public class CityController {

    @Resource
    private CityService cityService;

    /**
     * 获取城市信息
     * @param param 父级ID
     * @return 返回值
     */
    @ApiOperation(value = "获取城市信息", notes = "获取城市信息")
    @ApiOperationSupport(
        author = "upala",
        params = @DynamicParameters(properties = {
            @DynamicParameter(name = "id", value = "父级标识码ID", dataTypeClass = String.class)
        })
    )
    @RequestMapping(value = "/getCity", method = RequestMethod.POST)
    public ResponseCommon getCity(@RequestBody Map<String, Object> param) {
        Object data = param.get("id");
        if (data == null)
            return ResponseCommon.responseFail("城市标识码为空", Collections.emptyList());
        Integer parentId = Integer.parseInt(data.toString());
        return ResponseCommon.responseSuccess("查询数据成功", cityService.queryProvinces(parentId));
    }

}
