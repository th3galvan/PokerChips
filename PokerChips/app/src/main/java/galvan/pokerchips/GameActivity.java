package galvan.pokerchips;
//salu2
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import galvan.pokerchips.Datos.FirebaseReferences;
import galvan.pokerchips.Datos.PlayerItems;

import static android.graphics.Color.rgb;


public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    private int pos=0;
    private int bet2=0;
    private int ownChips=0;
    private int current_individual_bet=0;
    private int current_total_bet=0;
    private int total_bet=0;
    private int winner=0;
    private int big=50;
    private int loot =0;
    private int playersin=0;
    private int nState=0;
    private int Player_big_blind=0;
    private int Player_small_blind=0;
    //todo se podria rellenar en funcion del numero de jugadores
    private int winner_pos[] ={7,7,7,7,7,7,7,7,7,7};
    private int winner_check=0;
    private int eq_bet=0;
    private int winner2=0;
    private int playerscall=0;
    private int count_winner_pos=0;
    private int all_in_value=0;
    private int players_allin=0;
    private int restart_bet=0;
    private int cont_playersin=0;
    private int cont_winner_out=1;
    private int count_loser_pos=0;
    private int playernumber=0;
    private int time=0;
    private int initial_chips=0;
    private int time_big_up=0;
    private int change_value_big=0;
    private int min=0;
    private int number_players=0;
    private int playersout[] ={7,7,7,7};
    private int cont_playersout=0;
    private int number_playersout=0;
    private int playersalive=0;
    private int dealerpos=0;


    private String string_big="0";
    private String string_bet="0";
    private String name_host;

    private boolean all_in;
    private boolean checkout;
    private boolean winner_finish=false;
    private boolean im_allin=false;
    private boolean first_allin=true;

    private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    private TextView txt_bet;
    private TextView txt_current_total_bet;
    private TextView txt_total_bet;
    private TextView txt_big;
    private TextView txt_time_number;
    private TextView txt_turn;

    private ArrayList<PlayerItems> players;
    //private PlayerItemAdapter adapter; lo sustituimos por firebase adapter
    private FirebaseListAdapter<PlayerItems> adapter;
    private ListView playerlist;
