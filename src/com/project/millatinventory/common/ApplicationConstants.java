package com.project.millatinventory.common;

public interface ApplicationConstants {

	interface STATUS{
		String ACTIVE="ACTIVE";
		String DEACTIVE="DEACTIVE";
		String DELETED="DELETED";
	}
	
	interface BILL_TYPE{
		int EXCISE=1;
		int VAT=2;		
		
	}

	interface FILE_STATUS{
		String INITIATED="Initiated";
		String REJECTED="Rejected";
		String APPROVED="Approved";
		
	}
	interface EFT_TXN_STATUS{
		String INITIATED="I";
		String REJECTED="R";
		String APPROVED="A";
		
	}
	interface APPLICATION_STATUS{
		String INITIATED="Initiated";
		String REJECTED="Rejected";
		String APPROVED="Approved";
		
	}
	
	interface USER_LEVEL{
		String MAKER="1";
		String CHECKER="2";		
		
	}
	interface Length{
	int ApplicantNameLength = 50;
	int ShareholderNumber = 40;
	}
	Integer EXCAVATOR = 2;
}
