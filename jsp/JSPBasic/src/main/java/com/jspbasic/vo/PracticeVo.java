package com.jspbasic.vo;

public class PracticeVo {
	private String prodName;
	private int amount;
	private int price;
	private String color;
	
	
	
	public PracticeVo(String prodName, int amount, int price, String color) {
		super();
		this.prodName = prodName;
		this.amount = amount;
		this.price = price;
		this.color = color;
	}



	public String getProdName() {
		return prodName;
	}




	public void setProdName(String prodName) {
		this.prodName = prodName;
	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public int getPrice() {
		return price;
	}




	public void setPrice(int price) {
		this.price = price;
	}




	public String getColor() {
		return color;
	}




	public void setColor(String color) {
		this.color = color;
	}




	



	@Override
	public String toString() {
		return "PracticeVo [prodName=" + prodName + ", amount=" + amount + ", price=" + price + ", color=" + color
				+ "]";
	}
	
}
