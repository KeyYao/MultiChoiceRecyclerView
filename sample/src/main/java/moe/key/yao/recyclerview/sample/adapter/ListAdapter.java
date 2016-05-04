package moe.key.yao.recyclerview.sample.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import moe.key.yao.recyclerview.sample.R;

/**
 * Created by Key on 2016/5/4.
 *
 * @author Key
 */
public class ListAdapter extends moe.key.yao.recyclerview.Adapter<ListAdapter.ViewHolder> {

    private List<String> mData;

    public ListAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.text.setText(mData.get(position));
        if (isSelectable()) {
            if (holder.checkbox.isChecked() != isItemCheck(position)) {
                holder.checkbox.setOnCheckedChangeListener(null);
                holder.checkbox.setChecked(isItemCheck(position));
            }
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.checkbox.setOnCheckedChangeListener(new CheckBoxCheckedChangeListener(position));
        } else {
            holder.checkbox.setVisibility(View.GONE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);

            int[] attrs = new int[]{ R.attr.selectableItemBackground };
            TypedArray ta = itemView.getContext().obtainStyledAttributes(attrs);
            int id = ta.getResourceId(0, 0);
            itemView.setBackgroundResource(id);
            ta.recycle();

        }
    }

    private class CheckBoxCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        private int mPosition;

        public CheckBoxCheckedChangeListener(int position) {
            this.mPosition = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            setItemCheck(mPosition, isChecked);
        }
    }

}
