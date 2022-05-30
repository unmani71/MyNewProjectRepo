package Util;


import luanch_site.luanch_site.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

public class TestUtil extends TestBase{
	
	public static long page_load_Timeout = 120;
	public static long Implicit_wait = 50;
	public static long page_load_TimeoutForSiebel = 200;
	
	public static String TESTDATA_SHEET_PATH = userDirectory+prop.getProperty("resourcePath")+prop.getProperty("excelFilePath");

	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getTestData(String SheetName)
	{
	 FileInputStream file = null;
		try
		{
			file = new FileInputStream(TESTDATA_SHEET_PATH);	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		sheet = book.getSheet(SheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i=0; i<sheet.getLastRowNum(); i++)
		{
			for (int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
				data[i][k]= sheet.getRow(i + 1).getCell(k).toString();
			}
		}
	
	return data;
	}
	
	public static String takeScreenshotAtEndOfTest() throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "/Screenshots/" +System.currentTimeMillis()+ ".png"));
		
			return currentDir;
	}
}