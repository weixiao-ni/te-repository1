package com.example.hello_springboot.VO;

import java.util.Collections;
import java.util.List;

import com.example.hello_springboot.enums.ResultCode;

public class CommonResult<T> {
	private Integer code;
	private DataWrapper<T> data;
	private String message;
	public static class DataWrapper<T> {
		public DataWrapper(T singleData) {
		    this.list = Collections.singletonList(singleData);
		}
        private List<T> list; // 这里的变量名（如 list）会决定 JSON 里的键名

        public List<T> getList() { return list; }
        public void setList(List<T> list) { this.list = list; }
    }

    public DataWrapper<T> getData() {
        return data;
    }

    public void setData(DataWrapper<T> data) {
        this.data = data;
    }
    
    // 省略 code 和 message 的 getter/setter
    public static <T> CommonResult<T> failed(ResultCode resultCode) {
		CommonResult<T> result = new CommonResult<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }
	public static <T> CommonResult<T> failed(ResultCode resultCode,String data) {
		CommonResult<T> result = new CommonResult<>();
		// 方案 A：如果你想把 String 强行塞进 T 类型的 List 里
	    // 加上 @SuppressWarnings 消除强转警告，这是标准做法
	    @SuppressWarnings("unchecked")
		DataWrapper<T> da = new DataWrapper<>((T) data);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(da);
        return result;
    }
	public static <T> CommonResult<T> success(ResultCode resultCode) {
		CommonResult<T> result = new CommonResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
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
	
	
}
