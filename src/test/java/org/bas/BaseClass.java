package org.bas;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
public static WebDriver driver;
	
public static WebDriver chromeLaunch() {
	WebDriverManager.chromedriver().setup();
	return driver= new ChromeDriver();
}



	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();

	}
	public static WebElement findElementid(String idvalue) {
		WebElement findElementid = driver.findElement(By.id(idvalue));
		return findElementid;
	}
	
	public static WebElement findElementname(String namevalue) {
		WebElement findElementname=driver.findElement(By.name(namevalue));
		return findElementname;
	}
	
	public static WebElement findElementx(String xpath) {
		WebElement findElementx = driver.findElement(By.xpath(xpath));
		return findElementx;
	}
	
	public static void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	public static void sendKeys(WebElement e,String data) {
		e.sendKeys(data);
		}
	
	public static void quit() {
		driver.quit();
	}
	
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	public static void dragAndDrop(WebElement from, WebElement to) {
		Actions a= new Actions(driver);
		a.dragAndDrop(from, to);
	}
	
	public static void doubleClick(WebElement target) {
		Actions a= new Actions(driver);
		a.doubleClick(target);
	}
	public static void contextClick(WebElement contexttarget) {
		Actions a= new Actions(driver);
		a.contextClick(contexttarget);
		
	}
	public static void clickAndHold(WebElement drag) {
		Actions a= new Actions(driver);
		a.clickAndHold(drag);
		
	}
	public static void moveToElement(WebElement target) {
		Actions a= new Actions(driver);
		a.moveToElement(target);
	}
	
	public static void selectByIndex(WebElement element, int index) {
		Select s= new Select(element);
		s.selectByIndex(index);
		
	}
	
	public static void selectByVisibleText(WebElement element1,String text) {
		Select s= new Select(element1);
		s.selectByVisibleText(text);
	}
	
	public static void selectByValue(WebElement element2,String value) {
		Select s= new Select(element2);
		s.selectByValue(value);
	}
	
	public static void alertAccept() {
		Alert accept=driver.switchTo().alert();
		accept.accept();
		
	}
	public static void alertDismiss() {
		Alert dismiss=driver.switchTo().alert();
		dismiss.dismiss();
	}
	
	public static void downArrow() throws AWTException {
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public static void upArrow() throws AWTException {
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_UP);
		r.keyRelease(KeyEvent.VK_UP);
	}
	public static void enterButton() throws AWTException {
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	
	
	public static void takesScreenShot() throws IOException {
		
		TakesScreenshot take= (TakesScreenshot)driver;

		File from= take.getScreenshotAs(OutputType.FILE);		
		System.out.println(from);
		
		
		Scanner file= new Scanner(System.in);
		String fn = file.next();
		File to = new File("C:\\Users\\YASEENAERO\\eclipse-workspace\\SeliniumProject\\Screenshot\\"+fn+".png");

		FileUtils.copyFile(from, to);
		}
	
	public static void scrollDown(WebElement down) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
		
	}
	public static void scrollUp(WebElement up) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", up);
	}
	
	
	public static String excelRead(String fileName,String sheetname,int row1, int cell1) throws IOException {
		File file= new File("C:\\Users\\YASEENAERO\\eclipse-workspace-photon\\TeslaMaven\\src\\test\\resources\\"+fileName+".xlsx");

		FileInputStream filein= new FileInputStream(file);
		Workbook wb= new XSSFWorkbook(filein);
		Sheet sheet = wb.getSheet(sheetname);
		Row row = sheet.getRow(row1);
		Cell cell = row.getCell(cell1);
		int type = cell.getCellType();
	
			String value= null;
		
		if(type==1)//type1-string, type 0- numeric,date
		{
			 value= cell.getStringCellValue();
		}else 
		{
			if(DateUtil.isCellDateFormatted(cell)) {
				Date dd= cell.getDateCellValue();
				
				SimpleDateFormat s= new SimpleDateFormat("dd-MMMM-yyyy");
				value = s.format(dd);
				
			}
			else {
				double tt = cell.getNumericCellValue();
				long ln= (long)tt;
				 value = String.valueOf(ln);
				
			}
			
		}
		
		return value;
	}
}	
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	