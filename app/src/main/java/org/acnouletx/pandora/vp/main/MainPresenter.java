package org.acnouletx.pandora.vp.main;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.acnouletx.pandora.model.User;

/**
 * Created by txelly on 31/08/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();
    private static MainPresenter MAIN_PRESENTER;
    private static MainContract.View mView;

    public static MainPresenter getInstance(MainContract.View view) {
        if (MAIN_PRESENTER == null) {
            MAIN_PRESENTER = new MainPresenter();
            mView = view;
        }
        return MAIN_PRESENTER;
    }

    @Override
    public void start() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            mView.goToSignIn();
        } else {
            checkUsername(user.getEmail());
        }
    }

    private void checkUsername(String email) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child(USERS).orderByChild(EMAIL).equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.i(TAG, dataSnapshot.toString());
                    User user = new User(dataSnapshot.getChildren().iterator().next());
                    if (user.getUsername() == null) {
                        mView.goToCompleteUser(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO
            }
        });
    }

    @Override
    public void stop() {

    }
}
