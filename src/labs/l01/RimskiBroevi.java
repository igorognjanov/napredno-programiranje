package labs.l01;


public class RimskiBroevi {
    public String toRoman(int n){
        int [] array1 = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] array2 = {"I","IV","V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder str = new StringBuilder();
        while(n > 0){
            int maxIndex = 0;
            for(int i=0; i<array1.length; i++){
                if(n >= array1[i]) maxIndex = i;
            }
            str.append(array2[maxIndex]);
            n -= array1[maxIndex];
        }
        return str.toString();
    }
    public static void main(String[] args) {
        int n = 50;
        RimskiBroevi rim = new RimskiBroevi();
        System.out.println(rim.toRoman(n));
    }
}

