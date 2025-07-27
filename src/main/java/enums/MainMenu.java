package enums;

public enum MainMenu {
    זכויות_והטבות("מיצוי זכויות"),
    קצבאות("קצבאות והטבות"),
    דמי_ביטוח("דמי ביטוח"),
    יצירת_קשר("יצירת קשר");

    private final String text;

    MainMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}