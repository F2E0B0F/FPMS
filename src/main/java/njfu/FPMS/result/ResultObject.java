package njfu.FPMS.result;

import java.util.HashMap;
import java.util.Map;

public class ResultObject {
	private Boolean isSuccess;
	private Integer status;
	private String messageString;
	
	private Map<String, Object> data = new HashMap<String, Object>();
	
	private ResultObject() {};
	
	public static ResultObject ok() {
		ResultObject resultObject = new ResultObject();
		resultObject.setIsSuccess(ResultCodeEnum.SUCCESS.isSuccess());
		resultObject.setStatus(ResultCodeEnum.SUCCESS.getStatusCode());
		resultObject.setMessageString(ResultCodeEnum.SUCCESS.getMessageString());
		
		return resultObject;
	}

	public static ResultObject error() {
		ResultObject resultObject = new ResultObject();
		resultObject.setIsSuccess(ResultCodeEnum.UNKNOWN_ERROR.isSuccess());
		resultObject.setStatus(ResultCodeEnum.UNKNOWN_ERROR.getStatusCode());
		resultObject.setMessageString(ResultCodeEnum.UNKNOWN_ERROR.getMessageString());
		
		return resultObject;
	}

	public static ResultObject setResult(ResultCodeEnum result) {
		ResultObject resultObject = new ResultObject();
		resultObject.setIsSuccess(result.isSuccess());
		resultObject.setStatus(result.getStatusCode());
		resultObject.setMessageString(result.getMessageString());
		
		return resultObject;
	}
	
	public ResultObject data(Map<String, Object> dataMap) {
		this.data = dataMap;
		return this;
	}
	
	public ResultObject data(String keyString, Object valueObject) {
		this.data.put(keyString, valueObject);
		return this;
	}
	
	public ResultObject message(String messageString) {
		this.setMessageString(messageString);
		return this;
	}
	
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageString() {
		return messageString;
	}

	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
