package com.tj.pxdl.carlease.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.callback.StringCallback;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.base.BaseConfig;
import com.tj.pxdl.carlease.model.RegistModel;
import com.tj.pxdl.carlease.utils.CheckUtil;
import com.tj.pxdl.carlease.widget.OneKeyClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func4;
import rx.functions.FuncN;

public class RegistActivity extends BaseActivity {
    private String TAG="RegistActivity";

    @BindView(R.id.rightView) TextView rightView;
    @BindView(R.id.rightBtn) View rightBtn;
    @BindView(R.id.mobileEdit) OneKeyClearEditText mobileEdit;
    @BindView(R.id.smsCodeEdit) OneKeyClearEditText smsCodeEdit;
    @BindView(R.id.passedit) OneKeyClearEditText passedit;
    @BindView(R.id.repassEdit) OneKeyClearEditText repassEdit;
    @BindView(R.id.inviteEdit) OneKeyClearEditText inviteEdit;
    @BindView(R.id.regBtn) Button regBtn;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        rightView.setVisibility(View.VISIBLE);
        rightView.setText("登录");
        rightView.setTextColor(getResources().getColor(R.color.bg_theme_color));
        rightBtn.setVisibility(View.VISIBLE);

        Observable<CharSequence> mobileObservable = RxTextView.textChanges(mobileEdit);
        Observable<CharSequence> smsObservable = RxTextView.textChanges(smsCodeEdit);
        Observable<CharSequence> passObservable = RxTextView.textChanges(passedit);
        Observable<CharSequence> repassObservable = RxTextView.textChanges(repassEdit);

        List<Observable<CharSequence>> observableArr=new ArrayList<>();
        observableArr.add(mobileObservable);
        observableArr.add(smsObservable);
        observableArr.add(passObservable);
        observableArr.add(repassObservable);

        Observable.combineLatest(observableArr, new FuncN<Boolean>() {

            @Override
            public Boolean call(Object... args) {
                boolean is= CheckUtil.strIsMobile(args[0].toString().trim())
                        &&!TextUtils.isEmpty(args[1].toString().trim())
                        &&CheckUtil.isPassword(args[2].toString().trim())
                        &&args[2].toString().trim().equals(args[3].toString().trim());
                return is;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    regBtn.setEnabled(true);
                    regBtn.setBackgroundResource(R.drawable.bg_edittext_focused);
                    regBtn.setTextColor(getResources().getColor(R.color.bg_theme_color));
                }else{
                    regBtn.setEnabled(false);
                    regBtn.setBackgroundResource(R.drawable.bg_edittext_normal);
                    regBtn.setTextColor(getResources().getColor(R.color.border_grey));
                }
            }
        });

    }

    @OnClick({R.id.leftBtn, R.id.regBtn, R.id.accordBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.regBtn:
                showProgressDialog("请稍后..");
                String mobileStr=mobileEdit.getText().toString().trim();
                String passStr=passedit.getText().toString().trim();
                String vcodeStr=passedit.getText().toString().trim();
                reqRegist(mobileStr,passStr,vcodeStr);
                break;
            case R.id.accordBtn:
                break;
        }
    }

    private void reqRegist(String mobileStr,String passStr,String vcode){
        OkHttpUtils.post().url(BaseConfig.REGIST_URL)
                .addParams("mobile",mobileStr)
                .addParams("passWord",passStr)
                .addParams("vcode",vcode)
                .build().execute(new StringCallback() {
            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,"err:"+e);
                showTips("网络出现异常，请稍后");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG,"succ:"+response);
                RegistModel regModel= BaseApplication.gson.fromJson(response,RegistModel.class);
                if(regModel.isSuccess()){
                    showTips(getResources().getString(R.string.tip_reg));
                    finish();
                    overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                }else{
                    ArrayList<String> tipsArr=new ArrayList<String>();
                    tipsArr.add(regModel.getHint().getMobile());
                    tipsArr.add(regModel.getHint().getPassword());
                    tipsArr.add(regModel.getHint().getVcode());
                    showTips(tipsArr);
                }
            }
        });
    }
}
