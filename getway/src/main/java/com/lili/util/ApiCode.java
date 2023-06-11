package com.lili.util;

/**
 * REST API 响应码
 *
 * @author wangjiao
 * @since 2020/11/14
 */
public enum ApiCode {

    /**
     * 系统级Code: 5000内
     */
    SUCCESS(200, "操作成功"),
    UNAUTHORIZED(401, "非法访问"),
    NOT_PERMISSION(403, "没有权限"),
    NOT_FOUND(404, "你请求的资源不存在"),
    FAIL(500, "操作失败"),
    LOGIN_EXCEPTION(4000, "登陆失败"),
    SYSTEM_EXCEPTION(5000, "系统异常"),
    /**
     * 参数校验级Code: 5001 - 6000
     */
    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),
    /**
     * 业务级Code: 6001 - 7000
     */
    USER_UNAUTHORIZED(6001, "未授权，请先授权再访问"),
    USER_TWO_PASSWORDS_INCONSISTENT(6002, "两次输入密码不一致"),
    USER_ACCOUNT_REGISTERED(6003, "该账号已被注册"),
    ;
    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
