package com.oauth2_0;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.restassured.path.json.JsonPath;

public class OAuth2Test {

	public static void main(String[] args) throws InterruptedException {
		
//		WebDriver driver=new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("shishira36@gmail.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Ilovemymom@143");
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);
//		String url=driver.getCurrentUrl();
//		System.out.println(url);
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVMBsJjMmnlP4Ua-61RiJ8TOGJdw_zIKjzueIIu6QltWrtBAbssBVGJ9sxyNRBoorw0S7g&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		String partialCode=url.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		System.out.println(code);
		
		String accessTokenresponse=given().urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.queryParams("state", "verifyfjdss")
		.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
		.when().log().all().
		post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().extract().response().asString();
		JsonPath js=new JsonPath(accessTokenresponse);
		String access_Token=js.get("access_token");
		System.out.println(access_Token);
		
	String response=given().queryParam("access_token", access_Token)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().extract().response().asString();
		System.out.println(response);
		
	}

}
