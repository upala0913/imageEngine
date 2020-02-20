package com.upala.wong.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/********************************
 *  @program image
 *  @author 王鹏
 *  @since 2020/2/15 14:33
 *  @description 返回公共类
 *  @version 0.0.1
 ********************************/

@Data
@AllArgsConstructor
@ApiModel
public class ResponseCommon {

    @ApiModelProperty(value = "响应码", dataType = "Integer")
    private Integer code;
    @ApiModelProperty(value = "响应信息", dataType = "String")
    private String message;
    @ApiModelProperty(value = "响应状态", dataType = "boolean")
    private boolean status;
    @ApiModelProperty(value = "响应数据", dataType = "Object")
    private Object data;

    public ResponseCommon(int code, String message, boolean status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    /**
     * 成功返回，带数据
     * @param obj 数据
     * @return 返回map集合
     */
    public static ResponseCommon responseSuccess(String msg, Object obj) {
        return new ResponseCommon(FinalVarCommon.RESULT_SUCCESS_CODE, msg, FinalVarCommon.RESULT_SUCCESS_FLAG, obj);
    }

    /**
     * 成功返回，不带数据
     * @return 返回map集合
     */
    public static ResponseCommon responseSuccess(String message) {
        return new ResponseCommon(FinalVarCommon.RESULT_SUCCESS_CODE, message, FinalVarCommon.RESULT_SUCCESS_FLAG);
    }

    /**
     * 错误返回
     * @param message 错误信息
     * @return 返回map集合
     */
    public static ResponseCommon responseFail(String message) {
        return new ResponseCommon(FinalVarCommon.RESULT_SUCCESS_CODE, message, FinalVarCommon.RESULT_FAIL_FLAG);
    }

    /**
     * 错误返回
     * @param message 错误信息
     * @return 返回map集合
     */
    public static ResponseCommon responseFail(String message, Object data) {
        return new ResponseCommon(FinalVarCommon.RESULT_SUCCESS_CODE, message, FinalVarCommon.RESULT_FAIL_FLAG, data);
    }

}
