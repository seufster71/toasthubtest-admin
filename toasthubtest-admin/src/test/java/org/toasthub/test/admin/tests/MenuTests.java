package org.toasthub.test.admin.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.admin.pages.MenuAdminPage;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.pages.login.LoginPage;
import org.toasthub.test.core.pages.member.MemberArea;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuTests extends BaseTests {

	@Test
	public void t1loginTest(){
		LoginPage.login();
		
		Assert.assertEquals(true,MemberArea.IsAt());
	}

	// create menu
	@Test
	public void t2createMenuTest(){
		MenuAdminPage.create();
		Driver.waitSeconds(2);
		Assert.assertEquals(true,MenuAdminPage.exists("German"));
	}
	
	// modify menu
	@Test
	public void t3modifyMenuTest(){
		MenuAdminPage.modify();
		Driver.waitSeconds(1);
		Assert.assertEquals(true,MenuAdminPage.exists("Something xx"));
	}
	
	// search for the menu
	@Test
	public void t4searchMenuTest(){
		MenuAdminPage.search("German");
		Driver.waitSeconds(1);
		Assert.assertEquals(true,MenuAdminPage.exists("German"));
	}
	/*
	// create a duplicate menu -- negative test
	@Test
	public void createMenuTest(){
		MenuAdminPage.create();
		
		
	}
	
	
	*/
	// delete the menu
	@Test
	public void t5deleteMenuTest(){
		MenuAdminPage.delete("German");
		
		//Assert.assertEquals(false,LanguageAdminPage.languageExists());
	}
}
