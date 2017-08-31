package org.acnouletx.pandora.vp.signin.completeuser;

import org.acnouletx.pandora.baseview.BasePresenter;

/**
 * Created by txelly on 31/08/17.
 */

public class CompleteUserPresenter implements CompleteUserContract.Presenter {

    private static CompleteUserPresenter COMPLETE_USER_PRESENTER;

    private static CompleteUserContract.View mView;


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    public static CompleteUserContract.Presenter getInstance(CompleteUserContract.View view) {
        if(COMPLETE_USER_PRESENTER==null){
            COMPLETE_USER_PRESENTER= new CompleteUserPresenter();
            mView=view;
        }
        return COMPLETE_USER_PRESENTER;
    }

    @Override
    public void checkUsername(String text) {
        if()
    }
}
