package org.acnouletx.pandora.vp.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.baseview.BaseFragment;
import org.acnouletx.pandora.model.Box;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoxesFragment extends BaseFragment {

    private BoxesFragmentListener mListener;

    public BoxesFragment() {
    }

    public static BoxesFragment newInstance(List<Box> boxes) {
        BoxesFragment fragment = new BoxesFragment();
        Bundle args = new Bundle();
        args.putSerializable(BOXES, (Serializable) boxes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boxes_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new BoxRecyclerViewAdapter(
              //      (List<Box>) getArguments().getSerializable(BOXES),
                    new ArrayList<Box>(),
                    mListener));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
