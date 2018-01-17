package galvan.pokerchips;


/**
 * Created by Alvaro on 17/01/2018.
 */

import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

public class Zumbido {

    //En el manifest
    <uses-permission android:name="android.permission.VIBRATE"/>

    //donde tenga que zumbar (vibracion simple)
    Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    v.vibrate(3000);

}

