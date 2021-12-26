package org.bas;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AmazonAzusList extends BaseClass {
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		chromeLaunch();
		urlLaunch("http://amazon.in/");
		impWait(10);
		
		 findElementid("twotabsearchtextbox").sendKeys("azus vivobook 14"+Keys.ENTER);
		 
	     
		 List<WebElement> laptopname = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		/* WebElement sort = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']"));
		 sort.click();*/
		 
		
		for (int i = 0; i <laptopname.size(); i++) {
			
		 String lapnm = laptopname.get(i).getText();
		System.out.println("ALL  LAPTOP NAME---->"+lapnm);
		
		}
		
		List<WebElement> laptopPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		
		for (int i = 0; i <laptopPrice.size(); i++) {
			
		String lappr = laptopPrice.get(i).getText();
		 int amount=Integer.parseInt(lappr.replaceAll("[^0-9]", "").toString());
	if(amount<=100000) {
		
		System.out.println("ALL AZUS LAPTOP PRICE----->"+lappr);
		}
		}
		System.out.println("NEW BATCH CREATED");
	quit();
	}
	
	
}
