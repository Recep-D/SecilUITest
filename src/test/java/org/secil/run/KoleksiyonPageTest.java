package org.secil.run;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.secil.pages.KoleksiyonPage;
import org.secil.utils.BrowserFactory;
import org.secil.utils.ConfigurationReader;
import org.secil.utils.Hooks;

import com.microsoft.playwright.Page;

public class KoleksiyonPageTest extends Hooks {
    private KoleksiyonPage koleksiyonPage;

    @Test
    public void koleksiyonPageTest() {
        Page page= BrowserFactory.page;
        koleksiyonPage = new KoleksiyonPage(page);
       
        page.navigate(ConfigurationReader.getProperty("url"));

        //Assertions.assertTrue(page.title().contains("SEÇİL | Kadın Giyim & Modası | Online Alışveriş Sitesi"));
        
        koleksiyonPage.kabulEtClick();

        koleksiyonPage.koleksiyonClick();
        

    }

   


}
