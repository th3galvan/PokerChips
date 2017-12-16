package galvan.pokerchips;

/**
<<<<<<< HEAD
 * Created by Alvaro on 11/12/2017.
 *
 * Esta sin uso, la empece pero decidi usar PlayerItems mejor
 */

public class PlayerGalvan {
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


=======
 * Created by Galvan on 15/12/2017.
 */

public class Player {
    private String name;
    private  int chips;
    private  int bet;

    private  boolean big;
    private  boolean small;
    private  boolean dealer;

    private  boolean out;
    private  boolean turn;
    private  boolean allin;
    public Player(){}

    public Player(String name,
                  int chips,
                  int bet,
                  boolean big,
                  boolean small,
                  boolean dealer,
                  boolean out,
                  boolean turn,
                  boolean allin;){
        this.name = name;
        this.bet = bet;
        this.big = big;
        this.small = small;
        this.dealer = dealer;
        this.out = out;
        this.turn = turn;
        this.allin = allin;




    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public int getBet() {
        return bet;
    }

    public boolean isBig() {
        return big;
    }

    public boolean isSmall() {
        return small;
    }

    public boolean isDealer() {
        return dealer;
    }

    public boolean isOut() {
        return out;
    }

    public boolean isTurn() {
        return turn;
    }

    public boolean isAllin() {
        return allin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setAllin(boolean allin) {
        this.allin = allin;
    }
>>>>>>> introduccion parametros firebase + clase Player
}
