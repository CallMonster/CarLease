package com.tj.pxdl.carlease.model.user.req;

/**
 * Created by Chaersi on 17/3/13.
 */
public class RegistReqModel {
    /**
     * username : 18630290362
     * password : 123456
     * sms_code : 188818
     */

    private String username;
    private String password;
    private String sms_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }
}
