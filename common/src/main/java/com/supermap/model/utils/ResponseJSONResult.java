package com.supermap.model.utils;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermap.model.config.StateType;

import java.util.List;

/**
 * @Title: ResponseJSONResult.java
 * 200：表示成功
 * 500：表示错误，错误信息在responseMsg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */
public class ResponseJSONResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 成果 : 1
	 */
	private static final int SUCCESS_STATUS = 1;
	
	/**
	 * 失败: -1
	 */
	private static final int ERROR_STATUS = -1;
	
	/**
	 * 异常: -2
	 */
	private static final int EXCEPTION_STATUS = -2;
	
	private static final String SUCCESS_MSG = "success";
	
	private static final String ERROR_MSG = "error";
	
    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer responseCD;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 响应中的数据
     */
    private Object data;

    private String totalCount;
    
    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public ResponseJSONResult() {

    }

    public ResponseJSONResult(Integer responseCD, String responseMsg, Object data, String totalCount) {
        this.responseCD = responseCD;
        this.responseMsg = responseMsg;
        this.data = data;
        this.totalCount = totalCount;
    }

    public ResponseJSONResult(Integer responseCD, String responseMsg, Object data) {
        this.responseCD = responseCD;
        this.responseMsg = responseMsg;
        this.data = data;
    }
    
    public ResponseJSONResult(Integer responseCD, String responseMsg) {
        this.responseCD = responseCD;
        this.responseMsg = responseMsg;
    }
    
    public ResponseJSONResult(Object data) {
        this.responseCD = SUCCESS_STATUS;
        this.responseMsg = SUCCESS_MSG;
        this.data = data;
    }

    public ResponseJSONResult(Object data, String totalCount) {
        this.responseCD = SUCCESS_STATUS;
        this.responseMsg = SUCCESS_MSG;
        this.data = data;
        this.totalCount = totalCount;
    }

    public static ResponseJSONResult
    build(Integer responseCD, String responseMsg, Object data) {
        return new ResponseJSONResult
                (responseCD, responseMsg, data);
    }

    public static ResponseJSONResult
    build(Integer responseCD, String responseMsg, Object data, String totalCount) {
        return new ResponseJSONResult
                (responseCD, responseMsg, data, totalCount);
    }

    public static ResponseJSONResult
    ok(Object data) {
        return new ResponseJSONResult
                (data);
    }
    public static ResponseJSONResult
    ok(Object data, String totalCount) {
        return new ResponseJSONResult
                (data, totalCount);
    }
    public static ResponseJSONResult
    ok(Integer responseCD,String responseMsg) {
        return new ResponseJSONResult
                (responseCD, responseMsg);
    }
    public static ResponseJSONResult
    ok(StateType stateType) {
        return new ResponseJSONResult(stateType.getCode(),stateType.getValue());
    }
    public static ResponseJSONResult
    ok(StateType stateType,Object data,String totalCount) {
        return new ResponseJSONResult(stateType.getCode(),stateType.getValue(),data,totalCount);
    }
    public static ResponseJSONResult
    ok() {
        return new ResponseJSONResult
                (null);
    }
    public static ResponseJSONResult
    errorMsg(String responseMsg) {
        return new ResponseJSONResult
                (ERROR_STATUS, responseMsg, null);
    }

    public static ResponseJSONResult
    errorMap(Object data) {
        return new ResponseJSONResult
                (ERROR_STATUS, ERROR_MSG, data);
    }

    public static ResponseJSONResult
    errorTokenMsg(String responseMsg) {
        return new ResponseJSONResult
                (ERROR_STATUS, responseMsg, null);
    }

    public static ResponseJSONResult
    errorException(String responseMsg) {
        return new ResponseJSONResult
                (EXCEPTION_STATUS, responseMsg, null);
    }

    /**
     * @param jsonData
     * @param clazz
     * @return
     * @Description: 将json结果集转化为ResponseJSONResult对象
     * 需要转换的对象是一个类
     */
    public static ResponseJSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResponseJSONResult
                        .class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("responseCD").intValue(), jsonNode.get("responseMsg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param json
     * @return
     * @Description: 没有object对象的转化
     */
    public static ResponseJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, ResponseJSONResult
                    .class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonData
     * @param clazz
     * @return
     * @Description: Object是集合转化
     * 需要转换的对象是一个list
     */
    public static ResponseJSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("responseCD").intValue(), jsonNode.get("responseMsg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isOK() {
        return this.responseCD == SUCCESS_STATUS;
    }

    public Integer getResponseCD() {
        return responseCD;
    }

    public void setResponseCD(Integer responseCD) {
        this.responseCD = responseCD;
    }

    public String getresponseMsg() {
        return responseMsg;
    }

    public void setresponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
