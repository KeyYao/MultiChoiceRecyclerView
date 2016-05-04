package moe.key.yao.recyclerview;

import android.view.View;

/**
 * Created by Key on 2016/5/4.
 *  <p>
 *      BaseAdapter
 *  </p>
 * @author Key
 */
public abstract class Adapter<VH extends android.support.v7.widget.RecyclerView.ViewHolder> extends android.support.v7.widget.RecyclerView.Adapter<VH> {

    private boolean isEnableMultiChoice = false;

    private RecyclerView.MultiChoiceCallback mCallback;

    private RecyclerView.ChoiceManager mManager;

    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public final void init(RecyclerView.ChoiceManager manager, RecyclerView.MultiChoiceCallback callback) {
        this.mManager = manager;
        this.mCallback = callback;
    }

    public final void setEnableMultiChoice(boolean enable) {
        this.isEnableMultiChoice = enable;
    }

    public final void setItemCheck(int position, boolean isChecked) {
        mManager.setItemCheck(position, isChecked);
    }

    public final boolean isItemCheck(int position) {
        return mManager.isItemCheck(position);
    }

    public final void clearChoices() {
        mManager.clear();
    }

    public final boolean isSelectable() {
        return mManager.isSelectable();
    }

    public final void setOnItemClickListener(OnItemClickListener l) {
        this.mItemClickListener = l;
    }

    public final void setOnItemLongClickListener(OnItemLongClickListener l) {
        this.mItemLongClickListener = l;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setOnClickListener(new ClickListener(position));
        if (isEnableMultiChoice) {
            holder.itemView.setOnLongClickListener(new LongClickListener(position));
        }
    }

    private class ClickListener implements View.OnClickListener {

        private int mPosition;

        public ClickListener(int position) {
            this.mPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (mManager.isSelectable()) {
                setItemCheck(mPosition, !isItemCheck(mPosition));
                return;
            }
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mPosition);
            }
        }
    }

    private class LongClickListener implements View.OnLongClickListener {

        private int mPosition;

        public LongClickListener(int position) {
            this.mPosition = position;
        }

        @Override
        public boolean onLongClick(View v) {
            if (!mManager.isSelectable() && isEnableMultiChoice) {
                setItemCheck(mPosition, true);
                mCallback.onStart();
                return true;
            } else {
                return mItemLongClickListener != null && mItemLongClickListener.onItemLongClick(mPosition);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }

}
