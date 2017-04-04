package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class LanguageAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=language");
	}
	
	public static void create(String titleDefault, String titleEN, String titleES, String code, String direction) {
		
		// open create modal
		Driver.getInstance().findElement(By.id("language-menu")).click();
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("language-add")).click();
		Driver.waitSeconds(1);
		
		// fill form
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_DEFAULT")).sendKeys(titleDefault);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_CODE")).sendKeys(code);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		// not testing this yet
		//Driver.getInstance().findElement(By.id("radio-ADMIN_LANGUAGE_FORM_DEFAULT"));
		Select dir = new Select(Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_DIRECTION")));
		dir.selectByValue(direction);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-languageModal")).click();
		
	}

	public static void modify(String search, String titleEN) {
		
		// find test row ?
		WebElement x = Driver.getInstance().findElement(By.xpath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'sb')]"));
		// open modify modal
		x.click();
		System.out.println(x.getAttribute("id"));
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("ADMIN_LANGUAGE_FORM_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-languageModal")).click();
		
	}
	
	public static void delete(String search) {
		
		// find test row ?
		WebElement x = Driver.getInstance().findElement(By.xpath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'db')]"));
		// delete
		x.click();
		System.out.println(x.getText());
		Driver.waitSeconds(1);
		
		// acknowledge
		Driver.getInstance().findElement(By.id("modalButtonAccept-acknowledgeModal")).click();
	}
	
	public static void search(String text) {
		
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
