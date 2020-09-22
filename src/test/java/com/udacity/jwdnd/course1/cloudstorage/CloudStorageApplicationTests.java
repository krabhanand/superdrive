package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;

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
		Thread.sleep(5000);
		Assertions.assertEquals("Login", driver.getTitle());

		// if sign up is verified, proceed to login
		if(driver.getTitle().equals("Login"))
		{
			testHelper.fillLoginCredentials(username,password);
			Thread.sleep(1200);
			testHelper.attemptLogin();
			Thread.sleep(1200);
			//verify that home page is accessible
			driver.get("http://localhost:" + this.port + "/home");
			Assertions.assertEquals("Home",driver.getTitle());

			//do logout
			testHelper.logout();
			Thread.sleep(2000);
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
		driver.get("http://localhost:" + this.port + "/signup");
		String firstName="Tarang";
		String lastName="Talab";
		String username="machli";
		String password="kinare";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(4000);
		Assertions.assertNotEquals("Login", driver.getTitle());
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

		String noteHeadindg="sjis ehjfj df jd";
		String noteContents="shjdasjk kK JSD hd ajs jJHahA hgaghkhs JJHhjs sjhAJ aj HHJj HSAjh AHJS JJHjsdj JJJ jsa    DSJLKHJDS SD KJDFKJjsjd dfksf dfvjodfklflk fkl;klfd fg fd";
		//add a new note
		//System.out.println("login has been done, now note will be added.");
		testHelper.addNewNote(noteHeadindg,noteContents);
		Thread.sleep(3000);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewNotes();
		Thread.sleep(2000);
		//verify that note is displayed
		boolean isNoteAvailable=testHelper.verifyNote(noteHeadindg,noteContents);
		Assertions.assertTrue(isNoteAvailable);
	}

	@Test
	public void noteEditTest () throws InterruptedException {
		Thread.sleep(3000);
		driver.get("http://localhost:" + this.port + "/login");
		String username="machli";
		String password="kinare";

		testHelper.fillLoginCredentials(username,password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		String noteHeadindg="sjis ehjfj df jd";
		String noteNewContents="shhjdsj skjjdkkdsjjf fjkdsjkf gaghkhs JJHhjs sjhAJ aj HHJj HSAjh AHJS JJHjsdj JJJ jsa    DSJLKHJDS SD KJDFKJjsjd dfksf dfvjodfklflk fkl;klfd fg fd";
		testHelper.editNote(noteHeadindg,noteNewContents);
		Thread.sleep(2000);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewNotes();
		Thread.sleep(2000);
		//verify that note is displayed
		boolean isNoteAvailable=testHelper.verifyNote(noteHeadindg,noteNewContents);
		Assertions.assertTrue(isNoteAvailable);
	}

	@Test
	public void noteDeleteTest () throws InterruptedException {
		Thread.sleep(3000);
		driver.get("http://localhost:" + this.port + "/signup");
		String username = "machlia";
		String password = "kinarea";
		String firstName="Tarang";
		String lastName="Talab";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(6000);
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username, password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		//System.out.println("Login done in note delete");

		String noteHeadindg = "sjis ehjfj df jd";
		String noteNewContents = "shhjdsj skjjdkkdsjjf fjkdsjkf gaghkhs JJHhjs sjhAJ aj HHJj HSAjh AHJS JJHjsdj JJJ jsa    DSJLKHJDS SD KJDFKJjsjd dfksf dfvjodfklflk fkl;klfd fg fd";
		testHelper.addNewNote(noteHeadindg,noteNewContents);
		Thread.sleep(3000);
		driver.get("http://localhost:" + this.port + "/home");
		testHelper.deleteNote();
		Thread.sleep(2000);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewNotes();
		Thread.sleep(2000);
		//verify that note is displayed
		boolean isNoteAvailable=testHelper.verifyNote(noteHeadindg,noteNewContents);
		Assertions.assertFalse(isNoteAvailable);

	}

	@Test
	public void saveCredentialsTest() throws InterruptedException
	{
		driver.get("http://localhost:" + this.port + "/signup");
		String username = "machlie";
		String password = "kinaree";
		String firstName="Tarang";
		String lastName="Talab";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(6000);
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username, password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		String URL="https://www.needofhour.com";
		String credentialUsername="xxxxx@needofhour.com";
		String credentialPassword="yyyyyyyyyy";
		testHelper.addNewCredential(URL,credentialUsername,credentialPassword);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewCredentials();
		Thread.sleep(2000);
		//verify that credential is displayed
		boolean isCredentialAvailable=testHelper.verifyCredentials(driver,URL,credentialUsername,credentialPassword);
		Assertions.assertTrue(isCredentialAvailable);
	}
		//
	@Test
	public void editCredentials() throws InterruptedException
	{

		driver.get("http://localhost:" + this.port + "/signup");
		String username = "machliou";
		String password = "kinareou";
		String firstName="Tarang";
		String lastName="Talabon";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(6000);
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username, password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		String URL="https://www.needofhour.com";
		String credentialUsername="xxxxx@needofhour.com";
		String credentialPassword="yyyyyyyyyy";
		testHelper.addNewCredential(URL,credentialUsername,credentialPassword);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewCredentials();
		Thread.sleep(2000);
		//edit credential
		String newCredentialUsername="DFjkdfkgf"; String newCredentialPassword="adnjefjs";
		testHelper.editCredentials(driver,URL,credentialUsername,credentialPassword,newCredentialUsername,newCredentialPassword);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewCredentials();
		boolean isCredentialAvailable=testHelper.verifyCredentials(driver,URL,newCredentialUsername,newCredentialPassword);
		Assertions.assertTrue(isCredentialAvailable);

	}

	@Test
	public void deleteCredentials() throws InterruptedException
	{
		driver.get("http://localhost:" + this.port + "/signup");
		String username = "machlieq";
		String password = "kinareeq";
		String firstName="Tarang";
		String lastName="Talabpn";
		testHelper.signup(firstName,lastName,username,password);
		Thread.sleep(6000);
		Thread.sleep(1200);
		testHelper.fillLoginCredentials(username, password);
		Thread.sleep(1200);
		testHelper.attemptLogin();
		Thread.sleep(1200);
		String URL="https://www.needofhour.com";
		String credentialUsername="xxxxx@needofhour.com";
		String credentialPassword="yyyyyyyyyy";
		testHelper.addNewCredential(URL,credentialUsername,credentialPassword);
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(2000);
		testHelper.viewCredentials();
		Thread.sleep(2000);
		//verify that credential is displayed
		boolean isCredentialAvailable=testHelper.verifyCredentials(driver,URL,credentialUsername,credentialPassword);
		Assertions.assertTrue(isCredentialAvailable);

		testHelper.deleteCredential(driver, URL,credentialUsername,credentialPassword);
		boolean isCredentialAvailable2=testHelper.verifyCredentials(driver,URL,credentialUsername,credentialPassword);
		Assertions.assertFalse(isCredentialAvailable2);

	}

}
