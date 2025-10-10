package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;


public class P10_External_CheckBoxPage {
    private final Page page;

    public final Locator checkbox1;
    private final Locator checkbox2;

    // Constructor
    public P10_External_CheckBoxPage(Page page) {
        this.page = page;

        this.checkbox1 = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Checkbox 1"));

        this.checkbox2 = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Checkbox 2"));

    }

    public void checkBox1() {
        checkbox1.check();
    }

    public void uncheckBox1() {
        checkbox1.uncheck();
    }
    public void checkBox2() {
        checkbox2.check();
    }

    public void uncheckBox2() {
        checkbox2.uncheck();
    }

    public boolean isBox1Checked() {
        return checkbox1.isChecked();
    }
    public boolean isBox2Checked() {
        return checkbox2.isChecked();
    }
    // ----- Combined actions -----
    @Step("Check all checkboxes")
    public void checkAll() {
        if (!checkbox1.isChecked()) checkbox1.check();
        if (!checkbox2.isChecked()) checkbox2.check();
    }

    @Step("Uncheck all checkboxes")
    public void uncheckAll() {
        if (checkbox1.isChecked()) checkbox1.uncheck();
        if (checkbox2.isChecked()) checkbox2.uncheck();
    }

    public boolean areAllChecked() {
        return checkbox1.isChecked() && checkbox2.isChecked();
    }

    public boolean areAllUnchecked() {
        return !checkbox1.isChecked() && !checkbox2.isChecked();
    }

}
