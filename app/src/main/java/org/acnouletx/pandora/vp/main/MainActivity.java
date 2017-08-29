package org.acnouletx.pandora.vp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.model.Box;

import java.lang.ref.WeakReference;
import java.util.List;

import static org.acnouletx.pandora.utils.Constants.BOXES;

public class MainActivity extends AppCompatActivity {

    private WeakReference<BoxesFragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO get boxes
        mFragment= new WeakReference(
                BoxesFragment.newInstance((List<Box>) getIntent().getSerializableExtra(BOXES)));
        setFragment(mFragment);
    }

    private void setFragment(WeakReference<BoxesFragment> mFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, mFragment.get())
                .addToBackStack(null)
                .commit();
    }

}
