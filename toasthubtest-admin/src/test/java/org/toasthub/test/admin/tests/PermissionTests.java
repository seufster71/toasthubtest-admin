package org.toasthub.test.admin.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.admin.pages.PermissionAdminPage;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.pages.login.LoginPage;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PermissionTests extends BaseTests {

	@Test
	public void t1initTest(){
		LoginPage.login();
		PermissionAdminPage.gotoPage();
	}

	// create Permission
	@Test
	public void t2createPermissionTest(){
		PermissionAdminPage.create("German", "German", "Deutsche", "gr", "ltr");
		Assert.assertEquals(true,PermissionAdminPage.exists("German"));
	}
	
	// modify Permission
	@Test
	public void t3modifyPermissionTest(){
		PermissionAdminPage.modify("German", "GermanModify");
		Assert.assertEquals(true,PermissionAdminPage.exists("GermanModify"));
	}
	
	// search for the new Permission
	@Test
	public void t4searchPermissionTest(){
		PermissionAdminPage.search("GermanModify");
		Assert.assertEquals(true,PermissionAdminPage.exists("GermanModify"));
	}
	/*
	// create a duplicate Permission -- negative test
	@Test
	public void createPermissionTest(){
		PermissionAdminPage.createPermission();
	
	}
	
	// delete default Permission -- negative test
	
	*/
	// delete the Permission
	@Test
	public void t5deletePermissionTest(){
		PermissionAdminPage.delete("GermanModify");
		Driver.waitSeconds(1);
		Assert.assertEquals(false,PermissionAdminPage.exists("GermanModify"));
	}
}
