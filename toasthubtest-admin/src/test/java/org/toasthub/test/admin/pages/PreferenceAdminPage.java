package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class PreferenceAdminPage {
	
	public static void create() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=prefPublic");
		
		// open create modal
		Driver.getInstance().findElement(By.id("language-menu")).click();
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("language-add")).click();
		Driver.waitSeconds(1);
		
		// fill form
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_DEFAULT")).sendKeys("German");
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-en")).sendKeys("German");
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-es")).sendKeys("Deutsche");
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_CODE")).sendKeys("gr");
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		// not testing this yet
		//Driver.getInstance().findElement(By.id("radio-ADMIN_LANGUAGE_FORM_DEFAULT"));
		Select dir = new Select(Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_DIRECTION")));
		dir.selectByValue("ltr");
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-languageModal")).click();
		
	}

	public static void modify() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=language");
		
		// find test row ?
		
		// open modify modal
		Driver.getInstance().findElement(By.id("sb-3")).click();
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), "Something xx");
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-languageModal")).click();
		
	}
	
	public static void delete(String text) {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=language");
		
		// find test row ?
		List<WebElement> rows = Driver.getInstance().findElements(By.xpath("//tr/td[contains(text(),'"+text+"')]"));
		if (rows.size() == 1) {
			for (WebElement webElement : rows) {
				System.out.println(webElement.getText());
			}
			// delete
			//Driver.getInstance().findElement(By.id("db-3")).click();
			//Driver.waitSeconds(1);
		
			// acknowledge
			//Driver.getInstance().findElement(By.id("modalButtonAccept-acknowledgeModal")).click();
		}
	}
	
	public static void search(String text) {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=language");
		
		// search
		Driver.getInstance().findElement(By.id("languageSearchField")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.getInstance().findElement(By.id("languageSearchField-button")).click();
	}
	
	public static Boolean exists(String text) {
		
		List<WebElement> list = Driver.getInstance().findElements(By.xpath("//td[contains(text(),'"+text+"')]"));
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