/*
    //En el manifest
    <uses-permission android:name="android.permission.VIBRATE"/>

    //donde tenga que zumbar (vibracion simple)
    Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(3000);*/


   /* public PlayerItems(int i,
                       String name,         int chips,      boolean dealer,
                       boolean small,         boolean big,  boolean in,
                       int bet,             boolean turn,   boolean allin,
                       boolean call,        boolean win,    boolean annihilated)*/

    private PlayerItems Player0= new PlayerItems(1,
            "1", 0,
            true,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player1= new PlayerItems(1,
            "2", 0,
            false,      true,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player2= new PlayerItems(1,
            "3", 0,
            false,      false,   true,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player3 = new PlayerItems(1,
            "4", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player4= new PlayerItems(1,
            "5", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player5= new PlayerItems(1,
            "6", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);
    private PlayerItems Player6= new PlayerItems(1,
            "7", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems Player7 = new PlayerItems(1,
            "8", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);
    private PlayerItems Player8= new PlayerItems(1,
            "9", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);
    private PlayerItems Player9= new PlayerItems(1,
            "10", 0,
            false,      false,   false,
            true,
            0,          false,   false,
            false,      false,   false, false);

    private PlayerItems PlayerDataBase[]={Player0,Player1,Player2,Player3,Player4,Player5,Player6,Player7,Player8,Player9};

    private DatabaseReference FirebaseListRef;
    private String Scurrent_total_bet;
    //Declaramos las variables que se guardaran en la database de firebase

    private FirebaseDatabase database;

    private DatabaseReference posref;
    private DatabaseReference bet2ref;
    private DatabaseReference ownChipsref;
    private DatabaseReference current_individual_bet_ref;
    private DatabaseReference current_total_ref;
    private DatabaseReference total_bet_ref;
    private DatabaseReference winnerref;
    private DatabaseReference lootref;
    private DatabaseReference playersinref;
    private DatabaseReference nStateref;
    private DatabaseReference player_big_blind_ref;
    private DatabaseReference Player_small_blind_ref;
    private DatabaseReference eq_bet_ref;
    private DatabaseReference winner_check_ref;
    private DatabaseReference winner2ref;
    private DatabaseReference playerscallref;
    private DatabaseReference count_winner_posref;
    private DatabaseReference all_in_value_ref;
    private DatabaseReference players_allin_ref;
    private DatabaseReference restart_bet_ref;
    private DatabaseReference cont_playersin_ref;
    private DatabaseReference cont_winner_out_ref;
    private DatabaseReference count_loser_pos_ref;
    private DatabaseReference playersnumberref;
    private DatabaseReference timeref;
    private DatabaseReference cont_playersoutref;
    private DatabaseReference number_playersoutref;
    private DatabaseReference playersaliveref;
    private DatabaseReference dealerposref;
    private DatabaseReference string_bigref;
    private DatabaseReference string_betref;
    private DatabaseReference PlayerItemRef;
    private DatabaseReference PlayerItemRef1;
    private DatabaseReference PlayerItemRef2;
    private DatabaseReference PlayerItemRef3;
    private DatabaseReference PlayerItemRef4;
    private DatabaseReference PlayerItemRef5;
    private DatabaseReference PlayerItemRef6;
    private DatabaseReference PlayerItemRef7;
    private DatabaseReference PlayerItemRef8;
    private DatabaseReference PlayerItemRef9;
    private String ListOfPlayerReferences[]={FirebaseReferences.PLAYER_ITEM_REFERENCE,
            FirebaseReferences.PLAYER_ITEM_REFERENCE1,
            FirebaseReferences.PLAYER_ITEM_REFERENCE2,
            FirebaseReferences.PLAYER_ITEM_REFERENCE3,
            FirebaseReferences.PLAYER_ITEM_REFERENCE4,
            FirebaseReferences.PLAYER_ITEM_REFERENCE5,
            FirebaseReferences.PLAYER_ITEM_REFERENCE6,
            FirebaseReferences.PLAYER_ITEM_REFERENCE7,
            FirebaseReferences.PLAYER_ITEM_REFERENCE8,
            FirebaseReferences.PLAYER_ITEM_REFERENCE9,};

    private String game_id;
    private DatabaseReference game_reference;


    //para hacer una lista de firebase


    //no se usa nunca
    // private DatabaseReference adapterref;


    //salvar datos de la aplicacion si esta en segundo plano y hace onDestroy
    /*private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    private TextView txt_bet;
    private TextView txt_current_total_bet;
    private TextView txt_total_bet;
    private TextView txt_big;
    private TextView txt_time_number;*/

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //TODO: añadir veriables que haya que guardar su estado
        outState.putInt("big", big);
        outState.putString("current total bet", Scurrent_total_bet);
        outState.putInt("time big up", time_big_up);
        outState.putInt("number players", number_players);
        outState.putInt("pos", pos);
        String txt_ownchips = txt_ownChips.getText().toString();
        outState.putString("ownChips", (txt_ownchips));
        String txt_c_i_b = txt_current_individual_bet.getText().toString();
        outState.putString("current individual bet", (txt_c_i_b));
        String txt_c_t_b = txt_current_total_bet.getText().toString();
        outState.putString("current total bet", (txt_c_t_b));
        outState.putInt("player big blind", Player_big_blind);
        outState.putInt("payer small blind", Player_small_blind);


    }
    //Cargar datos salvados despues de un onDestroy todo:(no tiene pinta de funcionar arlvarinho)
    @Override
    protected void onRestoreInstanceState(Bundle recoverState){
        super.onRestoreInstanceState(recoverState);
        big = recoverState.getInt("big");
        Scurrent_total_bet = recoverState.getString("current total bet");
        time_big_up = recoverState.getInt("time big up");
        number_players = recoverState.getInt("number players");
        pos = recoverState.getInt("pos");
        String txt_ownchips = recoverState.getString("ownChips");
        txt_ownChips.setText(txt_ownchips);
        String txt_c_i_b = recoverState.getString("curent individual bet");
        txt_current_individual_bet.setText(txt_c_i_b);
        String txt_c_t_b = recoverState.getString("current total bet");
        txt_current_total_bet.setText(txt_c_t_b);
        Player_big_blind = recoverState.getInt("player big blind");
        Player_small_blind = recoverState.getInt("player small blind");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle code_receive = getIntent().getExtras();
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name_host =code_receive.getString("name");
        game_id=code_receive.getString("game_id");


        Log.i("Xavi",String.format("%d",number_players));

        for (int i=0; i<10-number_players; i++){
            Log.i("Xavi",String.format("%d",i));
            PlayerDataBase[9-i].setAnnihilated(true);
        }
/*
        for (int l = 0; l< playersalive; l++){
            if (Integer.toString(PlayerDataBase[l].getChips()).equals("0")){
                PlayerDataBase[l].setAnnihilated(true);
            }
        }*/

        for (int i = 0; i< playersalive; i++){
            if(PlayerDataBase[i].isAnnihilated()){playersout[cont_playersout]=i; cont_playersout++;
                PlayerDataBase[i].setIn(false);
                PlayerDataBase[i].setTurn(false);
                PlayerDataBase[i].setAllin(false);
                PlayerDataBase[i].setCall(false);
                PlayerDataBase[i].setWin(false);
            }
        }
        PlayersAnnihilateds();

        PlayerDataBase[0].setName(name_host);
        for (int i=0; i<number_players;i++){
        PlayerDataBase[i].setChips(initial_chips);}


        // TODO: 17/12/2017  hay que hacer algo para asignar un mobil con un jugador
        //Todo: hay que hacer que playernuber se obtenga de PlayerDataBase
        playernumber=0;

        //parte de añadir lista de jugadores y adapter


        playerlist = (ListView)findViewById(R.id.players_list);

        players = new ArrayList<>();

//hola generamos la lista
        for (int i=0;i<number_players;i++){
        players.add(PlayerDataBase[i]);
        PlayerDataBase[i].setGenerated(true);
            Log.i("Check",String.format("i %d // isGenerated %b",i, PlayerDataBase[i].isGenerated()));}


    /*    adapter = new PlayerItemAdapter(
                this,
                R.layout.activity_playeritems,
                players
        );
        playerlist.setAdapter(adapter);*/

//Enlace con la base de datos de firebase
        database = FirebaseDatabase.getInstance();

        //ints


        //FirebaseListAdapter
        FirebaseListRef = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE);

    adapter = new FirebaseListAdapter<PlayerItems>(
            this,
            PlayerItems.class,
            R.layout.activity_playeritems,
            FirebaseListRef) {
        @Override
        protected void populateView(View v, PlayerItems player, int position) {

            TextView state = (TextView) v.findViewById(R.id.txt_state);
            TextView turn = (TextView) v.findViewById(R.id.txt_isturn);
            TextView name = (TextView) v.findViewById(R.id.txt_name);
            TextView chips = (TextView) v.findViewById(R.id.txt_chips);
            TextView allin = v.findViewById(R.id.txt_allin);
            TextView call = v.findViewById(R.id.txt_call);
            TextView bet = (TextView) v.findViewById(R.id.txt_bet_list);
            CheckBox out = (CheckBox) v.findViewById(R.id.player_out);
            RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.layout_player);

            PlayerItems item = getItem(position);
            name.setText(item.getName());

            chips.setText(Integer.toString(item.getChips()));
            out.setChecked(item.isIn());

            bet.setText(String.format("Bet: %d",item.getBet()));
            if(item.isBig()){state.setText("B");}
            if(item.isSmall()){state.setText("S");}
            if(item.isDealer()){state.setText("D");}
            if(!item.isBig() & !item.isDealer() & !item.isSmall()){state.setText("x");}
            if(item.isTurn()){turn.setText(R.string.adapter_txt_turn);layout.setBackgroundColor(rgb(0,170,0));}
            else{
                turn.setText("");
                layout.setBackgroundColor(rgb(255,255,255));
            }
            if(item.isIn()& !item.isTurn()){
                layout.setBackgroundColor(rgb(255,255,255));
            }
            else if(!item.isIn()) {
                layout.setBackgroundColor(rgb(194,194,194));
            }
            if(item.isAllin()){allin.setText(R.string.adapter_txt_allin);}
            else{allin.setText("");}
            if(item.isCall()){call.setText(R.string.adapter_txt_call);}
            else{call.setText("");}
        }
    } ;
//FirebaseListRef.push().setValue(playerlist);
        //FirebaseListRef.setValue(playerlist);
        playerlist.setAdapter(adapter);


        final int chips[] ={5,10,25,50,100,250,500};

        //declaramos botones y views de las fichas
        final Button btn_plus_chip = (Button) findViewById(R.id.btn_plus_chip);
        final Button btn_minus_chip = (Button) findViewById(R.id.btn_minus_chip);
        final TextView txt_chip =(TextView) findViewById(R.id.txt_chip);

        //declaramos botones y views de la apuesta
        final Button btn_plus_bet = (Button)findViewById(R.id.btn_plus_bet);
        final Button btn_minus_bet = (Button) findViewById(R.id.btn_minus_bet);
        txt_bet =(TextView) findViewById(R.id.txt_bet);

        //declaramos el boton de All-in
        final Button btn_all_in=(Button)findViewById(R.id.btn_all_in);

        //TOTAL BET
        total_bet=0;
        txt_total_bet = (TextView) findViewById(R.id.txt_total_bet_number);


        //checkWinner();
        //declaramos el boton de igualar y una variable apuesta individual actual (arriba) y aqui la inicializamos
        final Button btn_equalize=(Button)findViewById(R.id.btn_equalize);
        txt_current_individual_bet = (TextView) findViewById(R.id.txt_current_individual_bet_number);
        current_individual_bet=big;

        txt_current_individual_bet.setText(Integer.toString(current_individual_bet));

        //declaramos e inicializamos el contador de tus fichas
        txt_ownChips = (TextView) findViewById(R.id.txt_own_chips_number);

        ownChips=PlayerDataBase[playernumber].getChips();
        txt_ownChips.setText(Integer.toString(ownChips));


        //inicializamos los views de la calculadora
        pos=0;
        String textChip = Integer.toString(chips[pos]);
        txt_chip.setText(textChip);
        bet2=0;
        String textBet = Integer.toString(bet2);
        txt_bet.setText(textBet);

        //declaramos el view de la apuesta actual total y el boton de apostar y pasar
        txt_current_total_bet= (TextView) findViewById(R.id.txt_current_total_bet_number);
        final Button btn_check= (Button) findViewById(R.id.btn_check);
        final Button btn_bet= (Button) findViewById(R.id.btn_bet);
        current_total_bet= 0;
        final String Scurrent_total_bet= Integer.toString(current_total_bet);
        txt_current_total_bet.setText(Scurrent_total_bet);

        //declaramos apuesta total de la mesa
        txt_total_bet = (TextView)findViewById(R.id.txt_total_bet_number);
        total_bet=0;
        String Stotal_bet = Integer.toString(total_bet);
        txt_total_bet.setText(Stotal_bet);

        //escribimos big blind
        txt_big = (TextView) findViewById(R.id.txt_big_value);
        txt_big.setText(Integer.toString(big));


        //Cogemos txt turn
        txt_turn = (TextView)findViewById(R.id.txt_turn);

        //utilizamos el metodo turnState para saber en que parte de la partida nos encontramos
        // blind bet, preflop, flop, turn, river
        turnState();

        database = FirebaseDatabase.getInstance();
        game_reference = database.getReference(FirebaseReferences.GAME_REFERENCE);

        posref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.POS_REFERENCE);
        posref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    pos = dataSnapshot.getValue(Integer.class);
                    refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        bet2ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.BET2_REFERENCE);
        bet2ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    bet2 = dataSnapshot.getValue(Integer.class);
                    refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ownChipsref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.OWNCHIPS_REFERENCE);
        ownChipsref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    ownChips = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        current_individual_bet_ref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.CURRENT_INDIVIDUAL_BET_REFERENCE);
        current_individual_bet_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_individual_bet = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_total_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.CURRENT_TOTAL_BET_REFERENCE);
        current_total_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    current_total_bet = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        total_bet_ref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.TOTAL_BET_REFERENCE);
        total_bet_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_bet = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        winnerref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.WINNER_REFERENCE);
        winnerref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                winner = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lootref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LOOT_REFERENCE);
        lootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    loot = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        playersinref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERSIN_REFERENCE);
        playersinref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playersin = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        nStateref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NSTATE_REFERENCE);
        nStateref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    nState = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        player_big_blind_ref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYER_BIG_BLIND_REFERENCE);
        player_big_blind_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    Player_big_blind = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Player_small_blind_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYER_SMALL_BLIND_REFERENCE);
        Player_small_blind_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    Player_small_blind = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*todo como subir un array?
        winner_pos_ref = database.getReference(FirebaseReferences.WINNER_POS_REFERENCE);
        winner_pos_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    winner_pos = dataSnapshot.getValue(Array.In);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        winner_check_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.WINNER_CHECK_REFERENCE);
        winner_check_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                winner_check = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        eq_bet_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.EQ_BET_REFERENCE);
        eq_bet_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eq_bet = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        winner2ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.WINNER2_REFERENCE);
        winner2ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    winner2 = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        playerscallref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERSCALL_REFERENCE);
        playerscallref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerscall = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        count_winner_posref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.COUNT_WINNER_POS_REFERENCE);
        count_winner_posref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    count_winner_pos = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        all_in_value_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.ALL_IN_VALUE_REFERENCE);
        all_in_value_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    all_in_value = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        players_allin_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_ALLIN_REFERENCE);
        players_allin_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    players_allin = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        restart_bet_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.RESTART_BET_REFERENCE);
        restart_bet_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    restart_bet = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cont_playersin_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.CONT_PLAYERSIN_REFERENCE);
        cont_playersin_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cont_playersin = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        cont_winner_out_ref =  database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.CONT_WINNER_OUT_REFERENCE);
        cont_winner_out_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    cont_winner_out = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        count_loser_pos_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.COUNT_LOSER_POS_REFERENCE);
        count_loser_pos_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    count_loser_pos = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        playersnumberref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERNUMBER_REFERENCE);
        playersnumberref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    playernumber = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        timeref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.TIME_REFERENCE);
        timeref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    time = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*todo subir un array?
        playersoutref = database.getReference(FirebaseReferences.PLAYERSOUT_REFERENCE);
        playersoutref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    playersout
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        cont_playersoutref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.CONT_PLAYERSOUT_REFERENCE);
        cont_playersoutref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    cont_playersout = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        number_playersoutref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NUMBER_PLAYERSOUT_REFERENCE);
        number_playersoutref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number_playersout = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        playersaliveref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERSALIVE_REFERENCE);
        playersaliveref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playersalive = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dealerposref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.DEALERPOS_REFERENCE);
        dealerposref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    dealerpos = dataSnapshot.getValue(Integer.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //strings
        string_bigref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.STRING_BIG_REFERENCE);
        string_bigref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    string_big = dataSnapshot.getValue(String.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        string_betref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.STRING_BET_REFERENCE);
        string_betref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                string_bet = dataSnapshot.getValue(String.class);
                refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //hacmeos push de los datos dentro de la id de nuestra partida

        game_reference.child(game_id).child(FirebaseReferences.POS_REFERENCE).setValue(pos);
        game_reference.child(game_id).child(FirebaseReferences.BET2_REFERENCE).setValue(bet2);
        game_reference.child(game_id).child(FirebaseReferences.OWNCHIPS_REFERENCE).setValue(ownChips);
        game_reference.child(game_id).child(FirebaseReferences.CURRENT_INDIVIDUAL_BET_REFERENCE).setValue(current_individual_bet);
        game_reference.child(game_id).child(FirebaseReferences.CURRENT_TOTAL_BET_REFERENCE).setValue(current_total_bet);
        game_reference.child(game_id).child(FirebaseReferences.TOTAL_BET_REFERENCE).setValue(total_bet);
        game_reference.child(game_id).child(FirebaseReferences.WINNER_REFERENCE).setValue(winner);
        game_reference.child(game_id).child(FirebaseReferences.LOOT_REFERENCE).setValue(loot);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSIN_REFERENCE).setValue(playersin);
        game_reference.child(game_id).child(FirebaseReferences.NSTATE_REFERENCE).setValue(nState);
        game_reference.child(game_id).child(FirebaseReferences.PLAYER_BIG_BLIND_REFERENCE).setValue(Player_big_blind);
        game_reference.child(game_id).child(FirebaseReferences.PLAYER_SMALL_BLIND_REFERENCE).setValue(Player_small_blind);
        game_reference.child(game_id).child(FirebaseReferences.WINNER_CHECK_REFERENCE).setValue(winner_check);
        game_reference.child(game_id).child(FirebaseReferences.EQ_BET_REFERENCE).setValue(eq_bet);
        game_reference.child(game_id).child(FirebaseReferences.WINNER2_REFERENCE).setValue(winner2);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSCALL_REFERENCE).setValue(playerscall);
        game_reference.child(game_id).child(FirebaseReferences.COUNT_WINNER_POS_REFERENCE).setValue(count_winner_pos);
        game_reference.child(game_id).child(FirebaseReferences.ALL_IN_VALUE_REFERENCE).setValue(all_in_value);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERS_ALLIN_REFERENCE).setValue(players_allin);
        game_reference.child(game_id).child(FirebaseReferences.RESTART_BET_REFERENCE).setValue(restart_bet);
        game_reference.child(game_id).child(FirebaseReferences.CONT_PLAYERSIN_REFERENCE).setValue(cont_playersin);
        game_reference.child(game_id).child(FirebaseReferences.CONT_WINNER_OUT_REFERENCE).setValue(cont_winner_out);
        game_reference.child(game_id).child(FirebaseReferences.COUNT_LOSER_POS_REFERENCE).setValue(count_loser_pos);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERNUMBER_REFERENCE).setValue(playernumber);
        game_reference.child(game_id).child(FirebaseReferences.TIME_REFERENCE).setValue(time);
        game_reference.child(game_id).child(FirebaseReferences.CONT_PLAYERSOUT_REFERENCE).setValue(cont_playersout);
        game_reference.child(game_id).child(FirebaseReferences.NUMBER_PLAYERSOUT_REFERENCE).setValue(number_playersout);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSALIVE_REFERENCE).setValue(playersalive);
        game_reference.child(game_id).child(FirebaseReferences.DEALERPOS_REFERENCE).setValue(dealerpos);

        game_reference.child(game_id).child(FirebaseReferences.STRING_BIG_REFERENCE).setValue(string_big);
        game_reference.child(game_id).child(FirebaseReferences.STRING_BET_REFERENCE).setValue(string_bet);


        //Players
        for(int i =0; i<number_players;i++){

            game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(ListOfPlayerReferences[i]).setValue(PlayerDataBase[i]);
        }


