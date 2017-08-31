package org.acnouletx.pandora.vp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseActivity;
import org.acnouletx.pandora.model.Box;
import org.acnouletx.pandora.model.User;
import org.acnouletx.pandora.vp.main.boxeslist.BoxesFragment;
import org.acnouletx.pandora.vp.main.searchlist.SearchFragment;
import org.acnouletx.pandora.vp.signin.SignInActivity;
import org.acnouletx.pandora.vp.signin.completeuser.CompleteUserActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getName();

    private MainContract.Presenter mPresenter;

    private WeakReference<BoxesFragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(MainPresenter.getInstance(this));
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null) {
            getBoxes();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_box:
                //TODO crear caixa
                return true;
            case R.id.action_search:
                setFragment(new SearchFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof BoxesFragment) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void goToSignIn() {
        startActivity(new Intent(this, SignInActivity.class));
    }

    @Override
    public void goToCompleteUser(User user) {
        startActivity(new Intent(this, CompleteUserActivity.class)
                .putExtra(USER, user));
    }

    public void getBoxes() {
        User user = (User) getIntent().getExtras().getSerializable(USER);
        if (user.getBoxes() == null) {
            mFragment = new WeakReference(BoxesFragment.newInstance(new ArrayList<Box>()));
        } else {
            mFragment = new WeakReference(BoxesFragment.newInstance(user.getBoxes()));
        }
        setFragment(mFragment.get());
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
