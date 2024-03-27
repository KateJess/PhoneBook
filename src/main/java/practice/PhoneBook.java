package practice;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    private static final String NAME_REGEX = "[А-Я].[а-я]+";
    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    TreeMap<String,String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        if (isCorrect(name, NAME_REGEX) && isCorrect(phone, PHONE_REGEX)) {
            if (phoneBook.containsValue(name)) {
                String oldKey = getKey(name);
                String newKey = oldKey + ", " + phone;
                phoneBook.remove(oldKey, name);
                phoneBook.put(newKey, name);
            }
            else {
                phoneBook.put(phone, name);
            }
        } else {
            System.out.printf("Правильность введенных параметров:" +
                    "\nНомер телефона: %b" +
                    "\nИмя: %b\n", isCorrect(phone, PHONE_REGEX), isCorrect(name, NAME_REGEX));
        }
    }

    public String getContactByPhone(String phone) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (phone.equals(key) || key.contains(phone)) {
                result.append(value).append(" - ").append(key);
            }
        }
        return result.toString();
    }

    public Set<String> getContactByName(String name) {
        TreeSet<String> phoneNumbersSet = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (name.equals(value)) {
                phoneNumbersSet.add(value + " - " + key);
            }
        }
        return phoneNumbersSet;
    }

    public Set<String> getAllContacts() {
        TreeSet<String> phoneNumbersList = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            phoneNumbersList.add(value + " - " + key);
        }
        return phoneNumbersList;
    }

    public boolean isCorrect(String line, String regex) {
        return line.matches(regex);
    }

    public String getKey(String name) {
        String result = "";
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            result = name.equals(value) ? key : "";
        }
        return result;
    }
}