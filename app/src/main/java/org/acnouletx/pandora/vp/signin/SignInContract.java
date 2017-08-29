package org.acnouletx.pandora.vp.signin;

import org.acnouletx.pandora.baseview.BasePresenter;
import org.acnouletx.pandora.baseview.BaseView;

/**
 * Created by txelly on 28/08/17.
 */

public interface SignInContract {

        interface View extends BaseView<SignInContract.Presenter> {
        }

        interface Presenter extends BasePresenter {
            void newUser(String username);
        }

}
