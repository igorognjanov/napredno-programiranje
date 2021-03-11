package labs.l03;

import java.util.*;

class Contact{
    private String name;
    private String[] numbers;

    Contact(String name, String... phonenumber) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        if(name.length () <= 4 || name.length () > 10)
            throw new InvalidNameException (name);
        for(int i=0; i<name.length (); i++){
            if(!(name.toLowerCase ().charAt (i) >= 'a' && name.toLowerCase ().charAt (i) <= 'z' )&&
                    !(name.charAt (i) >= '0' && name.charAt (i) <= '9'))
                throw new InvalidNameException (name);
        }
        this.name = name;
        if(phonenumber.length > 5) throw new InvalidNumberException();
        numbers = new String[0];
        for(int i=0; i<phonenumber.length; i++)
            addNumber (phonenumber[i]);
    }

    public void addNumber(String phonenumber) throws InvalidNumberException, MaximumSizeExceddedException {
        if(phonenumber.length () != 9) throw new InvalidNumberException ();
        String prefix = phonenumber.substring (0, 2);
        if(!prefix.equals ("07"))
            throw new InvalidNumberException ();
        char third = phonenumber.charAt (2);
        if(third != '0' && third != '1' && third != '2' && third != '5' && third != '6' && third != '7' &&
                third != '8')
            throw new InvalidNumberException ();
        if(numbers.length >= 5) throw new MaximumSizeExceddedException();
        int n = numbers.length;
        String[] temp = new String[n+1];
        for(int i=0; i<n; i++){
            temp[i] = numbers[i];
        }
        temp[n] = phonenumber;
        numbers = temp;
    }

    private String[] getSorted(String [] temp){
        for(int i=0; i<temp.length-1; i++){
            for (int j=0; j<temp.length-1-i; j++){
                if(temp[j + 1].compareTo (temp[j]) < 0){
                    String t = temp[j];
                    temp[j] = temp[j+1];
                    temp[j+1] = t;
                }
            }
        }
        return temp;
    }

    public String getName(){return name;}
    public String[] getNumbers(){
        String[] temp = getSorted (numbers.clone ());
        return temp;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (this.name);
        stringBuilder.append ("\n");
        stringBuilder.append (numbers.length);
        stringBuilder.append ("\n");
        for (int i=0; i<numbers.length; i++){
            stringBuilder.append (numbers[i]);
            if(i != numbers.length-1)stringBuilder.append ("\n");
        }
        return stringBuilder.toString ();
    }

    public Contact valueOf(String s) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        return new Contact ("igor");
    }
}

class InvalidNameException extends Exception{
    private String name;

    public InvalidNameException(String name){
        this.name = name;
    }

    public void print(){
        System.out.println (name + " InvalidNameException");
    }
}

class InvalidNumberException extends Exception{
    public InvalidNumberException() {
    }

    public void print(){
        System.out.println ("Vneseni se broja, a treba 5");
    }
}

class MaximumSizeExceddedException extends Exception{

    void print(){
        System.out.println ("Ne mozete da dodadite poveke broevi");
    }
}

class PhoneBook{
    private List<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<> (0);
    }
    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if(contacts.size () >= 250) throw new MaximumSizeExceddedException();
        for(int i=0; i<contacts.size (); i++){
            if(contacts.get (i).getName ().equals (contact.getName ())) throw new InvalidNameException (contact.getName ());
        }
        contacts.add (contact);
    }
    @Override
    public String toString(){
        String str = "";
        for(int i=0; i<contacts.size (); i++){
            str += contacts.get (i).toString () + "\n";
        }
        return str;
    }
}

