package tamil;

import java.util.ArrayList;
import java.util.Arrays;

import static tamil.AlageetuVaipadu.*;

public class Main {
    public static void main(String[] args) {
        String orig = "காணொளி";

        System.out.println("Original String : " + orig);
        StringBuffer sb = new StringBuffer();


        try {
            TamilCharacters tc = new TamilCharacters();
            String[] sarr = tc.detachProperChars(orig, sb);   //Parse proper Characters
            System.out.println(" Characters : " + Arrays.asList(sarr));
            String[] arr = allocatePart(tc, sarr, sb);  // detach Character ... ie. kuril, nedil
            System.out.println(" Character types : " + Arrays.asList(arr));
            ArrayList asaikal = getAsaikal(arr, sarr, sb);  //detaching asai
            String seer = getSeer(asaikal);   //get seer
            System.out.println(" Asaikal : " + asaikal);
            System.out.println("Seer :: " + seer);
            System.out.println(sb.toString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
