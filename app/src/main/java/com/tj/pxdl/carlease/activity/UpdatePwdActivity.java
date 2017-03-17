package com.tj.pxdl.carlease.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.base.BaseConfig;
import com.tj.pxdl.carlease.model.user.err.UpdateErrModel;
import com.tj.pxdl.carlease.model.user.req.UpdateReqModel;
import com.tj.pxdl.carlease.model.user.result.LoginResultModel;
import com.tj.pxdl.carlease.model.user.result.UpdateResultModel;
import com.tj.pxdl.carlease.utils.CheckUtil;
import com.tj.pxdl.carlease.utils.PreferenceUtils;
import com.tj.pxdl.carlease.widget.OneKeyClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func3;

/**
 * 修改密码
 *
 * @author Chaersi
 */
public class UpdatePwdActivity extends BaseActivity {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.pwdEdit) OneKeyClearEditText pwdEdit;
    @BindView(R.id.newpwdEdit) OneKeyClearEditText newpwdEdit;
    @BindView(R.id.repwdEdit) OneKeyClearEditText repwdEdit;
    @BindView(R.id.submitBtn) Button submitBtn;

    private PreferenceUtils preference;
    private Gson gson;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_update_pwd);
        ButterKnife.bind(this);

        preference=new PreferenceUtils(this);

        Observable<CharSequence> pwdObservable= RxTextView.textChanges(pwdEdit);
        Observable<CharSequence> newPassObservable= RxTextView.textChanges(newpwdEdit);
        Observable<CharSequence> rePassObservable= RxTextView.textChanges(repwdEdit);
        Observable.combineLatest(pwdObservable, newPassObservable, rePassObservable, new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
                return CheckUtil.isPassword(charSequence.toString().trim())
                        &&CheckUtil.isPassword(charSequence2.toString().trim())
                        &&charSequence2.toString().trim().equals(charSequence3.toString().trim());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    submitBtn.setEnabled(true);
                    submitBtn.setBackgroundResource(R.drawable.bg_edittext_focused);
                    submitBtn.setTextColor(getResources().getColor(R.color.bg_theme_color));
                }else{
                    submitBtn.setEnabled(false);
                    submitBtn.setBackgroundResource(R.drawable.bg_edittext_normal);
                    submitBtn.setTextColor(getResources().getColor(R.color.border_grey));
                }
            }
        });

    }

    @OnClick({R.id.leftBtn, R.id.submitBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.submitBtn:
                showProgressDialog("提交中...");
                String pwdStr=pwdEdit.getText().toString().trim();
                String newpwdStr=newpwdEdit.getText().toString().trim();
                String repwdStr=repwdEdit.getText().toString().trim();

                UpdateReqModel updatePwd=new UpdateReqModel();
                updatePwd.setOldPass(pwdStr);
                updatePwd.setConfirmNewPass(newpwdStr);
                updatePwd.setNewPass(repwdStr);
                reqUpdatePwd(updatePwd);
                break;
        }
    }

    private void reqUpdatePwd(UpdateReqModel updatePwd){
        gson= BaseApplication.gson;
        OkHttpUtils.postString().url(BaseConfig.USER_UPDATEPASS_URL)
                .addHeader("Authorization",preference.getToken())
                .content(gson.toJson(updatePwd))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id, Response response) {
                Log.e("login","err:"+e);
                showTips("网络出现异常，请稍后");
            }

            @Override
            public void onErrResponse(int respCode, String response) {
                super.onErrResponse(respCode, response);
                Log.e("login","resp:"+response);
                if(response!=null){
                    UpdateErrModel err=gson.fromJson(response,UpdateErrModel.class);
                    showTips(err.getMessage());
                }else{
                    showTips("网络出现异常，请稍后");
                }
            }

            @Override
            public void onResponse(String response, int id, int resultCode) {
                Log.i(TAG,response);
                UpdateResultModel updatePwd = gson.fromJson(response, UpdateResultModel.class);
                if(updatePwd.isSuccess()){
                    showTips("修改密码成功，以后请使用新密码");
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }
            }
        });

    }

}
