package section8;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact currentContact = myContacts.get(i);
            if (currentContact.getName().equals(contact.getName())) return false;
        }
        return myContacts.add(contact);
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int index = myContacts.indexOf(oldContact);
        if (index == - 1) return false;
        myContacts.set(index, newContact);
        return true;
    }

    public boolean removeContact(Contact contact) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact currentContact = myContacts.get(i);
            if (currentContact.getName().equals(contact.getName())) {
                myContacts.remove(i);
                return true;
            }
        }
        return false;
    }

    private int findContact(Contact contact) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact currentContact = myContacts.get(i);
            if (currentContact.getName().equals(contact.getName())) return i;
        }
        return -1;
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    public Contact queryContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) return contact;
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            System.out.println(
                    (i + 1) + ". " + contact.getName() +
                            " -> " + contact.getPhoneNumber());
        }
    }
}
