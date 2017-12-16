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

/*public class PlayerItemAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<PlayerItems> players;

    public PlayerItemAdapter(Activity activity, ArrayList<PlayerItems> players) {
        this.activity = activity;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int pos) {
        return players.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return players.get(pos).getId();
    }

    @Override
    public View getView(int pos, View contentView, ViewGroup parent) {
        View vi = contentView;

        if (contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            vi = inflater.inflate(R.layout.activity_playeritems, null);
        }

        PlayerItems item = players.get(pos);

        TextView name = (TextView) vi.findViewById(R.id.txt_name);
        name.setText(item.getName());


        TextView chips = (TextView) vi.findViewById(R.id.txt_chips);
        //chips.setText(item.getChips());



        CheckBox dealer = (CheckBox) vi.findViewById(R.id.player_dealer);
        dealer.setChecked(item.getDealer());

        CheckBox big = (CheckBox) vi.findViewById(R.id.player_big);
        big.setChecked(item.getBig());

        CheckBox small = (CheckBox) vi.findViewById(R.id.player_small);
        small.setChecked(item.getSmall());

        CheckBox out = (CheckBox) vi.findViewById(R.id.player_out);
        out.setChecked(item.getOut());


        return vi;

    }



}*/

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

        TextView name = (TextView) result.findViewById(R.id.txt_name);
        TextView chips = (TextView) result.findViewById(R.id.txt_chips);

        CheckBox dealer = (CheckBox) result.findViewById(R.id.player_dealer);
        CheckBox big = (CheckBox) result.findViewById(R.id.player_big);
        CheckBox small = (CheckBox) result.findViewById(R.id.player_small);
        CheckBox out = (CheckBox) result.findViewById(R.id.player_out);




        PlayerItems item = getItem(position);
        name.setText(item.getName());
        //chips.setText(item.getChips());

        dealer.setChecked(item.isDealer());
        big.setChecked(item.isBig());
        small.setChecked(item.isSmall());
        out.setChecked(item.isOut());
        return result;
    }
}