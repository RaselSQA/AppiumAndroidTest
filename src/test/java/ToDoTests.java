import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ToDoTests extends BaseTest {

    @BeforeMethod
    public void init() throws Exception {
        setUp();
    }

    @Test(priority = 1)
    public void addSingleTask() {
        driver.findElement(By.id("com.enoiu.todo:id/add_task_button")).click();
        driver.findElement(By.id("com.enoiu.todo:id/task_input")).sendKeys("Buy Milk");
        driver.findElement(By.id("com.enoiu.todo:id/save_button")).click();

        List<WebElement> tasks = driver.findElements(By.id("com.enoiu.todo:id/task_text"));
        Assert.assertTrue(tasks.stream().anyMatch(e -> e.getText().contains("Buy Milk")));

    }

    @Test(priority = 2)
    public void addFiftyTasks() {
        for (int i = 1; i <= 50; i++) {
            driver.findElement(By.id("com.enoiu.todo:id/add_task_button")).click();
            driver.findElement(By.id("com.enoiu.todo:id/task_input")).sendKeys("Task " + i);
            driver.findElement(By.id("com.enoiu.todo:id/save_button")).click();
        }

        List<WebElement> tasks = driver.findElements(By.id("com.enoiu.todo:id/task_text"));
        Assert.assertTrue(tasks.size() >= 50);
    }

    @Test(priority = 3)
    public void markTaskAsDone() {
        WebElement checkbox = driver.findElement(
                By.xpath("//android.widget.TextView[@text='Task 1']/following-sibling::android.widget.CheckBox")
        );
        checkbox.click();
        Assert.assertEquals(checkbox.getAttribute("checked"), "true");
    }

    @Test(priority = 4)
    public void searchTask() {
        driver.findElement(By.id("com.enoiu.todo:id/search_button")).click();
        driver.findElement(By.id("com.enoiu.todo:id/search_input")).sendKeys("Task 10");

        List<WebElement> results = driver.findElements(By.id("com.enoiu.todo:id/task_text"));
        Assert.assertTrue(results.stream().anyMatch(e -> e.getText().contains("Task 10")));
    }

    @Test(priority = 5)
    public void deleteTask() {
        WebElement deleteButton = driver.findElement(
                By.xpath("//android.widget.TextView[@text='Task 10']/following-sibling::android.widget.ImageButton")
        );
        deleteButton.click();

        List<WebElement> tasks = driver.findElements(By.id("com.enoiu.todo:id/task_text"));
        Assert.assertTrue(tasks.stream().noneMatch(e -> e.getText().equals("Task 10")));
    }
}
