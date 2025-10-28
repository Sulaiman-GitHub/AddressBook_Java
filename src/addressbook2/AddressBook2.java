import javax.swing.*;

import java.io.*;
import java.util.*;

// ===== OOP Concept: Class =====
// 'PersonInfo' is a class used to encapsulate data and behavior related to a person.
class PersonInfo {
    // ===== OOP Concept: Encapsulation =====
    // These are instance variables (attributes) of the class.
    String name;
    String address;
    String phoneNumber;

    // ===== OOP Concept: Constructor (Parameterized) =====
    // This initializes an object with name, address, and phone number.
    PersonInfo(String n, String a, String p) {
        name = n;
        address = a;
        phoneNumber = p;
    }

    // ===== OOP Concept: Method (Behavior) =====
    // Displays person info using a GUI dialog.
    void display() {
        // ===== OOP Concept: Abstraction =====
        // GUI details are abstracted away with a simple method call.
        JOptionPane.showMessageDialog(null, "Name: " + name + "\nAddress: " + address + "\nPhone no: " + phoneNumber);
    }
}

// ===== OOP Concept: Class =====
// 'AddressBook' class models a collection of persons.
class AddressBook {
    // ===== OOP Concept: Composition (has-a relationship) =====
    // AddressBook has a list of PersonInfo objects.
    ArrayList persons;

    // ===== Constructor =====
    AddressBook() {
        persons = new ArrayList();
        loadPersons(); // Load saved data (abstraction)
    }

    // ===== OOP Concept: Method (Behavior) =====
    void addPerson() {
        // ===== OOP Concept: Message Passing & Object Creation =====
        // Getting user input, creating a PersonInfo object
        String name = JOptionPane.showInputDialog("Enter Name:");
        String add = JOptionPane.showInputDialog("Enter Address:");
        String pNum = JOptionPane.showInputDialog("Enter PhoneNo:");
        
        // ===== Object Creation =====
        PersonInfo p = new PersonInfo(name, add, pNum);
        persons.add(p);  // Adding object to list
    }    

    // ===== OOP Concept: Method & Polymorphic Behavior via dynamic dispatch =====
    void searchPerson(String n) {
        for (int i = 0; i < persons.size(); i++) {
            // ===== OOP Concept: Type Casting & Dynamic Binding =====
            PersonInfo p = (PersonInfo) persons.get(i);
            if (n.equals(p.name)) {
                p.display(); // Calling method from another class (message passing)
            }
        }
    }

    // ===== Method =====
    void deletePerson(String n) {
        for (int i = 0; i < persons.size(); i++) {
            PersonInfo p = (PersonInfo) persons.get(i);
            if (n.equals(p.name)) {
                persons.remove(i); // Removing object
            }
        }
    }

    // ===== Persistence - saving state =====
    void savePersons() {
        try {
            PersonInfo p;
            String line;
            FileWriter fw = new FileWriter("persons.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < persons.size(); i++) {
                p = (PersonInfo) persons.get(i);
                line = p.name + "," + p.address + "," + p.phoneNumber;
                pw.println(line); // Saving object data to file
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch(IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    // ===== Load previously saved persons =====
    void loadPersons() {
        String tokens[] = null;
        String name, add, ph;
        try {
            FileReader fr = new FileReader("persons.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                tokens = line.split(",");
                name = tokens[0];
                add = tokens[1];
                ph = tokens[2];

                // ===== Object Creation =====
                PersonInfo p = new PersonInfo(name, add, ph);
                persons.add(p);  // Adding to list
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch(IOException ioEx) {
            System.out.println(ioEx);
        }
    }

}

// ===== OOP Concept: Main Driver Class =====
public class AddressBook2 {
    public static void main(String[] args) {
        // ===== OOP Concept: Object Creation =====
        AddressBook ab = new AddressBook(); // Creating object of AddressBook

        String input, s;
        int ch;

        while (true) {
            input = JOptionPane.showInputDialog("Enter 1 to Add\nEnter 2 to Search\nEnter 3 to Delete\nEnter 4 to Exit");
            ch = Integer.parseInt(input);

            // ===== OOP Concept: Message Passing =====
            // Calling methods on AddressBook object
            switch (ch) {
                case 1:
                    ab.addPerson(); // Add new person
                    break;
                case 2:
                    s = JOptionPane.showInputDialog("Enter name to search:");
                    ab.searchPerson(s); // Search person
                    break;
                case 3:
                    s = JOptionPane.showInputDialog("Enter name to delete:");
                    ab.deletePerson(s); // Delete person
                    break;
                case 4:;
                    ab.savePersons(); // Save before exiting
                    System.exit(0);
            }

        }

    }
}

