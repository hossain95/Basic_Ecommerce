package com.example.ecommerce.response;

import java.util.List;

public class GetRequestResponse<T> {
    private Enum status;
    private List<T> result;

    public GetRequestResponse(Enum status, List<T> result) {
        this.status = status;
        this.result = result;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