/*
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE1).setValue(PlayerDataBase[1]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE2).setValue(PlayerDataBase[2]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE3).setValue(PlayerDataBase[3]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE4).setValue(PlayerDataBase[4]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE5).setValue(PlayerDataBase[5]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE6).setValue(PlayerDataBase[6]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE7).setValue(PlayerDataBase[7]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE8).setValue(PlayerDataBase[8]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE9).setValue(PlayerDataBase[9]);
*/
        /*
        PlayerItemRef1 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef1.child(game_id).push().setValue(PlayerDataBase[1]);

        PlayerItemRef2 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef2.child(game_id).push().setValue(PlayerDataBase[2]);

        PlayerItemRef3 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef3.child(game_id).push().setValue(PlayerDataBase[3]);

        PlayerItemRef4 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef4.child(game_id).push().setValue(PlayerDataBase[4]);

        PlayerItemRef5 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef5.child(game_id).push().setValue(PlayerDataBase[5]);

        PlayerItemRef6 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef6.child(game_id).push().setValue(PlayerDataBase[6]);

        PlayerItemRef7 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef7.child(game_id).push().setValue(PlayerDataBase[7]);

        PlayerItemRef8 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef8.child(game_id).push().setValue(PlayerDataBase[8]);

        PlayerItemRef9 = database.getReference(FirebaseReferences.LIST_REFERENCE);
        PlayerItemRef9.child(game_id).push().setValue(PlayerDataBase[9]);*/


        PlayerItemRef = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE);
        PlayerItemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PlayerDataBase[0] = dataSnapshot.getValue(PlayerItems.class);
                Log.i("Lista",String.format("%d",PlayerDataBase[0].getBet()));
                refresh();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        PlayerItemRef1 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE1);
        PlayerItemRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PlayerDataBase[1] = dataSnapshot.getValue(PlayerItems.class);
                refresh();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(playernumber >1) {
            PlayerItemRef2 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE2);
            PlayerItemRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[2] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>2){
            PlayerItemRef3 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE3);
            PlayerItemRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[3] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>3){
            PlayerItemRef4 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE4);
            PlayerItemRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[4] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
                if(playernumber>4){
            PlayerItemRef5 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE5);
            PlayerItemRef5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[5] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>5)
            PlayerItemRef6 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE6);
            PlayerItemRef6.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[6] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>6){
            PlayerItemRef7 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE7);
            PlayerItemRef7.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[7] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>7){
            PlayerItemRef8 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE8);
            PlayerItemRef8.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[8] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if(playernumber>8){
            PlayerItemRef9 = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE9);
            PlayerItemRef9.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    PlayerDataBase[9] = dataSnapshot.getValue(PlayerItems.class);
                    refresh();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }}}}}}}


        //BTN+ Chips
        btn_plus_chip.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 if(pos<chips.length-1){
                                                     pos++;
                                                     String textChipBtn = Integer.toString(chips[pos]);
                                                     txt_chip.setText(textChipBtn);}

                                             }
                                         }
        );

        //BTN- Chips
        btn_minus_chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos>0){
                    pos--;
                    String textChipBtn = Integer.toString(chips[pos]);
                    txt_chip.setText(textChipBtn);
                }
            }
        });

        //BTN+ Bet
        btn_plus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(all_in){String textBetBtn = Integer.toString(all_in_value-PlayerDataBase[playernumber].getBet());
                    txt_bet.setText(textBetBtn);}
                else if(bet2+chips[pos]<=ownChips)
                {bet2=bet2+chips[pos];
                    String textBetBtn = Integer.toString(bet2);
                    txt_bet.setText(textBetBtn);}
                else{}}
        });

        //BTN- Bet
        btn_minus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (all_in){String textBetBtn = Integer.toString(all_in_value-PlayerDataBase[playernumber].getBet());
                    txt_bet.setText(textBetBtn);}
                else if(bet2<chips[pos]){}
                else{bet2=bet2-chips[pos];
                    String textBetBtn = Integer.toString(bet2);
                    txt_bet.setText(textBetBtn);}
            }
        });

        //BTN ALL IN

        btn_all_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage(getString(R.string.selectWinner));       //añadido recurso para traducir texto
                    builder.create().show();}
                else if (all_in){String textBetBtn = Integer.toString(all_in_value-PlayerDataBase[playernumber].getBet());
                    txt_bet.setText(textBetBtn);}
                else{
                    bet2=ownChips;
                    String textAllBtn = Integer.toString(bet2);
                    txt_bet.setText(textAllBtn);
                    //PlayerDataBase[playernumber].setIn(true);
                }}
        });


        //BTN EQUALIZE
        btn_equalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage(getString(R.string.selectWinner));       //añadido recurso para traducir texto
                    builder.create().show();}
                else if (all_in){String textBetBtn = Integer.toString(all_in_value-PlayerDataBase[playernumber].getBet());
                    txt_bet.setText(textBetBtn);}
                else {
                    if(current_individual_bet>ownChips){
                        bet2=ownChips;
                        String textCurrentIBtn= Integer.toString(bet2);
                        txt_bet.setText(textCurrentIBtn);
                        //Todo: aqui se entraria en all in
                    }
                    else{
                        //Cogemos valor de apuesta anterior para que al darle a igualar te ponga las fichas que te quedan para igualar la apuesta según las que tu ya habias apostado
                        eq_bet = PlayerDataBase[playernumber].getBet();
                        bet2=current_individual_bet-eq_bet;
                        String textCurrentIBtn= Integer.toString(bet2);
                        txt_bet.setText(textCurrentIBtn);
                    }
                }}
        });


        //BTN CHECK

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage(getString(R.string.selectWinner));       //añadido recurso para traducir texto
                    builder.create().show();}
                else{
                    Log.i("Galvan","CHECK PULSADO");
                    toCheck();
                }}
        });


        //BTN BET

        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bet2 = Integer.parseInt(txt_bet.getText().toString());

                if (PlayerDataBase[playernumber].getChips()==bet2){
                    im_allin=true;
                }

                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage(getString(R.string.selectWinner));       //añadido recurso para traducir texto
                    builder.create().show();}
                else{
                    if(bet2 == 0){
                        Message0bet();
                    }
                    if(all_in){bet2=all_in_value;
                        toBet(bet2);}
                    else {
                        toBet(bet2);}
                }}
        });

        playerlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View item, int pos, long id) {

                if(nState==8 & !winner_finish){
                    if(PlayerDataBase[pos].isIn()){
                        PlayersIn();
                        Log.i("WINNER",String.format("plasyersin%d // cont_winner_out%d", playersin,cont_winner_out));
                        if (cont_winner_out==playersin){
                            PlayerDataBase[pos].setWin(true);
                            winner2=10;
                            ChooseWinner();
                        }
                        else{
                            //cuando vote se suma uno
                            winner2++;
                            //se guarda la posición que ha votado en un array
                            PlayerDataBase[pos].setWin(true);
                            cont_winner_out++;
                            ChooseWinner();}
                    }}

                return true;

            }

        });

