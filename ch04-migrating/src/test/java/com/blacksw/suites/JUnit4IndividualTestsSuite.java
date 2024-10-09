package com.blacksw.suites;

import com.blacksw.categories.IndividualTests;
import com.blacksw.categories.JUnit4CustomerTest;
import com.blacksw.categories.JUnit4CustomersRepositoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(IndividualTests.class)
@Suite.SuiteClasses({ JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class })
public class JUnit4IndividualTestsSuite {
}
