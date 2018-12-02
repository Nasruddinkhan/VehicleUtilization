package com.project.millatinventory.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FILE_DATA")
public class FileData implements Serializable {
	
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	  /*  @Id
	     @Column(name = "Id")
	    
	    //@SequenceGenerator(name = "fileData_seq", sequenceName="fileData_seq", allocationSize = 1)
	    @GeneratedValue(generator ="fileData_seq")*/
		@Id
		@Column(name = "Id")
		//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="FILEDATA_SEQ")
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	   
		@Column(name = "Emp_Id")
	     private String empId;
	      
	     @Column(name = "Mobile_No")
	     private String mobileNo;
	     
	     @Column(name = "Company_Code")
	     private String companyCode;
	     
	     @Column(name = "Salary")
	     private BigDecimal salary;
	     
	     @Column(name = "Bank_Account")
	     private String bankAccount;
	     
	     @Column(name = "Name_Address")
	     private String nameAddress;
	     
	     @Column(name = "Month")
	     private String month;
	     
	     @Column(name = "Salary_Detail_1")
	     private String salaryDetail1;
	     
	     @Column(name = "Salary_Detail_2")
	     private String salaryDetail2;
	     
	     @Column(name = "ifsc_code")
	     private String ifscCode;
	     
	     @Column(name = "currencyCode")
	     private String currency;
	     
	     @Override
		public String toString() {
			return "FileData [id=" + id + ", empId=" + empId + ", mobileNo="
					+ mobileNo + ", companyCode=" + companyCode + ", salary="
					+ salary + ", bankAccount=" + bankAccount
					+ ", nameAddress=" + nameAddress + ", month=" + month
					+ ", salaryDetail1=" + salaryDetail1 + ", salaryDetail2="
					+ salaryDetail2 + ", ifscCode=" + ifscCode + ", currency="
					+ currency + ", bonusAmount=" + bonusAmount
					+ ", deductionAmount=" + deductionAmount + ", fileHeader="
					+   "]";
		}

		@Column(name = "bonus_Amount")
	     private BigDecimal bonusAmount;
	     
	     @Column(name = "deduction_Amount")
	     private BigDecimal deductionAmount;

	     @ManyToOne(fetch =FetchType.EAGER)	 	
	    @JoinColumn(name = "File_ID")
	    private FileHeader fileHeader;

		public Long getId() {
			return id;
		}
		

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getBankAccount() {
			return bankAccount;
		}
		public void setBankAccount(String bankAccount) {
			this.bankAccount = bankAccount;
		}

		public String getNameAddress() {
			return nameAddress;
		}

		public void setNameAddress(String nameAddress) {
			this.nameAddress = nameAddress;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getSalaryDetail1() {
			return salaryDetail1;
		}

		public void setSalaryDetail1(String salaryDetail1) {
			this.salaryDetail1 = salaryDetail1;
		}

		public String getSalaryDetail2() {
			return salaryDetail2;
		}

		public void setSalaryDetail2(String salaryDetail2) {
			this.salaryDetail2 = salaryDetail2;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public BigDecimal getBonusAmount() {
			return bonusAmount;
		}

	
		public FileHeader getFileHeader() {
			return fileHeader;
		}

		public void setFileHeader(FileHeader fileHeader) {
			this.fileHeader = fileHeader;
		}

		public BigDecimal getSalary() {
			return salary;
		}

		public void setSalary(BigDecimal salary) {
			this.salary = salary;
		}

		public BigDecimal getDeductionAmount() {
			return deductionAmount;
		}

		public void setDeductionAmount(BigDecimal deductionAmount) {
			this.deductionAmount = deductionAmount;
		}

		public void setBonusAmount(BigDecimal bonusAmount) {
			this.bonusAmount = bonusAmount;
		}
	
}