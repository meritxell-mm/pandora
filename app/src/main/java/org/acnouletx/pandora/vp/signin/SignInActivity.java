package org.acnouletx.pandora.vp.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseActivity;
import org.acnouletx.pandora.model.User;
import org.acnouletx.pandora.vp.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by txelly on 28/08/17.
 */

public class SignInActivity extends BaseActivity implements SignInContract.View,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = SignInActivity.class.getName();
    @BindView(R.id.signin_loading)
    ProgressBar mLoading;
    @BindView(R.id.signin_button_google)
    SignInButton mGoogleButton;
    private SignInContract.Presenter mPresenter;

   /* @BindView(R.id.signin_edittext)
    EditText mUsername;
    @BindView(R.id.signin_textinput)
    TextInputLayout mInput;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(SignInPresenter.getInstance(this));

        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);

        mPresenter.setGoogleLogin(this, this);
    }

    @OnClick(R.id.signin_button_google)
    public void onClickGoogleButton() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mPresenter.getGoogleApiClient());
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            mPresenter.firebaseAuthWithGoogle(account);
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, getResources().getString(R.string.sign_in_error_auth_failed),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoading(boolean loading) {
        if (loading) {
            mGoogleButton.setVisibility(View.GONE);
            mLoading.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
            mGoogleButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    public void goToMain(User user) {
        startActivity(new Intent(this, MainActivity.class).putExtra(USER, user));
        Log.i(TAG, "goToMain");
        finish();
    }

 /*   @OnClick(R.id.signin_button)
    public void signIn(){
        if(mUsername!=null&&!mUsername.getText().toString().equals("")){
           // mPresenter.newUser(mUsername.getText().toString());
            goToMain();
        }else{
            mInput.setError(getResources().getString(R.string.signin_error_empty_username));
        }
    }*/

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showError();
    }
}
