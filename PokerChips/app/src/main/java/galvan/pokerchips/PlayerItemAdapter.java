package galvan.pokerchips;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Alvaro on 13/12/2017.
 */


public class PlayerItemAdapter extends ArrayAdapter<PlayerItems> {

    public PlayerItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            result = inflater.inflate(R.layout.activity_playeritems, null);
        }
        TextView state = (TextView) result.findViewById(R.id.txt_state);
        TextView turn = (TextView) result.findViewById(R.id.txt_isturn);
        TextView name = (TextView) result.findViewById(R.id.txt_name);
        TextView chips = (TextView) result.findViewById(R.id.txt_chips);
        TextView allin = result.findViewById(R.id.txt_allin);
        CheckBox out = (CheckBox) result.findViewById(R.id.player_out);

        PlayerItems item = getItem(position);
        name.setText(item.getName());
        chips.setText(Integer.toString(item.getChips()));
        out.setChecked(item.isOut());
        if(item.isBig()==true){state.setText("B");}
        if(item.isSmall()==true){state.setText("S");}
        if(item.isDealer()==true){state.setText("D");}
        if(item.isBig()==false & item.isDealer()==false & item.isSmall()==false){state.setText("x");}
        if(item.isTurn()==true){turn.setText("Turn");}
                            else{turn.setText("");}

        if(item.isAllin()== true){allin.setText("ALL IN");}
                              else{allin.setText("");}
        return result;
    }
}