package com.example.server.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String code;
    private String message;
    private String messageKh;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(String code, String message, String messageKh, Object data) {
        this.code = code;
        this.message = message;
        this.messageKh = messageKh;
        this.data = data;
    }

    public void getSuccess(Object object){
        this.code = "200";
        this.message = "Get Successfully";
        this.messageKh = "ទាញយកបានជោគជ័យ";
        this.data = object;
    }


    public void createSuccess(Object object){
        this.code = "200";
        this.message = "Create Successfully";
        this.messageKh = "បង្កើតបានជោគជ័យ";
        this.data = object;
    }

    public void updateSuccess(Object object){
        this.code = "200";
        this.message = "Update Successfully";
        this.messageKh = "កែប្រែជោគជ័យ";
        this.data = object;
    }

    public void deleteSuccess(Object object){
        this.code = "200";
        this.message = "Delete Successfully";
        this.messageKh = "លុបទិន្នន័យបានជោគជ័យ";
        this.data = object;
    }
}
