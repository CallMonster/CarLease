package com.tj.pxdl.carlease.model;

/**
 * Created by Chaersi on 17/3/3.
 */
public class LoginModel {
    /**
     * success : true
     * result : null
     * hint : {"mobile":"","passWord":""}
     */

    private boolean success;
    private Object result;
    /**
     * mobile :
     * passWord :
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
        private String passWord;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }
}
