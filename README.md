# MultiChoiceRecyclerView

支持多选的RecyclerView

* layout
```xml

<moe.key.yao.recyclerview.RecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>

```

* adapter
```java
public class ListAdapter extends moe.key.yao.recyclerview.Adapter<ListAdapter.ViewHolder> {
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ...
    }
}
```

* activity
```java
mRecyclerView = (moe.key.yao.recyclerview.RecyclerView) findViewById(R.id.recycler_view);
mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

mRecyclerView.attachActivity(this, new Callback());
mRecyclerView.setMultiChoiceEnable(true);

mAdapter = new ListAdapter(data);
mRecyclerView.setAdapter(mAdapter);
```
* ActionMode Callback
```java
private class Callback implements moe.key.yao.recyclerview.RecyclerView.ActionModeCallback {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onItemCheckedStateChanged(int position, boolean checked) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
```
