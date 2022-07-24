import java.util.*;
import java.security.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\dave0\\IdeaProjects\\CS210project.java\\src\\Words"));
        String[] myarray = new String[466551];
        String[] hashArray = new String[myarray.length];
        String[] Sentences = new String[myarray.length];
        for (int i = 0; i < myarray.length; i++) {
            myarray[i] = sc.nextLine();
            hashArray[i] = sha256(myarray[i]);
        }

        int count = 0;
        int maxcount = 0;
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < myarray.length - 1; i++) {
            for (int j = i + 1; j < myarray.length; j++) {
                if (hashArray[i].charAt(i) == hashArray[j].charAt(j)) {

                    count++;
                }

                if (count > maxcount) {
                    maxcount = count;
                    i1 = i;
                    i2 = j;
                }
            }
        }
        System.out.println(maxcount);
        System.out.println(myarray[i1]);
        System.out.println(myarray[i2]);


    }

    public static String sha256(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return (e.toString());
        }
    }
}
