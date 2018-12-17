package com.monmar.personalbudget.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stat {
	
	private String category;
	
	private String month;
	
	private double amount;
	
	public Stat() {
		
	}
	
	public Stat(String category, double amount) {
		this(category, "January", amount);
	}
	
	public Stat(String category, String month) {
		this(category, month, 0.00);
	}
	
	public Stat(String category, String month, double amount) {
		this.category = category;
		this.month = month;
		this.amount = amount;
	}
}
