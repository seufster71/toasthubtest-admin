package org.toasthub.test.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.toasthub.test.core.general.GeneralSettings;
import org.toasthub.test.core.selenium.Driver;

public class UserAdminPage {
	
	public static void gotoPage() {
		// got to page
		Driver.getInstance().get(GeneralSettings.hostWebContext+"/admin/users");
	}
	
	public static void create(String firstName, String middleName, String lastName, String userName, String email, String zipCode, 
			String language, String alternateEmail, String logLevel, String password, String verifyPassword, Boolean status, Boolean forcePassReset, Boolean save) {
		
		// open create modal
		Driver.findOrWaitById("ADMIN_USER-ADD").click();

		// set general info
		Driver.findOrWaitById("ADMIN_USER_FORM_FIRSTNAME").sendKeys(firstName);
		Driver.findOrWaitById("ADMIN_USER_FORM_MIDDLENAME").sendKeys(middleName);
		Driver.findOrWaitById("ADMIN_USER_FORM_LASTNAME").sendKeys(lastName);
		
		Driver.findOrWaitById("ADMIN_USER_FORM_USERNAME").sendKeys(userName);
		Driver.findOrWaitById("ADMIN_USER_FORM_EMAIL").sendKeys(email);
		Driver.findOrWaitById("ADMIN_USER_FORM_ALTERNATE_EMAIL").sendKeys(alternateEmail);
		Driver.findOrWaitById("ADMIN_USER_FORM_ZIPCODE").sendKeys(zipCode);
		
		// set password
		Driver.findOrWaitById("ADMIN_USER_FORM_PASSWORD").sendKeys(password);
		Driver.findOrWaitById("ADMIN_USER_FORM_VERIFY_PASSWORD").sendKeys(verifyPassword);
		
		// select language
		Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_LANGUAGE']//div[@class=' css-tlfecz-indicatorContainer']").click();
		Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_LANGUAGE']//div[normalize-space(text())='"+language+"']").click();
		
		// select loglevel
		Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_LOGLEVEL']//div[@class=' css-tlfecz-indicatorContainer']").click();
		Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_LOGLEVEL']//div[normalize-space(text())='"+logLevel+"']").click();
		Driver.waitMilli(500);
		
		if (forcePassReset) {
			Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_FORCERESET-SWITCH']//a[normalize-space(text())='Yes']").click();
		} else {
			Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_FORCERESET-SWITCH']//a[normalize-space(text())='No']").click();
		}
		
		if (status) {
			Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_ACTIVE-SWITCH']//a[normalize-space(text())='Active']").click();
		} else {
			Driver.findOrWaitByXPath("//div[@id='ADMIN_USER_FORM_ACTIVE-SWITCH']//a[normalize-space(text())='Disable']").click();
		}
		
		if (save) {
			Driver.findOrWaitByXPath("//button[@id='ADMIN_USER-BUTTON-SAVE']").click();
		} else {
			Driver.findOrWaitByXPath("//button[@id='ADMIN_USER_BUTTON-CANCEL']").click();
		}
		
	}

	public static void modify(String search, String lastName) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/i[contains(@id,'MODIFY')]").click();
		Driver.findOrWaitById("ADMIN_USER_FORM_LASTNAME").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), lastName);
		Driver.waitMilli(500);
		// save
		Driver.findOrWaitByXPath("//button[@id='ADMIN_USER-BUTTON-SAVE']").click();
		
	}
	
	public static void addRole(String userName, String titleDefault, String titleEN, String titleES, String code, String application) {

		Driver.findOrWaitByXPath("//td[contains(text(),'"+userName+"')]/following-sibling::td/span/a[contains(@id,'rls')]").click();
		RoleAdminPage.create(titleDefault, titleEN, titleES, code, application);
		
		Driver.findOrWaitByXPath("//td[contains(text(),'"+titleEN+"')]");
		//Driver.findOrWaitById("modalButtonDecline-rolesWidgetModal").click();
	}
	
	public static void selectRole(String role) {
		Driver.findOrWaitByXPath("//td[contains(text(),'"+role+"')]/preceding-sibling::td/div/input[contains(@id,'cboxrole')]").click();
		Driver.findOrWaitByXPath("//div[@id='rolesMessageArea']/div[contains(text(),'Success')]");
	}
	
	public static void deleteRole(String role){
		Driver.waitMilli(500);
		Driver.findOrWaitByXPath("//td[contains(text(),'"+role+"')]/following-sibling::td/span/a[contains(@id,'db')]").click();
		// acknowledge
		Driver.waitMilli(500);
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
		Driver.waitMilli(500);
		
		Driver.findOrWaitById("modalButtonDecline-rolesWidgetModal").click();
	}
	
	public static void addPermission(String role, String titleDefault, String titleEN, String titleES, String code, Boolean read, Boolean write, String application) {

		Driver.findOrWaitByXPath("//td[contains(text(),'"+role+"')]/following-sibling::td/span/a[contains(@id,'prm')]").click();
		PermissionAdminPage.create(titleDefault, titleEN, titleES, code, read, write, application);
		
		Driver.findOrWaitByXPath("//td[contains(text(),'"+titleEN+"')]");
	}
	
	public static void selectPermission(String permission){
		Driver.findOrWaitByXPath("//td[contains(text(),'"+permission+"')]/preceding-sibling::td/div/input[contains(@id,'cboxpermission')]").click();
		Driver.findOrWaitByXPath("//div[@id='permissionsMessageArea']/div[contains(text(),'Success')]");
	}
	
	public static void deletePermission(String permission,String role){
		Driver.findOrWaitById("modalButtonDecline-permissionsWidgetModal").click();
		Driver.waitMilli(500);
		Driver.findOrWaitByXPath("//td[contains(text(),'"+role+"')]/following-sibling::td/span/a[contains(@id,'prm')]").click();
		Driver.waitMilli(1000);
		Driver.findOrWaitByXPath("//td[contains(text(),'"+permission+"')]/following-sibling::td/span/a[contains(@id,'dbprm')]").click();
		Driver.waitMilli(1000);
		// acknowledge
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
		
		Driver.waitMilli(500);
		Driver.findOrWaitById("modalButtonDecline-permissionsWidgetModal").click();
		
	}
	
	public static void delete(String search) {
		
		// find test row ?
		Driver.findOrWaitByXPath("//td[contains(text(),'"+search+"')]/following-sibling::td/span/a[contains(@id,'db')]").click();
		// acknowledge
		Driver.findOrWaitById("modalButtonAccept-acknowledgeModal").click();
	}
	
	public static void search(String text) {
		
		// search
		Driver.findOrWaitById("ADMIN_USER-SEARCH").sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), text);
		Driver.findOrWaitById("ADMIN_USER-SEARCH-button").click();
	}
	
	public static Boolean exists(String text) {
		return Driver.exists("//td[contains(text(),'"+text+"')]");
	}
	
}
