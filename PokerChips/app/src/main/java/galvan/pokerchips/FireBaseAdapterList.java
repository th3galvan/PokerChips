package galvan.pokerchips;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import galvan.pokerchips.PlayerItems;

/**
 * Created by Xavi on 16/01/2018.
 */

public class FireBaseAdapterList extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playeritems);

        DatabaseReference ListRef = FirebaseDatabase.getInstance().getReference().child("PlayerItem");

        FirebaseListAdapter<PlayerItems> adapter = new FirebaseListAdapter<PlayerItems>(this, PlayerItems.class, R.layout.activity_playeritems, ListRef) {
            @Override
            protected void populateView(View result, PlayerItems model, int position) {

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

            }
        };


    }
}
