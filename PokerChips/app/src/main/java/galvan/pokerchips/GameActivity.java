package galvan.pokerchips;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    private int pos;
    private int bet2;
    private int ownChips;
    private int current_individual_bet;
    private int current_total_bet;
    private int total_bet;
    private int winner=0;
    private int big=50;
    private int loot =0;
    private int playersin=0;
    private int nState;
    private int Player_big_blind;
    private int Player_small_blind;
    private int winner_pos[] ={7,7,7,7};
    private int winner_check;
    private int eq_bet;
    private int playerscall;
    private int winner2=0;
    private int count_winner_pos=0;
    private int all_in_value;
    private int players_allin;
    private int restart_bet;
    private int cont_playersin;
    private int cont_winner_out=0;
    private int count_loser_pos;
    private int playernumber;
    private int time;

    private String string_big;
    private String string_bet;

    private boolean all_in;
    private boolean checkout;
    private boolean winner_finish=false;

    private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    private TextView txt_bet;
    private TextView txt_current_total_bet;
    private TextView txt_total_bet;
    private TextView txt_big;
    private TextView txt_time_number;



    //VARIABLES DE PRUEBA
    private TextView fichasmalaquito ;
    private TextView fichasmalaquitodatabase ;
    private TextView txt_playernumber;
//// TODO: 17/12/2017 hay que hacer algo para asignar un mobil con un jugador

    private ArrayList<PlayerItems> players;
    private PlayerItemAdapter adapter;
    private ListView list;

    //PlayerItems( int i,
    //              String name, int chips,      boolean dealer,
    //              boolean small, boolean big,  boolean in,
    //              int bet,      boolean turn    boolean allin
    //              boolean win)
    private PlayerItems Fulanito= new PlayerItems(1,
            "Fulanito", 1000,
            true,      false,   false,
            true,
            0,          false,   false, false, false);
    private PlayerItems Menganito= new PlayerItems(2,
            "Menganito", 1000,
            false,        true,  false,
            true,
            0,           false,  false, false, false);
    private PlayerItems Malaquito= new PlayerItems(3,
            "Malaquito", 1000,
            false,      false,    true,
            true,
            0,         false,   false, false, false);

    private PlayerItems Estalactito = new PlayerItems(4,
            "Estalactito", 1000,
            false,      false,       false,
            true,
            0,           false,     false, false, false);

    private PlayerItems PlayerDataBase[]={Fulanito,Menganito,Malaquito,Estalactito};
    private int initial_chips;
    private int time_big_up;
    private int change_value_big;
    private String name_host;
    private int min;
    private TextView txt_turn;
    private int number_players;
    private String Scurrent_total_bet;


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
    //Cargar datos salvados despues de un onDestroy
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

        Intent intent =getIntent();

        Bundle code_receive = getIntent().getExtras();
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name_host =code_receive.getString("name");

        PlayerDataBase[0].setName(name_host);
        for (int i=0; i<PlayerDataBase.length;i++){
        PlayerDataBase[i].setChips(initial_chips);}


        // TODO: 17/12/2017  hay que hacer algo para asignar un mobil con un jugador
        //Todo: hay que hacer que playernuber se obtenga de PlayerDataBase
        playernumber=0;

        //parte de añadir lista de jugadores y adapter
        list = (ListView)findViewById(R.id.players_list);

        players = new ArrayList<>();
        //PlayerItems( int i,
        // String name, int chips,      boolean dealer,
        // boolean big, boolean small,  boolean out,
        // int bet,      boolean turn    boolean allin)
        players.add(Fulanito);

        players.add(Menganito);

        players.add(Malaquito);

        players.add(Estalactito);

        adapter = new PlayerItemAdapter(
                this,
                R.layout.activity_playeritems,
                players
        );
        list.setAdapter(adapter);



        final int chips[] ={5,10,25,50,100};




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
        //TODO: de momento usamos total bet para hacer display de la gente que esta in
        checkWinner();
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
        String Scurrent_total_bet= Integer.toString(current_total_bet);
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
        turnALLIN(); turnState();



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
                if(all_in){String textBetBtn = Integer.toString(all_in_value);
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
                if (all_in){String textBetBtn = Integer.toString(all_in_value);
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
                    builder.setMessage("You must select the winner");
                    builder.create().show();}
                else if (all_in){String textBetBtn = Integer.toString(all_in_value);
                    txt_bet.setText(textBetBtn);}
                else{
                bet2=ownChips;
                String textAllBtn = Integer.toString(bet2);
                txt_bet.setText(textAllBtn);
                PlayerDataBase[playernumber].setIn(true);
            }}
        });


        //BTN EQUALIZE
        btn_equalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage("You must select the winner");
                    builder.create().show();}
                else if (all_in){String textBetBtn = Integer.toString(all_in_value);
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
                    builder.setMessage("You must select the winner");
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

                if(nState==8){AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                    builder.setMessage("You must select the winner");
                    builder.create().show();}
                else{

                    if(all_in){bet2=all_in_value;
                    toBet(bet2);}

                if(bet2 == 0){
                    Message0bet();
                }
                else {
                        toBet(bet2);}
            }}
        });

        //Seleccionar ganador

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View item, int pos, long id) {

                if(nState==8 & !winner_finish){
                    if(PlayerDataBase[pos].isIn()){
                    PlayersIn();
                    Log.i("WINNER",String.format("plasyersin%d // cont_winner_out%d", playersin,cont_winner_out));
                    if (cont_winner_out==playersin){
                        PlayerDataBase[pos].setWin(true);
                        cont_winner_out=0;
                        winner2=4;
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



        //declaramos el view de la cuenta atras y la iniciamos con un contador
        txt_time_number = (TextView) findViewById(R.id.txt_time_number);

        time = 60000;
        min = time_big_up-1;

        new CountDownTimer(time, 1000){

            public void onTick(long millisUntilFinished){
                txt_time_number.setText(String.format("%d:",min)+ millisUntilFinished / 1000);
            }
            public void onFinish(){
                //TODO: aqui cuando el contador llega a cero hay que cambiar los valores de los parametros que haga falta

                Toast toastCiega = Toast.makeText(getApplicationContext(),
                        "Subida de Ciega", Toast.LENGTH_LONG);
                toastCiega.show();

                start();
            }
    }.start();


    }




private void Message0bet() {

        //Mensaje que nos indica que estamos dandole a apostar sin seleccionar ficha
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.Error);
        builder.setMessage("You are trying to bet very few chips, press CHECK/FOLD or bet some more chips");
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
            turnALLIN(); turnState();
            nextTurn();
        }
        else{
            Log.i("Galvan","FOLD");
            PlayerDataBase[playernumber].setIn(false);
            PlayerDataBase[playernumber].setCall(false);
            turnALLIN(); turnState();
            nextTurn();
        }
    }

    //este metodo se ejecuta cuando pulsamos pasar o apostar, si pulsamos bet el valor de la variable
    // bet se restará a tus fichas y se actualizará el valor de current_individual_bet si este es menor
    //a la nueva apuesta

    private void toBet(final int toBetBet) {
        //CALCULAMOS CON CUANTAS FICHAS NOS QUEDAMOS(en teoria es imposible tener un valor negativo en newownChips)
        final int newownChips=ownChips+PlayerDataBase[playernumber].getBet()-toBetBet;

        //SI VAMOS ALL IN
        if (newownChips<0){AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("You do not have enought chips, please FOLD");
            builder.create().show();}

        else if(newownChips==0 ){
            Log.i("Galvan","(newownChips<=0)");
            //CREAMOS DIALOGO
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirmation);
            builder.setCancelable(false);
            builder.setMessage("Are you sure you want to bet all your chips");
            //PULSAMOS YES
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    all_in=true;
                    ownChips=newownChips;
                    //REFRESCAR TXT_OWNCHIPS EN PANTALLA
                    String SownChips= Integer.toString(ownChips);
                    txt_ownChips.setText(SownChips);

                    //CAMBIO APUESTA ACTUAL INDIVIDUAL
                    higherBet(toBetBet);
                    //ACTUALIZACION CURRENT TOTAL BET
                    current_total_bet=current_total_bet+toBetBet;                               //actualiza el valor de current total bet
                    Scurrent_total_bet = Integer.toString(current_total_bet);
                    txt_current_total_bet.setText(Scurrent_total_bet);
                    //ACTUALIZACION BET
                    bet2=0;
                    txt_bet.setText(Integer.toString(bet2));
                    //DATABASE
                    PlayerDataBase[playernumber].setBet(toBetBet);
                    PlayerDataBase[playernumber].setChips(newownChips);
                    PlayerDataBase[playernumber].setAllin(true);
                    PlayerDataBase[playernumber].setCall(true);
                    PlayerDataBase[playernumber].setIn(true);
                    all_in_value=toBetBet;

                    Log.i("Galvan","apuesta confirmada");

                    //REFRESH ownChips
                    refresh();
                    //CAMBIO DE TURNO
                    nextTurn();
                    Log.i("Galvan","siguiente");
                    Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
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
                if(bet2==0){
                builder2.setMessage(String.format("Are you sure you want to bet %s chips?",Integer.toString(toBetBet)));}
                else {
                    builder2.setMessage(String.format("Are you sure you want to bet %s chips more?\n" +
                            "You current bet is %s chips",Integer.toString(toBetBet),Integer.toString(bet2)));}
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
                        PlayerDataBase[playernumber].setBet(toBetBet+bet2);
                        PlayerDataBase[playernumber].setChips(newownChips);
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
        for (int i=0;i<PlayerDataBase.length;i++){
             if(PlayerDataBase[i].isAllin()){players_allin++;}
        }
        PlayersIn();
        if(players_allin==playersin){nState=8;}

    }

    private void turnState() {

        switch (nState) {
            //BIG BLIND SMALL BLIND
            case 0:
                txt_turn.setText("PREFLOP");

                for(int i=0; i<PlayerDataBase.length; i++ ){
                    //busca quien es el big y le resta el valor Big a sus fichas y los suma al bote
                    if(PlayerDataBase[i].isBig()){
                        Player_big_blind = i;
                        PlayerDataBase[i].setChips(PlayerDataBase[i].getChips()-big);
                        //Creamos una memoria del num de fichas que ha apostado este jugador
                        PlayerDataBase[i].setBet(big);
                        current_total_bet= current_total_bet+big;
                        current_individual_bet=big;
                        //en el preflop el turno es para el siguiente del Big Blind
                        playernumber=i+1;
                        //inicializo turno
                        Log.i("WHERE",String.format("%d",playernumber));
                        if(playernumber>=PlayerDataBase.length){
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
                break;

            //PREFLOP
            case 1:
                txt_turn.setText("PREFLOP");
                PlayersIn();
                playerscall=0;

                for(int p=0 ;p<PlayerDataBase.length;p++){
                    if(PlayerDataBase[p].isCall()){playerscall++;}}

                //Log.i("XaviVilaseca",String.format("%d // %d", playersin, playerscall));
               if (playersin== playerscall){nState=2;}
                else {break;}

            //RESTART
            case 2:

                Restart();
                nState=3;

            //FLOP
            case 3:
                txt_turn.setText("FLOP");
                PlayersIn();
                playerscall=0;
                for(int l=0 ;l<PlayerDataBase.length;l++){
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
                txt_turn.setText("TURN");
                PlayersIn();
                playerscall=0;

                for(int p=0;p<PlayerDataBase.length;p++){
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
                txt_turn.setText("RIVER");
                PlayersIn();
                playerscall=0;
                for(int p=0;p<PlayerDataBase.length;p++){
                    if(PlayerDataBase[p].isCall()){playerscall++;}}

                //Log.i("XaviVilaseca",String.format("%d // %d", playersin, playerscall));
                Log.i("XaviVilaseca","7");
                if (playersin== playerscall){nState=8;}
                else {break;}

            //WINNER-
            case 8:
                txt_turn.setText("WINNER");
                AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                builder2.setMessage("The turn of bets is over it's time to know who is the winner");
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

                    AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                    builder2.setMessage(String.format("Player %s choose the winner",PlayerDataBase[0].getName()));
                    builder2.create().show();
                    cont_playersin++;
                    break;}
                else {winner2++;}

           case 1:
                if(PlayerDataBase[1].isIn() & !(playersin==cont_playersin)){

                    AlertDialog.Builder builder3= new AlertDialog.Builder(this);
                    builder3.setMessage(String.format("Player %s choose the winner",PlayerDataBase[1].getName()));
                    builder3.create().show();
                    cont_playersin++;
                    break;}

                else {winner2 =2;}

            case 2:
                if(PlayerDataBase[2].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder4= new AlertDialog.Builder(this);
                    builder4.setMessage(String.format("Player %s choose the winner",PlayerDataBase[2].getName()));
                    builder4.create().show();
                    cont_playersin++;
                    break;}

                else {winner2 =3;}

            case 3:

                if(PlayerDataBase[3].isIn() & !(playersin==cont_playersin)){
                    AlertDialog.Builder builder5= new AlertDialog.Builder(this);
                    builder5.setMessage(String.format("Player %s choose the winner",PlayerDataBase[3].getName()));
                    builder5.create().show();}
                break;

            case 4:
                winner_finish=true;
                cont_playersin=0;
                Log.i("WINNER","ESTOY?");
                PlayersIn();
                if(winner_finish & cont_winner_out==playersin){Log.i("WINS","FINISH");
                    checkWinner();
                }

        }
    }

    private void PlayersIn() {
        playersin=0;
        for (int i=0;i<PlayerDataBase.length ;i++ ){
            if(PlayerDataBase[i].isIn()){playersin++;}
        }
    }


    private void checkWinner() {

        Log.i("WINS","CHECKWINNER");
        //condiciones para que el juega haya acabado
        if(playersin>1 & (nState == 8)){
            //ahora vamos a ver si todos han votado lo mismo para ello cogemos la primera posicion y vamos comparando
            count_winner_pos=0;
            count_loser_pos=0;
            for (int i = 0; i<winner_pos.length;i++){
            winner_pos[i]=7;}

            for (int i =0;i<PlayerDataBase.length;i++){

                    if(PlayerDataBase[i].isWin()){winner_pos[count_winner_pos]=i; count_winner_pos++;}
            }
            winner_check=winner_pos[0];
            for (int i=0;i<winner_pos.length;i++){
                if (winner_pos[i]==7){winner_pos[i]=winner_check;}
            }

            for (int i =0;i<=count_winner_pos;i++){
                Log.i("WINS",String.format("winner_pos %d // winner_check %d", winner_pos[i],winner_check));
                if(winner_pos[i]==winner_check){}
                //si es diferente en algun momento ponemos el valor a -1
                else {winner_check=-1;
                        winner_finish=false;}
            }
            //si ha sido diferente volvemos vamos al step 9 que es volver a hacer el mismo ciclo
            if(winner_check==-1){

                AlertDialog.Builder builder5= new AlertDialog.Builder(this);
                builder5.setMessage("You are choosing differents players, please try it again and select the same winner");
                builder5.setCancelable(false);
                builder5.setNeutralButton(R.string.confirmation, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("WINS","DIFERENTE");
                        nState=8; winner2=0; cont_winner_out=0;
                        for (int o=0;o<PlayerDataBase.length;o++){
                            PlayerDataBase[o].setWin(false); }

                        turnALLIN(); turnState(); }
                });
                builder5.create().show();

                }
            //si todos son el mismo jugador se le asigna la victoria

            else {
                //Log.i("WINS","IGUAL");
                //todo sumar current total bet siempre?
                loot= PlayerDataBase[winner_check].getChips()+total_bet+current_total_bet;
                //Log.i("LOOT",String.format("loot %d // chips %d",loot,PlayerDataBase[winner_check].getChips()));
                PlayerDataBase[winner_check].setChips(loot);
                AlertDialog.Builder builder3= new AlertDialog.Builder(this);
                builder3.setMessage(String.format("Player %s won %s chips",PlayerDataBase[winner_check].getName(),Integer.toString(total_bet)));
                builder3.create().show();
                current_total_bet=0;
                current_individual_bet=0;
                total_bet=0;
                winner_finish=false;
                winner2=0;
                cont_winner_out=0;
                //Se pone to*do a 0
                for(int i =0;i<PlayerDataBase.length;i++){
                    PlayerDataBase[i].setIn(true);
                    PlayerDataBase[i].setBet(0);
                    PlayerDataBase[i].setCall(false);
                    PlayerDataBase[i].setTurn(false);
                //valores a 0 por allin
                    all_in=false;
                    all_in_value=0;
                    restart_bet=0;
                    string_bet = Integer.toString(restart_bet);
                    txt_bet.setText(string_bet);}
                rotarCiega(playersin);}
        }}

    private void OnePlayerIn() {
        PlayersIn();
        Log.i("WIN", String.format("%d",playersin));
        if(playersin==1){
            winner_finish=true;
            //buscamos el indice de jugador del ganador
            for(int i=0; i<PlayerDataBase.length;i++){
                if(PlayerDataBase[i].isIn()){
                    winner= i;
                }
            }
            Log.i("WIN",String.format("%d",winner));
            //sumamos la cantidad de current total bet a las fichas del ganador
            loot= PlayerDataBase[winner].getChips()+total_bet+current_total_bet;
            PlayerDataBase[winner].setChips(loot);
            AlertDialog.Builder builder3= new AlertDialog.Builder(this);
            builder3.setMessage(String.format("Player %s won %s chips",PlayerDataBase[winner].getName(),Integer.toString(total_bet)));
            builder3.create().show();
            current_total_bet=0;
            current_individual_bet=0;
            total_bet=0;
            winner2=0;
            for(int i =0;i<PlayerDataBase.length;i++){
                PlayerDataBase[i].setIn(true);
                PlayerDataBase[i].setBet(0);
                PlayerDataBase[i].setCall(false);
                PlayerDataBase[i].setTurn(false);
                //valores a 0 por allin
                all_in=false;
                all_in_value=0;
                restart_bet=0;
                string_bet = Integer.toString(restart_bet);
                txt_bet.setText(string_bet);}
            rotarCiega(playersin);}
    }

    private void Restart() {

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

        for(int i=0; i<PlayerDataBase.length;i++){
            int cero=0;
            PlayerDataBase[i].setBet(cero);
            PlayerDataBase[i].setCall(false);
        }
    }

    private void higherBet(int toBetBet) {

        if(all_in){
            for(int i = 0; i<PlayerDataBase.length; i++){

                PlayerDataBase[i].setCall(false);

                list.setAdapter(adapter);

                current_individual_bet = PlayerDataBase[playernumber].getBet();
                String Scurrent_individual_bet = Integer.toString(current_individual_bet);
                txt_current_individual_bet.setText(Scurrent_individual_bet);
                Log.i("Galvan","(current_individual_bet = toBetBet;)");
                refresh();
        }}


         else if (toBetBet+PlayerDataBase[playernumber].getBet()> current_individual_bet){
                for(int i = 0; i<PlayerDataBase.length; i++){

                PlayerDataBase[i].setCall(false);

                list.setAdapter(adapter);
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
        list.setAdapter(adapter);
        if(current_individual_bet!=0 || PlayerDataBase[playernumber].getBet()==current_individual_bet){btn_check.setText(R.string.check);}
        else{btn_check.setText(R.string.fold);}

    }

    private void nextTurn() {
        //si hay allin chequeo si ya han apostado todos

        //CAMBIO DE TURNO
        Log.i("Galvan","CAMBIO DE TURNO");
        PlayerDataBase[playernumber].setTurn(false);

        if((playernumber==PlayerDataBase.length-1)){playernumber=0;}
        else{playernumber++;}

        while(!PlayerDataBase[playernumber].isIn()){

            if((playernumber==PlayerDataBase.length-1)){playernumber=0;}
            else{playernumber++;}

        }
        //MIRAMOS SI PUEDE HABER GANADOR
        for (int i =0; i<PlayerDataBase.length;i++){
            if(PlayerDataBase[i].isIn()){playersin++;}
        }
        Log.i("WIN","YES");
        OnePlayerIn();

        PlayerDataBase[playernumber].setTurn(true);
        list.setAdapter(adapter);
        refresh();
    }

    private void AdjustTurn(){
        //Ajustamos el turno ya que cuando creaba los turnos se desplazaba una posicion hacia adelante
        PlayerDataBase[playernumber].setTurn(false);

        if (playernumber==0){playernumber=PlayerDataBase.length-1;}
        else{playernumber--;}

        while(!PlayerDataBase[playernumber].isIn()){

            if (playernumber==0){playernumber=PlayerDataBase.length-1;}
            else {playernumber--;}
        }

        PlayerDataBase[playernumber].setTurn(true);
        list.setAdapter(adapter);

    }



    private void rotarCiega(int playersin) {
        boolean checkout;//DESPLAZAMIENTO CIEGAS
        //si el ultimo de la lista no es ni big small ni dealer
        if( !PlayerDataBase[PlayerDataBase.length-1].isBig() &
                !PlayerDataBase[PlayerDataBase.length-1].isDealer() &
                !PlayerDataBase[PlayerDataBase.length-1].isSmall()) {
            boolean aux= false;
            for(int i=0; i<PlayerDataBase.length;i++){
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

                    PlayerDataBase[i].setBig(true);
                    Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));
                    aux=false;

                }
                Log.i("GALVAN",String.format("i= %s",Integer.toString(i)));
                list.setAdapter(adapter);
            }
        }
        //si el ultimo es big
        else if(PlayerDataBase[PlayerDataBase.length-1].isBig()) {
            PlayerDataBase[0].setBig(true);

            for (int i = 1; i < PlayerDataBase.length; i++) {
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

                    list.setAdapter(adapter);
                }
            }
        }
        //si el ultimo es small
        else if(PlayerDataBase[PlayerDataBase.length-1].isSmall()){
            PlayerDataBase[0].setBig(false);
            PlayerDataBase[0].setSmall(true);
            PlayerDataBase[1].setBig(true);

            for(int i=1; i<PlayerDataBase.length;i++){
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

                    PlayerDataBase[i].setBig(true);
                    Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));


                }
                list.setAdapter(adapter);
            }
        }
        else if(PlayerDataBase[PlayerDataBase.length-1].isDealer()){
            PlayerDataBase[0].setSmall(false);
            PlayerDataBase[0].setDealer(true);
            PlayerDataBase[1].setBig(false);
            PlayerDataBase[1].setSmall(true);
            PlayerDataBase[2].setBig(true);
            //DEALER->nA
            for(int i=3; i<PlayerDataBase.length;i++){
                if(PlayerDataBase[i].isDealer()){
                    PlayerDataBase[i].setDealer(false);
                    Log.i("GALVAN","Dealer-na");
                }
            }


            //RESTAURAR OUT
            for(int i = 0; i<PlayerDataBase.length; i++){
                checkout = PlayerDataBase[i].isIn();
                if(checkout==true){playersin++;}
                txt_current_total_bet.setText(Integer.toString(playersin));
            }

            //Rotar turno

            refresh();

        }
        for (int i=0; i<winner_pos.length;i++){
        winner_pos[i]=7;}
        nState=0;
        winner2 = 0;
        winner_finish=false;
        turnALLIN(); turnState();
    }

}