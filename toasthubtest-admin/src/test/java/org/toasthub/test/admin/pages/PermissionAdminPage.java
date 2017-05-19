package org.toasthub.test.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class PermissionAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=permission");
	}
	
	public static void create(String titleDefault, String titleEN, String titleES, String code, String direction) {
		
		// open create modal
		Driver.findOrWaitById("permission-menu").click();
		Driver.findOrWaitById("permission-add").click();
		
		// fill form
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_TITLE_DEFAULT").sendKeys(titleDefault);
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_TITLE_TEXT-en").sendKeys(titleEN);
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_TITLE_TEXT-es").sendKeys(titleES);
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_CODE").sendKeys(code);
		
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_CAN_READ-0").findElement(By.xpath("..")).click();
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_CAN_WRITE-0").findElement(By.xpath("..")).click();
		
		// select application
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_APPLICATION-button").click();
		
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_ACTIVE-0").findElement(By.xpath("..")).click();

		Select dir = new Select(Driver.findOrWaitById("ADMIN_PERMISSION_FORM_DIRECTION"));
		dir.selectByValue(direction);
		// save
		Driver.findOrWaitById("modalButtonAccept-permissionModal").click();
		
	}

	public static void modify(String search, String titleEN) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'sb')]").click();
		Driver.findOrWaitById("ADMIN_PERMISSION_FORM_TITLE_TEXT-en").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.findOrWaitById("modalButtonAccept-permissionModal").click();
		
	}
	
	public static void delete(String search) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'db')]").click();
		// acknowledge
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
	}
	
	public static void search(String text) {
		
		// search
		Driver.findOrWaitById("permissionSearchField").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.findOrWaitById("permissionSearchField-button").click();
	}
	
	public static Boolean exists(String text) {
		return Driver.exists("//td[contains(text(),'"+text+"')]");
	}
	
}
