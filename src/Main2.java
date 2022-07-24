import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

class Main2 {

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

    public static int count(String a, String b) { // count method created to give me the similarity score between the two hashes
        int sum = 0;// was created to store the count of the similarity score
        for(int i = 0; i < 64; i++) { // a for loop with a length up to 64.
            sum += a.charAt(i) == b.charAt(i) ? 1 : 0;// the sum is equaled to the amount of matching hash value from the two sentences
        }
        return sum;// here the sum is returned
    }

    public static void main(String[] args) throws IOException {// creating the main class
        List<String> sentences = Files.lines(Path.of("src\\sentences.txt")).toList();// ana array list made to hold all the sentences
        List<String> sha = new ArrayList<>();// another arraylist for all the sha values

        for(String sentence : sentences)//this increment is used for each sentence to run
            sha.add(sha256(sentence));// each sentence that runs in goes through the called SHA - 256 method and gets a hash value
        int max = 0;// it's the variable representing the current maximum.

        // comparing each sentence with each sentence in a nested for loop
        for (int i = 0; i < sentences.size(); i++) {// this loops the first sentence
            for (int j = i + 1; j < sentences.size(); j++) {// this loops the second sentence following the previous sentence every time
                int count = count(sha.get(i), sha.get(j)); // the count between each sentence called into to the method and is then stored in the count variable
                if(sentences.get(i).equals(sentences.get(j))) // this if statement was created to stop the duplicates from appearing
                {
                    continue;// this skips the duplicates if they appear
                }

                //sha list contains all the hashes, sentences contains all the sentences.
                if(count > max) { // if statement to print the highest has values
                    //this will update your maximum and print your sentences.
                    max = count; // max become count to recognise the highest value.
                    System.out.println(sentences.get(i));// prints the first sentence
                    System.out.println(sha.get(i));// prints the hash value of the sentence
                    System.out.println(sentences.get(j));// prints the following sentence after
                    System.out.println(sha.get(j));// prints the hash value here too
                    System.out.println(max);// prints the similarity count between them both and the highest one found between all the compared sentences lastly
                }

                
            }
        }
    }

}
