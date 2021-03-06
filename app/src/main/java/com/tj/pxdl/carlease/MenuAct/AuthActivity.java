package com.tj.pxdl.carlease.MenuAct;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.tj.opensrc.selectphoto.SystemAlbumPickerActivity;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.utils.ImageUtils;
import com.tj.pxdl.carlease.widget.OneKeyClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class AuthActivity extends BaseActivity {
    private final int IMAGE_TAG_DRIVE=1;
    private final int IMAGE_TAG_USER=2;
    private final int IMAGE_TAG_PAPER=3;

    private String DRIVE_IMAGE_PATH;
    private String USER_IMAGE_PATH;
    private String PAPER_IMAGE_PATH;

    @BindView(R.id.title) TextView title;
    @BindView(R.id.userEdit) OneKeyClearEditText userEdit;
    @BindView(R.id.idEdit) OneKeyClearEditText idEdit;
    @BindView(R.id.drivePhoto) ImageView drivePhoto;
    @BindView(R.id.drive_tipView) TextView driveTipView;
    @BindView(R.id.userPhoto) ImageView userPhoto;
    @BindView(R.id.user_tipView) TextView userTipView;
    @BindView(R.id.paperPhoto) ImageView paperPhoto;
    @BindView(R.id.paper_tipView) TextView paperTipView;
    @BindView(R.id.submitBtn) Button submitBtn;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        title.setText("用户认证");

        Observable<CharSequence> userObservable= RxTextView.textChanges(userEdit);
        Observable<CharSequence> idObservable= RxTextView.textChanges(idEdit);
        Observable.combineLatest(userObservable, idObservable, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2) {

                return null;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {

            }
        });


    }

    @OnClick({R.id.leftBtn, R.id.driveLayoutBtn, R.id.userLayoutBtn, R.id.paperLayoutBtn,R.id.submitBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.driveLayoutBtn:
                Intent driveIntent = new Intent(this, SystemAlbumPickerActivity.class);
                driveIntent.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(driveIntent, IMAGE_TAG_DRIVE);
                break;
            case R.id.userLayoutBtn:
                Intent userIntent = new Intent(this, SystemAlbumPickerActivity.class);
                userIntent.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(userIntent, IMAGE_TAG_USER);
                break;
            case R.id.paperLayoutBtn:
                Intent paperIntent = new Intent(this, SystemAlbumPickerActivity.class);
                paperIntent.putExtra(SystemAlbumPickerActivity.key_appPath,"photo/test");
                startActivityForResult(paperIntent, IMAGE_TAG_PAPER);
                break;
            case R.id.submitBtn:
                if(TextUtils.isEmpty(DRIVE_IMAGE_PATH)||TextUtils.isEmpty(USER_IMAGE_PATH)
                        ||TextUtils.isEmpty(PAPER_IMAGE_PATH)){
                    showTips("请选择照片后上传");
                }else{

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SystemAlbumPickerActivity.resultCode_SINGLE_PATH && requestCode == IMAGE_TAG_DRIVE) {
            DRIVE_IMAGE_PATH = data.getStringExtra(SystemAlbumPickerActivity.key_singlePath);
            drivePhoto.setImageBitmap(ImageUtils.getimage(DRIVE_IMAGE_PATH));
            driveTipView.setVisibility(View.GONE);
        }else if(resultCode == SystemAlbumPickerActivity.resultCode_SINGLE_PATH && requestCode == IMAGE_TAG_USER){
            USER_IMAGE_PATH = data.getStringExtra(SystemAlbumPickerActivity.key_singlePath);
            userPhoto.setImageBitmap(ImageUtils.getimage(USER_IMAGE_PATH));
            userTipView.setVisibility(View.GONE);
        } else if(resultCode == SystemAlbumPickerActivity.resultCode_SINGLE_PATH && requestCode == IMAGE_TAG_PAPER){
            PAPER_IMAGE_PATH = data.getStringExtra(SystemAlbumPickerActivity.key_singlePath);
            paperPhoto.setImageBitmap(ImageUtils.getimage(PAPER_IMAGE_PATH));
            paperTipView.setVisibility(View.GONE);
        }
    }

}
