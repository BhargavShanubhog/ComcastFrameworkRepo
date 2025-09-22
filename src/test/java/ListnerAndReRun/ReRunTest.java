package ListnerAndReRun;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import Baseclass.BaseClass;

public class ReRunTest{
	@Test(retryAnalyzer= ListnerUtility.RetryListenerImp.class)
	public void activateSim() {
		System.out.println("execute activateSim");
		Assert.assertEquals(" ", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
