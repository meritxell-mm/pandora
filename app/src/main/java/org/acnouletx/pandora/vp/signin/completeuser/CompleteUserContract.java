package org.acnouletx.pandora.vp.signin.completeuser;

import org.acnouletx.pandora.baseview.BasePresenter;
import org.acnouletx.pandora.baseview.BaseView;

/**
 * Created by txelly on 31/08/17.
 */

public interface CompleteUserContract {

    interface View extends BaseView<CompleteUserContract.Presenter> {
    }

    interface Presenter extends BasePresenter {

        void checkUsername(String text);

        void setUsername();

    }
}
