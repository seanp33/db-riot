package dbriot.services;

import org.apache.log4j.Logger;

import dbriot.DbRiot;

public class RecoveryServiceImpl implements RecoveryService {

	private static Logger logger = Logger.getLogger(RecoveryServiceImpl.class);
	
	@Override
	public void recover() {
		logger.info("RecoveryServiceImpl.recover() called.");
	}

}
