package com.itwillbs.domain;

public class ReviewCri {
    
    private int page;
    private int pageSize;
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        if(pageSize <= 0 || pageSize > 100) {
            this.pageSize = 10;
            return;
        }
        this.pageSize = pageSize;
    }
    
    public ReviewCri() {
        this.page = 1;
        this.pageSize = 5;
    }
    
    // 시작 레코드 번호 계산
    public int getStartRecord() {
        return (page - 1) * pageSize;
    }
    
    @Override
    public String toString() {
        return "ReviewCri [page=" + page + ", pageSize=" + pageSize + "]";
    }
    
}