class PhonebookTester {
/*
    public static void main(String[] args) {

        try {
            Contact contact = new Contact ("igigi", "072395415");
            contact.addNumber ("071395415");
            contact.addNumber ("075444555");
            String[] s = contact.getNumbers ();
            PhoneBook phoneBook = new PhoneBook ();
            //phoneBook.addContact (contact);
            phoneBook.addContact (new Contact ("igigi", "071999999"));
            phoneBook.addContact (contact);
            System.out.println (phoneBook);
        } catch (InvalidNameException e) {
            e.print ();
        } catch (InvalidNumberException e) {
            e.print ();
        } catch (MaximumSizeExceddedException e) {
            e.printStackTrace ();
        }

        Scanner jin = new Scanner(System.in);
        String line = jin.nextLine();
        switch( line ) {
            case "test_contact":
                testContact(jin);
                break;
            case "test_phonebook_exceptions":
                testPhonebookExceptions(jin);
                break;
            case "test_usage":
                testUsage(jin);
                break;
        }
    }

    private static void testFile(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() )
            phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
        String text_file = "phonebook.txt";
        PhoneBook.saveAsTextFile(phonebook,text_file);
        PhoneBook pb = PhoneBook.loadFromTextFile(text_file);
        if ( ! pb.equals(phonebook) ) System.out.println("Your file saving and loading doesn't seem to work right");
        else System.out.println("Your file saving and loading works great. Good job!");
    }

    private static void testUsage(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() ) {
            String command = jin.nextLine();
            switch ( command ) {
                case "add":
                    phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
                    break;
                case "remove":
                    phonebook.removeContact(jin.nextLine());
                    break;
                case "print":
                    System.out.println(phonebook.numberOfContacts());
                    System.out.println(Arrays.toString(phonebook.getContacts()));
                    System.out.println(phonebook.toString());
                    break;
                case "get_name":
                    System.out.println(phonebook.getContactForName(jin.nextLine()));
                    break;
                case "get_number":
                    System.out.println(Arrays.toString(phonebook.getContactsForNumber(jin.nextLine())));
                    break;
            }
        }
    }

    private static void testPhonebookExceptions(Scanner jin) {
        PhoneBook phonebook = new PhoneBook();
        boolean exception_thrown = false;
        try {
            while ( jin.hasNextLine() ) {
                phonebook.addContact(new Contact(jin.nextLine()));
            }
        }
        catch ( InvalidNameException e ) {
            System.out.println(e.name);
            exception_thrown = true;
        }
        catch ( Exception e ) {}
        if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw InvalidNameException");

//		exception_thrown = false;
//		try {
//		phonebook.addContact(new Contact(jin.nextLine()));
//		} catch ( MaximumSizeExceddedException e ) {
//			exception_thrown = true;
//		}
//		catch ( Exception e ) {}
//		if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw MaximumSizeExcededException");
  //
    }

    private static void testContact(Scanner jin) throws Exception {
        boolean exception_thrown = true;
        String names_to_test[] = { "And\nrej","asd","AAAAAAAAAAAAAAAAAAAAAA","Ð�Ð½Ð´Ñ€ÐµÑ˜A123213","Andrej#","Andrej<3"};
        for ( String name : names_to_test ) {
            try {
                new Contact(name);
                exception_thrown = false;
            } catch (InvalidNameException e) {
                exception_thrown = true;
            }
            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNameException");
        }
        String numbers_to_test[] = { "+071718028","number","078asdasdasd","070asdqwe","070a56798","07045678a","123456789","074456798","073456798","079456798" };
        for ( String number : numbers_to_test ) {
            try {
                new Contact("Andrej",number);
                exception_thrown = false;
            } catch (InvalidNumberException e) {
                exception_thrown = true;
            }
            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNumberException");
        }
        String nums[] = new String[10];
        for ( int i = 0 ; i < nums.length ; ++i ) nums[i] = getRandomLegitNumber();
        try {
            new Contact("Andrej",nums);
            exception_thrown = false;
        } catch (MaximumSizeExceddedException e) {
            exception_thrown = true;
        }
        if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw a MaximumSizeExceddedException");
        Random rnd = new Random(5);
        Contact contact = new Contact("Andrej",getRandomLegitNumber(rnd),getRandomLegitNumber(rnd),getRandomLegitNumber(rnd));
        System.out.println(contact.getName());
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
    }

    static String[] legit_prefixes = {"070","071","072","075","076","077","078"};
    static Random rnd = new Random();

    private static String getRandomLegitNumber() {
        return getRandomLegitNumber(rnd);
    }

    private static String getRandomLegitNumber(Random rnd) {
        StringBuilder sb = new StringBuilder(legit_prefixes[rnd.nextInt(legit_prefixes.length)]);
        for ( int i = 3 ; i < 9 ; ++i )
            sb.append(rnd.nextInt(10));
        return sb.toString();
    }

    }
*/
}
