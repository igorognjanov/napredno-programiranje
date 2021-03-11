package labs.l02.zadaca2;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;



public class ContactsTester {
    abstract public static class Contact{
        protected String date;

        public Contact(String date){
            this.date = date;
        }

        public boolean isNewerThan(Contact c){
            SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = df.parse (this.date);
                date2 = df.parse (c.date);
            } catch (ParseException e) {
                e.printStackTrace ();
            }
            if(date1 == null || date2 == null)return false;
            if(date1.compareTo (date2) > 0) return true;
            return false;
        }
        abstract public String getType();
    }

    public static class EmailContact extends Contact{
        private String email;

        public EmailContact(String date, String email){
            super(date);
            this.email = email;
        }

        public String getEmail(){
            return email;
        }

        @Override
        public String getType(){
            return "Email";
        }
    }

    public static class PhoneContact extends Contact{
        private String phone;

        public PhoneContact(String date, String phone){
            super(date);
            this.phone = phone;
        }

        public String getPhone(){
            return phone;
        }

        public String getOperator(){
            char prefix = this.phone.charAt (2);
            if(prefix == '0' || prefix == '1' || prefix == '2')
                return "TMOBILE";
            if(prefix == '5' || prefix == '6')
                return "ONE";
            if(prefix == '7' || prefix == '8')
                return "VIP";
            return "NE POSTOI OPERATOR SO OVOJ PREFIKS";
        }

        @Override
        public String getType(){
            return "Phone";
        }
    }

    public static class Student{
        private String firstName;
        private String lastName;
        private String city;
        private int age;
        private long index;
        private Contact[] contacts;

        public Student(String firstName, String lastName, String city, int age, long index){
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.age = age;
            this.index = index;
            contacts = new Contact[0];
        }

        public void addEmailContact(String date, String email){
            Contact[] temp = new Contact[contacts.length + 1];
            for(int i=0; i<contacts.length; i++){
                temp[i] = contacts[i];
            }
            temp[contacts.length] = new EmailContact (date, email);
            contacts = temp;
        }

        public void addPhoneContact(String date, String phone){
            Contact[] temp = new Contact[contacts.length + 1];
            for(int i=0; i<contacts.length; i++){
                temp[i] = contacts[i];
            }
            temp[contacts.length] = new PhoneContact (date, phone);
            contacts = temp;
        }

        public Contact[] getEmailContacts(){
            int k=0;
            for(int i=0; i<contacts.length; i++){
                if(contacts[i].getType ().equals ("Email"))
                    k++;
            }
            Contact[] temp = new Contact [k];
            int j=0;
            for(int i=0; i<contacts.length; i++){
                if(contacts[i].getType ().equals ("Email")){
                    temp[j] = contacts[i];
                    j++;
                }
            }
            return temp;
        }

        public Contact[] getPhoneContacts(){
            int k=0;
            for(int i=0; i<contacts.length; i++){
                if(contacts[i].getType ().equals ("Phone"))
                    k++;
            }
            Contact[] temp = new Contact [k];
            int j=0;
            for(int i=0; i<contacts.length; i++){
                if(contacts[i].getType ().equals ("Phone")){
                    temp[j] = contacts[i];
                    j++;
                }
            }
            return temp;
        }

        public String getCity(){ return city;}

        public String getName(){
            return firstName + " " + lastName;
        }

        public long getIndex(){return index;}

        public Contact getLatestContact(){
            if(contacts.length == 0) return null;
            Contact latest = contacts[0];
            for(int i=1; i<contacts.length; i++){
                if(contacts[i].isNewerThan (latest)){
                    latest = contacts[i];
                }
            }
            return latest;
        }

        public boolean isFromCity(String s){
            return this.city.equals (s);
        }

        public int getNumberOfContacts(){
            return contacts.length;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder ();
            str.append ("{\"ime\":\"");
            str.append (firstName + "\", \"prezime\":\"" + lastName + "\", \"vozrast\":" + age);
            str.append (", \"grad\":\"" + city +"\", \"indeks\":"+ index + ", \"telefonskiKontakti\":[");
            Contact [] pc = getPhoneContacts ();
            for(int i=0; i<pc.length; i++){
                PhoneContact p = (PhoneContact) pc[i];
                str.append ("\"" + p.getPhone () + "\"");
                if(i != pc.length-1)
                    str.append (", ");
            }
            str.append ("], \"emailKontakti\":[");
            pc = getEmailContacts ();
            for(int i=0; i<pc.length; i++){
                EmailContact em = (EmailContact) pc[i];
                str.append ("\"" + em.getEmail () + "\"");
                if(i != pc.length-1)
                    str.append (", ");
            }
            str.append ("]}");
            return str.toString ();
        }
    }

    public static class Faculty{
        private String name;
        private Student[] students;

        public Faculty(String name, Student [] students){
            this.name = name;
            this.students = new Student[students.length];
            for(int i=0; i<students.length; i++){
                this.students[i] = new Student (students[i].firstName, students[i].lastName, students[i].city,
                        students[i].age, students[i].index);
            }

        }
        public int countStudentsFromCity(String cityName){
            int count = 0;
            for(int i=0; i<students.length; i++){
                if(students[i].isFromCity (cityName)){
                    count++;
                }
            }
            return count;
        }
        public Student getStudent(long index){
            for(int i=0; i<students.length; i++){
                if(students[i].getIndex () == index)
                    return students[i];
            }
            return null;
        }

        public double getAverageNumberOfContacts(){
            double zbir = 0.0;
            for(int i=0; i<students.length; i++){
                zbir += students[i].getNumberOfContacts ();
            }
            return zbir/(double)students.length;
        }

        public Student getStudentWithMostContacts(){
            Student max = students[0];
            for(int i=0; i<students.length; i++){
                if(students[i].getNumberOfContacts () > max.getNumberOfContacts ()){
                    max = students[i];
                }
                else if(students[i].getNumberOfContacts () == max.getNumberOfContacts ()){
                    if(students[i].getIndex () > max.getIndex ())
                        max = students[i];
                }
            }
            return max;
        }

        @Override
        public String toString(){
            StringBuilder str  = new StringBuilder ();
            str.append ("{\"fakultet\":\"" + name + "\", \"studenti\":[");
            for(int i=0; i<students.length; i++){
                str.append (students[i].toString ());
                if(i != students.length-1)
                    str.append (", ");
            }
            str.append ("]}");
            return str.toString ();
        }
    }






    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}
