package org.toasthub.test.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class LanguageAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/language");
	}
	
	public static void create(String titleDefault, String titleEN, String titleES, String code, String direction) {
		
		// open create modal
		Driver.findOrWaitById("ADMIN_LANGUAGE-ADD").click();
		
		// fill form
		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_TITLE-DEFAULT").sendKeys(titleDefault);
		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_TITLE-TEXT-en").sendKeys(titleEN);
		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_TITLE-TEXT-es").sendKeys(titleES);
		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_CODE").sendKeys(code);
		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_ACTIVE-SWITCH").findElement(By.className("active")).click();
		// not testing this yet
		//Driver.getInstance().findElement(By.id("radio-ADMIN_LANGUAGE_FORM_DEFAULT"));
		Select dir = new Select(Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_DIRECTION"));
		dir.selectByValue(direction);
		// save
		Driver.findOrWaitById("modalButtonAccept-languageModal").click();
		
	}

	public static void modify(String search, String titleEN) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'sb')]").click();

		Driver.findOrWaitById("ADMIN_LANGUAGE_FORM_TITLE_TEXT-en").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.findOrWaitById("modalButtonAccept-languageModal").click();
		
	}
	
	public static void delete(String search) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'db')]").click();
		// acknowledge
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
	}
	
	public static void search(String text) {
		
		// search
		Driver.findOrWaitById("languageSearchField").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.findOrWaitById("languageSearchField-button").click();
	}
	
	public static Boolean exists(String text) {
		return Driver.exists("//td[contains(text(),'"+text+"')]");
	}
	
}
