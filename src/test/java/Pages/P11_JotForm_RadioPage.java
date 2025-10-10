package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.util.List;

public class P11_JotForm_RadioPage {
    private final Page page;
    private final List<String> radioOptions = List.of("Never","1-2 days", "3-4 days", "5+ days");

    public P11_JotForm_RadioPage(Page page) {
        this.page = page;
    }

    // Chọn radio theo tên
    public void selectRadio(String name) {
       // page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("3-5 days")).waitFor();

        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(name)).check();
    }

    // Kiểm tra radio được chọn
    public boolean isRadioChecked(String name) {

        return page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(name)).isChecked();
    }

    // Verify chỉ 1 radio được chọn, còn lại uncheck
    public boolean verifyOnlyOneChecked(String selectedName) {
        for (String option : radioOptions) {
            boolean checked = isRadioChecked(option);
            if (option.equals(selectedName) && !checked) return false; // Radio đã chọn phải checked
            if (!option.equals(selectedName) && checked) return false; // Còn lại phải unchecked
        }
        return true;
    }
}
