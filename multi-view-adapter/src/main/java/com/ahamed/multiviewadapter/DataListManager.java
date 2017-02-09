package com.ahamed.multiviewadapter;

import android.support.v7.util.DiffUtil;
import java.util.List;

public class DataListManager<M> extends BaseDataManager<M> {

  public DataListManager(RecyclerListAdapter baseAdapter) {
    super(baseAdapter);
  }

  public final void add(M item) {
    add(getDataList().size(), item);
  }

  public final void addAll(List<M> items) {
    addAll(getDataList().size(), items);
  }

  public final void add(int index, M item) {
    getDataList().add(index, item);
    onInserted(index, 1);
  }

  public final void addAll(int index, List<M> items) {
    getDataList().addAll(index, items);
    onInserted(index, items.size());
  }

  public final void set(int position, M item) {
    M oldItem = getDataList().get(position);
    getDataList().set(position, item);
    onChanged(position, 1, getChangePayload(oldItem, item));
  }

  public final void set(List<M> dataList) {
    DiffUtil.DiffResult result =
        DiffUtil.calculateDiff(new DiffUtilCallback<M>(this.getDataList(), dataList) {
          @Override public boolean areContentsTheSame(M oldItem, M newItem) {
            return DataListManager.this.areContentsTheSame(oldItem, newItem);
          }

          @Override public Object getChangePayload(M oldItem, M newItem) {
            return DataListManager.this.getChangePayload(oldItem, newItem);
          }
        });
    setDataList(dataList);
    result.dispatchUpdatesTo(this);
  }

  public final void remove(M item) {
    remove(getDataList().indexOf(item));
  }

  public final void remove(int index) {
    getDataList().remove(index);
    onRemoved(index, 1);
  }

  public final void removeAll() {
    int oldSize = getDataList().size();
    getDataList().clear();
    onRemoved(0, oldSize);
  }
}
