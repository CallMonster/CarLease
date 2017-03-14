package com.tj.pxdl.carlease.model.user.result;

/**
 * Created by Chaersi on 17/3/13.
 */
public class LoginResultModel {
    /**
     * access_token : 75c9658d-5afd-4c95-aa0f-ca8009593b1c
     * token_type : bearer
     * refresh_token : 090c597e-e04e-4a3c-bcb3-266b4e80e44c
     * expires_in : 43199
     * scope : read write trust
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
