package com.tj.pxdl.carlease.model.user.req;

/**
 * Created by Chaersi on 17/3/13.
 */
public class UpdateReqModel {
    /**
     * password : 123456
     * newPassword : 123456
     * confirmNewPass : 123456
     */

    private String oldPass;
    private String newPass;
    private String confirmNewPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmNewPass() {
        return confirmNewPass;
    }

    public void setConfirmNewPass(String confirmNewPass) {
        this.confirmNewPass = confirmNewPass;
    }
}

