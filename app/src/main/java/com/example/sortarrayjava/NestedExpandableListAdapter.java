package com.example.sortarrayjava;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NestedExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<Long, List<Transaction>> longListMap;
    private List<Long> tids;

    public NestedExpandableListAdapter(Context context, Map<Long, List<Transaction>> longListMap) {
        this.context = context;
        this.longListMap = longListMap;
        this.tids = new ArrayList<>(longListMap.keySet());
    }

    @Override
    public int getGroupCount() {
        return tids.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        long tid = getGroupId(groupPosition);
        List<Transaction> transactions = longListMap.get(tid);
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public Long getGroup(int groupPosition) {
        return tids.get(groupPosition);
    }

    @Override
    public Transaction getChild(int groupPosition, int childPosition) {
        long tid = getGroupId(groupPosition);
        List<Transaction> transactions = longListMap.get(tid);
        return transactions != null && childPosition < transactions.size() ? transactions.get(childPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // You can return a unique identifier for the child here if needed
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

//    private Context context;
//    private List<Transaction> transactions;
//    Map<Long, List<Transaction>> longListMap;
//
//    public NestedExpandableListAdapter(Context context, List<Transaction> transactions, Map<Long, List<Transaction>> longListMap) {
//        this.context = context;
//        this.transactions = transactions;
//        this.longListMap = longListMap;
//    }
//
//    @Override
//    public int getGroupCount() {
//        return longListMap.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        int tid = (int) getGroupId(groupPosition);
//         List<Transaction>tidMap = longListMap.get(tid);
//        return tidMap != null ? tidMap.size() : 0;
////        return 1;
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        List<Long> tids = new ArrayList<>(longListMap.keySet());
//        return tids.get(groupPosition);
////        return longListMap.keySet().toArray()[groupPosition];
//    }
//
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
////        long tid = (long) getGroup(groupPosition);
////        List<Transaction> transactions = longListMap.get(tid);
////        return transactions.get(childPosition) ;
//        int mid = (int) getGroupId(groupPosition);
//         List<Transaction> tidMap = longListMap.get(mid);
//        if (tidMap != null) {
//            return tidMap.toArray()[childPosition];
//        }
//        return null;
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return (long) getGroup(groupPosition);
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return (long) getChild(groupPosition, childPosition);
//
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.nestedviewheader, null);
        }

        TextView amountTextView = view.findViewById(R.id.listTitlenested);

        long tid = (long) getGroup(groupPosition);

        Log.d("fdhsgshjfg",String.valueOf(tid));
//
//        long tid = (long) getGroupId(groupPosition);
        amountTextView.setText("TID: " + String.valueOf(tid));


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.nested_child_item_layout, null);
        }

        TextView amountTextView = view.findViewById(R.id.amount_text);
        TextView narrationTextView = view.findViewById(R.id.narration_text);

        Transaction transaction = (Transaction) getChild(groupPosition, childPosition);

        Log.d("hdghhgjgh",String.valueOf(transaction.getAmount())+" "+String.valueOf(transaction.getNarration()));
        amountTextView.setText("Amount: " + String.valueOf(transaction.getAmount()));
        narrationTextView.setText("Narration: " + String.valueOf(transaction.getNarration()));

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
