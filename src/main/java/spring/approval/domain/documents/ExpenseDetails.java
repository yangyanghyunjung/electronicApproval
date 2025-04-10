package spring.approval.domain.documents;

import lombok.Data;

@Data
public class ExpenseDetails {
    private String selectedCategory;
    private String expenditure_dt;
    private String selectedAccount;
    private String txtReason;
    private String txtAmount;
    private String selectedCostcenter;
}
