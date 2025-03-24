package org.secil.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LocatorsPage {

    private final Page page;

    public LocatorsPage(Page page) {
        this.page = page;
    }

    public Locator cookies() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Kabul Et"));
    }

    public Locator enterButton() {
        return page.locator("a").filter(new Locator.FilterOptions().setHasText("Giriş Yap")).first();
    }

    public Locator userNameBox(){
        return page.getByPlaceholder("Email adresiniz");
    }

    public Locator enterUserName(){
        return page.getByPlaceholder("Email adresiniz");
    }

    public Locator passwordBox(){
        return page.getByPlaceholder("Şifreniz");
    }

    public Locator enterPassword(){
        return page.getByPlaceholder("Şifreniz");
    }

    public Locator clickLoginButton(){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Giriş"));
    }
}


