package ListnerAndReRun;

import org.junit.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;

public class InvoiceTest extends BaseClass{
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}

}
