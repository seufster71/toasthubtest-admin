package org.toasthub.test.admin.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.toasthub.test.admin.tests.LanguageTests;
import org.toasthub.test.admin.tests.PreferenceTests;
import org.toasthub.test.admin.tests.ServiceCrawlerTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	   LanguageTests.class,
	   ServiceCrawlerTests.class,
	   PreferenceTests.class
	})


public class AdminSuite {

}
