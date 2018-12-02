package com.project.millatinventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DataEntryExpense")
public class DataEntryExpense {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dataEntry_Expense_Id")
	private Integer dataEntryExpenseId;
	
	@Override
	public String toString() {
		return "DataEntryExpense [dataEntryExpenseId=" + dataEntryExpenseId + ", dataEntry=" + dataEntryId + ", expenses="
				+ expenses + ", expenseAmount=" + expenseAmount + "]";
	}

	public Integer getDataEntryExpenseId() {
		return dataEntryExpenseId;
	}

	public void setDataEntryExpenseId(Integer dataEntryExpenseId) {
		this.dataEntryExpenseId = dataEntryExpenseId;
	}

	public Expenses getExpenses() {
		return expenses;
	}

	public void setExpenses(Expenses expenses) {
		this.expenses = expenses;
	}
	private Integer dataEntryId;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="expenseId",referencedColumnName="expenseId")		
	private Expenses expenses;
	
	@Column(name="Expense_Amount")
	private String expenseAmount;

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public Integer getDataEntryId() {
		return dataEntryId;
	}

	public void setDataEntryId(Integer dataEntryId) {
		this.dataEntryId = dataEntryId;
	}

	
	
}
