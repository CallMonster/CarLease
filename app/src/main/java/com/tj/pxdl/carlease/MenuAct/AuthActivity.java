package com.tj.pxdl.carlease.MenuAct;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.opensrc.selectphoto.SystemAlbumPickerActivity;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.utils.ImageUtils;
import com.tj.pxdl.carlease.widget.OneKeyClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        title.setText("用户认证");

    }

    @OnClick({R.id.leftBtn, R.id.driveLayoutBtn, R.id.userLayoutBtn, R.id.paperLayoutBtn})
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