/*  Esto fue cuando intentamos hacer el Choose Winner general
        playerlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> playerlist, View item, int pos, long id) {
                PlayersAnnihilateds();

                if(nState==8 & !winner_finish){
                    if(PlayerDataBase[pos].isIn()){
                        PlayersIn();
                        Log.i("WINNER",String.format("plasyersin%d // cont_winner_out%d", playersin,cont_winner_out));
                        if (cont_winner_out==playersin){
                            PlayerDataBase[pos].setWin(true);
                            check_winner_finish=true;
                            pulsado=true;
                            check_players_pressed=playersalive;
                            ChooseWinner();

                        }
                        else{
                            //cuando vote se suma uno-
                            check_players_pressed++;
                            //se guarda la posición que ha votado en un array
                            PlayerDataBase[pos].setWin(true);
                            cont_winner_out++;
                            ChooseWinner();}

                    }}

                return true;

            }

        });*/




        //declaramos el view de la cuenta atras y la iniciamos con un contador
        txt_time_number = (TextView) findViewById(R.id.txt_time_number);

        time =  60000;
        min = time_big_up-1;


        new CountDownTimer(time, 1000){

            public void onTick(long millisUntilFinished){
                if (millisUntilFinished/1000<10){
                    txt_time_number.setText(String.valueOf( min + ":0" + millisUntilFinished/1000));

                }
                else{
                txt_time_number.setText(String.valueOf( min + ":" + millisUntilFinished/1000));}
            }
            public void onFinish(){

                min--;
                if (min==-1){
                    min=time_big_up-1;
                    big=change_value_big+big;
                    Toast big_notify = Toast.makeText(getApplicationContext(),
                                                  R.string.BigChangeMessage,
                                                               Toast.LENGTH_LONG);

                    big_notify.show();
                    txt_big.setText(Integer.toString(big));
                }



                start();
            }
        }.start();

    }


