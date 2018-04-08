package com.job.util;


public class DataWrapper<T>  {
    private int status;
    private T data;

	public DataWrapper() {
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
