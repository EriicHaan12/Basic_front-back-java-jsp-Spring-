package com.mini.etc;

public class PagingInfo {
	private int pageNo; // 현재 페이지
	private int viewPostCntPerPage; // 한페이지 당 보여줄 글의 갯수
	private int totalPageCnt;// 총 페이지 수
	private int startRowIndex;// 페이지에서 보여주기 시작할 row 의 index 번호
	private int totalPostCnt;// 전체 글의 갯수

	private int pageCntPerBlock = 3; // 한개의 블럭에 3개의 페이지를 보여준다.
	// 현재 페이지에서 보여지는 페이지 버튼의 갯수
	private int PageBlockOfCurrentPage;// 현재 페이지가 속한 페이징 블럭
	private int startNumOfCurrentPagingBlock; // 현재 페이징 블럭의 시작번호
	private int endNumOfCurrentPagingBlock; // 현재 페이징 블럭의 끝번호

	public int getTotalPostCnt() {
		return this.totalPostCnt;
	}

	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getViewPostCntPerPage() {
		return this.viewPostCntPerPage;
	}

	public void setViewPostCntPerPage(int viewPostCntPerPage) { // 한 페이지당 보여줄 글의 갯수
		this.viewPostCntPerPage = viewPostCntPerPage;
	}

	public int getTotalPageCnt() {
		return this.totalPageCnt;
	}

	public void setTotalPageCnt(int totalPostCnt, int viewPostCntPerPage) {// 총 페이지 수
		if ((totalPostCnt % viewPostCntPerPage) == 0) {
			// 3) 총 페이지 수 : 게시판 글 수/ 하나의 페이지에 보여줄 글의 수
			// 20/3 = 6(나누어 떨어지지 않았으므로 +1) => 7페이지 (나머지를 보여줄 페이지 하나가 더 필요하기 때문)
			this.totalPageCnt = totalPostCnt / viewPostCntPerPage;
		} else {
			this.totalPageCnt = (totalPostCnt / viewPostCntPerPage) + 1;
		}
	}

	public int getStartRowIndex() {
		return this.startRowIndex;
	}

	public void setStartRowIndex(int pageNo) {
		// 보여주기 시작할 row index 번호 = 한 페이지에서 보여줄 글의 갯수* (현재 페이지-1)
		// 1페이지 3*(1-1)=0
		this.startRowIndex = this.viewPostCntPerPage * (pageNo - 1);
	}

//----------------------페이징 블럭 ------------------------------

	public int getPageCntPerBlock() {
		// 1개의 블럭에 몇개 페이지를 둘것인가.
		return this.pageCntPerBlock;
	}

	public void setPageCntPerBlock(int pageCntPerBlock) {
		this.pageCntPerBlock = pageCntPerBlock;
	}

	public int getPageBlockOfCurrentPage() {
		return this.PageBlockOfCurrentPage;
	}

	public void setPageBlockOfCurrentPage(int pageNo) {
		// 현재 페이지가 속한 페이징 블럭 : 현재 페이지 번호 /

		// 나누어 떨어지지 않으면 올림 해준다
		if (pageNo % this.pageCntPerBlock == 0) {
			this.PageBlockOfCurrentPage = pageNo / this.pageCntPerBlock;
		} else {
			// double 타입만 받는 math.ceil을 위해 형변환 해주고, 원래 int로 반환해주기위해 한번더 형변환
			this.PageBlockOfCurrentPage = (int) Math.ceil((double)pageNo / this.pageCntPerBlock);
		}
	}

	public int getStartNumOfCurrentPagingBlock() {
		return startNumOfCurrentPagingBlock;
	}

	public void setStartNumOfCurrentPagingBlock(int pageBlockOfCurrentPage) {
		// 현재 페이징 블럭 시작 번호 : ((현재 페이징블럭 - 1) * pageCntPerBlock)+ 1
		this.startNumOfCurrentPagingBlock = ((pageBlockOfCurrentPage - 1) * this.pageCntPerBlock) + 1;
	}

	public int getendNumOfCurrentPagingBlock() {
		return endNumOfCurrentPagingBlock;
	}

	public void setEndNumOfCurrentPagingBlock(int startNumOfCurrentPagingBlock) {
		// 현재 페이징 블럭 끝 번호 : (현재 페이징 블럭 시작 번호 + pageCntPerBlock)-1
		this.endNumOfCurrentPagingBlock = (startNumOfCurrentPagingBlock + this.pageCntPerBlock) - 1;

		if (this.endNumOfCurrentPagingBlock > this.totalPageCnt) {
			this.endNumOfCurrentPagingBlock = this.totalPageCnt;
		}
	}

	@Override
	public String toString() {
		return "PagingInfo [pageNo=" + pageNo + ", viewPostCntPerPage=" + viewPostCntPerPage + ", totalPageCnt="
				+ totalPageCnt + ", startRowIndex=" + startRowIndex + ", totalPostCnt=" + totalPostCnt
				+ ", pageCntPerBlock=" + pageCntPerBlock + ", PageBlockOfCurrentPage=" + PageBlockOfCurrentPage
				+ ", startNumOfCurrentPagingBlock=" + startNumOfCurrentPagingBlock + ", endNumOfCurrentPagingBlock="
				+ endNumOfCurrentPagingBlock + "]";
	}

}
