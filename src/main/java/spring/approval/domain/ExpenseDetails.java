package spring.approval.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ExpenseDetails {
    private String selectedCategory;
    private String expenditure_dt;
    private String selectedAccount;
    private String txtReason;
    private String txtAmount;
    private String selectedCostcenter;

    public ExpenseDetails() {
    }

    public ExpenseDetails(String selectedCategory, String expenditure_dt, String selectedAccount, String txtReason,
                          String txtAmount, String selectedCostcenter) {
        this.selectedCategory = selectedCategory;
        this.expenditure_dt = expenditure_dt;
        this.selectedAccount = selectedAccount;
        this.txtReason = txtReason;
        this.txtAmount = txtAmount;
        this.selectedCostcenter = selectedCostcenter;
    }
}
