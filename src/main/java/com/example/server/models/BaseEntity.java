package com.example.server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseEntity {
    private String createdBy;
    private Data createdDate;
    private String updatedBy;
    private Data updatedDate;
}
