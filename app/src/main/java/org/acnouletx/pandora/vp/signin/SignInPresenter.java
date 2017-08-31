package org.acnouletx.pandora.vp.signin;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.model.User;

/**
 * Created by txelly on 28/08/17.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private static final String TAG = SignInPresenter.class.getName();

    private static SignInPresenter SIGN_IN_PRESENTER;

    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;

    private static SignInContract.View mView;
    private GoogleApiClient mGoogleApiClient;


    public static SignInPresenter getInstance(SignInContract.View view) {
        if (SIGN_IN_PRESENTER == null) {
            SIGN_IN_PRESENTER = new SignInPresenter();
            setView(view);
            setFirebaseLogin();
        }
        return SIGN_IN_PRESENTER;
    }

    private static void setFirebaseLogin() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private static void getUserInfo(final String email, final String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child(USERS).orderByChild(EMAIL).equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.i(TAG, dataSnapshot.toString());
                    User user = new User(dataSnapshot.getChildren().iterator().next());
                    mView.goToMain(user);
                } else {
                    newUser(email, token);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.setLoading(false);
                mView.showError();
            }
        });
    }

    private static void getToken(final FirebaseUser user) {
        user.getToken(true)
                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            String idToken = task.getResult().getToken();
                            Log.i(TAG, "onComplete: " + idToken);
                            getUserInfo(user.getEmail(), idToken);
                        } else {
                            mView.showError();
                            // Handle error -> task.getException();
                        }
                    }
                });
    }

    private static void newUser(final String email, final String token) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference(USERS);

        User user = new User(email, token);

        db.child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getUserInfo(email, token);
            }
        });
    }

    public static void setView(SignInContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void stop() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            mView.setLoading(true);
                            getToken(mAuth.getCurrentUser());
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            mView.showError();
                        }
                    }
                });
    }

    @Override
    public void setGoogleLogin(FragmentActivity activity, GoogleApiClient.OnConnectionFailedListener listener) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity /* FragmentActivity */, listener /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }
}
