package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class PreferenceAdminPage {
	
	public static void gotoPage(){
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=prefPublic");
	}
	
	public static void create(String name, String defaultTitle, String titleEN, String titleES) {
		
		// open create modal
		Driver.getInstance().findElement(By.id("pref-menu")).click();
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("pref-add")).click();
		Driver.waitSeconds(1);
		
		// fill form
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		// not testing this yet
		//Select dir = new Select(Driver.getInstance().findElement(By.id("APP_PAGE_FORM_CATEGORY")));
		//dir.selectByValue("PUBLIC");
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
		
	}

	public static void modify(String name, String titleEN) {
		
		// find test row ?
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]/../following-sibling::ul/li/a[contains(@id,'settings-menu')]"));
		// open menu  
		m.click();
		WebElement o = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]/../following-sibling::ul/li/ul/li/a[contains(text(),'Modify')]"));
		// open menu
		o.click();
		
		// open modify modal
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
		
	}
	
	public static void delete(String name) {
	
		// find test row ?
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]/../following-sibling::ul/li/a[contains(@id,'settings-menu')]"));
		// open menu
		m.click();
		WebElement o = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]/../following-sibling::ul/li/ul/li/a[contains(text(),'Delete')]"));
		// open menu
		o.click();
			
		Driver.waitSeconds(1);
		
		// acknowledge
		Driver.getInstance().findElement(By.id("modalButtonAccept-acknowledgeModal")).click();
		
	}
	
	public static void search(String text) {
	
		// search
		Driver.getInstance().findElement(By.id("preferenceSearchField")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.getInstance().findElement(By.id("preferenceSearchField-button")).click();
	}
	
	public static Boolean exists(String name) {
		
		List<WebElement> list = Driver.getInstance().findElements(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
