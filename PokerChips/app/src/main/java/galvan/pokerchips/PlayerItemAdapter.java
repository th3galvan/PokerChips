package galvan.pokerchips;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import galvan.pokerchips.Datos.PlayerItems;

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
        TextView call = result.findViewById(R.id.txt_call);
        TextView bet = (TextView) result.findViewById(R.id.txt_bet_list);
        CheckBox out = (CheckBox) result.findViewById(R.id.player_out);
        RelativeLayout layout = (RelativeLayout) result.findViewById(R.id.layout_player);

        PlayerItems item = getItem(position);
        name.setText(item.getName());
        chips.setText(Integer.toString(item.getChips()));
        out.setChecked(item.isIn());
        if(item.isIn()){}
        else {layout.setBackgroundColor(Color.GRAY);}
        bet.setText(String.format("Bet: %d",item.getBet()));
        if(item.isBig()){state.setText("B");}
        if(item.isSmall()){state.setText("S");}
        if(item.isDealer()){state.setText("D");}
        if(item.isBig()==false & item.isDealer()==false & item.isSmall()==false){state.setText("x");}
        if(item.isTurn()){turn.setText("Turn");layout.setBackgroundColor(Color.GREEN);}
        else{turn.setText("");}

        if(item.isAllin()){allin.setText("ALL IN");}
        else{allin.setText("");}
        if(item.isCall()){call.setText("Call");}
        else{call.setText("");}
        return result;
    }
}