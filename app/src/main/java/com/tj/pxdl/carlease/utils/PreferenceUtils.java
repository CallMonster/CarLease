package com.tj.pxdl.carlease.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tj.pxdl.carlease.model.user.result.LoginResultModel;

/**
 * Created by Chaersi on 17/3/13.
 */
public class PreferenceUtils {

    private final String SAVE_USER_KEY="USER_KEY";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public PreferenceUtils(Context context){
        preferences = context.getSharedPreferences(SAVE_USER_KEY, Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

    /**
     * 保存用户登录返回结果
     * @param loginResult
     */
    public void saveUserInfo(LoginResultModel loginResult){
        editor.putString("access_token",loginResult.getAccess_token());
        editor.putString("token_type",loginResult.getToken_type());
        editor.putString("refresh_token",loginResult.getRefresh_token());
        editor.putInt("expires_in",loginResult.getExpires_in());
        editor.putString("scope",loginResult.getScope());
        editor.commit();
    }

    /**
     * 获取token
     * @return
     */
    public String getToken(){
        return "bearer "+preferences.getString("access_token","");
    }

    /**
     * 获取用户登录信息
     * @return
     */
    public LoginResultModel getUserInfo(){
        LoginResultModel loginResult=new LoginResultModel();
        loginResult.setAccess_token(preferences.getString("access_token",""));
        loginResult.setToken_type(preferences.getString("token_type",""));
        loginResult.setRefresh_token(preferences.getString("refresh_token",""));
        loginResult.setExpires_in(preferences.getInt("expires_in",0));
        loginResult.setScope(preferences.getString("scope",""));
        return loginResult;
    }


}
