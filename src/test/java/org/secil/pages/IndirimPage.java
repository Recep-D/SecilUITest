package org.secil.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class IndirimPage {
    private final Page page;

    public IndirimPage(Page page) {
        this.page = page;
    }

    public Locator kabulEt() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Kabul Et"));
    }

    public Locator indirimLink() {
        return page.locator("//*[@id=\"__next\"]/main/header/div[6]/div/ul/li[8]/div/label/a");
    }

    public Locator filtrele() {
        return page.getByText("Filtrele");
    }
    public Locator renk() {
        return page.getByText("Renk", new Page.GetByTextOptions().setExact(true));
    }
    public Locator siyah() {
        return page.getByText("Siyah", new Page.GetByTextOptions().setExact(true));
    }
    public Locator kahverengi() {
        return page.getByText("Kahverengi", new Page.GetByTextOptions().setExact(true));
    }
    
    public Locator marka() {
        return page.getByText("Marka", new Page.GetByTextOptions().setExact(true));
    }
    public Locator secil() {
        return page.getByLabel("Seçil");
    }
    public Locator close() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
    }

    

    
     
   


}
