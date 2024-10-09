package com.blacksw.suites;

import com.blacksw.categories.JUnit4CustomerTest;
import com.blacksw.categories.JUnit4CustomersRepositoryTest;
import com.blacksw.categories.RepositoryTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.ExcludeCategory(RepositoryTests.class)
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepositoryTest.class})
public class JUnit4ExcludeRepositoryTestsSuite {
}
