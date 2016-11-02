package first.com.fundmanger;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);



       /* Calendar c = Calendar.getInstance();
        Intent myintent = new Intent(this,MainApp.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,myintent,0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC,8000000,AlarmManager.INTERVAL_DAY,pi);*/


        final DBHandler db = new DBHandler(getApplicationContext());
        final Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                        Intent intent = new Intent(MainActivity.this,SignIn.class);
                        startActivity(intent);
                        finish();
                }
            }
        };
        timerThread.start();


    }


}