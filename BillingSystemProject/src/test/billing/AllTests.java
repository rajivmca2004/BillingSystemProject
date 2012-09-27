/**
 * JUnit Suite- To run both test classes one by one in one go.
 * Classes- BillingDiscountHelperTest, BusinessServiceTest 
 */
package test.billing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BillingDiscountHelperTest.class, BusinessServiceTest.class })
public class AllTests {

}
