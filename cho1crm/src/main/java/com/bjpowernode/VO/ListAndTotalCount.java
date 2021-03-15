package com.bjpowernode.VO;

import java.util.List;

public class ListAndTotalCount<T> {
    private int total ;
    private List<T> dataList;

    public ListAndTotalCount(int total, List<T> dataList) {
        this.total = total;
        this.dataList = dataList;
    }

    public ListAndTotalCount() {
    }

    @Override
    public String toString() {
        return "ListAndTotalCount{" +
                "total=" + total +
                ", dataList=" + dataList +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
