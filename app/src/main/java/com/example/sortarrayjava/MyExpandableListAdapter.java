package com.example.sortarrayjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<Integer, Map<Long, List<Transaction>>> groupedTransactions;

    public MyExpandableListAdapter(Context context, Map<Integer, Map<Long, List<Transaction>>> groupedTransactions) {
        this.context = context;
        this.groupedTransactions = groupedTransactions;
    }

    @Override
    public int getGroupCount() {
        return groupedTransactions.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int mid = (int) getGroupId(groupPosition);
        Map<Long, List<Transaction>> tidMap = groupedTransactions.get(mid);
        return tidMap != null ? tidMap.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupedTransactions.keySet().toArray()[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        int mid = (int) getGroupId(groupPosition);
        Map<Long, List<Transaction>> tidMap = groupedTransactions.get(mid);
        if (tidMap != null) {
            return tidMap.keySet().toArray()[childPosition];
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (int) getGroup(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (long) getChild(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }
        TextView groupTextView = view.findViewById(R.id.listTitle);
        int mid = (int) getGroup(groupPosition);
        groupTextView.setText("Mid: " + mid);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.childes_header, null);
        }
//            TextView childTextView = view.findViewById(R.id.listTitle);
            ExpandableListView expandableListView = view.findViewById(R.id.expandableListView);
        int mid = (int) getGroup(groupPosition);
            long tid = (long) getChild(groupPosition, childPosition);
//            childTextView.setText("Tid: " + tid);
            NestedExpandableListAdapter nestedAdapter = new NestedExpandableListAdapter(context,groupedTransactions.get(mid));
            expandableListView.setAdapter(nestedAdapter);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

