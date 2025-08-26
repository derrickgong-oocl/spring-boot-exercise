package com.oocl.training;
import java.util.List;

public class Page<T> {
    private int page;

    private int size;

    private long total;

    private int totalPages;

    private List<T> content;

    public Page( long total, int page, int size, List<T> content) {
        this.total = total;
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalPages = (int) Math.ceil((double) total / size);
    }


    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }
}
