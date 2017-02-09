package com.ahamed.multiviewadapter;

import android.support.v7.util.ListUpdateCallback;
import java.util.ArrayList;
import java.util.List;

class BaseDataManager<M> implements ListUpdateCallback {

  private List<M> dataList = new ArrayList<>();
  private final RecyclerListAdapter listAdapter;

  BaseDataManager(RecyclerListAdapter baseAdapter) {
    this.listAdapter = baseAdapter;
  }

  int getCount() {
    return dataList.size();
  }

  M getItem(int position) {
    return dataList.get(position);
  }

  @Override public final void onInserted(int position, int count) {
    listAdapter.notifyBinderItemRangeInserted(this, position, count);
  }

  @Override public final void onRemoved(int position, int count) {
    listAdapter.notifyBinderItemRangeRemoved(this, position, count);
  }

  @Override public final void onMoved(int fromPosition, int toPosition) {
    listAdapter.notifyBinderItemMoved(this, fromPosition, toPosition);
  }

  @Override public final void onChanged(int position, int count, Object payload) {
    listAdapter.notifyBinderItemRangeChanged(this, position, count, payload);
  }

  public boolean areContentsTheSame(M oldItem, M newItem) {
    return oldItem.equals(newItem);
  }

  @SuppressWarnings("UnusedParameters") public Object getChangePayload(M oldItem, M newItem) {
    return null;
  }

  List<M> getDataList() {
    return dataList;
  }

  void setDataList(List<M> dataList) {
    this.dataList = dataList;
  }
}