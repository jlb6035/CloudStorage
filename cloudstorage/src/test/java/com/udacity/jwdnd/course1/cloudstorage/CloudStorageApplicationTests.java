package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void ensureUnauthorized(){
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void signupTestHomePageAndLogout(){
		signUpAndLogin();
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void createNote() throws InterruptedException {
		signUpAndLogin();
		HomePage homePage = new HomePage(driver);
		homePage.addNote("Clean", "Clean bathroom");
		Thread.sleep(1000);
		driver.get("http://localhost:" + this.port + "/");
		Thread.sleep(1000);
		Assertions.assertEquals("Clean", homePage.getNote()); }

		@Test
		public void editNote() throws InterruptedException {
			signUpAndLogin();
			HomePage homePage = new HomePage(driver);
			homePage.addNote("Clean", "Clean bathroom");
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/");
			homePage.editNote("Cook", "Cook Turkey Burgers");
			driver.get("http://localhost:" + this.port + "/");
			Assertions.assertEquals("Cook", homePage.getNote());
		}

		@Test
		public void deleteNote() throws InterruptedException {
			signUpAndLogin();
			HomePage homePage = new HomePage(driver);
			homePage.addNote("Clean", "Clean bathroom");
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/");
			Thread.sleep(1000);
			homePage.deleteNote();
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/");
			Thread.sleep(1000);
			Assertions.assertEquals(false, homePage.verifyNoteDeleted());
		}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void addCredential() throws InterruptedException {
		String username = "abc123";
		String password = "abc1234";
		String url = "google.com";
		signUpAndLogin();
		HomePage homePage = new HomePage(driver);
		homePage.addCredential(url, username, password);
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals(true, homePage.verifyCredentialsAdded(url, username));
		Assertions.assertEquals(true, homePage.verifyCredentialsEncrypted(password));
	}

	@Test
	public void editCredentials() throws InterruptedException {
		String username = "abc123";
		String password = "abc1234";
		String url = "google.com";
		signUpAndLogin();
		HomePage homePage = new HomePage(driver);
		homePage.addCredential(url, username, password);
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals(true, homePage.verifyUnencryptedPassword(password));
		homePage.editCredentials("yahoo", "jones", "cookies");
		driver.get("http://localhost:" + this.port + "/");
		Thread.sleep(1000);
		Assertions.assertEquals(true, homePage.verifyCredentialsAdded("yahoo", "jones"));
		Assertions.assertEquals(true, homePage.verifyCredentialsEncrypted("cookies"));
	}

	@Test
	public void deleteCredentials() throws InterruptedException {
		String username = "abc123";
		String password = "abc1234";
		String url = "google.com";
		signUpAndLogin();
		HomePage homePage = new HomePage(driver);
		homePage.addCredential(url, username, password);
		driver.get("http://localhost:" + this.port + "/");
		homePage.deleteCredentials();
		driver.get("http://localhost:" + this.port + "/");
		Thread.sleep(1000);
		Assertions.assertEquals(false, homePage.verifyCredentialDeleted());
	}

	public void signUpAndLogin(){
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Jordan", "Boyd", "jlb123@gmail.com", "1224");
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("jlb123@gmail.com", "1224");
	}

}
