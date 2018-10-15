package comp1110.ass2;
import java.lang.reflect.Array;
import java.util.*;
public class test9 {

    public static void main(String[] args) {

//        Challenges c = Challenges.newChallenge(1);
//        System.out.println(c.getStatement());

        TwitGame1 twisGame = new TwitGame1(1);
        System.out.println(twisGame.getPlacement());


//        ArrayList<String> s = new ArrayList<>();
//        ArrayList<String> s2 = new ArrayList<>();
//        ArrayList<String> s3 = new ArrayList<>();
//        System.out.println(getSolutions("b6B0c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0"));


//        Set<String> next = TwistGame.getViablePiecePlacements("b6B0c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0");
//        if (next!=null){
//            for (String i:next){
//                s.add(TwistGame.GetNewPlacement("b6B0c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0",i));
//            }
//        }
//        String[] s1 = s.toArray(new String[0]);
//        for (String i: s1){
//            if (TwistGame.IsSolution(i)){
//                System.out.println(i);
//            }
//        }

//        Set<String> next = TwistGame.getViablePiecePlacements("c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0");
//        if (next!=null){
//            for (String i:next){
//                s.add(TwistGame.GetNewPlacement("c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0",i));
//            }
//        }
//        for (String j:s){
//            Set<String> next1 = TwistGame.getViablePiecePlacements(j);
//            if (next1!=null){
//                for (String k: next1){
//                    s2.add(TwistGame.GetNewPlacement(j,k));
//                }
//            }
//
//        }
//
//        for (String i: s2){
//            if (TwistGame.IsSolution(i)){
//                s3.add(i);
//            }
//
//        }
//        String[] s1 = s3.toArray(new String[0]);
//        for (String i: s1){
//            if (TwistGame.IsSolution(i)){
//                System.out.println(i.substring(0,32));
//            }
//        }


//        String[] s ={"123","123"};
//        TreeSet<String> ans = new TreeSet<>();
//        for (int i=0; i<s.length;i++){
//            ans.add(s[i]);
//        }
//        String[] s1 = new String[ans.size()];
//        for (int i=0; i<s1.length; i++){
//            s1[i] = ans.pollFirst();
//        }
//        for (String i: s){
//            System.out.println(i);
//        }



//        String[] day = new String[10];
//        for(int i =0; i < 10; i++)
//            day[i] = "day";
//        System.out.println(Arrays.toString(day));

    }
}
