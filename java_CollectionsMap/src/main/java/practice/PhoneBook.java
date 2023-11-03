package practice;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
    public TreeSet<String> phoneBook = new TreeSet<>();


    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента

        if (!checkPhone(phone).equals("") && !checkName(name).equals("")) {
            String contactByName = getContactByName(name).toString().replaceAll("\\[*\\]*", "");
            String contactByPhone = getContactByPhone(phone);

            if (contactByName.equals("") && contactByPhone.equals("") &&
                    !contactByName.equals(name) && !contactByPhone.equals(phone)) {
                phoneBook.add(name + " - " + phone);
                System.out.println("Контакт сохранен!");

            } else if (contactByName.contains(name)) {
                phoneBook.remove(contactByName);
                phoneBook.add(contactByName + ", " + phone);
                System.out.println("Контакт сохранен!");

            } else if (contactByPhone.contains(phone)) {
                phoneBook.remove(contactByPhone);
                contactByPhone = contactByPhone.replaceAll("[^0-9,]*", "");
                phoneBook.add(name + " - " + contactByPhone);
                System.out.println("Контакт сохранен!");
            }
        }
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку

        return getContactByName(phone).toString().replaceAll("\\[*\\]*", "");
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet

        Iterator<String> itr = phoneBook.iterator();
        String contactFomBook;
        if (phoneBook.size() == 1) {
            contactFomBook = itr.next();
            if (contactFomBook.contains(name)) {
                return Set.of(contactFomBook);
            }
        }

        while (itr.hasNext()) {
            contactFomBook = itr.next();
            if (contactFomBook.contains(name)) {
                return Set.of(contactFomBook);
            }
        }

        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return phoneBook.isEmpty() ? new TreeSet<>() : phoneBook;
    }

    public String checkPhone(String phone) {
        return !phone.equals("") && phone.matches("[0-9]*") ? phone : "";
    }

    public String checkName(String name) {
        return !name.equals("") && name.matches("[А-Яа-я]*") ? name : "";

        // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
        // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
    }
}