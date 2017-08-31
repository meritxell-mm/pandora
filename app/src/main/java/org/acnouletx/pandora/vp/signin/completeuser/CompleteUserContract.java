package org.acnouletx.pandora.vp.signin.completeuser;

import org.acnouletx.pandora.baseview.BasePresenter;
import org.acnouletx.pandora.baseview.BaseView;
import org.acnouletx.pandora.model.User;

/**
 * Created by txelly on 31/08/17.
 */

public interface CompleteUserContract {

    interface View extends BaseView<CompleteUserContract.Presenter> {

        void setAvailable(boolean exists);

        void goToMain(User user);
    }

    interface Presenter extends BasePresenter {

        void checkUsername(String text);

        void completeUser(User user, String name, String username);

    }
}
