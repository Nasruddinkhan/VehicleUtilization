package com.project.millatinventory.daoimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.dao.StudentDao;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.Student;
import com.project.millatinventory.model.StudentSearch;

@Repository("StudentDao")
public class StudentDaoImpl implements StudentDao {
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	Logger logger = Logger.getLogger(StudentDaoImpl.class);

	@Override
	public List<Object[]> getStudent(StudentSearch studentSearch) throws ParseException {
		Criteria criteria = null;
		Session session = null;
		List list = null;
		try {
			String hqlC = " SELECT s.studentId, s.studentName, s.studentClass, s.studentAge, s.joiningDate, sb.subjectName,sb.marks, sb.results "+
							" FROM Student s JOIN s.subject sb";
			
			session = sessionFactory.openSession();
			java.util.Date fromDate = null;
			java.util.Date toDate = null;
			if (studentSearch.getFromDate() != null && !studentSearch.getFromDate().isEmpty()
					&& studentSearch.getToDate() != null && !studentSearch.getToDate().isEmpty()) {
				logger.info("From Date=" + studentSearch.getFromDate());
				logger.info("To Date=" + studentSearch.getToDate());

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

				fromDate = sdf1.parse(studentSearch.getFromDate());
				toDate = sdf1.parse(studentSearch.getToDate());

				/*Calendar cal = Calendar.getInstance();
				cal.setTime(toDate);
				cal.add(Calendar.DATE, 1);
				toDate = cal.getTime();*/
				logger.info("From Date=" + fromDate);
				logger.info("To Date=" + toDate);

				hqlC += " where joiningDate between :fromDate and :toDate ";
				
			}
			
			
			
			
			
			
			Query createQuery = session.createQuery(hqlC);
			
			if (studentSearch.getFromDate() != null && !studentSearch.getFromDate().isEmpty()
					&& studentSearch.getToDate() != null && !studentSearch.getToDate().isEmpty()) {
				createQuery.setParameter("fromDate", fromDate);
				createQuery.setParameter("toDate", toDate);
			}
			
			/*criteria = session.createCriteria(Student.class);

			if (studentSearch.getFromDate() != null && !studentSearch.getFromDate().isEmpty()
					&& studentSearch.getToDate() != null && !studentSearch.getToDate().isEmpty()) {
				logger.info("From Date=" + studentSearch.getFromDate());
				logger.info("To Date=" + studentSearch.getToDate());

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

				java.util.Date fromDate = sdf1.parse(studentSearch.getFromDate());
				java.util.Date toDate = sdf1.parse(studentSearch.getToDate());

				Calendar cal = Calendar.getInstance();
				cal.setTime(toDate);
				cal.add(Calendar.DATE, 1);
				toDate = cal.getTime();
				logger.info("From Date=" + fromDate);
				logger.info("To Date=" + toDate);

				criteria.add(Restrictions.between("joiningDate", fromDate, toDate));
			}
		
			*/
			//list = criteria.list();
			list = createQuery.list();
			System.out.println(list);
			logger.info("list="+list.size());
		} finally {

			session.close();
		}
		System.out.println(list);

		return list;
	}
}