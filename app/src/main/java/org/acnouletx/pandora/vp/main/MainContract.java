package org.acnouletx.pandora.vp.main;

import org.acnouletx.pandora.baseview.BasePresenter;
import org.acnouletx.pandora.baseview.BaseView;
import org.acnouletx.pandora.model.User;

/**
 * Created by txelly on 31/08/17.
 */

public class MainContract {

    interface View extends BaseView<MainContract.Presenter> {
        void goToSignIn();

        void goToCompleteUser(User user);
    }

    interface Presenter extends BasePresenter {
    }
}
