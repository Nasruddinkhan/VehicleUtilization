	package com.project.millatinventory.daoimpl;

	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Calendar;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;
	import javax.sql.DataSource;

	import org.apache.log4j.Logger;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Qualifier;
	import org.springframework.jdbc.core.BatchPreparedStatementSetter;
	import org.springframework.jdbc.core.BeanPropertyRowMapper;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
	import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
	import org.springframework.stereotype.Repository;

	import com.project.millatinventory.common.ApplicationConstants;
	import com.project.millatinventory.dao.ExternalFundTransferDao;
	import com.project.millatinventory.dao.UploadDao;
	import com.project.millatinventory.model.FileData;
	import com.project.millatinventory.model.FileHeader;
	import com.project.millatinventory.model.FileSearch;
	import com.project.millatinventory.model.ExternalTransactionSearch;
	import com.project.millatinventory.model.ExternalTransactionSummary;
	import com.project.millatinventory.model.ExternalTransations;

	@Repository("ExternalFundTransferDao")
	public class ExternalFundTransferDaoImpl implements ExternalFundTransferDao {

		final static Logger logger = Logger.getLogger(ExternalFundTransferDaoImpl.class);
		@Autowired
		private JdbcTemplate jdbcTemplate;
		//Changes for oracle connection
		@Autowired
		@Resource(name = "mySQLDataSource")
		//@Qualifier(value="oracleDataSource")
		private DataSource dataSource;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		@Override
		public List getFileDetails(ExternalTransactionSummary summary) {

			List list = new ArrayList(3);
			logger.info("getFileDetails");
			String sql = "SELECT 	PYM02BID , PYM02FIL ,	PYM02TOT ,	PYM02SUC ,	PYM02FAIL,"
					+ " PYM02PBR,	PYM02TAMT,	PYM02USRU,	PYM02DATU,	PYM02USRP,	PYM02DATP,"
					+ " PYM02ACT,	PYM02REM,	PYM02STS  FROM MRPYMP02 where 1=1 ";

			if (null != summary.getPYM02FIL() && !summary.getPYM02FIL().isEmpty()) {
				sql += " and PYM02FIL=? ";
				list.add(summary.getPYM02FIL());
			}

			if (null != summary.getPYM02USRU() && !summary.getPYM02USRU().isEmpty()) {
				sql += " and PYM02USRU=? ";
				list.add(summary.getPYM02USRU());
			}
			if (null != summary.getPYM02STS() && !summary.getPYM02STS().isEmpty()) {
				sql += " and PYM02STS=? ";
				list.add(summary.getPYM02STS());
			}

			sql += " order by PYM02BID desc";

			logger.info("SQL=" + sql);
			List<ExternalTransactionSummary> queryForList = jdbcTemplate.query(sql, list.toArray(),
					new BeanPropertyRowMapper(ExternalTransactionSummary.class));
			logger.info("Size=" + queryForList.size());
			return queryForList;
		}

		@Override
		public List getFileDetails(ExternalTransactionSearch eftSearch) throws ParseException {

			// Query query = null;
			List list = new ArrayList<>(4);

			logger.info("getFileDetails=");

			String sql = "SELECT 	PYM02BID , PYM02FIL ,	PYM02TOT ,	PYM02SUC ,	PYM02FAIL,"
					+ " PYM02PBR,	PYM02TAMT,	PYM02USRU,	PYM02DATU,	PYM02USRP,	PYM02DATP,"
					+ " PYM02ACT,	PYM02REM,	PYM02STS " + " FROM MRPYMP02 MR2 WHERE PYM02USRU = ? AND PYM02BID IN ( "
					+ " SELECT PYM03BID FROM MRPYMP03 MR3 where 1=1 ";

			list.add(eftSearch.getCreatedBy());

			if (eftSearch.getBranch() != null && !eftSearch.getBranch().isEmpty()) {
				logger.info("Branch=" + eftSearch.getBranch());

				// criteria.add(Restrictions.eq("fileData.empId",fileSearch.getEmpId()));

				sql += " AND PYM03AB1   = ? ";
				list.add(eftSearch.getBranch());
			}
			if (eftSearch.getAccountNumber() != null && !eftSearch.getAccountNumber().isEmpty()) {
				logger.info("Account No.=" + eftSearch.getAccountNumber());
				sql += " AND PYM03AN1   = ? ";
				list.add(eftSearch.getAccountNumber());

			}
			if (eftSearch.getPaymentPurposeCode() != null && !eftSearch.getPaymentPurposeCode().equals("0")
					&& !eftSearch.getPaymentPurposeCode().isEmpty()) {
				logger.info("purpose Code=" + eftSearch.getPaymentPurposeCode());
				sql += " AND PYM03PYP = ? ";
				list.add(eftSearch.getPaymentPurposeCode());
			}
			sql += " ) ";
			if (eftSearch.getFromDate() != null && !eftSearch.getFromDate().isEmpty() && eftSearch.getToDate() != null
					&& !eftSearch.getToDate().isEmpty()) {
				logger.info("From Date=" + eftSearch.getFromDate());
				logger.info("To Date=" + eftSearch.getToDate());

				/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

				java.util.Date fromDate = sdf1.parse(eftSearch.getFromDate());
				java.util.Date toDate = sdf1.parse(eftSearch.getToDate());

				Calendar cal = Calendar.getInstance();
				cal.setTime(toDate);
				cal.add(Calendar.DATE, 1);
				toDate = cal.getTime();
				logger.info("From Date=" + fromDate);
				logger.info("To Date=" + toDate);*/

				sql += " AND PYM02DATU  BETWEEN  '" + eftSearch.getFromDate() + "' AND '" + eftSearch.getToDate() + "'";
			}

			logger.info("Search =" + sql);

			List<ExternalTransactionSummary> queryForList = jdbcTemplate.query(sql, list.toArray(),
					new BeanPropertyRowMapper(ExternalTransactionSummary.class));

			logger.info(queryForList);
			return queryForList;
		}

		/*
		 * @Override public List<FileData> getFileData(Integer fileId) {
		 * 
		 * List list = null; try {
		 * 
		 * session = sessionFactory.openSession();
		 * 
		 * String sql =
		 * " from FileData f where f.fileHeader.fileId=:fileId order by id"; query =
		 * session.createQuery(sql); query.setParameter("fileId", fileId); list =
		 * query.list(); } finally { session.close(); } return list; }
		 */

		@Override
		public void saveFileHeader(ExternalTransactionSummary summary) {
			
			System.out.println("Hi");
			String insertSummary = " INSERT INTO mrpymp02 (PYM02BID, 	PYM02FIL,	PYM02TOT, "
					+ " PYM02TAMT,	PYM02USRU,	PYM02DATU,		PYM02STS	)" + " VALUES (?,?,?,?,?,?,?)";

			String insertDataSql = " INSERT INTO mrpymp03 "
					+ " (PYM03BID, 	PYM03ITM, 	PYM03AB1, 	PYM03AN1, 	PYM03AS1, 	PYM03TAMT, 	PYM03OAN1, 	PYM03BAD1,"
					+ " PYM03PYP, 	PYM03PD1, 	PYM03PD2, 	PYM03SWT, 	PYM03PCCY, 	PYM03CCODE, 	PYM03CAMT ) VALUES ("
					+ " ?, ? ,?, ? ,?, ? ,?, ? ,?, ? ,?, ? ,?, ? ,? )";

			jdbcTemplate.update(insertSummary, summary.getPYM02BID(), summary.getPYM02FIL(), summary.getPYM02TOT(),
					summary.getPYM02TAMT(), summary.getPYM02USRU(), summary.getPYM02DATU(), summary.getPYM02STS());
			System.out.println("Hiiiiiiiiiii");
			final List<ExternalTransations> fileData = summary.getFileData();

			jdbcTemplate.batchUpdate(insertDataSql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ExternalTransations externalTransations = fileData.get(i);

					ps.setString(1, externalTransations.getPYM03BID());
					ps.setInt(2, 0);
					ps.setString(3, externalTransations.getPYM03AB1());
					ps.setString(4, externalTransations.getPYM03AN1());
					ps.setString(5, externalTransations.getPYM03AS1());
					ps.setBigDecimal(6, externalTransations.getPYM03TAMT());
					ps.setString(7, externalTransations.getPYM03OAN1());
					ps.setString(8, externalTransations.getPYM03BAD1());
					ps.setString(9, externalTransations.getPYM03PYP());
					ps.setString(10, externalTransations.getPYM03PD1());
					ps.setString(11, externalTransations.getPYM03PD2());
					ps.setString(12, externalTransations.getPYM03SWT());
					ps.setString(13, externalTransations.getPYM03PCCY());
					ps.setString(14, externalTransations.getPYM03CCODE());
					ps.setBigDecimal(15, externalTransations.getPYM03CAMT());

				}

				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return fileData.size();
				}
			});
			// sessionFactory.openSession().save(fileHeader);
		}

		public static void main(String[] args) throws ParseException {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date fromDate = sdf1.parse("2017-05-14 13:02:07.0");
			System.out.println(fromDate);
		}

		/*
		 * @Override public FileData getFileDataById(Long fileDataId) {
		 * 
		 * FileData fileData = null; try { session = sessionFactory.openSession();
		 * 
		 * String sql = " from FileData f where f.id=:id order by id"; query =
		 * session.createQuery(sql); query.setParameter("id", fileDataId); fileData
		 * = (FileData) query.uniqueResult(); } finally { session.close(); } return
		 * fileData; }
		 */

		/*
		 * @Override public Integer updateFileRecord(FileData fileData) {
		 * 
		 * int count = 0; /*try { logger.info("Start updateFileRecord"); session =
		 * sessionFactory.openSession(); query = session .createQuery(
		 * "update FileData set empId=:empid, mobileNo=:mobileNo, companyCode=:companyCode ,"
		 * +
		 * " bankAccount=:bankAccount, nameAddress=:nameAddress, month=:month ,salaryDetail1=:salaryDetail1,"
		 * +
		 * " salaryDetail2=:salaryDetail2, ifscCode=:ifscCode , currency=:currency,bonusAmount=:bonusAmount ,"
		 * + " deductionAmount=:deductionAmount where id=:id  ");
		 * query.setParameter("empid", fileData.getEmpId());
		 * query.setParameter("mobileNo", fileData.getMobileNo());
		 * query.setParameter("companyCode", fileData.getCompanyCode());
		 * query.setParameter("bankAccount", fileData.getBankAccount());
		 * query.setParameter("nameAddress", fileData.getNameAddress());
		 * query.setParameter("month", fileData.getMonth());
		 * query.setParameter("salaryDetail1", fileData.getSalaryDetail1());
		 * query.setParameter("salaryDetail2", fileData.getSalaryDetail2());
		 * query.setParameter("ifscCode", fileData.getIfscCode());
		 * query.setParameter("currency", fileData.getCurrency());
		 * query.setParameter("bonusAmount", fileData.getBonusAmount());
		 * query.setParameter("deductionAmount", fileData.getDeductionAmount());
		 * query.setParameter("id", fileData.getId()); logger.info("update Query" +
		 * query); count = query.executeUpdate(); } finally { session.close(); }
		 * return count; }
		 */

		@Override
		public Integer updateStatus(String[] selectedIds, String status, ExternalTransactionSummary fileHeader) {

			int count = 0;
			String hql = "";
			MapSqlParameterSource map=new MapSqlParameterSource();
				logger.info(selectedIds + " updateStatus to " + status);
				hql = " update mrpymp02 set PYM02STS= '"+status+"' ";
				if (null != fileHeader && null != fileHeader.getPYM02REM() && !fileHeader.getPYM02REM().isEmpty()) {
					hql += " , PYM02REM=:PYM02REM";
					map.addValue("PYM02REM",fileHeader.getPYM02REM());
				}
				if (null != fileHeader && null != fileHeader.getPYM02DATP()) {
					hql += " , PYM02DATP=:PYM02DATP ";
					map.addValue("PYM02DATP",fileHeader.getPYM02DATP());
				}
				if (null != fileHeader && null != fileHeader.getPYM02USRP() && !fileHeader.getPYM02USRP().isEmpty()) {
					hql += " , PYM02USRP=:PYM02USRP";
					map.addValue("PYM02USRP",fileHeader.getPYM02USRP());
				}
			
				
				hql += " where PYM02BID  in (:ids)";			
				map.addValue("ids", Arrays.asList(selectedIds));

				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
				logger.info("update Query" + hql);
				count = template.update(hql, map);
				logger.info("update count="+count);
			return count;
		}

		@Override
		public List<ExternalTransactionSummary> getFileDetailsByStatus(String... status) {

			List list = null;
			/*
			 * try {
			 * 
			 * logger.info("getFileDetails"); session =
			 * sessionFactory.openSession();
			 * 
			 * String sql =
			 * "from FileHeader where status in(:status) order by fileId desc";
			 * query = session.createQuery(sql);
			 * 
			 * query.setParameterList("status", status);
			 * 
			 * logger.info("HQL=" + query); list = query.list(); } finally {
			 * session.close(); }
			 */
			return list;
		}

		@Override
		public String getMaxBatchId() {
			String maxBatchId = null;
			  logger.info("getMaxBatchId"); 	
			  //Query Changes 07/09/2017
			  String  sql = "SELECT nvl(MAX(PYM02BID),0) FROM mrpymp02 ";
			  logger.info("jdbcTemplatewassss =" +  jdbcTemplate);
		      maxBatchId = jdbcTemplate.queryForObject(sql,String.class);
			  // logger.info("HQL=" + sql); // maxBatchId =
		      
		     logger.info("BatchId =" +  maxBatchId); 
			 
			return maxBatchId;
		}

		@Override
		public List<ExternalTransations> getFileTransactions(String batchId) {
			logger.info("getFileTransactions" + batchId);
			String sql = " Select * from mrpymp03 f where f.PYM03BID=? ";
			logger.info("Select=" + sql);
			List<ExternalTransations> queryForList = jdbcTemplate.query(sql, new String[] { batchId },
					new BeanPropertyRowMapper(ExternalTransations.class));
			logger.info("Size=" + queryForList);
			return queryForList;
		}

	}


