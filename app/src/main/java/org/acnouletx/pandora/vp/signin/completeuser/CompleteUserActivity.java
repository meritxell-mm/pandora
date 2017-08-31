package org.acnouletx.pandora.vp.signin.completeuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by txelly on 31/08/17.
 */

public class CompleteUserFragment extends BaseFragment implements CompleteUserContract.View {

    private static final String TAG = CompleteUserFragment.class.getName();
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 12;
    private static CompleteUserContract.Presenter mPresenter;
    @BindView(R.id.completeuser_edittext_username)
    EditText mUsername;
    @BindView(R.id.completeuser_toggle_checkname)
    ToggleButton mCheckName;
    @BindView(R.id.completeuser_button_done)
    Button mDone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_user, container, false);
        mCheckName.setChecked(false);
        mDone.setVisibility(View.GONE);
        setPresenter(CompleteUserPresenter.getInstance(this));
        return view;
    }

    @OnTextChanged(R.id.completeuser_edittext_username)
    public void onTextChanged(String text) {
        if (text.length() > MIN_LENGTH && text.length() < MAX_LENGTH) {
            mPresenter.checkUsername(text);
        } else {
            mCheckName.setChecked(false);
        }
    }

    @OnClick(R.id.completeuser_button_done)
    public void onClickDone(){
        mPresenter.setUsername();
        goToMain();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void setPresenter(CompleteUserContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
