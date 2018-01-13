package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayersListActivity extends AppCompatActivity {

    private ArrayList<String> players_list;
    private ArrayAdapter<String> adapter;

    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private String name;
    private String name_player1="Paco";
    private String name_player2="Pepe";
    private String name_player3="Yo";
    private String name_player4="Tu";
    private String name_player5="MechaHitler";
    private String name_player6="Sivir";
    private String name_player7="Pra";
    private String name_player8="Bru";
    private String name_player9="TT";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);

        Bundle code_receive = getIntent().getExtras();
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name =code_receive.getString("name");

        Button btn_finish = (Button) findViewById(R.id.btn_comenzar);

        ListView list = (ListView) findViewById(R.id.list_players);


        String[] values = {name, name_player1, name_player2, name_player3, name_player4,
                name_player5, name_player6, name_player7, name_player8, name_player9};

        players_list = new ArrayList<>();

        for (int i=0; i<number_players;i++){
        players_list.add(values[i]);}

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players_list);

        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View item, int pos, long id) {



                return false;
            }
        });

       btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_game = new Intent(PlayersListActivity.this, GameActivity.class);
                intent_game.putExtra("name",name);
                intent_game.putExtra("playersnumber",number_players);
                intent_game.putExtra("initial_chips",initial_chips);
                intent_game.putExtra("bigblind",big);
                intent_game.putExtra("frecuency",time_big_up);
                intent_game.putExtra("change",change_value_big);
                startActivity(intent_game);
            }
        });


    }}
