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
	private TestHelper testHelper;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {

		this.driver = new ChromeDriver();
		testHelper=new TestHelper(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	// user can view login pages
	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	//user can view sign up page
	@Test
	public void getSignupPage(){
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up",driver.getTitle());
	}
	//any other request is directed to login page
	@Test
	public void getAnyPage(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login",driver.getCurrentUrl());
	}



	// try to do a sign up and then do log in,verify home page is accessible, then logout, then verify home page is not accessible
	@Test
	public void doSignup() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		String firstName="Tarang";
		String lastName="Talab";
		String username="machli";
		String password="kinare";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(3000);
		Assertions.assertTrue(testHelper.verifySignup());

		// if sign up is verified, proceed to login
		if(testHelper.verifySignup())
		{
			testHelper.goToLoginPAge();
			Thread.sleep(1200);
			testHelper.fillLoginCredentials(username,password);
			Thread.sleep(1200);
			testHelper.attemptLogin();
			Thread.sleep(1200);
			//verify that home page is accessible
			driver.get("http://localhost:" + this.port + "/home");
			Assertions.assertEquals("Home",driver.getTitle());

			//do logout
			testHelper.logout();
			// ensure that home is not accessible
			driver.get("http://localhost:" + this.port + "/home");
			Assertions.assertNotEquals("Home",driver.getTitle());
		}
	}



	//fail to do login
	@Test
	public void attemptFailedLogin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		String username="machlai";
		String password="kinarse";
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username,password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		Assertions.assertNotEquals("Home",driver.getTitle());
	}
	//fail to sign up
	@Test
	public void attemptFailedSipnUp() throws InterruptedException {
		driver.get("http://localhost:\" + this.port + \"/signup");
		String firstName="Tarang";
		String lastName="Talab";
		String username="machli";
		String password="kinare";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(3000);
		Assertions.assertFalse(testHelper.verifySignup());
	}




	// create a note and verify that it is displayed
	@Test
	public void testNoteCreationDisplay() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		String username="machli";
		String password="kinare";
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username,password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);

		String noteHeadindg="sjis ehjfj df jdfsiujkd 13jJIJDS  jjd JD j d";
		String noteContents=" shjdasjk kK JSD hd ajs jJHahA hgaghkhs JJHhjs sjhAJ aj HHJj HSAjh AHJS JJHjsdj JJJ jsa    DSJLKHJDS SD KJDFKJjsjd dfksf dfvjodfklflk fkl;klfd fg fd";
		//add a new note
		testHelper.addNewNote(noteHeadindg,noteContents);

		//verify that note is displayed
		boolean isNoteAvailable=testHelper.verifyNote();
		Assertions.assertTrue(isNoteAvailable);
	}


	//

}
