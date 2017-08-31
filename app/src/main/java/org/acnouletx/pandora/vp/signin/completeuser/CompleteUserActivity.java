package org.acnouletx.pandora.vp.signin.completeuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseActivity;
import org.acnouletx.pandora.model.User;
import org.acnouletx.pandora.vp.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by txelly on 31/08/17.
 */

public class CompleteUserActivity extends BaseActivity implements CompleteUserContract.View {

    private static final String TAG = CompleteUserActivity.class.getName();
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 12;
    private static CompleteUserContract.Presenter mPresenter;
    @BindView(R.id.completeuser_edittext_name)
    EditText mName;
    @BindView(R.id.completeuser_edittext_username)
    EditText mUsername;
    @BindView(R.id.completeuser_toggle_checkname)
    ToggleButton mCheckName;
    @BindView(R.id.completeuser_button_done)
    Button mDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(CompleteUserPresenter.getInstance(this));
        setContentView(R.layout.activity_complete_user);
        ButterKnife.bind(this);
        mPresenter.start();
        setViews();
    }

    private void setViews() {
        mCheckName.setChecked(false);
        mDone.setEnabled(false);
    }

    @OnTextChanged(R.id.completeuser_edittext_username)
    public void onUsernameChanged(CharSequence text) {
        if (text.toString().matches("^[a-zA-Z0-9_-]{" + MIN_LENGTH + "," + MAX_LENGTH + "}$")) {
            mPresenter.checkUsername(text.toString());
        } else {
            mCheckName.setChecked(false);
        }
    }

    @OnTextChanged(R.id.completeuser_edittext_name)
    public void onNameChanged(CharSequence text) {
        if (mCheckName.isChecked() && text.toString().trim().length() > 0) {
            mDone.setEnabled(true);
        } else {
            mDone.setEnabled(false);
        }
    }

    @OnClick(R.id.completeuser_button_done)
    public void onClickDone() {
        mPresenter.completeUser(((User) getIntent().getExtras().getSerializable(USER)),
                mName.getText().toString(),
                mUsername.getText().toString());
    }

    @Override
    public void setPresenter(CompleteUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setAvailable(boolean available) {
        mCheckName.setChecked(available);
        if (mName.getText().length() > 0 && available) {
            mDone.setEnabled(true);
        } else {
            mDone.setEnabled(false);
        }
    }

    @Override
    public void goToMain(User user) {
        startActivity(new Intent(this, MainActivity.class).putExtra(USER, user));
        Log.i(TAG, "goToMain");
        finish();
    }
}
