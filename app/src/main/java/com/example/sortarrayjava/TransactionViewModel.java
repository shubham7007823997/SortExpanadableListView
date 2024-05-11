package com.example.sortarrayjava;

import android.content.res.Resources;

import androidx.lifecycle.ViewModel;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TransactionViewModel extends ViewModel {
    Resources resources;
    private MutableLiveData<Map<Integer, Map<Long, List<Transaction>>>> groupedTransactions;
    public TransactionViewModel(Resources resources) {
        this.resources = resources;
    }
    public LiveData<Map<Integer, Map<Long, List<Transaction>>>> getGroupedTransactions() {
        if (groupedTransactions == null) {
            groupedTransactions = new MutableLiveData<>();
            loadData();
        }
        return groupedTransactions;
    }

    private void loadData() {
        String jsonString = loadJSONFromRawResource(R.raw.data);

        try {
            Map<Integer, Map<Long, List<Transaction>>> groupedData = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            SortData sortData = null;
            try {
                sortData = objectMapper.readValue(jsonString, SortData.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            for (Transaction transaction : sortData.getSort()) {
                int mid = transaction.getMid();
                long tid = transaction.getTid();
                groupedData.putIfAbsent(mid, new HashMap<>());
                groupedData.get(mid).putIfAbsent(tid, new ArrayList<>());
                groupedData.get(mid).get(tid).add(transaction);
            }
            groupedTransactions.setValue(groupedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String loadJSONFromRawResource(int resourceId) {
        String jsonStr = null;
        try {
            InputStream inputStream = resources.openRawResource(resourceId);
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            jsonStr = stringBuilder.toString();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
