package com.posts.PostsMicroService.DTO;

import org.json.simple.JSONObject;

import java.util.List;

public class ResponseDto {

    String message;
    Integer statusCode;
    Boolean status;
    List<JSONObject> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }

    public void setVariables(Boolean status, Integer code, String message){

        this.setStatusCode(code);
        this.setStatus(status);
        this.setMessage(message);
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", status=" + status +
                '}';
    }
}
