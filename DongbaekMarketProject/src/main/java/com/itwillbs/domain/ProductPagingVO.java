package com.itwillbs.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPagingVO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductPagingVO.class);
	
	private int totalCount; // 전체 글의 수
	private int startPage; // 블럭의 시작번호
	private int endPage; // 블럭의 끝번호

	private boolean prev; // 이전버튼
	private boolean next; // 다음버튼

	private int pageBlock = 2; // 페이지 블럭의 개수
	
	private ProductCri cri;

	public void setCri(ProductCri cri) {
		this.cri = cri;
		logger.debug(" 페이징 처리에 필요한 정보를 계산 - 시작 ");
		
//		pageCalc(cri);
		
		logger.debug(" 페이징 처리에 필요한 정보를 계산 - 끝 ");
	}
	

	
	public void pageCalc() {
		// endPage
		endPage = (int) Math.ceil(cri.getPage() / (double) pageBlock) * pageBlock;

		logger.debug(" endPage ",endPage);
		System.out.println(endPage);
		System.out.println(totalCount);
		System.out.println((double) cri.getPageSize());
		// startPage
		startPage = (endPage - pageBlock) + 1;

		int tmpEndPage = (int) Math.ceil(totalCount / (double) cri.getPageSize());

		if (endPage > tmpEndPage) { // 글이 없음
		    endPage = tmpEndPage;
		}

		System.out.println(endPage);

		// prev
		// prev = startPage == 1? false : true;
		prev = startPage != 1;

		// next
		// next = endPage * cri.getPageSize() >= totalCount? false : true;
		next = endPage * cri.getPageSize() < totalCount;
	}

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pageCalc();
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

	public ProductCri getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "ProductPagingVO [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + ", pageBlock=" + pageBlock + ", cri=" + cri + "]";
	}

	
}
