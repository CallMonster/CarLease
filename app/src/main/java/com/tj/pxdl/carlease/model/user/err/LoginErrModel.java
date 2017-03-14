package com.tj.pxdl.carlease.model.user.err;

/**
 * Created by Chaersi on 17/3/13.
 */
public class LoginErrModel {
    /**
     * error : access_denied
     * error_description : 不允许访问
     */

    private String error;
    private String error_description;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
