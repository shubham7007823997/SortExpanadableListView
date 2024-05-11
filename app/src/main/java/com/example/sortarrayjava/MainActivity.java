package com.example.sortarrayjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    private TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView12);
        transactionViewModel = new ViewModelProvider(this, new TransactionViewModelFactory(getResources())).get(TransactionViewModel.class);

        transactionViewModel.getGroupedTransactions().observe(this, groupedTransactions -> {
            // Update UI with groupedTransactions
            MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(this, groupedTransactions);
            expandableListView.setAdapter(myExpandableListAdapter);
        });
    }
}