package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.Callback;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.base.BaseConfig;
import com.tj.pxdl.carlease.model.LoginModel;
import com.tj.pxdl.carlease.model.RegistModel;
import com.tj.pxdl.carlease.utils.CheckUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

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
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        rightView.setVisibility(View.VISIBLE);
        rightView.setText("注册");
        rightView.setTextColor(getResources().getColor(R.color.bg_theme_color));
        rightBtn.setVisibility(View.VISIBLE);

        Observable<CharSequence> userObservable = RxTextView.textChanges(userEdit);
        Observable<CharSequence> passObservable = RxTextView.textChanges(passEdit);
        Observable.combineLatest(userObservable, passObservable, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence userStr, CharSequence passStr) {
                return CheckUtil.strIsMobile(userStr.toString())&&CheckUtil.isPassword(passStr.toString());
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
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
                showProgressDialog("");
                String userStr=userEdit.getText().toString().trim();
                String passStr=passEdit.getText().toString().trim();
                reqLogin(userStr,passStr);
                break;
            case R.id.forgetPassBtn:

                break;
        }
    }

    private void reqLogin(String username,String passStr){
        OkHttpUtils.post().url(BaseConfig.LOGIN_URL)
                .addParams("mobile",username)
                .addParams("passWord",passStr)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("login","err:"+e);
                showTips("网络出现异常，请稍后");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("login","succ:"+response);
                LoginModel loginModel= BaseApplication.gson.fromJson(response,LoginModel.class);
                if(loginModel.isSuccess()){
                    showTips(getResources().getString(R.string.tip_login));
                    finish();
                    overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                }else{
                    ArrayList<String> tipsArr=new ArrayList<String>();
                    tipsArr.add(loginModel.getHint().getMobile());
                    tipsArr.add(loginModel.getHint().getPassWord());
                    showTips(tipsArr);
                }
            }
        });
    }

}
