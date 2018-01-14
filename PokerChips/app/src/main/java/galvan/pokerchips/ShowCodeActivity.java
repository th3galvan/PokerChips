package galvan.pokerchips;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * Created by xvilaseca on 09/01/2018.
 */

public class ShowCodeActivity extends AppCompatActivity {

    private TextView txt_code;

    private int code;
    private int players;
    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private String name;
    private ImageView image;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showqr);

        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");
        players =code_receive.getInt("players");
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name =code_receive.getString("name");


        Button btn_code = (Button)findViewById(R.id.btn_generate_code);
        txt_code = (TextView)findViewById(R.id.txt_code);
        image = (ImageView) findViewById(R.id.image);


        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string_code = Integer.toString(code);
                txt_code.setText(string_code);

                Intent intent_list = new Intent(ShowCodeActivity.this, PlayersListActivity.class);
                intent_list.putExtra("name", name);
                intent_list.putExtra("playersnumber", number_players);
                intent_list.putExtra("initial_chips", initial_chips);
                intent_list.putExtra("bigblind", big);
                intent_list.putExtra("frecuency", time_big_up);
                intent_list.putExtra("change", change_value_big);
                startActivity(intent_list);

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


