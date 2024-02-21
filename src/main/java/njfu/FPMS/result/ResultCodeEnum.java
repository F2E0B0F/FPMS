package njfu.FPMS.result;

public enum ResultCodeEnum {
	SUCCESS(true, 20000, "SUCCESS"),
	UNKNOWN_ERROR(false, 20001, "UNKNOWN_ERROR"),
	PARAM_ERROR(false, 20002, "PARAMETER_ERROR");
	
	private boolean isSuccess;
	private int statusCode;
	private String messageString;
	
	private ResultCodeEnum(boolean isSuccess, int statusCode, String messageString) {
		this.isSuccess = isSuccess;
		this.statusCode = statusCode;
		this.messageString = messageString;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessageString() {
		return messageString;
	}
}
