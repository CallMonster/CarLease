package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.pxdl.carlease.MenuAct.AuthActivity;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.base.BaseConfig;
import com.tj.pxdl.carlease.model.user.err.LoginErrModel;
import com.tj.pxdl.carlease.model.user.result.LoginResultModel;
import com.tj.pxdl.carlease.utils.CheckUtil;
import com.tj.pxdl.carlease.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * 登录
 * @author Chaersi
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.rightView) TextView rightView;
    @BindView(R.id.rightBtn) View rightBtn;

    @BindView(R.id.passEdit) EditText passEdit;
    @BindView(R.id.userEdit) EditText userEdit;
    @BindView(R.id.loginBtn) Button loginBtn;
    @BindView(R.id.forgetPassBtn) TextView forgetPassBtn;

    private Gson gson;
    private PreferenceUtils preference;
    private int intent_flag;//当其为0时直接跳转至收首页，否则返回结果
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        intent_flag=intent.getIntExtra("intent_flag",0);

        rightView.setVisibility(View.VISIBLE);
        rightView.setText("注册");
        rightView.setTextColor(getResources().getColor(R.color.bg_theme_color));
        rightBtn.setVisibility(View.VISIBLE);

        preference=new PreferenceUtils(this);

        Observable<CharSequence> userObservable = RxTextView.textChanges(userEdit);
        Observable<CharSequence> passObservable = RxTextView.textChanges(passEdit);
        Observable.combineLatest(userObservable, passObservable, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence userStr, CharSequence passStr) {
                return CheckUtil.strIsMobile(userStr.toString())&&CheckUtil.isPassword(passStr.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundResource(R.drawable.bg_edittext_focused);
                    loginBtn.setTextColor(getResources().getColor(R.color.bg_theme_color));
                }else{
                    loginBtn.setEnabled(false);
                    loginBtn.setBackgroundResource(R.drawable.bg_edittext_normal);
                    loginBtn.setTextColor(getResources().getColor(R.color.border_grey));
                }
            }
        });
    }

    @OnClick({R.id.leftBtn, R.id.rightBtn,R.id.forgetPassBtn,R.id.loginBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.rightBtn:
                Intent intent=new Intent(this,RegistActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.loginBtn:
                showProgressDialog("请稍后..");
                String userStr=userEdit.getText().toString().trim();
                String passStr=passEdit.getText().toString().trim();
                reqLogin(userStr,passStr);
                break;
            case R.id.forgetPassBtn:
                Intent aaa=new Intent(this,AuthActivity.class);
                startActivity(aaa);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
        }
    }

    private void reqLogin(String username,String passStr){
        gson=BaseApplication.gson;
        OkHttpUtils.post().url(BaseConfig.USER_LOGIN_URL)
                .addParams("client_id","client-id")
                .addParams("client_secret:","client_secret")
                .addParams("grant_type:","password")
                .addParams("username",username)
                .addParams("password",passStr)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id, Response response) {
                Log.e("login","err:"+e);
                if(response!=null){
                    LoginErrModel err= gson.fromJson(response.body().toString(),LoginErrModel.class);
                    showTips(err.getError_description());
                }else{
                    showTips("网络出现异常，请稍后");
                }
            }

            @Override
            public void onResponse(String response, int id,int resultCode) {
                Log.d("login","succ:"+id+"---"+response);
                LoginResultModel login = gson.fromJson(response, LoginResultModel.class);
                preference.saveUserInfo(login);
                if (intent_flag == 0) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

}
