package trie;

public class Shell {

    public static void main (String[] args) {
        Trie test = new Trie();
        System.out.println(test.add("peter", 100));
        test.add("peter", 100);
        test.add("johannes", 101);
        test.add("daria", 0);
        System.out.println(test.toString());
    }
}
