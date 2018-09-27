package comp1110.ass2;

import java.util.HashSet;

public class testing {
    public static void main(String[] args) {
        HashSet <String> ans = new HashSet<>();
        ans.add("b2C4");
        String s = "b2C6";
        String a = TwistGame.symmetry(s);
        System.out.println(a);

        System.out.println(ans.contains(TwistGame.symmetry(s)));
    }
}
