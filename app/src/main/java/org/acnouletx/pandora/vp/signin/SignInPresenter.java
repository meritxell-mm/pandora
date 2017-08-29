package org.acnouletx.pandora.vp.signin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.acnouletx.pandora.model.User;
import org.acnouletx.pandora.vp.signin.SignInContract.Presenter;

import static org.acnouletx.pandora.utils.Constants.USERS;

/**
 * Created by txelly on 28/08/17.
 */

public class SignInPresenter implements Presenter{

    private static SignInPresenter SIGN_IN_PRESENTER;

    public static SignInPresenter getInstance(){
        if(SIGN_IN_PRESENTER==null){
            SIGN_IN_PRESENTER = new SignInPresenter();
        }
        return SIGN_IN_PRESENTER;
    }

    @Override
    public void start() {
        //TODO check logged user and go to main
    }

    @Override
    public void newUser(String username) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference(USERS);

        User user= new User(username);

        db.setValue(user);
    }
}
