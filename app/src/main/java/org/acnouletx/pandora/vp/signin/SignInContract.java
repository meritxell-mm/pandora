package org.acnouletx.pandora.vp.signin;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

import org.acnouletx.pandora.baseview.BasePresenter;
import org.acnouletx.pandora.baseview.BaseView;
import org.acnouletx.pandora.model.User;

/**
 * Created by txelly on 28/08/17.
 */

public interface SignInContract {

        interface View extends BaseView<SignInContract.Presenter> {
            void goToMain(User user);

            void showError();

            void setLoading(boolean loading);
        }

        interface Presenter extends BasePresenter {

            void firebaseAuthWithGoogle(GoogleSignInAccount acct);

            void setGoogleLogin(FragmentActivity activity,
                                GoogleApiClient.OnConnectionFailedListener listener);

            GoogleApiClient getGoogleApiClient();
        }

}
