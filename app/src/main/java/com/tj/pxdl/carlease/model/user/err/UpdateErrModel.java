package com.tj.pxdl.carlease.model.user.err;

/**
 * Created by Chaersi on 17/3/13.
 */
public class UpdateErrModel {
    /**
     * success : false
     * message : 错误信息
     */

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
