package tutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class Booking {
    AppiumDriver driver;

    @BeforeTest
    public void setup(){
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("uiautomator2")
                .setDeviceName("emulator-5554").setAppPackage("com.booking")
                .setAppActivity("com.booking.startup.HomeActivity")
                .setPlatformVersion("13")
                .setNoReset(false)
                .eventTimings();
        try {
            driver = new AppiumDriver(new URL("http://0.0.0.0:4723/"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().getImplicitWaitTimeout();
    }

    String calendarView = "com.booking:id/calendar_month_list";
    String childrenView = "com.booking:id/age_picker_view";
    String selectRoom = "com.booking:id/rooms_recycler_view";
    @Test
    public void doTest()  {
        selectFilterHome();
        selectDays(14,28);
        validateRoomsAdultsChildrens(1,2,1);
        selectItemOfSearchResults();
    }
    public void selectFilterHome() {
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
        driver.findElement(AppiumBy.id("com.booking:id/facet_search_box_accommodation_destination")).click();
        driver.findElement(AppiumBy.id("com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content")).sendKeys("CUZCO");
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")).click();
    }
    public void selectDays(int startDate, int endDate) {
        verticalScroll(driver,calendarView);
        verticalScroll(driver,calendarView);
        driver.findElement(AppiumBy.accessibilityId(startDate + " February 2023")).click();
        driver.findElement(AppiumBy.accessibilityId(endDate + " February 2023")).click();
        driver.findElement(AppiumBy.id("com.booking:id/facet_date_picker_confirm")).click();
    }
    public void validateRoomsAdultsChildrens(int rooms, int adults, int children) {
        driver.findElement(AppiumBy.id("com.booking:id/facet_search_box_accommodation_occupancy")).click();
        int numberRooms = Integer.parseInt((driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView")).getText()));
        int numberAdults = Integer.parseInt((driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.TextView")).getText()));
        int numberChildren = Integer.parseInt((driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.LinearLayout/android.widget.TextView")).getText()));

        if (numberRooms==0){
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.Button[2]")).click();
        }
        for (int i = numberAdults; i < adults; i++) {
            driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.Button[2]")).click();
        }

        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.LinearLayout/android.widget.Button[2]")).click();
        verticalScroll(driver,childrenView);
        verticalScroll(driver,childrenView);
        verticalScroll(driver,childrenView);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.findElement(AppiumBy.id("com.booking:id/group_config_apply_button")).click();
        driver.findElement(AppiumBy.id("com.booking:id/facet_search_box_cta")).click();
    }

    public void selectItemOfSearchResults() {
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")).click();
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout[2]/android.view.ViewGroup")).click();
        driver.findElement(AppiumBy.id("com.booking:id/list_item")).click();
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.Button")).click();

    }

    public void verticalScroll(AppiumDriver driver, String getView){
        //Creating Vertical Scroll Event
        //Scrollable Element
        WebElement ele01 = driver.findElement(AppiumBy.id(getView));
        int centerX = ele01.getRect().x + (ele01.getSize().width/2);
        double startY = ele01.getRect().y + (ele01.getSize().height * 0.8);
        double endY = ele01.getRect().y + (ele01.getSize().height * 0.1);
        //Type of Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        //Creating Sequence object to add actions
        Sequence swipe = new Sequence(finger,1);
        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, (int)endY));
        //Get up Finger from Srceen
        swipe.addAction(finger.createPointerUp(0));
        //Perform the actions
        driver.perform(Arrays.asList(swipe));

    }

    public void horizontalScroll(AppiumDriver driver){
        //Creating Horizontal Scroll Event
        //Scrollable Element
        WebElement ele01 = driver.findElement(AppiumBy.id("com.booking:id/facet_with_bottom_sheet_header_content"));

        int centerY = ele01.getRect().y + (ele01.getSize().height/2);

        double startX = ele01.getRect().x + (ele01.getSize().width * 0.9);

        double endX = ele01.getRect().x + (ele01.getSize().width * 0.1);
        //Type of Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        //Creating Sequence object to add actions
        Sequence swipe = new Sequence(finger,1);
        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),(int)startX,centerY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),(int)endX,centerY));
        //Get up Finger from Srceen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void tearDown(){
        //driver.quit();
    }
}
