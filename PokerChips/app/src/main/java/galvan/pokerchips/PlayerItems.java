package galvan.pokerchips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayerItems extends AppCompatActivity {
    protected long id;
    private  String name;
    private int chips;
    private boolean dealer;
    private boolean big;
    private boolean small;
    private boolean out;

    private  int bet;
    private  boolean turn;
    private  boolean allin;


    public PlayerItems(int i,
                       String name,         int chips,      boolean dealer,
                       boolean small,         boolean big,  boolean out,
                       int bet,             boolean turn,   boolean allin   ) {

        this.name = name;   this.chips = chips;     this.dealer = dealer;
        this.big = big;     this.small = small;     this.out = out;
        this.bet = bet;     this.turn = turn;       this.allin= allin;
    }

    public PlayerItems(long id, String name, int chips, boolean dealer, boolean big, boolean small, boolean out) {
        this.id = id;
        this.name = name;
        this.chips = chips;
        this.dealer = dealer;
        this.big = big;
        this.small = small;
        this.out = out;
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

    public void setChecked(boolean dealer, boolean big, boolean small, boolean out)
    {
        this.dealer = dealer;
        this.big = big;
        this.small = small;
        this.out = out;
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

    public boolean isOut()
    {
        return out;
    }

    public boolean getOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
    public void setBet(int bet) {
        this.bet = bet;
    }
    public void setAllin(boolean allin){this.allin=allin;}
    public void setTurn(boolean turn){this.turn=turn;}
}
