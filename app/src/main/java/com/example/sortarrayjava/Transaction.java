package com.example.sortarrayjava;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Transaction {
    @JsonProperty("Mid")
    private int mid;

    @JsonProperty("Tid")
    private long tid;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("narration")
    private long narration;

    public int getMid() {

        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getNarration() {
        return narration;
    }

    public void setNarration(long narration) {
        this.narration = narration;
    }
}
class SortData {
    private List<Transaction> sort;

    public List<Transaction> getSort() {
        return sort;
    }

    public void setSort(List<Transaction> sort) {
        this.sort = sort;
    }
}
