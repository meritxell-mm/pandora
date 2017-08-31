package org.acnouletx.pandora.vp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseActivity;
import org.acnouletx.pandora.model.Box;
import org.acnouletx.pandora.vp.signin.SignInActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends BaseActivity {

    private WeakReference<BoxesFragment> mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO in a presenter?
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, SignInActivity.class));
        }
        setContentView(R.layout.activity_main);
        getBoxes();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_box:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFragment.get() instanceof BoxesFragment) {
            finish();
        }
    }

    private void setFragment(WeakReference<BoxesFragment> mFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, mFragment.get())
                .addToBackStack(null)
                .commit();
    }

    public void getBoxes() {
        getIntent().getExtras().getSerializable(USER);
        mFragment = new WeakReference(BoxesFragment.newInstance((List<Box>) getIntent().getSerializableExtra(BOXES)));
        setFragment(mFragment);
    }
}
