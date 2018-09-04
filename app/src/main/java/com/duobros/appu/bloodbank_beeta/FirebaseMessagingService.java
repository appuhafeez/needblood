package com.duobros.appu.bloodbank_beeta;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by appu on 15/7/17.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //Toast.makeText(this, "message recieved", Toast.LENGTH_LONG).show();
       showNotification(remoteMessage);
    }

    private void showNotification(RemoteMessage message) {
        Intent i = new Intent(this,Mainactivity123.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(message.getData().get("grp") + " required at")
                .setContentText(message.getData().get("addr"))
                .setSmallIcon(R.mipmap.icon)
                .setSound(alarmSound)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.GREEN, 3000, 3000)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0, builder.build());


        try {
            //File bloodfile = Environment.getExternalStoragePublicDirectory("Bloodbank");
            File bloodfile=getFilesDir();
            if (!bloodfile.exists())
                bloodfile.mkdir();
            File file = new File(bloodfile, "required.txt");


            if (!file.exists())
                file.createNewFile();

            FileReader fr=new FileReader(file);
            Scanner sc=new Scanner(fr);
            if(sc.hasNextLine()){
                FileWriter fout = new FileWriter(file,true);
                //Formatter fmt1=new Formatter();
                //fmt1.format("%s",token);

                fout.write("\n"+message.getData().get("exp")+"\n"+message.getData().get("grp")+"\n"+message.getData().get("phone")+"\n"+message.getData().get("addr"));
                fout.close();

            }else {
                FileWriter fout = new FileWriter(file,true);
                //Formatter fmt1=new Formatter();
                //fmt1.format("%s",token);

                fout.write(message.getData().get("exp")+"\n"+message.getData().get("grp")+"\n"+message.getData().get("phone")+"\n"+message.getData().get("addr"));
                fout.close();
            }

        }catch (Exception e){
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
