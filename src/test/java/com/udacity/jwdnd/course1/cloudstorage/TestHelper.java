package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestHelper {
    public TestHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="inputFirstName")
    private WebElement firstName;

    @FindBy(id="inputLastName")
    private WebElement lastName;

    @FindBy(id="inputPassword")
    private WebElement singnUpPassword;

    @FindBy(id="inputUsername")
    private WebElement signupUsername;

    @FindBy(id="signUpButton")
    private WebElement signupButton;

    @FindBy(id="inputPassword")
    private WebElement loginPassword;

    @FindBy(id="inputUsername")
    private WebElement loginUsername;

    @FindBy(id="loginButton")
    private WebElement loginButton;

    @FindBy(id="logoutButton")
    private  WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTabHomePage;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTabHomePage;

    @FindBy(id="nav-files-tab")
    private WebElement filesTabHomePage;

    @FindBy(id="addNoteButton")
    private WebElement createNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="note-submit")
    private WebElement noteSubmit;

    @FindBy(className = "meraNoteDescription")
    private List<WebElement> tds;

    @FindBy(className="meraNoteTitle")
    private List<WebElement> ths;

    @FindBy(tagName = "Button")
    private List<WebElement> buttons;

    @FindBy(tagName = "a")
    private List<WebElement> aa;

    @FindBy(id="add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id="credential-url")
    private WebElement inputCredentialURL;

    @FindBy(id="credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id="credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id="credential-bharo")
    private WebElement submitCredentialButton;

    @FindBy(tagName="th")
    private List<WebElement> thsare;



    public void signup(String firstName, String lastName, String username, String password) throws InterruptedException {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.signupUsername.sendKeys(username);
        this.singnUpPassword.sendKeys(password);
        signupButton.click();
       // Thread.sleep(3500);

    }




    public void fillLoginCredentials(String username, String password) {
        this.loginUsername.sendKeys(username);
        this.loginPassword.sendKeys(password);
    }

    public void attemptLogin() {
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    public void addNewNote(String noteHeadindg, String noteContents) throws InterruptedException {
        notesTabHomePage.click();
        Thread.sleep(1500);
        createNoteButton.click();
        Thread.sleep(1500);
        noteTitle.sendKeys(noteHeadindg);
        noteDescription.sendKeys(noteContents);
        Thread.sleep(1500);
        noteSubmit.click();
           }


    public boolean verifyNote(String noteHeadindg, String noteContents) throws InterruptedException {
        Thread.sleep(1000);


        for(WebElement w:ths)
        {
            //System.out.println("note heading"+noteHeadindg);
            String s=(String)w.getText();
            //System.out.println(w.getText());
            if(s.equals(noteHeadindg))
            {
               // System.out.println("equal heading ke mil gaaya+ "+w.getText());

                for(WebElement q:tds)
                {
                    String t=((String)q.getText()).trim();
                    t=t.replaceAll("( )+", " ");
                    noteContents=noteContents.replaceAll("( )+", " ");
                    //System.out.println(q.getText().trim());
                    //System.out.println(noteContents.trim());
                    if(t.equals(noteContents.trim())) {
                        //System.out.println("equal content ke mil gaaya+ "+q.getText().trim());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void viewNotes() {
        notesTabHomePage.click();

    }

    public void editNote(String noteHeadindg, String noteNewContents) throws InterruptedException {
        notesTabHomePage.click();
        Thread.sleep(1500);
        for(WebElement button: buttons){
            if(button.getText().equals("Edit")){
                button.click();
                Thread.sleep(500);
                noteTitle.clear();
                noteTitle.sendKeys(noteHeadindg);
                noteDescription.clear();
                noteDescription.sendKeys(noteNewContents);
                Thread.sleep(1000);
                noteSubmit.click();
                break;
            }
        }
    }
    public void deleteNote() throws InterruptedException{
        notesTabHomePage.click();
        Thread.sleep(1500);
        for(WebElement a: aa){
            if(a.getText().equals("Delete")){
                a.click();
                break;
            }
        }
    }


    public void addNewCredential(String url, String credentialUsername, String credentialPassword) throws InterruptedException {
        credentialsTabHomePage.click();
        Thread.sleep(1000);
        addCredentialButton.click();
        Thread.sleep(1000);
        inputCredentialURL.sendKeys(url);
        inputCredentialUsername.sendKeys(credentialUsername);
        inputCredentialPassword.sendKeys(credentialPassword);
        Thread.sleep(500);
        submitCredentialButton.click();
        Thread.sleep(2000);

    }

    public void viewCredentials() {
        credentialsTabHomePage.click();
    }

    public boolean verifyCredentials(WebDriver driver, String url, String credentialUsername, String credentialPassword) {
        for(WebElement w:thsare)
        {
            String s=(String)w.getText();
            //System.out.println(s);
           // System.out.println(url);
            if(s.equals(url)) {
                // System.out.println("equal url ke mil gaaya+ "+s);
                WebElement un=driver.findElement(By.cssSelector("table#credentialTable tbody tr td:nth-of-type(2)"));
                WebElement pwd=driver.findElement(By.cssSelector("table#credentialTable tbody tr td:nth-of-type(3)"));
                if(un.getText().toString().equals(credentialUsername) && !pwd.getText().toString().equals(credentialPassword))
                    return true;
            }
        }
        return false;

    }


    public void editCredentials(WebDriver driver, String url, String credentialUsername, String credentialPassword, String newCredentialUsername, String newCredentialPassword) throws InterruptedException {
        for(WebElement button: buttons) {
            if (button.getText().equals("Edit")) {
                button.click();
                Thread.sleep(2000);
                //System.out.println("from input"+inputCredentialPassword.getAttribute("value"));
                //System.out.println("from original"+credentialPassword);
                if(inputCredentialPassword.getAttribute("value").toString().equals(credentialPassword)){
                    Thread.sleep(1000);
                    inputCredentialURL.clear();
                    inputCredentialUsername.clear();
                    inputCredentialPassword.clear();
                    inputCredentialURL.sendKeys(url);
                    inputCredentialUsername.sendKeys(newCredentialUsername);
                    inputCredentialPassword.sendKeys(newCredentialPassword);
                    Thread.sleep(500);
                    submitCredentialButton.click();
                    Thread.sleep(2000);
                }
                break;
            }
        }
    }

    public void deleteCredential(WebDriver driver, String url, String credentialUsername, String credentialPassword) {
        for(WebElement w:thsare)
        {
            String s=(String)w.getText();
            //System.out.println(s);
            //System.out.println(url);
            if(s.equals(url)) {
                //System.out.println("equal url ke mil gaaya+ "+s);
                WebElement un=driver.findElement(By.cssSelector("table#credentialTable tbody tr td:nth-of-type(2)"));
                WebElement pwd=driver.findElement(By.cssSelector("table#credentialTable tbody tr td:nth-of-type(3)"));
                if(un.getText().toString().equals(credentialUsername) && !pwd.getText().toString().equals(credentialPassword))
                {
                    for(WebElement a: aa){
                        if(a.getText().equals("Delete")){
                            a.click();
                            break;
                        }
                    }
                    break;
                }            }
        }
    }
}
