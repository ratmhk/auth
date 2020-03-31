package com.utils;

import java.util.Map;

public class PageBean {
    private int currentPage;
    private int pageSize;
    private int totalRecord;
    private int totalPage;
    private int startIndex;

    public PageBean(int currentPage, int pageSize, int totalRecord) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        if (totalRecord % pageSize == 0) {
            this.totalPage = totalRecord / pageSize;
        } else {
            this.totalPage = totalRecord / pageSize + 1;
        }

        if (totalRecord > 0 && currentPage * pageSize > totalRecord) {
            currentPage = this.totalPage;
        }

        this.startIndex = (currentPage - 1) * pageSize;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public static int getCurrentPage(Map<String, Object> map) {

        int currentPage;
        if (map != null && map.containsKey("currentPage")) {
            currentPage = (Integer)map.get("currentPage");
            if (currentPage < 1) {
                currentPage = 1;
            }
        } else {
            currentPage = 1;
        }

        return currentPage;
    }

    public static int getPageSize(Map<String, Object> map) {

        int pageSize;
        if (map != null && map.containsKey("pageSize") && (Integer)map.get("pageSize") > 0) {
            pageSize = (Integer)map.get("pageSize");
            if (pageSize > 300) {
                pageSize = 300;
            }
        } else {
            pageSize = 10;
        }

        return pageSize;
    }
}
