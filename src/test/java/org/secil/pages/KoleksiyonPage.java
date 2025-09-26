package org.secil.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class KoleksiyonPage {

    /*
     * page.getByRole(AriaRole.LINK, new
     * Page.GetByRoleOptions().setName("Ceket").setExact(true)).click();
     * page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.
     * compile("^hızlı alışverişyeni - biye detaylı düğmeli ceket3499\\.90 ₺$"))).
     * getByRole(AriaRole.LINK).first().click();
     * page.getByText("Mağaza Stok Durumu").click();
     * page.getByRole(AriaRole.BUTTON, new
     * Page.GetByRoleOptions().setName("40")).click();
     * page.getByRole(AriaRole.BUTTON, new
     * Page.GetByRoleOptions().setName("STOK DURUMU SORGULA")).click();
     * page.getByRole(AriaRole.BUTTON, new
     * Page.GetByRoleOptions().setName("Close")).click();
     * page.getByText("Beden Tablosu").click();
     * page.getByRole(AriaRole.TAB, new
     * Page.GetByRoleOptions().setName("PANTOLON")).click();
     * page.locator("#body-table").getByRole(AriaRole.CHECKBOX).first().check();
     * page.locator("#body-table").getByRole(AriaRole.BUTTON).first().click();
     */

    private final Page page;

    public KoleksiyonPage(Page page) {
        this.page = page;
    }

    public Locator kabulEt() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Kabul Et"));
    }
    public void kabulEtClick() {
        kabulEt().click();
    }


    public Locator koleksiyon() {
        return page.locator("//*[@id=\"__next\"]/main/header/div[6]/div/ul/li[5]/div/label/a");
    }
    public void koleksiyonClick() {
        koleksiyon().click();
    }

}