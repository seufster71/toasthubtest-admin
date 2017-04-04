package org.toasthub.test.admin.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.admin.pages.PreferenceAdminPage;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.pages.login.LoginPage;
import org.toasthub.test.core.pages.member.MemberArea;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferenceTests extends BaseTests {

	@Test
	public void t1loginTest(){
		LoginPage.login();
		
		Assert.assertEquals(true,MemberArea.IsAt());
	}

	// create preference
	@Test
	public void t2createPreferenceTest(){
		PreferenceAdminPage.create();
		Driver.waitSeconds(2);
		Assert.assertEquals(true,PreferenceAdminPage.exists("German"));
	}
	
	// modify preference
	@Test
	public void t3modifyPreferenceTest(){
		PreferenceAdminPage.modify();
		Driver.waitSeconds(1);
		Assert.assertEquals(true,PreferenceAdminPage.exists("Something xx"));
	}
	
	// search for the new preference
	@Test
	public void t4searchPreferenceTest(){
		PreferenceAdminPage.search("German");
		Driver.waitSeconds(1);
		Assert.assertEquals(true,PreferenceAdminPage.exists("German"));
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
		PreferenceAdminPage.delete("German");
		
		//Assert.assertEquals(false,PreferenceAdminPage.exists());
	}
}
