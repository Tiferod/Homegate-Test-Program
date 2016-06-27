package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message{
	
	private String error, message, path, error_code;
	private int status;

	
	public Message() {
			
	}	  
		  
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
    @Override
    public String toString() {
        return "\n \n status = " + status +
                "\n error = " + error +
                "\n message = " + message +
                "\n path = " + path +
                "\n error_code = " + error_code +
                '\n';
    }
    
}
