package com.job.util;


public class DataPageWrapper<T>  {
    private int status;
    private T data;

    private int pageSize;
    private int pageIndex;
    private int totalNumber;
    private int totalPage;
    
    
    
    
	



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public int getTotalNumber() {
		return totalNumber;
	}



	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}



	public int getTotalPage() {
		return totalPage;
	}



	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}



	public DataPageWrapper() {
		status = 0;
    }

   

    public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

 
}
