package galvan.pokerchips;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import galvan.pokerchips.Datos.FirebaseReferences;


/**
 * Created by xvilaseca on 09/01/2018.
 */

public class ShowCodeActivity extends AppCompatActivity {

    private TextView txt_code;

    private int code;
    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;
    private int players_join=0;

    private String name;
    private ImageView image;
    private DatabaseReference game_reference;
    private String game_id;
    private DatabaseReference players_join_ref;
    private DatabaseReference host_ready_ref;
    private DatabaseReference game_id_ref;
    private DatabaseReference Scan_notify_ref;

    private boolean Scan_notify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showqr);


        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name =code_receive.getString("name");
        game_id = code_receive.getString("game_id");

        //aqui genero una Key en firebase aleatoria y me la guardo en game_id, cada id correspondra con una partida diferente
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        game_id_ref = database.getReference(FirebaseReferences.GAME_ID_REFERENCE);
        game_id_ref.removeValue();
        game_id_ref.setValue(game_id);

        //todo saco id fuera para poder usarla
        game_reference = database.getReference(FirebaseReferences.GAME_REFERENCE);
        //game_reference.child(game_id).child(FirebaseReferences.GAME_ID_REFERENCE).setValue(game_id);
        players_join_ref = game_reference.child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.setValue(players_join);

        //listener para captar cuando se van introduciendo diferentes jugadores
        //metemos dentro el intent para que sea automatico

        //todo para salir del paso

        boolean host_ready = false;
        host_ready_ref = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.HOST_READY_REFERENCE);
        host_ready_ref.setValue(host_ready);

       // players_join_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //cojemos el valor de base de datos
                players_join = dataSnapshot.getValue(Integer.class);
                players_join_ref.removeValue();
                players_join_ref.setValue(players_join);

                //hasta que no se hayan incorporado todos los jugadores no se pasa a la siguiente pantalla
                Log.i("Xavi",String.format("players_join %d // number_players %d",players_join,number_players));
                if (players_join==number_players){

                    //renuevo para que todos los jugadores obtengan el valor
                    players_join_ref.removeValue();
                    players_join_ref.setValue(number_players);

                    Intent intent_list = new Intent(ShowCodeActivity.this, PlayersListActivity.class);
                    intent_list.putExtra("name", name);
                    intent_list.putExtra("playersnumber", number_players);
                    intent_list.putExtra("initial_chips", initial_chips);
                    intent_list.putExtra("bigblind", big);
                    intent_list.putExtra("frecuency", time_big_up);
                    intent_list.putExtra("change", change_value_big);
                    intent_list.putExtra("game_id",game_id);
                    startActivity(intent_list);}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button btn_code = (Button)findViewById(R.id.btn_generate_code);
        txt_code = (TextView)findViewById(R.id.txt_code);
        image = (ImageView) findViewById(R.id.image);




        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string_code = Integer.toString(code);
                txt_code.setText(string_code);

                //funcion de generar QR
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(string_code, BarcodeFormat.QR_CODE, 300, 300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}


