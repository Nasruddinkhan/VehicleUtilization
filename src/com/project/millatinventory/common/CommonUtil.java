package com.project.millatinventory.common;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibm.icu.text.SimpleDateFormat;
import com.project.millatinventory.common.ApplicationConstants.BILL_TYPE;

public class CommonUtil {

	public static Map<Integer, String> getBillType(){
		Map<Integer, String>mp=new HashMap<Integer, String>();
		mp.put(BILL_TYPE.EXCISE, "EXCISE");
		mp.put(BILL_TYPE.VAT, "VAT");
		return mp;
		
	}
	public static boolean isNumeric(String val){
		
		return java.util.regex.Pattern.matches("\\d+", val);
	}
	public static boolean isAlphaNumeric(String val){
		return val.matches("^[a-zA-Z0-9]*$");
	}
	public static void main(String[] args) {
		System.out.println(getToday("dd-MMMMM-yy"));
	}
	public static String getToday(String format){
		
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
public static String convertFormat(Date date,String format){
		
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		return sdf.format(date);
	}
public static String formatAmount(int amount){
	
	NumberFormat formatter = NumberFormat.getInstance();
	formatter.setMinimumFractionDigits(2);
	return formatter.format(amount);
	 
}
public static int  db_Backup(String DBName, String StorageLocation,String sqlDump, String userName, String Pass) throws IOException, InterruptedException {
	Process p = null;
	int c=0;
	 Runtime runtime = Runtime.getRuntime();
     p = runtime.exec(sqlDump+"mysqldump -u"+userName+" -p"+Pass+" --add-drop-database -B "+DBName+" -r " + ""+StorageLocation+"" + "Navdurga" + ".sql");
     c=p.waitFor();
	return c;
}
public static int db_Restore(String pathsqlconsole, String UserName, String Password, String source) throws IOException, InterruptedException {
	String[] restoreCmd = new String[]{pathsqlconsole+"mysql ", "--user=" + UserName, "--password=" + Password, "-e", "source " + source};
    Process runtimeProcess;
    int processComplete=0;
        runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
        processComplete = runtimeProcess.waitFor();
	return processComplete;
}
}
