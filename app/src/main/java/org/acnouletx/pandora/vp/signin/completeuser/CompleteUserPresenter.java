package org.acnouletx.pandora.vp.signin.completeuser;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class CompleteUserPresenter implements CompleteUserContract.Presenter {

    private static CompleteUserPresenter COMPLETE_USER_PRESENTER;

    private static CompleteUserContract.View mView;

    public static CompleteUserContract.Presenter getInstance(CompleteUserContract.View view) {
        if (COMPLETE_USER_PRESENTER == null) {
            COMPLETE_USER_PRESENTER = new CompleteUserPresenter();
            mView = view;
        }
        return COMPLETE_USER_PRESENTER;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void checkUsername(String text) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child(USERS).orderByChild(USERNAME).equalTo(text);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mView.setAvailable(!dataSnapshot.exists());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO?
            }
        });
    }

    @Override
    public void completeUser(final User user, final String name, final String username) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference db = database.getReference(USERS).child(user.getId());
        db.child(USERNAME).setValue(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                db.child(NAME).setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.setUsername(username);
                        user.setName(name);
                        mView.goToMain(user);
                    }
                });
            }
        });
    }
}
