package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestHelper {
    public TestHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {

    }

    public boolean verifySignup() {
        return false;
    }

    public void goToLoginPAge() {
    }

    public void fillLoginCredentials(String username, String password) {
    }

    public void attemptLogin() {
    }

    public void logout() {
    }

    public void addNewNote(String noteHeadindg, String noteContents) {
    }

    public boolean verifyNote() {
        return false;
    }
}
