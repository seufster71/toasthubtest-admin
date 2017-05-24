package org.toasthub.test.admin.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.admin.pages.UserAdminPage;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.pages.login.LoginPage;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTests extends BaseTests {

	@Test
	public void t1initTest(){
		LoginPage.login();
		UserAdminPage.gotoPage();
	}

	// create user
	@Test
	public void t2createUserTest(){
		UserAdminPage.create("LeRoy", "Theman", "Jenkins", "l.jenkins", "l.jenkins@ggg.com", "33344", "Spanish", "ljenkins@yyy.com", "DEBUG", true, false);
		Assert.assertEquals(true,UserAdminPage.exists("Jenkins"));
	}
	
	// modify user
	@Test
	public void t3modifyUserTest(){
		UserAdminPage.modify("Jenkins", "JenkinsModify");
		Assert.assertEquals(true,UserAdminPage.exists("JenkinsModify"));
	}
	
	// search for the new user
	@Test
	public void t4searchUserTest(){
		UserAdminPage.search("JenkinsModify");
		Assert.assertEquals(true,UserAdminPage.exists("JenkinsModify"));
	}

	// create a duplicate user -- negative test
	@Test
	public void t5addRoleTest(){
		UserAdminPage.addRole("JenkinsModify","Member");
	}
	
	
	// delete the user
	@Test
	public void t5deleteUserTest(){
		UserAdminPage.delete("JenkinsModify");
		Driver.waitSeconds(1);
		Assert.assertEquals(false,UserAdminPage.exists("JenkinsModify"));
	}
}
