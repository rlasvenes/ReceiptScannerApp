package main.fr.ubordeaux.ui.swing.Layouts;

public enum Layouts {
    MAIN_MENU(0),
    CREATE_TICKET(1),
    LIST_TICKET(2),
    MODIFY_TICKET(3);


    private int value;
    Layouts(int i) {
        value = i;
    }

    public int id() {
        return value;
    }
}
