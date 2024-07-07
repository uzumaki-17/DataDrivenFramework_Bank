package com.sdet.testcases;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.sdet.base.TestBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void Add_Customer(String firstName, String lastName, String postCode, String alertText) {
        System.out.println("Adding customer: " + firstName + " " + lastName + " " + postCode);
        driver.findElement(By.cssSelector(OR.getProperty("bmAddCustomer_btn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("bmFirstName"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("bmLastName"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("bmPostCode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("bmAddCustomerSubmit"))).click();
       Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
       Assert.assertTrue(alert.getText().contains(alertText));
       try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       alert.accept();
       
       
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        File excelFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheet("AddCustomerTest");

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];
        DataFormatter formatter = new DataFormatter(); // Create DataFormatter instance

        for (int r = 0; r < rowCount; r++) {
            Row row = sheet.getRow(r + 1);

            for (int c = 0; c < colCount; c++) {
                Cell cell = row.getCell(c);
                data[r][c] = formatter.formatCellValue(cell); // Use DataFormatter to get the cell value as String
            }
        }

        workbook.close();
        fis.close();

        // Print data to verify
        for (Object[] row : data) {
            for (Object cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        return data;
    }

}
