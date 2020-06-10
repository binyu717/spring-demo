package cn.yu.demo.client.model;

/**
 * response msg
 *
 * @author bin.yu
 * @create 2020-02-23 15:50
 * @info best wishes no bug
 **/
public class ResponseMsg<T> {

    /* 是否成功,必选 */
    private Boolean succ;

    /* 业务数据,可选 */
    private T data;

    /* 业务错误码,可选 */
    private String code;

    /* 详细错误信息,可选 */
    private String message;

    /* 当前第几页,可选 */
    private Integer pageIndex;

    /* 每页多少条记录,可选 */
    private Integer pageSize;

    /* 总共多少页,可选 */
    private Long pages;

    /* 总共多少条记录,可选 */
    private Long total;

    public ResponseMsg<T> success() {
        succ = true;
        return this;
    }

    public ResponseMsg<T> success(String msg) {
        succ = true;
        message = msg;
        return this;
    }

    public ResponseMsg<T> success(String msg, T data) {
        succ = true;
        message = msg;
        this.data = data;
        return this;
    }

    public ResponseMsg<T> fail() {
        succ = false;
        return this;
    }

    public ResponseMsg<T> fail(String msg) {
        succ = false;
        message = msg;
        return this;
    }
    public ResponseMsg<T> setSuccess(Boolean success) {
        succ = success;
        return this;
    }
    public ResponseMsg<T> setData(T data) {
        this.data = data;
        return this;
    }
    public ResponseMsg<T> setCode(String code) {
        this.code = code;
        return this;
    }
    public ResponseMsg<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public static <T> ResponseMsg<T> buildSuccess() {
        return build(null, null, true, null);
    }

    public static <T> ResponseMsg<T> buildSuccess(String msg) {
        return build(null, msg, true, null);
    }

    public static <T> ResponseMsg<T> buildSuccess(String msg, T data) {
        return build(null, msg, true, data);
    }

    public static <T> ResponseMsg<T> buildFail(String code, String msg) {
        return build(code, msg, false, null);
    }

    private static <T> ResponseMsg<T> build(String code, String msg, boolean success, T data) {
        return new ResponseMsg<T>().setSuccess(success).setCode(code).setMessage(msg).setData(data);
    }
}
