package mk.ukim.finki.aud1;

public class StringPrefix {
    public static boolean isPrefix(String str1, String str2){
        if(str1.length() > str2.length()) return false;
        if(str1.equals(str2)) return true;
        for(int i=0; i<str1.length(); i++){
            if((str1.toLowerCase()).charAt(i) != (str2.toLowerCase()).charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = new String("igor");
        String str2 = new String("IgorOgnjanov");
        if(isPrefix(str1, str2)) System.out.println(str1 + " e prefiks na " + str2);
        else System.out.println(str1 + " ne e prefiks na " + str2);
    }
}
