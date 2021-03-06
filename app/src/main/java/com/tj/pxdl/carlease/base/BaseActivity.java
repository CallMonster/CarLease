package com.tj.pxdl.carlease.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.tj.pxdl.carlease.utils.NetStateReceiver;
import com.tj.pxdl.carlease.utils.OnClickUtils;

import java.util.ArrayList;

/**
 * Created by Chaersi on 16/7/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,NetStateReceiver.onNETWORK_STATUS {
    public String TAG="carleasr_Act";
    private ProgressDialog progressDialog;
    public int netState;//0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
            onCreate();
        }
    }

    public abstract void onCreate();

    @Override
    public void onClick(View v) {
        if (OnClickUtils.isFastDoubleClick()) {
            return;
        }
        onClickListener(v);
    }

    public abstract void onClickListener(View v);

    @Override
    public void onNet_STATUS_Listener(int state) {
        netState=state;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("history","savestate");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("savestate");
    }

    private Toast toast;
    public void showTips(String tips) {
        if (toast != null) {
            toast.setText(tips);
            toast.show();
        } else {
            toast = Toast.makeText(getApplicationContext(), tips, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showTips(ArrayList<String> datas){
        for(String tip:datas){
            if(!TextUtils.isEmpty(tip)){
                showTips(tip);
                break;
            }
        }
    }

    /**
     *
     * @param tips
     * @param gravity
     */
    public void showTips(String tips,int gravity) {
        if (toast != null) {
            toast.setText(tips);
            toast.setGravity(gravity,0,0);
            toast.show();
        } else {
            toast = Toast.makeText(getApplicationContext(), tips, Toast.LENGTH_SHORT);
            toast.setGravity(gravity,0,0);
            toast.show();
        }
    }

    /**
     * 加载进度条
     * @param content
     */
    public void showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(content);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
