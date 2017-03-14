package com.tj.pxdl.carlease.model.user.req;

/**
 * Created by Chaersi on 17/3/13.
 */
public class UpdateReqModel {
    /**
     * password : 123456
     * newPassword : 123456
     * confirm_new_pass : 123456
     */

    private String password;
    private String newPassword;
    private String confirm_new_pass;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirm_new_pass() {
        return confirm_new_pass;
    }

    public void setConfirm_new_pass(String confirm_new_pass) {
        this.confirm_new_pass = confirm_new_pass;
    }
}
