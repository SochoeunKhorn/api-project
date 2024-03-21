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
    private int page;
    private int limit;
    private int totalLimit;

    public BaseResponse() {
    }

    public void getSuccess(Object object){
        this.code = "200";
        this.message = "Get Successfully";
        this.messageKh = "ទាញយកបានជោគជ័យ";
        this.data = object;
    }

    public void getSuccessPagination(Object object,int page,int limit,int totalLimit){
        this.code = "200";
        this.message = "Get Pagination Successfully";
        this.messageKh = "ទាញយកបានជោគជ័យ";
        this.page = page;
        this.limit = limit;
        this.data = object;
        this.totalLimit = totalLimit;
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
