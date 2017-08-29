package org.acnouletx.pandora.vp.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.vp.main.MainActivity;
import org.acnouletx.pandora.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static org.acnouletx.pandora.utils.Constants.USERS;

/**
 * Created by txelly on 28/08/17.
 */

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInContract.Presenter mPresenter;

    @BindView(R.id.signin_edittext)
    EditText mUsername;
    @BindView(R.id.signin_textinput)
    TextInputLayout mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(SignInPresenter.getInstance());

        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.signin_button)
    public void signIn(){
        if(mUsername!=null&&!mUsername.getText().toString().equals("")){
            mPresenter.newUser(mUsername.getText().toString());
            goToMain();
        }else{
            mInput.setError(getResources().getString(R.string.signin_error_empty_username));
        }
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
