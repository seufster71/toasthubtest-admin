package org.toasthub.test.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class PreferenceAdminPage {
	
	private static Integer item = null;
	
	public static void gotoPage(){
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/index.html?page=prefPublic");
	}
	
	public static void create(String name, String defaultTitle, String titleEN, String titleES) {
		
		// open create modal
		Driver.getAndWait("pref-menu").click();

		Driver.getAndWait("pref-add").click();
		
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
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_page_menu-"+id).click();
		Driver.getAndWait("pref_page_modify-"+id).click();
		
		// open modify modal
		Driver.waitSeconds(1);
		Driver.getInstance().findElement(By.id("APP_PAGE_FORM_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
	}
	
	public static void delete(String name) {
	
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_page_menu-"+id).click();
		Driver.getAndWait("pref_page_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
		
	}
	
	public static void search(String text) {
		// search
		Driver.getInstance().findElement(By.id("preferenceSearchField")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.getInstance().findElement(By.id("preferenceSearchField-button")).click();
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
		//WebElement parent = m.findElement(By.xpath(".."));
		String[] href = m.getAttribute("href").split("-");
		item = Integer.parseInt(href[1]);
		// open menu  
		m.click();
	}
	
	// Formfield
	public static void createFormfield(String name, String defaultTitle, String titleEN, String titleES, String fieldType, String group,
			String labelEN, String labelES) {
		// go to tab
		Driver.getAndWait("tab_formfield-"+item).click();
		// open menu
		Driver.getAndWait("pref_formfield_main_menu-"+item).click();
		// create modal
		Driver.getAndWait("pref_formfield_main_create-"+item).click();
		
		Driver.waitMilli(500);
		// fill form
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_ACTIVE-0")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_FIELDTYPE")).sendKeys(fieldType);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_GROUP")).sendKeys(group);		

		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_LABEL-en")).sendKeys(labelEN);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_RENDERED-0-en")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_REQUIRED-0-en")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_LABEL-es")).sendKeys(labelES);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_RENDERED-0-es")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_VALUE_REQUIRED-0-es")).findElement(By.xpath("..")).click();
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
				
	}
	
	public static void modifyFormfield(String name, String titleEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_formfield_menu-"+id).click();
		Driver.getAndWait("pref_formfield_modify-"+id).click();
		
		// open modify modal
		Driver.waitMilli(500);
		Driver.getInstance().findElement(By.id("APP_FORMFIELD_NAME_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
	}
	
	public static void deleteFormfield(String name) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_formfield_menu-"+id).click();
		Driver.getAndWait("pref_formfield_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
	}
	
	// Label 
	public static void createLabel(String name, String defaultTitle, String titleEN, String titleES, String options, String labelEN, String labelES) {
		// go to tab
		Driver.getAndWait("tab_label-"+item).click();
		// open menu
		Driver.getAndWait("pref_label_main_menu-"+item).click();
		// crate modal
		Driver.getAndWait("pref_label_main_create-"+item).click();
		
		Driver.waitMilli(500);
		// fill form
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_ACTIVE-0")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_OPTIONALPARAMS")).sendKeys(options);	

		Driver.getInstance().findElement(By.id("APP_LABEL_VALUE_VALUE-en")).sendKeys(labelEN);
		Driver.getInstance().findElement(By.id("APP_LABEL_VALUE_RENDERED-0-en")).findElement(By.xpath("..")).click();

		Driver.getInstance().findElement(By.id("APP_LABEL_VALUE_VALUE-es")).sendKeys(labelES);
		Driver.getInstance().findElement(By.id("APP_LABEL_VALUE_RENDERED-0-es")).findElement(By.xpath("..")).click();
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
				
	}
	
	public static void modifyLabel(String name, String titleEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_label_menu-"+id).click();
		Driver.getAndWait("pref_label_modify-"+id).click();
		
		// open modify modal
		Driver.waitMilli(500);
		Driver.getInstance().findElement(By.id("APP_LABEL_NAME_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
	}
	
	public static void deleteLabel(String name) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_label_menu-"+id).click();
		Driver.getAndWait("pref_label_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
	}
	
	
	// Text
	public static void createText(String name, String defaultTitle, String titleEN, String titleES, String options,
			String valueEN, String valueES) {
		// go to tab
		Driver.getAndWait("tab_text-"+item).click();
		// open menu
		Driver.getAndWait("pref_text_main_menu-"+item).click();
		// create modal
		Driver.getAndWait("pref_text_main_create-"+item).click();
		
		Driver.waitMilli(500);
		// fill form
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_ACTIVE-0")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_OPTIONALPARAMS")).sendKeys(options);		

		Driver.getInstance().findElement(By.id("APP_TEXT_VALUE_VALUE-en")).sendKeys(valueEN);
		Driver.getInstance().findElement(By.id("APP_TEXT_VALUE_RENDERED-0-en")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_TEXT_VALUE_VALUE-es")).sendKeys(valueES);
		Driver.getInstance().findElement(By.id("APP_TEXT_VALUE_RENDERED-0-es")).findElement(By.xpath("..")).click();
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
				
	}
	
	public static void modifyText(String name, String titleEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_text_menu-"+id).click();
		Driver.getAndWait("pref_text_modify-"+id).click();
		
		// open modify modal
		Driver.waitMilli(500);
		Driver.getInstance().findElement(By.id("APP_TEXT_NAME_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
	}
	
	public static void deleteText(String name) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_text_menu-"+id).click();
		Driver.getAndWait("pref_text_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
	}
	
	// Option
	public static void createOption(String name, String defaultTitle, String titleEN, String titleES, String valueType, String defaultValue,
			String options, String valueEN, String validationEN, String valueES, String validationES) {
		// go to tab
		Driver.getAndWait("tab_option-"+item).click();
		// open menu
		Driver.getAndWait("pref_option_main_menu-"+item).click();
		// create modal
		Driver.getAndWait("pref_option_main_create-"+item).click();
		
		Driver.waitMilli(500);
		// fill form
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_NAME")).sendKeys(name);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_TITLE_DEFAULT")).sendKeys(defaultTitle);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_TITLE_TEXT-en")).sendKeys(titleEN);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_TITLE_TEXT-es")).sendKeys(titleES);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_ACTIVE-0")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_VALUETYPE")).sendKeys(valueType);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_DEFAULTVALUE")).sendKeys(defaultValue);		
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_USEDEFAULT-1")).findElement(By.xpath("..")).click();
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_OPTIONALPARAMS")).sendKeys(options);
		
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_VALUE-en")).sendKeys(valueEN);
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_VALIDATION-en")).sendKeys(validationEN);
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_RENDERED-0-en")).findElement(By.xpath("..")).click();
		
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_VALUE-es")).sendKeys(valueES);
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_VALIDATION-es")).sendKeys(validationES);
		Driver.getInstance().findElement(By.id("APP_OPTION_VALUE_RENDERED-0-es")).findElement(By.xpath("..")).click();
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
				
	}
	
	public static void modifyOption(String name, String titleEN) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_option_menu-"+id).click();
		Driver.getAndWait("pref_option_modify-"+id).click();
		
		// open modify modal
		Driver.waitMilli(500);
		Driver.getInstance().findElement(By.id("APP_OPTION_NAME_TITLE_TEXT-en")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), titleEN);
		// save
		Driver.getInstance().findElement(By.id("modalButtonAccept-prefsModal")).click();
	}
	
	public static void deleteOption(String name) {
		WebElement m = Driver.getInstance().findElement(By.xpath("//h2/a[contains(text(),'"+name+"')]"));
		String[] href = m.getAttribute("href").split("-");
		int id = Integer.parseInt(href[1]);
		Driver.getAndWait("pref_option_menu-"+id).click();
		Driver.getAndWait("pref_option_delete-"+id).click();
		
		// acknowledge
		Driver.getAndWait("modalButtonAccept-acknowledgeModal").click();
	}
}
