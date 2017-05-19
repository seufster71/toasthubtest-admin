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

	// create language
	@Test
	public void t2createUserTest(){
		UserAdminPage.create("German", "German", "Deutsche", "gr", "ltr");
		Assert.assertEquals(true,UserAdminPage.exists("German"));
	}
	
	// modify language
	@Test
	public void t3modifyUserTest(){
		UserAdminPage.modify("German", "GermanModify");
		Assert.assertEquals(true,UserAdminPage.exists("GermanModify"));
	}
	
	// search for the new language
	@Test
	public void t4searchUserTest(){
		UserAdminPage.search("GermanModify");
		Assert.assertEquals(true,UserAdminPage.exists("GermanModify"));
	}
	/*
	// create a duplicate language -- negative test
	@Test
	public void createUserTest(){
		UserAdminPage.createUser();
	}
	
	*/
	// delete the language
	@Test
	public void t5deleteUserTest(){
		UserAdminPage.delete("GermanModify");
		Driver.waitSeconds(1);
		Assert.assertEquals(false,UserAdminPage.exists("GermanModify"));
	}
}
