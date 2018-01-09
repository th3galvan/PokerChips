package galvan.pokerchips;

import android.support.v7.app.AppCompatActivity;

public class PlayerItems extends AppCompatActivity {
    protected long id;
    private  String name;
    private int chips;
    private boolean dealer;
    private boolean big;
    private boolean small;
    private boolean in;

    private  int bet;
    private  boolean turn;
    private  boolean allin;
    private  boolean call;


    public PlayerItems(int i,
                       String name,         int chips,      boolean dealer,
                       boolean small,         boolean big,  boolean in,
                       int bet,             boolean turn,   boolean allin, boolean call) {

        this.name = name;   this.chips = chips;     this.dealer = dealer;
        this.big = big;     this.small = small;     this.in = in;
        this.bet = bet;     this.turn = turn;       this.allin= allin;
        this.call = call;
    }

    public PlayerItems(long id, String name, int chips, boolean dealer, boolean big, boolean small, boolean in) {
        this.id = id;
        this.name = name;
        this.chips = chips;
        this.dealer = dealer;
        this.big = big;
        this.small = small;
        this.in = in;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public boolean isDealer()
    {
        return dealer;
    }
    public boolean isTurn()
    {
        return turn;
    }
    public boolean isAllin()
    {
        return allin;
    }

    public void setChecked(boolean dealer, boolean big, boolean small, boolean in)
    {
        this.dealer = dealer;
        this.big = big;
        this.small = small;
        this.in = in;
    }

    public boolean getDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public boolean isBig()
    {
        return big;
    }

    public boolean getBig() {
        return big;
    }

    public int getBet(){return bet;}


    public void setBig(boolean big) {
        this.big = big;
    }

    public boolean isSmall()
    {
        return small;
    }

    public boolean getSmall() {
        return small;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public boolean isIn()
    {
        return in;
    }



    public void setIn(boolean in) {
        this.in = in;
    }
    public void setBet(int bet) {
        this.bet = bet;
    }
    public void setAllin(boolean allin){this.allin = allin;}
    public void setTurn(boolean turn){this.turn = turn;}
    public void setCall(boolean call){this.call = call;}
    public boolean isCall(){return call;}
}
