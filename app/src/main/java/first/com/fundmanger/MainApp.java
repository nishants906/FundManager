package first.com.fundmanger;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainApp extends AppCompatActivity {

    ViewPager  pager;
    TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);

        Intent intent = getIntent();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogoDescription("BillBook");
        getSupportActionBar().setTitle("BillBook");

        pager = (ViewPager) findViewById(R.id.pager);
        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        DBHandler db = new DBHandler(getApplicationContext());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_month) {
            Intent intent = new Intent(getApplicationContext(), ResetMonth.class);
            startActivity(intent);
            finish();

            return true;
        }
        else if(id == R.id.view_stats){
            Intent intent = new Intent(getApplicationContext(), Statistics.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}