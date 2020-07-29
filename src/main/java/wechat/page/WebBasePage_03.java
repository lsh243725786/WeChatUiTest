package wechat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wechat.uitest.BasePage_01;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class WebBasePage_03 extends BasePage_01 {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage_03() {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        selenium 4.0 use duration
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver, 10);
    }

    public WebBasePage_03(RemoteWebDriver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver,10);

    }


    public void quit() {
        driver.quit();
    }

    public void click(By by){
        //todo: 异常处理
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }


    /**
     * 重写父类的click方法，用于执行click操作
     * @param map 父类传入的数据
     */
    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
        //取出map的key和value
        String key= map.keySet().toArray()[0].toString();
        String value= map.values().toArray()[0].toString();

        By by = null;
        //把key转化成小写进行对比，如果key=id，是id进行定位的，就执行By.id方法
        if(key.toLowerCase().equals("id".toLowerCase())){
            by=By.id(value);
        }
        //把key转化成小写进行对比，如果key=linkText，是linkText进行定位的，就执行By.linkText方法
        if(key.toLowerCase().equals("linkText".toLowerCase())){
            by=By.linkText(value);
        }

        if(key.toLowerCase().equals("partialLinkText".toLowerCase())){
            by=By.partialLinkText(value);
        }
        click(by);
    }

    /**
     * 重写父类sendKey方法，用于执行sendKey操作
     * @param map
     */
    @Override
    public void sendKey(HashMap<String, Object> map) {
        super.sendKey(map);
        if(map.containsKey("sendKeys")) {
            if (map.containsKey("id".toLowerCase())) {
                sendKeys(By.id(map.get("id").toString()),map.get("sendKeys").toString());
            }
        }
    }

    /**
     * 重写父类action方法
     * @param map 父类传入的数据
     */
    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);

        if(map.containsKey("action")) {
            String action = map.get("action").toString().toLowerCase();
            if (action.equals("get")) {
                driver.get(map.get("url").toString());
            }else {
                System.out.println("没有设置这种action关键字：" + action);
            }
        }
    }


}
