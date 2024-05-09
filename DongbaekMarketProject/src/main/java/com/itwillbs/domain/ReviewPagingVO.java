package com.itwillbs.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviewPagingVO {

	
	private static final Logger logger = LoggerFactory.getLogger(ReviewPagingVO.class);
	
    private int totalCount; // 전체 리뷰 수
    private int startPage; // 시작 페이지 번호
    private int endPage; // 끝 페이지 번호
    private boolean prev; // 이전 페이지 버튼 활성화 여부
    private boolean next; // 다음 페이지 버튼 활성화 여부

    private int pageBlock = 5; // 페이지 블록의 개수

    private ReviewCri cri;

    public ReviewPagingVO() {
        // 기본 생성자
    }

    // 페이징 정보를 계산하는 메서드
    public void pageCalc() {
        // 끝 페이지 번호 계산
        endPage = (int) Math.ceil(cri.getPage() / (double) pageBlock) * pageBlock;
        
        // 시작 페이지 번호 계산
        startPage = (endPage - pageBlock) + 1;

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / cri.getPageSize());

        // 끝 페이지 번호가 총 페이지 수보다 큰 경우 조정
        if (endPage > totalPages) {
            endPage = totalPages;
        }
        
        System.out.println(endPage);
        
        // 이전 페이지 버튼 활성화 여부 계산
        prev = startPage != 1;

        // 다음 페이지 버튼 활성화 여부 계산
        next = endPage * cri.getPageSize() < totalCount;
    }

    // Getter 및 Setter 메서드
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        pageCalc(); // 페이징 정보 다시 계산
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(int pageBlock) {
        this.pageBlock = pageBlock;
    }

    public void setCri(ReviewCri cri) {
        this.cri = cri;
        logger.debug(" 페이징 처리에 필요한 정보를 계산 - 시작 ");
        //pageCalc();
        logger.debug(" 페이징 처리에 필요한 정보를 계산 - 끝 ");
    }

    public ReviewCri getCri() {
        return cri;
    }

    @Override
    public String toString() {
        return "ReviewPagingVO [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage
                + ", prev=" + prev + ", next=" + next + ", pageBlock=" + pageBlock + ", cri=" + cri + "]";
    }
}
