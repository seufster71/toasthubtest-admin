package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class ServiceCrawlerAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=service");
	}
	
	public static void create(String name, String api, String app, String className, String category) {
		
		// open create modal
		Driver.getInstance().findElement(By.id("serviceCrawler-menu")).click();
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("serviceCrawler-add")).click();
		Driver.waitSeconds(1);
		
		// fill form
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_SERVICE_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_API_VERSION")).sendKeys(api);
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_APP_VERSION")).sendKeys(app);
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_CLASS_NAME")).sendKeys(className);
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		// not testing this yet
		//Driver.getInstance().findElement(By.id("radio-ADMIN_LANGUAGE_FORM_DEFAULT"));
		Select dir = new Select(Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_CATEGORY")));
		dir.selectByValue(category);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-serviceCrawlerModal")).click();
		
	}

	public static void modify(String name, String className) {
		
		// find test row ?
		WebElement x = Driver.getInstance().findElement(By.xpath("//td[contains(text(),'"+name+"')]/following-sibling::td/span/a[contains(@id,'sb')]"));
		// open modify modal
		x.click();
		// open modify modal
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("ADMIN_SERVICE_CRAWLER_FORM_CLASS_NAME")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), className);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-serviceCrawlerModal")).click();
		
	}
	
	public static void delete(String name) {
		
		// find test row ?
		WebElement x = Driver.getInstance().findElement(By.xpath("//td[contains(text(),'"+name+"')]/following-sibling::td/span/a[contains(@id,'db')]"));
		// delete
		x.click();
		Driver.waitSeconds(1);
		
		// acknowledge
		Driver.getInstance().findElement(By.id("modalButtonAccept-acknowledgeModal")).click();
		
	}
	
	public static void search(String text) {
		
		// search
		Driver.getInstance().findElement(By.id("serviceCrawlerSearchField")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.getInstance().findElement(By.id("serviceCrawlerSearchField-button")).click();
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
