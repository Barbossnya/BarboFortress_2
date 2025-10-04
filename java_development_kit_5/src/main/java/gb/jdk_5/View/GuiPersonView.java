package gb.jdk_5.View;

import java.util.List;
import gb.jdk_5.Model.Person;

public interface GuiPersonView {
    void showSuccess(String message);
    void showError(String message);
    void clearForm();
    void refreshPersonList();
    void updatePersonList(List<Person> people);
    void showLoading(boolean isLoading);
} 