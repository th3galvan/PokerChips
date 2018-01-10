package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PlayersListActivity extends AppCompatActivity {


    private String values[]={"Menganito","Fulanito","Juanito","Es-actito","Teodoro","Pauek","Eustaquio"};
    private ArrayList<String> players_list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playerlist);

        Button btn_finish = (Button) findViewById(R.id.btn_comenzar);

        ListView list = (ListView) findViewById(R.id.list_players);

        players_list = new ArrayList<>();

    for (int i=0;i<values.length;i++){
        players_list.add(values[i]);}

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players_list);

        list.setAdapter(adapter);

       btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_game = new Intent(PlayersListActivity.this, GameActivity.class);
                startActivity(intent_game);
            }
        });


    }}
