package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class option<Cell, Workbook, Sheet> extends FragmentActivity
{
    String option="";

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        option = i.getStringExtra("option");
        setContentView(R.layout.option);
        TextView t=(TextView)findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeToFile(option);
                scheduleNotification();
                finish();
            }
        });
        t.setText("Option: " + option + "  ");

        ImageView img2 =(ImageView) findViewById(R.id.imageView2);

        int imgRes1 = R.drawable.option1;
        img2.setBackgroundResource(imgRes1);
    }
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(option.this, "Option "+data+" is choosen", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public String[][] readGeData(int a) {
        String[][] result=new String[0][0];
        a=1;
        try
        {
            AssetManager am = getAssets();
            InputStream is = am.open("Option1" + a + ".xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();
            String[][] table= new String[row][col];
            for(int i = 0; i < row; i++)
            {
                for(int j = 0; j < col; j++)
                {
                    Cell z=s.getCell(j,i);
                    table[i][j]=z.getContents();
                }
            }
            result=table;
        }
        catch (Exception ignored) {
        }
        return result;
    }
    //    public void getGeDest() {
//        String[][] data=readGeData(Integer.parseInt(option));
//        Long current=System.currentTimeMillis();
//        Log.d("time",current+"");
//        Log.d("adsa", data.length+"");
//        for(int i=1; i<data.length;i++) {
//            Long dest=Long.parseLong(data[i][0]);
//            String desti=data[i][1];
//            String dst_1=data[i][2];
//            scheduleNotification(getNotification("You have "+desti+" in "+dst_1), (int)(dest-current));
//            Log.d("You have ",desti+" in "+dst_1);
//            Log.d("asdasf",(int)(dest-current)+"");
//        }
//
//    }
    @SuppressLint("ShortAlarm")
    private void scheduleNotification() {

        Intent intentsOpen = new Intent(this, NotificationPublisher.class);
        //intentsOpen.setAction("com.manish.alarm.ACTION");
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(this,111, intentsOpen, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pendingIntent);
    }

}

