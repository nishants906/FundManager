package first.com.fundmanger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateProfile extends AppCompatActivity {
    TextView head,subHead;
    TextView login, cancel;
    EditText name, username, pass, limit;
    String Name,Username,Pass, Limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);



        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        limit = (EditText) findViewById(R.id.limit);

        pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


        login = (TextView) findViewById(R.id.login);
        cancel = (TextView) findViewById(R.id.cancel);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = String.valueOf(name.getText());
                Username = String.valueOf(username.getText());
                Pass = String.valueOf(pass.getText());

                if(Pass.length()!=4 || !isNumeric(Pass)) {
                    Toast.makeText(getApplicationContext(),"Please Enter Four Digit Pass only",Toast.LENGTH_SHORT).show();
                    pass.setText("");
                }

                Limit = String.valueOf(limit.getText());

                if(!isNumeric(Limit)) {
                    Toast.makeText(getApplicationContext(),"Please Enter Digits Only",Toast.LENGTH_SHORT).show();
                    limit.setText("");
                }

                Pass = String.valueOf(pass.getText());
                Limit = String.valueOf(limit.getText());

                if(Name.equals("") || Username.equals("")) {
                    Toast.makeText(getApplicationContext(),"Please Enter Your Details",Toast.LENGTH_SHORT).show();
                }
                else if(Pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Four digit Pass Only",Toast.LENGTH_SHORT).show();
                }
                else if(Limit.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Amount",Toast.LENGTH_SHORT).show();
                }
                else {
                    DBHandler db = new DBHandler(getApplicationContext());
                    db.resetTable_Credit();
                    db.resetTable_Misc();
                    db.resetTable_final();
                    db.resetTable_Debit();
                    db.resetTable_Records();


                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-mm-yy");
                    String formattedDate = df.format(c.getTime());


                    db.addStartDate(formattedDate);
                    db.addRecords(Name, Username, Pass);
                    db.addAmount(Limit);

                    Intent intent = new Intent(getApplicationContext(), MainApp.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();
            }
        });
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