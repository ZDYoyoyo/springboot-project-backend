package com.zdy.pojo;


//统一响应结果

public class Result<T> {
    private Integer code; // 業務狀態碼  0-成功  1-失敗
    private String message; // 提示信息
    private T data; // 響應數據


    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    // 快速返回操作成功響應結果(帶響應數據)
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }
    // 快速返回操作成功響應結果
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
