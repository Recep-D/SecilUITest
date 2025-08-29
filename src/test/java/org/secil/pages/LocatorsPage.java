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

    public Locator loginSuccess(){
        return page.locator("div").filter(new Locator.FilterOptions().setHasText("Giriş başarılı")).nth(2);
    }

    public Locator searchBox(){
        return page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("P"));
    }
    
    public Locator yeniGelenlerLink() {
        // Using the specific class and text content from the HTML
        return page.locator("a.pb-6:has-text('Yeni Gelenler')");
    }

    public Locator enterSearchBox(){
        return page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Pant"));
    }

    public Locator clickSearchButton(){
        return page.locator(".grid > div:nth-child(3) > div > a").first();
    }

    public Locator clickForKaban(){
        return page.locator(".flex > .flex > a").first();
    }

    public Locator clickForOtherKaban(){
        return page.locator(".flex > .flex > a:nth-child(2)");
    }

    public boolean isProductAvailable() {
        page.waitForLoadState();
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sepete Ekle")).isVisible();
    }

    public boolean isProductOutOfStock() {
        page.waitForLoadState();
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Tükendi")).isVisible();
    }

    public Locator addToCartButton() {
        page.waitForLoadState();
        if (!isProductAvailable()) {
            throw new RuntimeException("Ürün şu anda stokta bulunmamaktadır.");
        }
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sepete Ekle"));
    }


}
