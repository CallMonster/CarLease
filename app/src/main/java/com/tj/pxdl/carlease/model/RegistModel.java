package com.tj.pxdl.carlease.model;

/**
 * Created by Chaersi on 17/3/3.
 */
public class RegistModel {
    /**
     * success : true
     * result : null
     * hint : {"mobile":"tel excption","vcode":"sms Code excption","password":"password excption"}
     */
    private boolean success;
    private Object result;
    /**
     * mobile : tel excption
     * vcode : sms Code excption
     * password : password excption
     */
    private HintBean hint;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public HintBean getHint() {
        return hint;
    }

    public void setHint(HintBean hint) {
        this.hint = hint;
    }

    public static class HintBean {
        private String mobile;
        private String vcode;
        private String password;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getVcode() {
            return vcode;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
