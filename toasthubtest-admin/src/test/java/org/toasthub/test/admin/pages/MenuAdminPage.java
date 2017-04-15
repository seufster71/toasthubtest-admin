package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class MenuAdminPage {
	
	private static Integer item = null;
	
	public static void gotoPage(){
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=menuPublic");
	}
	
	public static void create(String name, String defaultTitle, String titleEN, String titleES, String apiVersion, String appVersion) {
		
		// open create modal
		Driver.getAndWait("menu-menu").click();

		Driver.getAndWait("menu-add").click();
		
		// fill form
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_CODE")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_APIVERSION")).sendKeys(apiVersion);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_APPVERSION")).sendKeys(appVersion);
		Select dir = new Select(Driver.getInstance().findElement(By.id("APP_MENU_FORM_CATEGORY")));
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		dir.selectByValue("PUBLIC");
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
		
	}

	public static void modify(String name, String titleEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("menu_item_menu-"+id).click();
		Driver.getAndWait("menu_item_modify-"+id).click();

		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("APP_MENU_FORM_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
		
	}
	
	public static void delete(String name) {
		
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("menu_item_menu-"+id).click();
		Driver.getAndWait("menu_item_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
		
	}
	
	public static void search(String text) {
		// search
		Driver.getInstance().findElement(By.id("menuSearchField")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.getInstance().findElement(By.id("menuSearchField-button")).click();
	}
	
	public static Boolean exists(String name) {
		try {
			List<WebElement> list = Driver.getInstance().findElements(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void openSub(String name){
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		item = Integer.parseInt(href[1]);
		// open menu  
		m.click();
	}
	
	// SubMenu
	public static void createSubMenu(String name, String valueEN, String hrefEN, String imageEN, String valueES, String hrefES, String imageES) {
		// open menu
		Driver.getAndWait("menu_item_main_menu-"+item).click();
		// create modal
		Driver.getAndWait("menu_item_main_create-"+item).click();
		
		Driver.waitMilli(500);
		// fill form
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_CODE")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
		
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-en")).sendKeys(valueEN);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_HREF-en")).sendKeys(hrefEN);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_IMAGE-en")).sendKeys(imageEN);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_RENDERED-0-en")).findElement(By.xpath("..")).click();
		
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-es")).sendKeys(valueES);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_HREF-es")).sendKeys(hrefES);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_IMAGE-es")).sendKeys(imageES);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_RENDERED-0-es")).findElement(By.xpath("..")).click();
		
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
				
	}
	
	public static void modifySubMenu(String name, String valueEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("menu_subItem_menu-"+id).click();
		Driver.getAndWait("menu_subItem_modify-"+id).click();
		
		// open modify modal
		Driver.waitMilli(500);
		Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), valueEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
	}
	
	public static void deleteSubMenu(String name) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("menu_subItem_menu-"+id).click();
		Driver.getAndWait("menu_subItem_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
	}
	
	// menu SubItem
		public static void createSubItemMenu(String name, String valueEN, String hrefEN, String imageEN, String valueES, String hrefES, String imageES) {
			// open menu
			Driver.getAndWait("menu_subItem_main_menu-"+item).click();
			// create modal
			Driver.getAndWait("menu_subItem_main_create-"+item).click();
			
			Driver.waitMilli(500);
			// fill form
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_CODE")).sendKeys(name);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_ACTIVE-0")).findElement(By.xpath("..")).click();
			
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-en")).sendKeys(valueEN);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_HREF-en")).sendKeys(hrefEN);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_IMAGE-en")).sendKeys(imageEN);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_RENDERED-0-en")).findElement(By.xpath("..")).click();
			
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-es")).sendKeys(valueES);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_HREF-es")).sendKeys(hrefES);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_IMAGE-es")).sendKeys(imageES);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_RENDERED-0-es")).findElement(By.xpath("..")).click();
			
			// save
			Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
					
		}
		
		public static void modifySubItemMenu(String name, String valueEN) {
			WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
			String[] href = m.getAttribute("href").split("-");
			int id = Integer.parseInt(href[1]);
			Driver.getAndWait("menu_subItem_menu-"+id).click();
			Driver.getAndWait("menu_subItem_modify-"+id).click();
			
			// open modify modal
			Driver.waitMilli(500);
			Driver.getInstance().findElement(By.id("APP_MENU_ITEM_FORM_VALUE-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), valueEN);
			// save
			Driver.getInstance().findElement(By.id("modalButtonAccept-menuModal")).click();
		}
		
		public static void deleteSubItemMenu(String name) {
			WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
			String[] href = m.getAttribute("href").split("-");
			int id = Integer.parseInt(href[1]);
			Driver.getAndWait("menu_subItem_menu-"+id).click();
			Driver.getAndWait("menu_subItem_delete-"+id).click();
			
			// acknowledge
			Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
		}
}
