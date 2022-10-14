package org.toasthub.test.admin.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.toasthub.test.admin.tests.LanguageTests;
import org.toasthub.test.admin.tests.MenuTests;
import org.toasthub.test.admin.tests.PermissionTests;
import org.toasthub.test.admin.tests.PreferenceTests;
import org.toasthub.test.admin.tests.RoleTests;
import org.toasthub.test.admin.tests.UserTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	//   LanguageTests.class,
	//   PreferenceTests.class,
	//   MenuTests.class,
	 //  PermissionTests.class,
	//   RoleTests.class,
	   UserTests.class,
	})

// move to system   ApplicationTests.class  ClientDomainTests.class ServiceCrawlerTests.class

public class AdminSuite {

}
