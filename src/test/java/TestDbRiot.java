package dbriot.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dbriot.DbRiot;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class TestDbRiot {

	@Autowired
	DbRiot dbRiot;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		dbRiot.init();
	}

}
