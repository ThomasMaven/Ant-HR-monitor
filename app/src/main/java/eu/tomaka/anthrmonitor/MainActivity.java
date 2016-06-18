package eu.tomaka.anthrmonitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    static private HRConnector connector;

    TextView statusTextView, aktualnyPuls;
    public Button connectButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton = (Button) findViewById(R.id.connectButton);
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        aktualnyPuls = (TextView) findViewById(R.id.currentPulseTextView);
        //Create HRConnector object
        connector = new HRConnector(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void setStatus ( String s ) {
        statusTextView.setText(s);
    }

    public void setPulse ( String s ) {
        aktualnyPuls.setText(s);
    }

    // connect-disconnect button onClick() handler
    public void connDisconn(View view) {
        if ( connector.isConnected() ) {
            connector.disconnect(false);
        } else {
            connector.connect();
        }
    }



}
