package com.store.model;

public class ErrorResponseBody {
    private String timestamp;
    private int status;
    private String error;
    private String path;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public ErrorResponseBody() {
    }


}
