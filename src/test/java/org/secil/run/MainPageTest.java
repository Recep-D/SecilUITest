package org.secil.run;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.secil.pages.LocatorsPage;
import org.secil.utils.BrowserFactory;
import org.secil.utils.ConfigurationReader;
import org.secil.utils.Hooks;

import java.util.regex.Pattern;

public class MainPageTest extends Hooks {

    private LocatorsPage locatorsPage;


    @Test
    void mainPageTest() {

        Page page=BrowserFactory.page;
        locatorsPage = new LocatorsPage(page);

        page.navigate(ConfigurationReader.getProperty("url"));
        page.waitForTimeout(3000);

        String pageUrl = page.url();
        Assertions.assertTrue(Pattern.matches("https://www.secilstore.com/", pageUrl));

        String pageTitleActual = page.title();
        Assertions.assertEquals("Kadın Giyim ve Aksesuar Markası: SecilStore - SecilStore", pageTitleActual );

        boolean isElementVisible = page.locator("button:has-text('Hesabım')").isVisible();
        System.out.println("İlk context’te 'Hesabım' teksti bulundu mu?: " + isElementVisible);

        locatorsPage.cookies().click();
        locatorsPage.enterButton().click();
        locatorsPage.userNameBox().click();
        locatorsPage.enterUserName().fill("sdet.recepdemirci@gmail.com");
        locatorsPage.passwordBox().click();
        locatorsPage.enterPassword().fill("Tester/1");
        locatorsPage.clickLoginButton().click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText("Giriş başarılı")).nth(2).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("P")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Pant")).fill("kaban");
        page.getByText("\"kaban\" aramasından toplam 40").click();
        page.locator(".grid > div:nth-child(3) > div > a").first().click();
        page.locator(".flex > .flex > a").first().click();
        page.locator(".flex > .flex > a:nth-child(2)").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sepete Ekle")).click();

        String accountText = page.locator("//span[.='Hesabım']").last().innerText();
        System.out.println("accountText = " + accountText);

        boolean isAccountTextVisible = page.locator("//span[.='Hesabım']").last().isVisible();
        System.out.println("Giriş sonrası 'Hesabım' teksti bulundu mu?: " + isAccountTextVisible);



    }
}
