package trie;

public class Trie {

    private static final char[] alphabet = {  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final Node root;

    /*
        Erzeugt einen neuen Trie.
     */
    public Trie() {
        root = new Node();
    }

    /*
        Fügt an der Stelle key einen neuen Eintrag mit dem Wert points hinzu. Wenn nötig werden dazu auch neue Knoten und Kanten erstellt.
        Liefert nur dann false zurück, wenn an der Stelle key im Trie schon ein Eintrag existiert.
     */
    public boolean add(String key, Integer points){
        System.out.println("Adding the word " + key + ", with " + points + " points.");
        //Star from the root
        Node current = root;

        //traverse the key
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);    //assign ch with the character at index i.
            int index = getIndex(ch);   //get the array index for that character (e.g. a = 0)
            //Check if nodes exist
                if (current.children[index] == null) {
                    //System.out.println("Current character is " + ch);
                    Node insert;
                    insert = new Node(ch, current);
                    current.children[index] = insert;
                }
            current = current.children[index];
        }
        if(current.isWord) {
            current.setPoints(points);
            return false;
        }
        else {
            current.setPoints(points);
            current.setWord();
            return true;
        }
    }

    /*
        Sucht im Trie nach einem Eintrag an der Stelle key und löscht diesen mit Hilfe der Methode public void remove() aus der Klasse Node.
        Liefert nur dann true zurück, wenn ein bestehender Eintrag gefunden und gelöscht wurde.
     */

    //to delete a word I need to start at the bottom of the branch and work upwards until I reach a leaf that is branching off or is a word
    //e.g. String and Strings I only need to delete the s if I want to delete Strings
    public boolean remove(String key) {
        //get the last node from key
        Node current = root.find(key);
        if (current == null || !current.isWord) {
            return false;
        }
        char[] characters = key.toCharArray();
        char temp;

        //reverse the array
        for (int i = 0; i < key.length() / 2; i++) {
            temp = characters[i];
            characters[i] = characters[key.length() - i];
            characters[key.length() - i] = temp;
        }

        //remove the first leaf as its guaranteed to be a word
        Node temporary = current.parent;
        current.remove();
        current = temporary;
        //traverse the array backwards starting from the second character (because we just removed the first one
        for (int i = 1; i < characters.length; i++) {
            //if leaf still has children then we don't need to remove more leafs as the current leaf leads to a different word
            //or
            //if leaf is a word then we still need it as far as then
            if (current.children.length > 0 || current.isWord) {
                break;
            }

            temporary = current.parent;
            current.remove();
            current = temporary;

        }
        return true;
    }

    /*
        Sucht im Trie nach einem Eintrag an der Stelle key und ändert dessen Wert zu points. Gibt nur dann true zurück,
        wenn ein bereits existierender Eintrag gefunden und geändert wurde.
     */
    public boolean change(String key, Integer points) {
        return false;
    }

    /**
     * Searches for the entry points at the position given by key
     * @param key
     * @return
     */
    public Integer points(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null.");
        }
        Integer points = null;

        Node current = root.find(key);
        if (current.isWord) {
            //return the points if there are no points null will be returned
            return current.points;
        }
        return null;
    }

    /*
        Gibt den gesamten Trie als String in dem zuvor beschriebenen Ausgabeformat zurück.
     */
    public String toString(){
        return root.toString();
    }

     private boolean find(String word) throws Exception {
        Node current = root;
        char ch;

        for(int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);


            if(current.children[getIndex(ch)].ch == ch) {
                current = current.children[i];
            }
            else
            {
                return false;
            }
        }
        return true;
     }

     private int getIndex(char ch) {
        switch (ch){
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            case 'k':
                return 10;
            case 'l':
                return 11;
            case 'm':
                return 12;
            case 'n':
                return 13;
            case 'o':
                return 14;
            case 'p':
                return 15;
            case 'q':
                return 16;
            case 'r':
                return 17;
            case 's':
                return 18;
            case 't':
                return 19;
            case 'u':
                return 20;
            case 'v':
                return 21;
            case 'w':
                return 22;
            case 'x':
                return 23;
            case 'y':
                return 24;
            case 'z':
                return 25;
            default:
                System.out.println("Error! The word seems to have an unexpected character.");
                return 0;
        }
     }
}
