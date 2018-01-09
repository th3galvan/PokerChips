package galvan.pokerchips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alvaro on 11/12/2017.
 */

public class PlayersListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);

        ListView players_list = (ListView)findViewById(R.id.players_list);
        String[] values = new String[]{"Menganito","Fulanito","Juanito","Estalactito","Teodoro","Pauek","Eustaquio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        players_list.setAdapter(adapter);


    }
}
