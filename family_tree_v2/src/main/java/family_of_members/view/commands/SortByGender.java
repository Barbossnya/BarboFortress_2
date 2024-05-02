package family_of_members.view.commands;

import family_of_members.view.ConsoleUI;

public class SortByGender extends Command {
    public SortByGender(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Отсортировать список по гендеру";
    }

    public void execute() {
        consoleUI.sortByGender();
    }
}