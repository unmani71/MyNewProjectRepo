package TestSite;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import luanch_site.luanch_site.TestBase;

public class TestSiteLaunch extends TestBase {
	

	@Test
	public void SiteLaunch() throws IOException {
	
	try{
		System.out.println("Hi---");
		//	Assert.assertTrue(true );
	}
	catch(Exception e)
	{
		System.out.println("IOException");
	}

}
}
