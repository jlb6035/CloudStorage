package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logout")
    private WebElement logoutButton;

    @FindBy(id = "addNote")
    private WebElement addNote;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "saveNote")
    private WebElement saveNote;

    @FindBy(id = "note")
    private WebElement note;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNote")
    private WebElement deleteNote;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentials;

    @FindBy(id = "addCredential")
    private WebElement addCredential;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "saveCredential")
    private WebElement saveCredential;

    @FindBy(id = "credentialPasswordDisplayed")
    private WebElement credentialPasswordDisplayed;

    @FindBy(id = "credentialUrlDisplayed")
    private WebElement credentialUrlDisplayed;

    @FindBy(id = "credentialUsernameDisplayed")
    private WebElement credentialUsernameDisplayed;

    @FindBy(id = "editCredential")
    private WebElement editCredential;

    @FindBy(id = "deleteCredentials")
    private WebElement deleteCredentials;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void addNote(String title, String description) throws InterruptedException {
        Thread.sleep(1000);
        this.navNotesTab.click();
        Thread.sleep(1000);
        this.addNote.click();
        Thread.sleep(1000);
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
        this.saveNote.click();
    }

    public String getNote() throws InterruptedException {
        this.navNotesTab.click();
        Thread.sleep(1000);
        return note.getText();
    }

    public void editNote(String editTitle, String editDescription) throws InterruptedException {
        Thread.sleep(1000);
        this.navNotesTab.click();
        Thread.sleep(1000);
        editNoteButton.click();
        Thread.sleep(1000);
        this.noteTitle.clear();
        this.noteTitle.sendKeys(editTitle);
        this.noteDescription.clear();
        this.noteDescription.sendKeys(editDescription);
        this.saveNote.click();

    }

    public void deleteNote() throws InterruptedException {
        Thread.sleep(1000);
        this.navNotesTab.click();
        Thread.sleep(1000);
        this.deleteNote.click();
    }

    public boolean verifyNoteDeleted() throws InterruptedException {
        this.navNotesTab.click();
        Thread.sleep(1000);
        try {
            return this.note.isDisplayed();
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void addCredential(String url, String username, String password) throws InterruptedException {
        Thread.sleep(1000);
        this.navCredentials.click();
        Thread.sleep(1000);
        this.addCredential.click();
        Thread.sleep(1000);
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        this.saveCredential.click();
    }

    public boolean verifyCredentialsAdded(String url, String username) throws InterruptedException {
        Thread.sleep(1000);
        this.navCredentials.click();
        Thread.sleep(1000);
        return credentialUrlDisplayed.getText().equalsIgnoreCase(url) && credentialUsernameDisplayed.getText().equalsIgnoreCase(username);
    }

    public boolean verifyCredentialsEncrypted(String password) throws InterruptedException {
        return credentialPasswordDisplayed.getText() != password;
    }

    public boolean verifyUnencryptedPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        this.navCredentials.click();
        Thread.sleep(1000);
        editCredential.click();
        Thread.sleep(1000);
        return this.credentialPassword.getAttribute("value").equals(password);
    }

    public void editCredentials(String url, String username, String password) {
        this.credentialUrl.clear();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.clear();
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.clear();
        this.credentialPassword.sendKeys(password);
        this.saveCredential.click();
    }

    public void deleteCredentials() throws InterruptedException {
        Thread.sleep(1000);
        this.navCredentials.click();
        Thread.sleep(1000);
        this.deleteCredentials.click();
    }

    public boolean verifyCredentialDeleted() throws InterruptedException {
        Thread.sleep(1000);
        this.navCredentials.click();
        Thread.sleep(1000);
        try {
            return this.credentialUrlDisplayed.isDisplayed();
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }

}
