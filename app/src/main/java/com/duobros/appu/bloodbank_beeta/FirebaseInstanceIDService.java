package com.duobros.appu.bloodbank_beeta;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by appu on 15/7/17.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
    }

    private void registerToken(String token) {
       /* SharedPreferences.Editor editor=getSharedPreferences("BLOOD_BANK",MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.apply();*/
        try{
            //Toast.makeText(this, token, Toast.LENGTH_LONG).show();
            /*File bloodfile= Environment.getExternalStoragePublicDirectory("Bloodbank");
            if (!bloodfile.exists())
                bloodfile.mkdir();
            File file=new File(bloodfile,"token.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fout=new FileWriter(file);
            //Formatter fmt1=new Formatter();
            //fmt1.format("%s",token);
            fout.write(token);
            fout.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
