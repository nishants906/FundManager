package first.com.fundmanger;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignIn extends AppCompatActivity {

    TextView head, subHead;
    TextView create_new_profile, cancel, log_in;
    EditText username, password;
    String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        create_new_profile = (TextView) findViewById(R.id.create_profile);
        cancel = (TextView) findViewById(R.id.cancel);
        log_in = (TextView) findViewById(R.id.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        username.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationmassage = new Intent(getApplicationContext(),Notificationmassage.class);

//This is alarm manager
        PendingIntent pi = PendingIntent.getService(this, 0 , notificationmassage, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
        create_new_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateProfile.class);
                startActivity(intent);
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = String.valueOf(username.getText());
                Password = String.valueOf(password.getText());


                if(Password.length()!=4 || !isNumeric(Password)) {
                    Toast.makeText(getApplicationContext(),"Enter 4-digit Pin",Toast.LENGTH_SHORT).show();
                    password.setText("");
                }
                Password = String.valueOf(password.getText());
                DBHandler db = new DBHandler(getApplicationContext());

                if(Username.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_SHORT).show();
                }

                else if(db.getRecordsRowCount()==0) Toast.makeText(getApplicationContext(), "No User Exists. Create new Account to Login", Toast.LENGTH_SHORT).show();
                else  {

                    String[][] actual_details = db.getLoginDetails();
                    if(Username.equals(actual_details[0][0]) && Password.equals(actual_details[1][0])){
                        Intent intent = new Intent(getApplicationContext(), MainApp.class);
                        startActivity(intent);
                        }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}