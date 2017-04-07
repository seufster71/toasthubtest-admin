package org.toasthub.test.admin.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.admin.pages.PreferenceAdminPage;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.pages.login.LoginPage;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferenceTests extends BaseTests {

	@Test
	public void t1loginTest(){
		LoginPage.login();
		Driver.waitMilli(500);
		PreferenceAdminPage.gotoPage();
	}

	// create preference
	@Test
	public void t2createPreferenceTest(){
		PreferenceAdminPage.create("TEST_PAGE_NAME_TEST","PrefTest","PrefTest","Pref test spanish");
		Driver.waitSeconds(2);
		Assert.assertEquals(true,PreferenceAdminPage.exists("PrefTest"));
	}
	
	// modify preference
	@Test
	public void t3modifyPreferenceTest(){
		PreferenceAdminPage.modify("PrefTest","PrefModify");
		Driver.waitSeconds(1);
		Assert.assertEquals(true,PreferenceAdminPage.exists("PrefModify"));
	}
	
	// search for the new preference
	@Test
	public void t4searchPreferenceTest(){
		PreferenceAdminPage.search("PrefModify");
		Driver.waitMilli(500);
		Assert.assertEquals(true,PreferenceAdminPage.exists("PrefModify"));
	}
	/*
	// create a duplicate preference -- negative test
	@Test
	public void createPreferenceTest(){
		PreferenceAdminPage.create();
		
		
	}
	

	*/
	// delete the preference
	@Test
	public void t5deletePreferenceTest(){
		PreferenceAdminPage.delete("PrefModify");
		Driver.waitMilli(500);
		Assert.assertEquals(false,PreferenceAdminPage.exists("PrefModify"));
		Driver.waitMilli(500);
	}
}
