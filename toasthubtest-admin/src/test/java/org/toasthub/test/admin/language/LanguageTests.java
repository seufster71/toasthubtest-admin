package org.toasthub.test.admin.language;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.toasthub.test.core.general.BaseTests;
import org.toasthub.test.core.login.LoginPage;
import org.toasthub.test.core.member.MemberArea;
import org.toasthub.test.core.selenium.Driver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LanguageTests extends BaseTests {

	@Test
	public void t1loginTest(){
		LoginPage.login();
		
		Assert.assertEquals(true,MemberArea.IsAt());
	}

	// create language
	@Test
	public void t2createLanguageTest(){
		LanguageAdminPage.createLanguage();
		Driver.waitSeconds(2);
		Assert.assertEquals(true,LanguageAdminPage.languageExists("German"));
	}
	
	// modify language
	@Test
	public void t3modifyLanguageTest(){
		LanguageAdminPage.modifyLanguage();
		Driver.waitSeconds(1);
		Assert.assertEquals(true,LanguageAdminPage.languageExists("Something xx"));
	}
	
	// search for the new language
	@Test
	public void t4searchLanguageTest(){
		LanguageAdminPage.searchLanguage("German");
		Driver.waitSeconds(1);
		Assert.assertEquals(true,LanguageAdminPage.languageExists("German"));
	}
	/*
	// create a duplicate language -- negative test
	@Test
	public void createLanguageTest(){
		LanguageAdminPage.createLanguage();
		
		
	}
	
	// delete default language -- negative test
	
	
	// delete the language
	@Test
	public void deleteLanguageTest(){
		LanguageAdminPage.deleteLanguage();
		
		Assert.assertEquals(false,LanguageAdminPage.languageExists());
	}
	
	*/
}
