package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class testing {
    public static void main(String[] args) {
        String[]  ans = TwistGame.getSolutions("i4B0j7C0j5D0k2B0");
        String ans2 = "";
        for (String ss : ans){
            System.out.println(ss);
            ans2 += ss;
            ans2 += ", ";
        }

        System.out.println(ans);
    }
}
