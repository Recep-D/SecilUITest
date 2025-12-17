package org.secil.run;

import org.junit.jupiter.api.Test;
import org.secil.pages.IndirimPage;
import org.secil.pages.LocatorsPage;
import org.secil.utils.BrowserFactory;
import org.secil.utils.ConfigurationReader;
import org.secil.utils.Hooks;

import com.microsoft.playwright.Page;

public class IndirimPageTest extends Hooks {
    private IndirimPage indirimPage;

    @Test
    public void indirimPageTest() {
        Page page = BrowserFactory.page;
        indirimPage = new IndirimPage(page);

        // Navigate to main page
        page.navigate(ConfigurationReader.getProperty("url"));
        page.waitForLoadState();
        
        indirimPage.kabulEt().click();

        indirimPage.indirimLink().click();
        
        // Apply filters
        indirimPage.filtrele().click();
        indirimPage.renk().click(); 
        indirimPage.siyah().click();
        indirimPage.kahverengi().click();
        indirimPage.marka().click();
        indirimPage.secil().click();
        indirimPage.close().click();


        
    }
    

}
