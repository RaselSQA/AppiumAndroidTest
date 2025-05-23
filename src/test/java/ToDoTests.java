import  io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ToDoTests extends BaseTest {

    @Test(priority = 1)
    public void addSingleTask() {
        driver.findElementById("com.enoiu.todo:id/add_task_button").click();
        driver.findElementById("com.enoiu.todo:id/task_input").sendKeys("Buy Milk");
        driver.findElementById("com.enoiu.todo:id/save_button").click();

        List<MobileElement> tasks = driver.find2w  ElementsById("com.enoiu.todo:id/task_text");
        Assert.assertTrue(tasks.stream().anyMatch(e -> e.getText().contains("Buy Milk")));
    }

    @Test(priority = 2)
    public void addFiftyTasks() {
        for (int i = 1; i <= 50; i++) {
            driver.findElementById("com.enoiu.todo:id/add_task_button").click();
            driver.findElementById("com.enoiu.todo:id/task_input").sendKeys("Task " + i);
            driver.findElementById("com.enoiu.todo:id/save_button").click();
        }

        List<MobileElement> tasks = driver.findElementsById("com.enoiu.todo:id/task_text");
        Assert.assertTrue(tasks.size() >= 50);
    }

    @Test(priority = 3)
    public void markTaskAsDone() {
        MobileElement task = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Task 1\")");
        MobileElement checkbox = task.findElementByXPath("..//android.widget.CheckBox");
        checkbox.click();

        Assert.assertEquals(checkbox.getAttribute("checked"), "true");
    }

    @Test(priority = 4)
    public void searchTask() {
        driver.findElementById("com.enoiu.todo:id/search_button").click();
        driver.findElementById("com.enoiu.todo:id/search_input").sendKeys("Task 10");

        List<MobileElement> results = driver.findElementsById("com.enoiu.todo:id/task_text");
        Assert.assertTrue(results.stream().anyMatch(e -> e.getText().contains("Task 10")));
    }

    @Test(priority = 5)
    public void deleteTask() {
        MobileElement task = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Task 10\")");
        MobileElement deleteButton = task.findElementByXPath("..//android.widget.ImageButton"); // Adjust if needed
        deleteButton.click();

        List<MobileElement> tasks = driver.findElementsById("com.enoiu.todo:id/task_text");
        Assert.assertTrue(tasks.stream().noneMatch(e -> e.getText().equals("Task 10")));
    }
}