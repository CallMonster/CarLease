package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.chaersi.okhttputils.request.RequestCall;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.base.BaseConfig;
import com.tj.pxdl.carlease.model.user.err.LoginErrModel;
import com.tj.pxdl.carlease.model.user.result.LoginResultModel;
import com.tj.pxdl.carlease.utils.PreferenceUtils;

import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 欢迎页面
 * @author Chaersi
 */
public class WelcomeActivity extends BaseActivity {

    private PreferenceUtils preference;
    private Gson gson;
    private RequestCall call;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        preference=new PreferenceUtils(this);
        timer.start();
        if(TextUtils.isEmpty(preference.getUserInfo().getRefresh_token())){
            reqFastLogin(preference.getUserInfo());
        }
    }

    @Override
    public void onClickListener(View v) {}

    private void reqFastLogin(LoginResultModel loginModel){
        gson=BaseApplication.gson;
        call = OkHttpUtils.post().url(BaseConfig.USER_FASTLOGIN_URL)
                .tag(this)
                .addParams("client_id","client-id")
                .addParams("client_secret:","client_secret")
                .addParams("grant_type:","refresh_token")
                .addParams("refresh_token",loginModel.getRefresh_token())
                .build();
        call.execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("login","err:"+e);
            }

            @Override
            public void onResponse(String response, int id,int resultCode) {
                Log.d("login","succ:"+id+"---"+response);
                if(200==resultCode){
                    LoginResultModel login= gson.fromJson(response,LoginResultModel.class);
                    preference.saveUserInfo(login);
                }
            }
        });
    }

    CountDownTimer timer=new CountDownTimer(3000,1000) {

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call!=null){
            call.cancel();
        }
    }

}
