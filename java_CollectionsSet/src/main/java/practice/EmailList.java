package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmailList {

    public List<String> arrayList = new ArrayList();
    public void add(String email) {
        // TODO: валидный формат email добавляется, email это строка, она быть может любой
        // принять решение добавлять аргумент email или нет должен этот метод
        if (email.indexOf('@') > 0 && email.indexOf('.') > 0 && !arrayList.contains(email.toLowerCase())) {
            arrayList.add(email.toLowerCase());
        }
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается сортированный список электронных адресов в алфавитном порядке
        Collections.sort(arrayList);
        return arrayList;
    }

}
