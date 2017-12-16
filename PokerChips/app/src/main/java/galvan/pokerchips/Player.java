package galvan.pokerchips;

/**
 * Created by Alvaro on 11/12/2017.
 *
 * Esta sin uso, la empece pero decidi usar PlayerItems mejor
 */

public class Player {
    private  String name;
    private int chips;
    private boolean dealer;
    private boolean big;
    private boolean small;
    private boolean out;

    public Player (){
        name = "";
        chips = 0;
        dealer = false;
        big = false;
        small = false;
        out = false;

    }

    public Player (String n, int c, boolean d, boolean b, boolean s, boolean o){
        name = n;
        chips = c;
        dealer = d;
        big = b;
        small = s;
        out = o;

    }


}
