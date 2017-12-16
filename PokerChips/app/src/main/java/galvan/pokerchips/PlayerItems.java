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

    public PlayerItems(int i, String name, int chips, boolean dealer, boolean big, boolean small, boolean out) {
        this.name = "";
        this.chips = 0;
        this.dealer = false;
        this.big = false;
        this.small = false;
        this.out = true;
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


}