private void Message0bet() {

        //Mensaje que nos indica que estamos dandole a apostar sin seleccionar ficha
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.Error);
        builder.setMessage(getString(R.string.betFewChips));    //añadido recurso para traducir texto
        builder.create().show();
    }

    private void toCheck() {
        //DATABASE
        //Seguimos en partida si no hay apuesta o si mi apuesta es igual a la minima
        if(current_individual_bet==0 || PlayerDataBase[playernumber].getBet()==current_individual_bet){
            PlayerDataBase[playernumber].setCall(true);
            PlayerDataBase[playernumber].setIn(true);
            Log.i("Galvan","CHECK");
            //utilizamos el metodo turnState para saber en que parte de la partida nos encontramos
            // blind bet, preflop, flop, turn, river
            turnALLIN();
            nextTurn();turnState();
        }
        else{
            Log.i("Galvan","FOLD");
            PlayerDataBase[playernumber].setIn(false);
            PlayerDataBase[playernumber].setCall(false);
            turnALLIN();
            nextTurn();turnState();
        }
    }

    //este metodo se ejecuta cuando pulsamos pasar o apostar, si pulsamos bet el valor de la variable
    //bet se restará a tus fichas y se actualizará el valor de current_individual_bet si este es menor
    //a la nueva apuesta

    private void toBet(final int toBetBet) {
        //CALCULAMOS CON CUANTAS FICHAS NOS QUEDAMOS(en teoria es imposible tener un valor negativo en newownChips)
        final int newownChips=ownChips+PlayerDataBase[playernumber].getBet()-all_in_value;

        //SI VAMOS ALL IN
        if (newownChips<0){AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.enoughtChips));       //añadido recurso para traducir texto
            builder.create().show();}

        else if(newownChips==0 || im_allin){
            Log.i("Galvan","(newownChips<=0)");
            //CREAMOS DIALOGO
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirmation);
            builder.setCancelable(false);
            builder.setMessage(getString(R.string.betAllChips));        //añadido recurso para traducir texto
            //PULSAMOS YES
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    all_in=true;
                    if (first_allin){
                        first_allin=false;
                        all_in_value=PlayerDataBase[playernumber].getBet()+PlayerDataBase[playernumber].getChips();
                    }
                    if (PlayerDataBase[playernumber].getBet()==newownChips){
                        ownChips=0;
                        //REFRESCAR TXT_OWNCHIPS EN PANTALLA
                        String SownChips= Integer.toString(ownChips);
                        txt_ownChips.setText(SownChips);
                    }
                    else{
                        ownChips=newownChips;
                        //REFRESCAR TXT_OWNCHIPS EN PANTALLA
                        String SownChips= Integer.toString(ownChips);
                        txt_ownChips.setText(SownChips);}
                    //sumo bet tambien
                    Log.i("Value",String.format("%d",all_in_value));

                    //ACTUALIZACION CURRENT TOTAL BET
                    current_total_bet=current_total_bet+all_in_value-PlayerDataBase[playernumber].getBet();                               //actualiza el valor de current total bet
                    Scurrent_total_bet = Integer.toString(current_total_bet);
                    txt_current_total_bet.setText(Scurrent_total_bet);
                    //ACTUALIZACION BET
                    txt_bet.setText(Integer.toString(0));
                    //DATABASE
                    PlayerDataBase[playernumber].setBet(all_in_value);
                    //CAMBIO APUESTA ACTUAL INDIVIDUAL
                    higherBet(all_in_value);
                    PlayerDataBase[playernumber].setChips(0);
                    PlayerDataBase[playernumber].setAllin(true);
                    PlayerDataBase[playernumber].setCall(true);
                    PlayerDataBase[playernumber].setIn(true);

                    Log.i("Galvan","apuesta confirmada");

                    //REFRESH ownChips
                    refresh();
                    //CAMBIO DE TURNO
                    nextTurn();
                    Log.i("Galvan","siguiente");
                    Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                    //todo he comentado esto porque abajo tambien se llama a esto y creo que es innecesario
                    //turnALLIN(); turnState();
                }

            });
            //PULSAMOS CANCEL
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.create().show();
        }
        //SI HACEMOS UNA APUESTA NORMAL
        else{
            bet2 = PlayerDataBase[playernumber].getBet();
            Log.i("Galvan","(newownChips<=0)");
            if((toBetBet+bet2<current_individual_bet || toBetBet==0)){}
            else  {
                all_in=false;
                //CREAMOS DIALOGO
                AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                builder2.setCancelable(false);
                if(bet2==0){
                    builder2.setMessage(String.format(getString(R.string.betXChips),Integer.toString(toBetBet)));}
                else {                                                                              //TODO: textos traducible, recursos betXChips, betXChipsMore y currenBetXChips
                    builder2.setMessage(String.format(getString(R.string.betXChipsMore) +
                            getString(R.string.currentBetXChips),Integer.toString(toBetBet),Integer.toString(bet2)));}
                //PULSAMOS YES
                builder2.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("Galvan","apuesta confirmada");
                        //EL VALOS newownChips ES VALIDO
                        ownChips=newownChips;
                        //REFRESCAMOS ownChips EN PANTALLA
                        String SownChips= Integer.toString(ownChips);
                        txt_ownChips.setText(SownChips);
                        //CAMBIO APUESTA ACTUAL INDIVIDUAL
                        Log.i("Galvan","higher bet");
                        higherBet(toBetBet);
                        Log.i("Galvan","current_total_bet=current_total_bet+toBetBet");
                        current_total_bet=current_total_bet+toBetBet;                                   //actualiza el valor de current total bet
                        String Scurrent_total_bet= Integer.toString(current_total_bet);
                        txt_current_total_bet.setText(Scurrent_total_bet);

                        //DATABASE
                        PlayerDataBase[playernumber].setChips(PlayerDataBase[playernumber].getChips()-toBetBet);
                        PlayerDataBase[playernumber].setBet(toBetBet+bet2);
                        PlayerDataBase[playernumber].setCall(true);
                        PlayerDataBase[playernumber].setIn(true);

                        //ACTUALIZACION BET
                        bet2=0;
                        txt_bet.setText(Integer.toString(bet2));

                        //CAMBIO DE TURNO
                        Log.i("Galvan","Inicio Cambio de Turno");
                        nextTurn();
                        refresh();


                        Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                    }
                });
                builder2.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bet2=toBetBet;
                    }
                });
                builder2.create().show();

            }}

        String SownChips= Integer.toString(ownChips);
        txt_ownChips.setText(SownChips);
        turnALLIN(); turnState();
    }



    private void turnALLIN(){

        players_allin=0;
        for (int i = 0; i< playersalive; i++){
            if(PlayerDataBase[i].isAllin()){players_allin++;}
        }
        PlayersIn();
        if(players_allin==playersin){nState=8;
            total_bet=current_total_bet;
            txt_total_bet.setText(Integer.toString(total_bet));
            current_total_bet=0;
            txt_current_total_bet.setText(Integer.toString(current_total_bet));
        }

    }

    private void turnState() {

        switch (nState) {

            //BIG BLIND SMALL BLIND
            case 0:

                txt_turn.setText(getString(R.string.preflop));

                PlayersAnnihilateds();

                for (int i=0; i<playersalive;i++) {
                    PlayerDataBase[i].setIn(true);
                }

                for(int i = 0; i< playersalive; i++ ){

                    //busca quien es el big y le resta el valor Big a sus fichas y los suma al bote
                    if(PlayerDataBase[i].isBig()){
                        Player_big_blind = i;
                        PlayerDataBase[i].setChips(PlayerDataBase[i].getChips()-big);
                        //Creamos una memoria del num de fichas que ha apostado este jugador
                        Log.i("Big",String.format("Estoy aqui 10 %d",i));
                        PlayerDataBase[i].setBet(big);
                        current_total_bet= current_total_bet+big;
                        current_individual_bet=big;
                        //en el preflop el turno es para el siguiente del Big Blind
                        playernumber=i+1;
                        //inicializo turno
                        Log.i("WHERE",String.format("%d",playernumber));
                        if(playernumber>= playersalive){
                            playernumber=0;
                            PlayerDataBase[playernumber].setTurn(true);
                            refresh();}
                        else{PlayerDataBase[playernumber].setTurn(true);
                            refresh();}

                    }
                    //busca al small blind y le resta la apuesta y la suma al bote
                    if(PlayerDataBase[i].isSmall()){
                        Player_small_blind = i;
                        PlayerDataBase[i].setChips(PlayerDataBase[i].getChips()-(big/2));
                        //Creamos una memoria del num de fichas que ha apostado este jugador
                        PlayerDataBase[i].setBet(big/2);
                        current_total_bet= current_total_bet+(big/2);
                        refresh();
                    }

                }
                nState=1;
                playerlist.setAdapter(adapter);
                break;

            //PREFLOP
            case 1:
                txt_turn.setText(getString(R.string.preflop));  //añadido recurso

                PlayersIn();
                playerscall=0;

                for(int p = 0; p< playersalive; p++){
                    if(PlayerDataBase[p].isCall()){playerscall++;}}

                Log.i("XaviVilaseca",String.format("%d // %d", playersin, playerscall));
                if (playersin== playerscall){nState=2;}
                else {break;}

                //RESTART
            case 2:

                Restart();
                nState=3;

                //FLOP
            case 3:
                txt_turn.setText(getString(R.string.flop)); //añadido recurso
                PlayersIn();
                playerscall=0;
                for(int l = 0; l< playersalive; l++){
                    if(PlayerDataBase[l].isCall()){playerscall++;}}

                Log.i("Players",String.format("%d // %d", playersin, playerscall));
                Log.i("XaviVilaseca","3");
                if (playersin == playerscall){nState=4;}
                else {break;}

                //RESTART
            case 4:

                Restart();
                AdjustTurn();
                nState=5;

                //TURN
            case 5:
                txt_turn.setText(getString(R.string.turn)); //añadido recurso
                PlayersIn();
                playerscall=0;

                for(int p = 0; p< playersalive; p++){
                    if(PlayerDataBase[p].isCall()){playerscall++;}}

                //Log.i("XaviVilaseca",String.format("%d // %d", playersin, playerscall));
                Log.i("XaviVilaseca","5");
                if (playersin== playerscall){nState=6;}
                else {break;}

                //RESTART
            case 6:

                Restart();
                AdjustTurn();
                nState=7;

                //RIVER
            case 7:
                txt_turn.setText(getString(R.string.river));    //añadido recurso
                PlayersIn();
                playerscall=0;
                for(int p = 0; p< playersalive; p++){
                    if(PlayerDataBase[p].isCall()){playerscall++;}}

                //Log.i("XaviVilaseca",String.format("%d // %d", playersin, playerscall));
                Log.i("XaviVilaseca","7");
                if (playersin== playerscall){nState=8;}
                else {break;}

                //WINNER-
            case 8:
                txt_turn.setText(getString(R.string.winner));   //añadido recurso
                AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                builder2.setMessage(getString(R.string.whoIsWinner));       //añadido recurso para traducir el texto
                builder2.setCancelable(false);
                builder2.setNeutralButton(R.string.confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //buscamos cunatos jugadores quedan
                        PlayersIn();
                        //los jugadores que queden seran los que tengan que votar

                        Log.i("WINNERFINISH",String.format("%b",winner_finish));
                        if (!winner_finish){
                            ChooseWinner();}
                    }
                });
                builder2.create().show();

        }}

    private void ChooseWinner() {

        PlayersIn();
        cont_playersin=0;
        switch (winner2){

            case 0:
                if(PlayerDataBase[0].isIn() & !(playersin==cont_playersin)){

                    for (int i=0; i<playersalive;i++){
                        PlayerDataBase[i].setWin(false);
                    }

                    AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                    builder2.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[0].getName()));
                    builder2.create().show();
                    cont_playersin++;

                    setDataBaseChanges();
                    break;}
                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();}

            case 1:
                if(PlayerDataBase[1].isIn() & !(playersin==cont_playersin)){

                    AlertDialog.Builder builder3= new AlertDialog.Builder(this);
                    builder3.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[1].getName()));
                    builder3.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    break;}

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();}

            case 2:
                if(PlayerDataBase[2].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[2].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    setDataBaseChanges();

                    break;}

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();}

            case 3:

                if(PlayerDataBase[3].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[3].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    playerlist.setAdapter(adapter);
                    setDataBaseChanges();
                    }

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    playerlist.setAdapter(adapter);
                    setDataBaseChanges();}
                break;
            case 4:
                if(PlayerDataBase[4].isIn() & !(playersin==cont_playersin)){

                    AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                    builder2.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[4].getName()));
                    builder2.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    }
                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();
                }
                break;

            case 5:
                if(PlayerDataBase[5].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[5].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    break;
                    }

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();
                }
            case 6:
                if(PlayerDataBase[6].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[6].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    break;
                    }

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();
                }

            case 7:

                if(PlayerDataBase[7].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[7].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    break;
                    }

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();
                }


            case 8:
                if(PlayerDataBase[8].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[8].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    setDataBaseChanges();
                    break;
                    }

                else {
                    PlayerDataBase[winner2].setIn(false);
                    winner2++;
                    setDataBaseChanges();
                }
            case 9:

                if(PlayerDataBase[9].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder5= new AlertDialog.Builder(this);
                    builder5.setMessage(String.format(getString(R.string.chooseWinner),PlayerDataBase[9].getName()));
                    builder5.create().show();
                    setDataBaseChanges();
                }
                else{
                    PlayerDataBase[winner2].setIn(false);
                    setDataBaseChanges();                }

                break;

            case 10:
                winner_finish=true;
                cont_playersin=0;
                Log.i("WINNER","ESTOY?");
                PlayersIn();
                Log.i("WINNER",String.format("cont_winner_out %d // playersin %d",cont_winner_out,playersin));
                if(winner_finish & cont_winner_out==playersin){Log.i("WINS","FINISH");
                    checkWinner();

                }
                setDataBaseChanges();


        }

