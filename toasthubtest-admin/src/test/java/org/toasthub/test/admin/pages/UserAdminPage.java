package org.toasthub.test.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class UserAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=user");
	}
	
	public static void create(String titleDefault, String titleEN, String titleES, String code, String direction) {
		
		// open create modal
		Driver.findOrWaitById("user-menu").click();
		Driver.findOrWaitById("user-add").click();

		// fill form
		Driver.findOrWaitById("ADMIN_USER_FORM_TITLE_DEFAULT").sendKeys(titleDefault);
		Driver.findOrWaitById("ADMIN_USER_FORM_TITLE_TEXT-en").sendKeys(titleEN);
		Driver.findOrWaitById("ADMIN_USER_FORM_TITLE_TEXT-es").sendKeys(titleES);
		Driver.findOrWaitById("ADMIN_USER_FORM_CODE").sendKeys(code);
		Driver.findOrWaitById("ADMIN_USER_FORM_ACTIVE-0").findElement(By.xpath("..")).click();
		// not testing this yet
		//Driver.getInstance().findElement(By.id("radio-ADMIN_USER_FORM_DEFAULT"));
		Select dir = new Select(Driver.findOrWaitById("ADMIN_USER_FORM_DIRECTION"));
		dir.selectByValue(direction);
		// save
		Driver.findOrWaitById("modalButtonAccept-userModal").click();
		
	}

	public static void modify(String search, String titleEN) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'sb')]").click();
		Driver.findOrWaitById("ADMIN_USER_FORM_TITLE_TEXT-en").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.findOrWaitById("modalButtonAccept-userModal").click();
		
	}
	
	public static void delete(String search) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'db')]").click();
		// acknowledge
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
	}
	
	public static void search(String text) {
		
		// search
		Driver.findOrWaitById("userSearchField").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.findOrWaitById("userSearchField-button").click();
	}
	
	public static Boolean exists(String text) {
		return Driver.exists("//td[contains(text(),'"+text+"')]");
	}
	
}
