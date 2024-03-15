package com.zdy.pojo;



import java.util.List;

// 分頁返回結果物件

public class PageBean<T>{
    private Long total; // 總條數
    private List<T> items; // 當前頁數據集合

    public PageBean() {
    }

    public PageBean(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
