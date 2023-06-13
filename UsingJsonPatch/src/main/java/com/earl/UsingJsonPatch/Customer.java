package com.earl.UsingJsonPatch;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

	@Id
	private String id;
	private String telephone;
	private BigDecimal balance;

	public Customer() {

	}

	public Customer(String id, String telephone, BigDecimal balance) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", telephone=" + telephone + ", balance=" + balance + "]";
	}
}
