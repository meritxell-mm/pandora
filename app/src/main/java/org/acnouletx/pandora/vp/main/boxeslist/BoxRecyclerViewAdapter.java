package org.acnouletx.pandora.vp.main.boxeslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.acnouletx.pandora.R;
import org.acnouletx.pandora.model.Box;

import java.util.List;

import butterknife.BindView;

public class BoxRecyclerViewAdapter extends RecyclerView.Adapter<BoxRecyclerViewAdapter.ViewHolder> {

    private final List<Box> mValues;
    private final BoxesFragmentListener mListener;

    public BoxRecyclerViewAdapter(List<Box> items, BoxesFragmentListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Box mItem;

        @BindView(R.id.item_box_name)
        TextView mName;
        @BindView(R.id.item_box_members)
        RecyclerView mMembers;


        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

    }
}
