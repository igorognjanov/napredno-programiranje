package mk.ukim.finki.aud8.Phonebook;

import com.sun.source.tree.Tree;

import java.util.*;


class DuplicateNumberException extends Exception{
    private String num;

    public DuplicateNumberException(String num) {
        this.num = num;
    }

    public String getMessage(){
        return "Duplicate number: " + num;
    }

}


class Contact {
    private String phone;
    private String name;
    static Comparator<Contact> contactComparator =
            Comparator.comparing (Contact::getName).thenComparing (Contact::getPhone);
    public Contact(String name, String phone) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name + " " + phone;
    }


    @Override
    public int hashCode() {
        return Objects.hash (phone);
    }


}



class PhoneBook{
    private HashMap<String, String> namesByPhoneNumber;
    private HashMap<String, TreeSet<Contact>> contactsBySubnumber;
    private HashMap<String, TreeSet<Contact>> contactsByName;


    public PhoneBook() {
        namesByPhoneNumber = new HashMap<> ();
        contactsByName = new HashMap<> ();
        contactsBySubnumber = new HashMap<> ();
    }

    public void addContact(String name, String number) throws DuplicateNumberException{
        Contact contact = new Contact (name, number);
        if(namesByPhoneNumber.containsKey (number)) throw new DuplicateNumberException (number);
        namesByPhoneNumber.put (number, name);

        contactsByName.putIfAbsent (name, new TreeSet<> (Contact.contactComparator));
        contactsByName.get (name).add (contact);

        List<String> subnumbers = getSubnumbers (number);
        subnumbers.stream ().forEach (subnumber -> {
            contactsBySubnumber.putIfAbsent (subnumber, new TreeSet<> (Contact.contactComparator));
            contactsBySubnumber.get (subnumber).add (contact);
        });
    }

    public static List<String> getSubnumbers(String str){
        ArrayList<String> list = new ArrayList<> ();
        for(int i=0; i<=str.length ()-3; i++){
            for(int j=3; i+j<=str.length (); j++){
                list.add(str.substring (i, i+j));
            }
        }
        return list;
    }

    public void contactsByName(String name){
        System.out.println ("NAME:" + name);
        if(contactsByName.containsKey (name))
            contactsByName.get (name).stream ().forEach (System.out::println);
        else System.out.println ("NOT FOUND");
    }

    public void contactsByNumber(String number){
        System.out.println ("NUM:" + number);
        if(contactsBySubnumber.containsKey (number))
            contactsBySubnumber.get (number).stream ().forEach (System.out::println);
        else System.out.println ("NOT FOUND");
    }
}

public class PhoneBookTest {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook ();
        Scanner scanner = new Scanner (System.in);
        int n = scanner.nextInt ();
        scanner.nextLine ();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine ();
            String[] parts = line.split (":");
            try {
                phoneBook.addContact (parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println (e.getMessage ());
            }
        }
        while (scanner.hasNextLine ()) {
            String line = scanner.nextLine ();
            String[] parts = line.split (":");
            if (parts[0].equals ("NUM")) {
                phoneBook.contactsByNumber (parts[1]);
            } else {
                phoneBook.contactsByName (parts[1]);
            }
        }

    }
}
