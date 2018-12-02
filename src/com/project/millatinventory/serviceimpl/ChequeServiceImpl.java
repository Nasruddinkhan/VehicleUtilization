package com.project.millatinventory.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.ChequeDao;
import com.project.millatinventory.model.Cheque;
import com.project.millatinventory.service.ChequeService;
@Service("ChequeServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class ChequeServiceImpl implements ChequeService{
	@Autowired
	private ChequeDao chequeDao;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {
		return chequeDao.getChequeByNumber(chequeNumber);
	}
}