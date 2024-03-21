package com.example.server.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
    private int limit;
    private int page;
    private String status;
}