/* todo esto se intento hacer para hacer est emetodo general
        PlayersIn();

        for(int i = check_players_pressed;i<playersin;i++){
            if(PlayerDataBase[i].isIn() & !check_winner_finish){
                AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                builder2.setMessage(String.format("Player %s choose the winner",PlayerDataBase[i].getName()));
                builder2.create().show();
                i=playersin ;
                }
                else{check_players_pressed++;}
            }

            if (pulsado){

        winner_finish=true;
        Log.i("WINNER","ESTOY?");
        PlayersIn();
        Log.i("WINNER",String.format("cont_winner_out %d // playersin %d",cont_winner_out,playersin));
        if(winner_finish & cont_winner_out==playersin){Log.i("WINS","FINISH");
            checkWinner();}}*/

    }

    private void PlayersIn() {
        playersin=0;
        for (int i = 0; i< playersalive; i++ ){
            if(PlayerDataBase[i].isIn()){playersin++;}
        }
    }

    private void checkWinner() {


        PlayersIn();
        Log.i("WINS","CHECKWINNER");
        //condiciones para que el juega haya acabado
        if(playersin>1 & (nState == 8)){
            //ahora vamos a ver si todos han votado lo mismo para ello cogemos la primera posicion y vamos comparando
            count_winner_pos=0;
            count_loser_pos=0;
            for (int i = 0; i<winner_pos.length;i++){
                winner_pos[i]=7;}

            for (int i = 0; i< playersalive; i++){

                if(PlayerDataBase[i].isWin()){winner_pos[count_winner_pos]=i; count_winner_pos++;}
            }
            winner_check=winner_pos[0];
            for (int i=0;i<winner_pos.length;i++){
                if (winner_pos[i]==7){winner_pos[i]=winner_check;}
            }

            for (int i =0;i<count_winner_pos;i++){
                Log.i("WINS",String.format("winner_pos %d // winner_check %d", winner_pos[i],winner_check));
                if(winner_pos[i]==winner_check){}
                //si es diferente en algun momento ponemos el valor a -1
                else {winner_check=-1;
                    winner_finish=false;}
            }
            //si ha sido diferente volvemos vamos al step 8 que es volver a hacer el mismo ciclo
            if(winner_check==-1){

                AlertDialog.Builder builder5= new AlertDialog.Builder(this);
                builder5.setMessage(getString(R.string.differentsWinners));     //añadido recurso para traducir el texto
                builder5.setCancelable(false);
                builder5.setNeutralButton(R.string.confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("WINS","DIFERENTE");
                        nState=8; winner2=0; cont_winner_out=1; winner_check=0;
                        for (int j=0;j<winner_pos.length;j++){
                            winner_pos[j]=7;
                        }
                        for (int o = 0; o< playersalive; o++){
                            PlayerDataBase[o].setWin(false); }
                        //turnALLIN();
                        turnState(); }
                });
                builder5.create().show();

            }

            else if (winner_check!=-1){

                for (int i=0; i<playersalive; i++){
                    if (i==winner_check){}
                    else {
                        PlayerDataBase[i].setWin(false);
                    }

                }
                if (all_in){
                    for (int i=0; i<PlayerDataBase.length;i++){
                        if (PlayerDataBase[i].isIn() & !PlayerDataBase[i].isWin()){
                            PlayerDataBase[i].setAnnihilated(true);
                        }
                    }
                }
                //Log.i("WINS","IGUAL");

                loot= PlayerDataBase[winner_check].getChips()+total_bet+current_total_bet;
                //Log.i("LOOT",String.format("loot %d // chips %d",loot,PlayerDataBase[winner_check].getChips()));
                PlayerDataBase[winner_check].setChips(loot);
                AlertDialog.Builder builder3= new AlertDialog.Builder(this);
                builder3.setMessage(String.format(getString(R.string.PlayerWonChips),PlayerDataBase[winner_check].getName(),Integer.toString(total_bet)));
                builder3.create().show();
                //Se pone to*do a 0
                current_total_bet=0;
                current_individual_bet=0;
                total_bet=0;
                winner2=0;
                nState=0;
                winner_finish=false;
                cont_winner_out=1;
                for (int j=0;j<winner_pos.length;j++){
                    winner_pos[j]=7;
                }
                for(int i = 0; i< playersalive; i++){
                    //PlayerDataBase[i].setIn(true);
                    PlayerDataBase[i].setBet(0);
                    PlayerDataBase[i].setCall(false);
                    PlayerDataBase[i].setTurn(false);
                    PlayerDataBase[i].setAllin(false);
                    //valores a 0 por allin
                    all_in=false;
                    all_in_value=0;
                    restart_bet=0;
                    string_bet = Integer.toString(restart_bet);
                    txt_bet.setText(string_bet);}
                RotateBlind(playersin);}
        }}


    private void OnePlayerIn() {
        PlayersIn();
        Log.i("WIN", String.format("%d",playersin));
        if(playersin==1){
            winner_finish=true;
            //buscamos el indice de jugador del ganador
            for(int i = 0; i< playersalive; i++){
                if(PlayerDataBase[i].isIn()){
                    winner= i;
                }
            }
            Log.i("WIN",String.format("%d",winner));
            //sumamos la cantidad de current total bet a las fichas del ganador
            loot= PlayerDataBase[winner].getChips()+total_bet+current_total_bet;
            PlayerDataBase[winner].setChips(loot);
            AlertDialog.Builder builder3= new AlertDialog.Builder(this);
            Log.i("WIN","HE GANADO");
            builder3.setMessage(String.format(getString(R.string.PlayerWonChips),PlayerDataBase[winner].getName(),Integer.toString(total_bet+current_total_bet)));
            builder3.create().show();
            current_total_bet=0;
            current_individual_bet=0;
            total_bet=0;
            winner2=0;
            for(int i = 0; i< playersalive; i++){
                if (!PlayerDataBase[i].isAnnihilated()){
               // PlayerDataBase[i].setIn(true);
                PlayerDataBase[i].setBet(0);
                PlayerDataBase[i].setCall(false);
                PlayerDataBase[i].setTurn(false);}
                //valores a 0 por allin
                all_in=false;
                all_in_value=0;
                restart_bet=0;
                nState=0;
                string_bet = Integer.toString(restart_bet);
                txt_bet.setText(string_bet);}
            RotateBlind(playersin);}
    }

    private void Restart() {

        txt_big.setText(Integer.toString(big));
        //Restauramos todos los valores
        total_bet=current_total_bet+total_bet;
        String Stotal_bet = Integer.toString(total_bet);
        txt_total_bet.setText(Stotal_bet);

        //Reiniciamos to*do
        current_individual_bet=0;
        String Scurrent_individual_bet = Integer.toString(current_individual_bet);
        txt_current_individual_bet.setText(Scurrent_individual_bet);

        current_total_bet=0;
        String Scurrent_total_bet = Integer.toString(current_total_bet);
        txt_current_total_bet.setText(Scurrent_total_bet);

        //Reiniciamos apuestas y call

        for(int i = 0; i< playersalive; i++){
            int cero=0;
            PlayerDataBase[i].setBet(cero);
            PlayerDataBase[i].setCall(false);
        }
    }

    private void higherBet(int toBetBet) {

        if(all_in){
            for(int i = 0; i< playersalive; i++){

                PlayerDataBase[i].setCall(false);

                playerlist.setAdapter(adapter);}

            current_individual_bet = PlayerDataBase[playernumber].getBet();
            String Scurrent_individual_bet = Integer.toString(current_individual_bet);
            txt_current_individual_bet.setText(Scurrent_individual_bet);
            Log.i("Galvan","(current_individual_bet = toBetBet;)");
            refresh();
        }


        else if (toBetBet+PlayerDataBase[playernumber].getBet()> current_individual_bet){
            for(int i = 0; i< playersalive; i++){

                PlayerDataBase[i].setCall(false);

                playerlist.setAdapter(adapter);
            }

            //se sustituye por la apuesta mas grande
            current_individual_bet = PlayerDataBase[playernumber].getBet()+toBetBet;
            String Scurrent_individual_bet = Integer.toString(current_individual_bet);
            txt_current_individual_bet.setText(Scurrent_individual_bet);
            Log.i("Galvan","(current_individual_bet = toBetBet;)");
            refresh();}

    }

    private void refresh() {
        //REFRESH ownChips
        Button btn_check = (Button)findViewById(R.id.btn_check);
        ownChips= PlayerDataBase[playernumber].getChips();
        txt_ownChips.setText(Integer.toString(ownChips));
        txt_current_individual_bet.setText(Integer.toString(current_individual_bet));
        txt_current_total_bet.setText(Integer.toString(current_total_bet));
        playerlist.setAdapter(adapter);
        if(current_individual_bet!=0 || PlayerDataBase[playernumber].getBet()==current_individual_bet){btn_check.setText(R.string.check);}
        else{btn_check.setText(R.string.fold);}

    }

    private void nextTurn() {
        //si hay allin chequeo si ya han apostado todos

        //CAMBIO DE TURNO
        //ACTUALIZACION BET
        PlayersAnnihilateds();
        txt_bet.setText(Integer.toString(0));

        Log.i("Galvan","CAMBIO DE TURNO");
        PlayerDataBase[playernumber].setTurn(false);

        if((playernumber== playersalive -1)){playernumber=0;}
        else{playernumber++;}

        while(!PlayerDataBase[playernumber].isIn() ){

            if((playernumber== playersalive -1)){playernumber=0;}
            else{playernumber++;}

        }
       /*for (int i=0;i<playersout.length;i++){
            if (playernumber==playersout[i]){playernumber++;}}*/

        //MIRAMOS SI PUEDE HABER GANADOR
        //PlayersIn();
        Log.i("WIN","YES");
        OnePlayerIn();
        Log.i("Xavi",String.format("playernumber %d // playeralive %d",playernumber,playersalive));
        PlayerDataBase[playernumber].setTurn(true);
        playerlist.setAdapter(adapter);
        refresh();


        //entramos los datos dentro de la id de la partida pero no hacemos push
        //ints


        setDataBaseChanges();


        /*
        posref.child(game_id).setValue(pos);
        bet2ref.child(game_id).setValue(bet2);
        ownChipsref.child(game_id).setValue(ownChips);
        current_individual_bet_ref.child(game_id).setValue(current_individual_bet);
        current_total_ref.child(game_id).setValue(current_total_bet);
        total_bet_ref.child(game_id).setValue(total_bet);
        winnerref.child(game_id).setValue(winner);
        lootref.child(game_id).setValue(loot);
        playersinref.child(game_id).setValue(playersin);
        nStateref.child(game_id).setValue(nState);
        player_big_blind_ref.child(game_id).setValue(Player_big_blind);
        Player_small_blind_ref.child(game_id).setValue(Player_small_blind);
        winner_check_ref.child(game_id).setValue(winner_check);
        eq_bet_ref.child(game_id).setValue(eq_bet);
        winner2ref.child(game_id).setValue(winner2);
        playerscallref.child(game_id).setValue(playerscall);
        count_winner_posref.child(game_id).setValue(count_winner_pos);
        all_in_value_ref.child(game_id).setValue(all_in_value);
        players_allin_ref.child(game_id).setValue(players_allin);
        restart_bet_ref.child(game_id).setValue(restart_bet);
        cont_playersin_ref.child(game_id).setValue(cont_playersin);
        cont_winner_out_ref.child(game_id).setValue(cont_winner_out);
        count_loser_pos_ref.child(game_id).setValue(count_loser_pos);
        playersnumberref.child(game_id).setValue(playernumber);
        timeref.child(game_id).setValue(time);
        cont_playersoutref.child(game_id).setValue(cont_playersout);
        number_playersoutref.child(game_id).setValue(number_playersout);
        playersaliveref.child(game_id).setValue(playersalive);
        dealerposref.child(game_id).setValue(dealerpos);

        //strings
        string_bigref.child(game_id).setValue(string_big);
        string_betref.child(game_id).setValue(string_bet);

        //playersitems
        PlayerItemRef.child(game_id).setValue(PlayerDataBase[0]);
        PlayerItemRef1.child(game_id).setValue(PlayerDataBase[1]);
        PlayerItemRef2.child(game_id).setValue(PlayerDataBase[2]);
        PlayerItemRef3.child(game_id).setValue(PlayerDataBase[3]);
        PlayerItemRef4.child(game_id).setValue(PlayerDataBase[4]);
        PlayerItemRef5.child(game_id).setValue(PlayerDataBase[5]);
        PlayerItemRef6.child(game_id).setValue(PlayerDataBase[6]);
        PlayerItemRef7.child(game_id).setValue(PlayerDataBase[7]);
        PlayerItemRef8.child(game_id).setValue(PlayerDataBase[8]);
        PlayerItemRef9.child(game_id).setValue(PlayerDataBase[9]);*/


    }

    private void setDataBaseChanges() {
        game_reference.child(game_id).child(FirebaseReferences.POS_REFERENCE).setValue(pos);
        game_reference.child(game_id).child(FirebaseReferences.BET2_REFERENCE).setValue(bet2);
        game_reference.child(game_id).child(FirebaseReferences.OWNCHIPS_REFERENCE).setValue(ownChips);
        game_reference.child(game_id).child(FirebaseReferences.CURRENT_INDIVIDUAL_BET_REFERENCE).setValue(current_individual_bet);
        game_reference.child(game_id).child(FirebaseReferences.CURRENT_TOTAL_BET_REFERENCE).setValue(current_total_bet);
        game_reference.child(game_id).child(FirebaseReferences.TOTAL_BET_REFERENCE).setValue(total_bet);
        game_reference.child(game_id).child(FirebaseReferences.WINNER_REFERENCE).setValue(winner);
        game_reference.child(game_id).child(FirebaseReferences.LOOT_REFERENCE).setValue(loot);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSIN_REFERENCE).setValue(playersin);
        game_reference.child(game_id).child(FirebaseReferences.NSTATE_REFERENCE).setValue(nState);
        game_reference.child(game_id).child(FirebaseReferences.PLAYER_BIG_BLIND_REFERENCE).setValue(Player_big_blind);
        game_reference.child(game_id).child(FirebaseReferences.PLAYER_SMALL_BLIND_REFERENCE).setValue(Player_small_blind);
        game_reference.child(game_id).child(FirebaseReferences.WINNER_CHECK_REFERENCE).setValue(winner_check);
        game_reference.child(game_id).child(FirebaseReferences.EQ_BET_REFERENCE).setValue(eq_bet);
        game_reference.child(game_id).child(FirebaseReferences.WINNER2_REFERENCE).setValue(winner2);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSCALL_REFERENCE).setValue(playerscall);
        game_reference.child(game_id).child(FirebaseReferences.COUNT_WINNER_POS_REFERENCE).setValue(count_winner_pos);
        game_reference.child(game_id).child(FirebaseReferences.ALL_IN_VALUE_REFERENCE).setValue(all_in_value);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERS_ALLIN_REFERENCE).setValue(players_allin);
        game_reference.child(game_id).child(FirebaseReferences.RESTART_BET_REFERENCE).setValue(restart_bet);
        game_reference.child(game_id).child(FirebaseReferences.CONT_PLAYERSIN_REFERENCE).setValue(cont_playersin);
        game_reference.child(game_id).child(FirebaseReferences.CONT_WINNER_OUT_REFERENCE).setValue(cont_winner_out);
        game_reference.child(game_id).child(FirebaseReferences.COUNT_LOSER_POS_REFERENCE).setValue(count_loser_pos);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERNUMBER_REFERENCE).setValue(playernumber);
        game_reference.child(game_id).child(FirebaseReferences.TIME_REFERENCE).setValue(time);
        game_reference.child(game_id).child(FirebaseReferences.CONT_PLAYERSOUT_REFERENCE).setValue(cont_playersout);
        game_reference.child(game_id).child(FirebaseReferences.NUMBER_PLAYERSOUT_REFERENCE).setValue(number_playersout);
        game_reference.child(game_id).child(FirebaseReferences.PLAYERSALIVE_REFERENCE).setValue(playersalive);
        game_reference.child(game_id).child(FirebaseReferences.DEALERPOS_REFERENCE).setValue(dealerpos);

        game_reference.child(game_id).child(FirebaseReferences.STRING_BIG_REFERENCE).setValue(string_big);
        game_reference.child(game_id).child(FirebaseReferences.STRING_BET_REFERENCE).setValue(string_bet);


        //Players

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE).setValue(PlayerDataBase[0]);

        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE1).setValue(PlayerDataBase[1]);
        if(number_players>1){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE2).setValue(PlayerDataBase[2]);
            if(number_players>2){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE3).setValue(PlayerDataBase[3]);
                if(number_players>3){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE4).setValue(PlayerDataBase[4]);
                    if(number_players>4){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE5).setValue(PlayerDataBase[5]);
                        if(number_players>5){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE6).setValue(PlayerDataBase[6]);
                            if(number_players>6){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE7).setValue(PlayerDataBase[7]);
                                if(number_players>7){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE8).setValue(PlayerDataBase[8]);
                                    if(number_players>8){
        game_reference.child(game_id).child(FirebaseReferences.LIST_REFERENCE).child(FirebaseReferences.PLAYER_ITEM_REFERENCE9).setValue(PlayerDataBase[9]);
    }}}}}}}}}


    private void AdjustTurn(){
        //Ajustamos el turno ya que cuando creaba los turnos se desplazaba una posicion hacia adelante
        PlayerDataBase[playernumber].setTurn(false);

        if (playernumber==0){playernumber= playersalive -1;}
        else{playernumber--;}

        while(!PlayerDataBase[playernumber].isIn()){

            if (playernumber==0){playernumber= playersalive -1;}
            else {playernumber--;}
        }

        PlayerDataBase[playernumber].setTurn(true);
        playerlist.setAdapter(adapter);

    }



    private void RotateBlind(int playersin) {

        //Miramos si algun jugador esta fuera de la partida

        //si alguno esta fuera eliminamos to*do lo que pueda tener on y cogemos posicion en cont_playersout
        for (int i = 0; i< PlayerDataBase.length; i++){
            if(PlayerDataBase[i].isAnnihilated()){
                PlayerDataBase[i].setIn(false);
                PlayerDataBase[i].setTurn(false);
                PlayerDataBase[i].setAllin(false);
                PlayerDataBase[i].setCall(false);
                PlayerDataBase[i].setWin(false);
                //PlayerDataBase[i].setBig(false);
                //PlayerDataBase[i].setSmall(false);
                //PlayerDataBase[i].setDealer(false);

            }
        }
        //contamos cuantos jugadores estan fuera
        PlayersAnnihilateds();
            Log.i("Turn",String.format("playersalive %d",playersalive));

            if (playersalive>3){
                //DESPLAZAMIENTO CIEGAS
                //si el ultimo de la lista no es ni big small ni dealer
                if( !PlayerDataBase[playersalive -1].isBig() &
                        !PlayerDataBase[playersalive -1].isDealer() &
                        !PlayerDataBase[playersalive -1].isSmall()) {
                    boolean aux= false;
                    for(int i = 0; i< playersalive; i++){

                        PlayerDataBase[i].setIn(true);
                        PlayerDataBase[i].setAllin(false);



                        //DEALER->nA
                        if(PlayerDataBase[i].isDealer()){
                            PlayerDataBase[i].setDealer(false);
                            Log.i("GALVAN","Dealer-na");
                        }
                        //SMALL->DEALER
                        if(PlayerDataBase[i].isSmall()){
                            PlayerDataBase[i].setDealer(true);
                            PlayerDataBase[i].setSmall(false);
                            Log.i("GALVAN","Small-Big");
                        }
                        //BIG->SMALL
                        if(PlayerDataBase[i].isBig()){
                            PlayerDataBase[i].setSmall(true);
                            PlayerDataBase[i].setBig(false);
                            Log.i("GALVAN","Big-Small");
                            aux=true;
                            Log.i("GALVAN","aux true");
                        }
                        //nA->BIG
                        if( aux==true&&
                                !PlayerDataBase[i].isBig() &
                                        !PlayerDataBase[i].isDealer() &
                                        !PlayerDataBase[i].isSmall()) {
                            Log.i("Big","Estoy aqui 10");
                            PlayerDataBase[i].setBig(true);
                            Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));
                            aux=false;

                        }
                        Log.i("GALVAN",String.format("i= %s",Integer.toString(i)));
                        playerlist.setAdapter(adapter);
                    }
                }
                //si el ultimo es big
                else if(PlayerDataBase[playersalive -1].isBig()) {
                    Log.i("Big","Estoy aqui 1");
                    PlayerDataBase[0].setBig(true);

                    for (int i = 1; i < playersalive; i++) {
                        PlayerDataBase[i].setIn(true);
                        PlayerDataBase[i].setAllin(false);


                        //DEALER->nA
                        if (PlayerDataBase[i].isDealer()) {
                            PlayerDataBase[i].setDealer(false);
                            Log.i("GALVAN", "Dealer-na");
                        }
                        //SMALL->DEALER
                        if (PlayerDataBase[i].isSmall()) {
                            PlayerDataBase[i].setDealer(true);
                            PlayerDataBase[i].setSmall(false);
                            Log.i("GALVAN", "Small-Big");
                        }
                        //BIG->SMALL
                        if (PlayerDataBase[i].isBig()) {
                            PlayerDataBase[i].setSmall(true);
                            PlayerDataBase[i].setBig(false);

                            playerlist.setAdapter(adapter);
                        }
                    }
                }
                //si el ultimo es small
                else if(PlayerDataBase[playersalive -1].isSmall()){
                    PlayerDataBase[0].setBig(false);
                    PlayerDataBase[0].setSmall(true);
                    Log.i("Big","Estoy aqui 2");
                    PlayerDataBase[1].setBig(true);

                    for(int i = 1; i< playersalive; i++){
                        PlayerDataBase[i].setIn(true);
                        PlayerDataBase[i].setAllin(false);



                        //DEALER->nA
                        if(PlayerDataBase[i].isDealer()){
                            PlayerDataBase[i].setDealer(false);
                            Log.i("GALVAN","Dealer-na");
                        }
                        //SMALL->DEALER
                        if(PlayerDataBase[i].isSmall()){
                            PlayerDataBase[i].setDealer(true);
                            PlayerDataBase[i].setSmall(false);
                            Log.i("GALVAN","Small-Big");
                        }

                        if( PlayerDataBase[i-1].isSmall()) {
                            Log.i("Big","Estoy aqui 3");
                            PlayerDataBase[i].setBig(true);
                            Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));


                        }
                        playerlist.setAdapter(adapter);
                    }
                }
                else if(PlayerDataBase[playersalive -1].isDealer()){
                    PlayerDataBase[0].setSmall(false);
                    PlayerDataBase[0].setDealer(true);
                    PlayerDataBase[1].setBig(false);
                    PlayerDataBase[1].setSmall(true);
                    Log.i("Big","Estoy aqui 4");

                    PlayerDataBase[2].setBig(true);
                    //DEALER->nA
                    for(int i = 3; i< playersalive; i++){
                        if(PlayerDataBase[i].isDealer()){
                            PlayerDataBase[i].setDealer(false);
                            Log.i("GALVAN","Dealer-na");
                        }
                    }}


                    //RESTAURAR OUT
                    for(int i = 0; i< playersalive; i++){
                        checkout = PlayerDataBase[i].isIn();
                        if(checkout==true){playersin++;}
                        txt_current_total_bet.setText(Integer.toString(playersin));
                    }

                    //Rotar turno

                    refresh();
                            }

            else if(playersalive<=3){

                 //y ahora reorganizamos los estados
                 //si temenos mas de 3 jugadores vivos
                Log.i("Aniquilador",String.format("PlayersAlive =%d",playersalive));


            if (playersalive == 3) {
                for (int i = 0; i < PlayerDataBase.length - 1; i++) {
                    PlayerDataBase[i].setDealer(false);
                    PlayerDataBase[i].setSmall(false);
                    PlayerDataBase[i].setBig(false);

                }
                //si la posición del big no se sale de la parte de la lista de los vivos:
                Log.i("Reorganizador", String.format("dealerpos+2=%d , playersalive=%d", dealerpos + 2, playersalive));

                if (dealerpos + 2 == playersalive - 1) {//Bigpos<playersalive-1
                    Log.i("Reorganizador", "(el big no se sale)");
                    Log.i("Big","Estoy aqui 5");
                    PlayerDataBase[dealerpos].setBig(true);
                    PlayerDataBase[dealerpos + 1].setDealer(true);
                    PlayerDataBase[dealerpos + 2].setSmall(true);

                }
                //si se sale
                //si solo se sale el big
                else if (dealerpos + 2 == playersalive) {
                    Log.i("Reorganizador", "(el big se sale)");
                    Log.i("Big","Estoy aqui 6");
                    PlayerDataBase[dealerpos].setBig(true);
                    PlayerDataBase[dealerpos + 1].setDealer(true);
                    PlayerDataBase[0].setSmall(true);
                    Log.i("CheckBig3",String.format("PlayerDabatabig 0 %b // " +
                                    "PlayerDabatabig 1 %b // PlayerDabatabig 2 %b // PlayerDabatabig 3 %b",
                            PlayerDataBase[0].isBig(),PlayerDataBase[1].isBig(),PlayerDataBase[2].isBig(),
                            PlayerDataBase[3].isBig()));
                }

                //si se sale el small y el big
                else if (dealerpos == playersalive - 1) {
                    Log.i("Reorganizador", "(small y blind  se salen)");
                    Log.i("Big","Estoy aqui 7");
                    PlayerDataBase[dealerpos].setBig(true);
                    PlayerDataBase[0].setDealer(true);
                    PlayerDataBase[1].setSmall(true);
                }

            }

            //para 2 jugadores vivos no ponemos dealer
            else if (playersalive == 2) {

                for (int i = 0; i < PlayerDataBase.length - 1; i++) {
                    PlayerDataBase[i].setDealer(false);
                    PlayerDataBase[i].setSmall(false);
                    PlayerDataBase[i].setBig(false);

                }

                if (dealerpos + 2 == playersalive - 1) {//Bigpos<playersalive-1
                    Log.i("Reorganizador", "(el big no se sale)");
                    Log.i("Big","Estoy aqui 8");
                    PlayerDataBase[dealerpos].setBig(true);
                    PlayerDataBase[dealerpos+1].setSmall(true);
                }
                //si se sale
                //si solo se sale el big
                else {
                    Log.i("Reorganizador", "(small y blind  se salen)");
                    PlayerDataBase[0].setSmall(true);
                    Log.i("Big","Estoy aqui 9");
                    PlayerDataBase[1].setBig(true);

            }
//cuando queda un jugador
            if (playersalive == 1) {
                PlayerDataBase[0].setChips(1000000);
            }
        }
        }

        for (int i=0; i<winner_pos.length;i++){
            winner_pos[i]=7;
        }
        winner2=0;
        nState=0;
        winner_finish=false;
        first_allin=true;
        im_allin=false;
        total_bet=0;
        txt_total_bet.setText(Integer.toString(total_bet));
        //Log.i("CheckBig",String.format("PlayerDabatabig 0 %b // " +
        //        "PlayerDabatabig 1 %b // PlayerDabatabig 2 %b // PlayerDabatabig 3 %b",
        //        PlayerDataBase[0].isBig(),PlayerDataBase[1].isBig(),PlayerDataBase[2].isBig(),
        //        PlayerDataBase[3].isBig()));
        turnState();
    }

    private void PlayersAnnihilateds() {

//antes de aniquilar obtenemos la posicion del dealer con un for

        number_playersout=0;

        for (int l = 0; l< PlayerDataBase.length; l++){
            if (PlayerDataBase[l].isAnnihilated()){
                number_playersout++;
                Log.i("Xavi",String.format("Number_playersout%d",number_playersout));
            }
        }

        //todo no elimina bien a los jugadores
        for (int i = 0; i < playersalive; i++){

            if(PlayerDataBase[i].isAnnihilated() & PlayerDataBase[i].isGenerated()){
                players.remove(i);
            }
        }


        for (int i = 0; i< playersalive; i++){
            Log.i("Check",String.format("i %d isAnnihilated %b // %b isGenerated",i, PlayerDataBase[i].isAnnihilated(),PlayerDataBase[i].isGenerated()));
            if(PlayerDataBase[i].isAnnihilated() & PlayerDataBase[i].isGenerated()){
                //al aniquilar a un player el reorganizador se activará a continuación

                dealerpos=-1;
                for(int j=0; j<playersalive; j++){
                    if(PlayerDataBase[j].isDealer()){
                        dealerpos=j;
                        Log.i("Aniquilador",String.format("Dealer position =%d",dealerpos));
                    }
                }

                PlayerItems auxPlayer = PlayerDataBase[i];
                for(int l=i; l<9;l++){
                    PlayerDataBase[l]=PlayerDataBase[l+1];
                }
                PlayerDataBase[9]=auxPlayer;
                Log.i("Aniquilador",String.format("Player name %s",PlayerDataBase[0].getName()));
                Log.i("Aniquilador",String.format("Player name %s",PlayerDataBase[1].getName()));
                Log.i("Aniquilador",String.format("Player name %s",PlayerDataBase[2].getName()));
            }
        }

        //actualizamos el valor de los jugadores que quedan vivos

        //Log.i("Check3",String.format("Plaersalive %d // i %d",playersalive,i));
        //Log.i("Check2",String.format("i %d isAnnihilated %b // %b isGenerated",i, PlayerDataBase[i].isAnnihilated(),PlayerDataBase[i].isGenerated()));7

        playersalive =10-number_playersout;
        //Una vez reorganizada la PlayerDataBase eliminamos los estados de ciega de todos los players
//REORGANIZATOR

    }

}