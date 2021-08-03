package trie;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private static final char[] alphabet = {  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public Node node;
    public Node parent;
    public char ch;
    public boolean isWord;
    public Integer points;
    public Node[] children;


    /*
        Erzeugt einen Wurzelknoten.
     */
    public Node() {
        children = new Node[25];
        this.ch = '\0';
        this.node = null;
        this.parent = null;
        this.isWord = false;
        this.points = null;
    }

    /*
        Erzeugt einen Knoten für das Zeichen ch und setzt den Knoten parent
        als Vater. Gleichzeitig ist dafür zu sorgen, dass der erzeugte Knoten auch als Kind von parent gesetzt wird.
     */
    public Node(char ch, Node node) {
        children = new Node[25];
        this.ch = ch;
        this.node = node;
        this.parent = node;
        this.isWord = false;
        this.points = null;
    }

    /*
        Setzt den Verweis des Kindknotens für das Zeichen ch auf den Knoten child.
     */
    public void setChild(char ch, Node child) {
        this.node.children[getIndex(ch)] = child;
        child.parent = this.node;
    }

    /*
        Gibt den Kindknoten für das Zeichen ch zurück. Wenn kein Kind gefunden wird, wird null zurück geliefert.
     */
    public Node getChild(char ch) {
        if (node.children[getIndex(ch)] != null) {
            return node.children[getIndex(ch)];
        }
        return null;
    }

    /*
        Navigiert entsprechend der Zeichenfolge key durch den aktuellen Unterbaum und sucht nach dem Knoten an dieser Stelle.
        Gibt null zurück, wenn kein passender Knoten existiert.
     */
    public Node find(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null.");
        }
        else if (key.length() == 0){
            return this;
        }
        else {
            char firstCh = key.charAt(0);
            Node child = getChild(firstCh);

            return child != null ? child.find(key.substring(1)) : null;
        }
    }

    /*
        Löscht den im Knoten gespeicherten Wert und entfernt alle dadurch unnütz gewordenen Knoten

        Hinweis: Schreiben Sie sich hierfür eine Hilfsmethode private void cleanup(),
        welche alle unnützen Knoten oberhalb und einschließlich des aktuellen Knotens aus dem Trie löscht.
     */
    public void remove() {

        Node[] children = node.getChildren();
        for (Node child : children) {
            child.remove();
        }
    }

    /*
        Gibt die textuelle Darstellung des aktuellen Knotens und seiner Kinder im geforderten Format zurück.
        Traversiert die Kinder des Knotens mit Tiefensuche.
        Die Kinder werden dabei entsprechend der alphabetischen Reihenfolge besucht.
     */
    StringBuilder string = new StringBuilder();
    public String toString(){
        //if node == \0 it is the root thus + gets appended at the beginning
        if (node == null) {
            string.append('+');
        }


        for (int i = 0; i < alphabet.length; i++) {
            string.append('(');
            string.append(getChild(alphabet[i]).toString());
            if (node.isWord) {
                string.append("[").append(node.getPoints()).append("]");
            }
            string.append(')');
        }
        return string.toString();
    }

    /*
        Setzt den im Knoten gespeicherten Punktewert.
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /*
        Liest den im Knoten gespeicherten Punktewert aus
     */
    public Integer getPoints() {
        return this.points;
    }

    public void setWord()
    {
        this.isWord = true;
    }

    private Node[] getChildren(){

        List<Node> children = new LinkedList<>();

        for(int i = 0; i < alphabet.length; i++) {
            if (node.getChild(alphabet[i]) != null) {
                children.add(node.getChild(alphabet[i]));
            }
        }
        return (Node[]) children.toArray();
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
                System.out.println("Error!");
                return 0;
        }
    }
}
