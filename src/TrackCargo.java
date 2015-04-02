
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;



import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TrackCargo {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://vozovoz.ru/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void browserReload() throws Exception {
        driver.close();
        Thread.sleep(20000);
        setUp();
    }

    @Test
    public void testCargo() throws Exception {
        int counter = 0;
        while (true) {
            //  driver.get(baseUrl + "calculate-the-order");
        /*    Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@test-id='from.use.ppv']")).click();
            //driver.findElement(By.xpath("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div/app-calculator-location/div/ng-form/div/label[2]")).click();
            //Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@id='main']/ng-form/div/div/app-calculator/div/div/div/div/div/div/app-calculator-location/div/ng-form/div[3]/div/oi-multiselect/div/ul/li")).click();
            Thread.sleep(3000);
*/
       /*     try {
                for (int i=0; i<3; i++) {
                    System.out.println(driver.findElement(By.xpath("//*[@test-id='from.ppv.city']/ul/li["+i+"]")));

                }
            }
            catch (NoSuchElementException e) {}
*/
            // List<WebElement> allElements = driver.findElements(By.xpath("//*[@test-id='from.ppv.city']"));
            //int i=0;
            //int j=1;
            /*for (WebElement element: allElements) {
                System.out.println(element.getText());
                i=i+j;
                System.out.println(i);
            }*/
            //  if (i==9)
            // System.out.println(i);
            counter++;
            if (counter == 60) {
                browserReload();
                counter = 0;
                System.out.println("reload counter=" + counter);
            }
            ;
             Thread.sleep(45000);
            try {
                driver.get(baseUrl);
                for (int second = 0; ; second++) {
                    if (second >= 5) throw new NoSuchElementException("fail to find TRACK CARGO");
                    try {
                        if ("Отслеживание груза".equals(driver.findElement(By.linkText("Отслеживание груза")).getText()))
                            break;
                    } catch (Exception e) {
                    }
                    Thread.sleep(1000);
                }

                driver.findElement(By.linkText("Отслеживание груза")).click();
                driver.findElement(By.xpath("(//*[@ng-model='orderInfo.number'])")).clear();
                Thread.sleep(1000);
                driver.findElement(By.xpath("(//*[@ng-model='orderInfo.number'])")).sendKeys("500010397");
                driver.findElement(By.cssSelector("button.btn.btn-sm")).click();
                Thread.sleep(5000);

                for (int second = 0; ; second++) {
                    if (second >= 7) throw new NoSuchElementException("fail to find DOP INFO");
                    try {
                        if ("Дополнительная информация".equals(driver.findElement(By.linkText("Дополнительная информация")).getText()))
                            break;
                    } catch (Exception e) {
                    }
                    Thread.sleep(1000);
                }

                driver.findElement(By.linkText("Дополнительная информация")).click();
                driver.findElement(By.xpath("//input[@type='tel']")).clear();
                driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("79646410678");
                driver.findElement(By.cssSelector("button.btn.btn-default")).click();

                for (int second = 0; ; second++) {


                    if (second >= 7) throw new NoSuchElementException("fail to OOO V-STAR");
                    try {
                        if ("ООО \"В-СТАР\"".equals(driver.findElement(By.xpath("//div[@id='main']/div/div/div/div/div[3]/div/dl[2]/dd/div/p")).getText()))
                            break;
                    } catch (Exception e) {
                        System.out.print(e.fillInStackTrace());
                        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFile, new File("C:\\errorlogCargo\\sender info not found " + System.currentTimeMillis() + ".png"));
                        break;
                    }


                }

            } catch (NoSuchElementException e) {
                System.out.print(e.getMessage());
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("C:\\errorlogCargo\\something is worng " + System.currentTimeMillis() + ".png"));
            } catch (NoSuchWindowException e) {
                System.out.print(e.getMessage());
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("C:\\errorlogCargo\\TraceCargo NoSuchWindowException " + System.currentTimeMillis() + ".png"));
            } catch (AssertionError e) {
                System.out.print(e.getMessage());
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("C:\\errorlogCargo\\sender info not found " + System.currentTimeMillis() + ".png"));
            }

        }

    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
