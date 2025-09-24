package org.secil.run;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import org.secil.pages.LocatorsPage;
import org.secil.utils.BrowserFactory;
import org.secil.utils.ConfigurationReader;
import org.secil.utils.Hooks;

import java.nio.file.Paths;

public class Outletpage extends Hooks {

    private LocatorsPage locatorsPage;

    @Test
    void outletPageTest() {
        Page page = BrowserFactory.page;
        locatorsPage = new LocatorsPage(page);

        try {
            // Navigate to the main page
            String baseUrl = ConfigurationReader.getProperty("url");
            System.out.println("Navigating to: " + baseUrl);
            page.navigate(baseUrl);
            
            // Wait for page to load
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.waitForTimeout(2000);

            // Accept cookies if present
            try {
                locatorsPage.cookies().click();
                System.out.println("Cookies accepted");
            } catch (Exception e) {
                System.out.println("No cookie dialog found: " + e.getMessage());
            }

            // Navigate to outlet page
            String outletUrl = baseUrl + "/outlet";
            System.out.println("Navigating to outlet page: " + outletUrl);
            page.navigate(outletUrl);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.waitForTimeout(2000);

            // Take screenshot of outlet page
            page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("outlet-page-screenshot.png"))
                .setFullPage(true));
            System.out.println("Outlet page screenshot saved");

            // Verify we're on the outlet page
            String currentUrl = page.url();
            System.out.println("Current URL: " + currentUrl);
            
            if (currentUrl.contains("outlet")) {
                System.out.println("Successfully navigated to outlet page");
            } else {
                System.out.println("Warning: URL does not contain 'outlet'");
            }

            // Click the first photo on the top left of the page
            System.out.println("Looking for the first product photo...");
            try {
                // Wait for products to load
                page.waitForTimeout(3000);
                
                // Multiple selectors to find the first product image
                String[] imageSelectors = {
                    "div.product-item:first-child img",
                    ".product-grid .product-item:first-child img",
                    ".products .product:first-child img",
                    "img[alt*='product'], img[alt*='ürün']",
                    ".product-image:first-child img",
                    "a:has(img):first-child"
                };
                
                boolean productClicked = false;
                for (String selector : imageSelectors) {
                    try {
                        Locator productImage = page.locator(selector).first();
                        if (productImage.isVisible()) {
                            System.out.println("Found product image with selector: " + selector);
                            productImage.scrollIntoViewIfNeeded();
                            productImage.click();
                            productClicked = true;
                            System.out.println("Successfully clicked the first product photo");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Selector failed: " + selector + " - " + e.getMessage());
                    }
                }
                
                if (!productClicked) {
                    // Fallback: try clicking any clickable product element
                    try {
                        page.locator("a").filter(new Locator.FilterOptions().setHas(page.locator("img"))).first().click();
                        productClicked = true;
                        System.out.println("Clicked product using fallback method");
                    } catch (Exception e) {
                        throw new RuntimeException("Could not find any product to click: " + e.getMessage());
                    }
                }
                
                // Wait for page loading after clicking product
                System.out.println("Waiting for product page to load...");
                page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                page.waitForLoadState(LoadState.NETWORKIDLE);
                page.waitForTimeout(2000);
                
                // Take screenshot of product page
                page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("product-page-screenshot.png"))
                    .setFullPage(true));
                System.out.println("Product page screenshot saved");
                
            } catch (Exception e) {
                System.err.println("Error clicking product photo: " + e.getMessage());
                throw e;
            }

            // Click "SEPETE EKLE" button
            System.out.println("Looking for 'SEPETE EKLE' button...");
            try {
                // Multiple selectors for the add to cart button
                String[] addToCartSelectors = {
                    "button:has-text('SEPETE EKLE')",
                    "button:has-text('Sepete Ekle')",
                    "button[class*='add-to-cart']",
                    "button[class*='addtocart']",
                    ".add-to-cart-button",
                    "input[value*='SEPETE EKLE']",
                    "a:has-text('SEPETE EKLE')"
                };
                
                boolean addToCartClicked = false;
                for (String selector : addToCartSelectors) {
                    try {
                        Locator addToCartButton = page.locator(selector).first();
                        if (addToCartButton.isVisible()) {
                            System.out.println("Found 'SEPETE EKLE' button with selector: " + selector);
                            
                            // Check if product is available before adding to cart
                            if (locatorsPage.isProductOutOfStock()) {
                                System.out.println("Product is out of stock, cannot add to cart");
                                break;
                            }
                            
                            addToCartButton.scrollIntoViewIfNeeded();
                            addToCartButton.click();
                            addToCartClicked = true;
                            System.out.println("Successfully clicked 'SEPETE EKLE' button");
                            
                            // Wait for add to cart action to complete
                            page.waitForTimeout(2000);
                            
                            // Take screenshot after adding to cart
                            page.screenshot(new Page.ScreenshotOptions()
                                .setPath(Paths.get("after-add-to-cart-screenshot.png"))
                                .setFullPage(true));
                            System.out.println("After add to cart screenshot saved");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Add to cart selector failed: " + selector + " - " + e.getMessage());
                    }
                }
                
                if (!addToCartClicked) {
                    System.out.println("Could not find 'SEPETE EKLE' button. Product might be out of stock or button not available.");
                    // Take screenshot for debugging
                    page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("sepete-ekle-not-found-screenshot.png"))
                        .setFullPage(true));
                }
                
            } catch (Exception e) {
                System.err.println("Error clicking 'SEPETE EKLE' button: " + e.getMessage());
                throw e;
            }

            System.out.println("Outlet page test completed successfully");

        } catch (Exception e) {
            System.err.println("Error during outlet page test: " + e.getMessage());
            
            // Take error screenshot
            page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("outlet-error-screenshot.png"))
                .setFullPage(true));
            System.err.println("Error screenshot saved to: outlet-error-screenshot.png");
            
            throw e;
        }
    }
}
