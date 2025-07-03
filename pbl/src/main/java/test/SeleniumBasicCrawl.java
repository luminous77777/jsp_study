package test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumBasicCrawl {
	
	public static void main(String[] args) {
		//웹 데이터 크롤링
		//1. webDriver 자동 세팅
		WebDriverManager.chromedriver().setup();
		
		//2.크롬 브라우저 실행
		
		WebDriver driver = new ChromeDriver();
		
		try {
			//3. 웹사이트로 이동
			driver.get("https://mochaclass.com/Search?keyword");
			
			//4. webdriverWait 생성(최대 10초 대기)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			//5 . 동적으로 로딩되는 클래스 목록이 나타날때까지 대기
			// 실제 구조에 따라 클래스명, 태그명등은 변경해야함
			// 예시로 .class-card라는 클래스가 각 강좌 카드라고 가정
			List<WebElement> classCards = wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".MuiGrid-root.css-2xazwd a"))
					);
			
			//6. 결과 출력
			for (WebElement card : classCards) {
				// 예 강의 제목이 h3 안에 있다고 가정
				// WebElement title= card.findElement(By.cssSelector("h3"));
				System.out.println("link" + card.getAttribute("href"));
				
				//요청 간 딜레이(서버 과부하 방지)
				Thread.sleep(1200); //난수화 가능
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			driver.quit();
		}
		
		
	}

}
