package jp.ac.titech.itpro.sdl.hilbert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private TextView orderView;
    private HilbertView hilbertView;
    private Button decButton, incButton;
    private final static int MAX_ORDER = 9;
    private int order = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            order = savedInstanceState.getInt("ORDER");
        //if value is stored load the value from the key
        setContentView(R.layout.activity_main);
        orderView = findViewById(R.id.order_view);
        hilbertView = findViewById(R.id.hilbert_view);
        decButton = findViewById(R.id.dec_button);
        incButton = findViewById(R.id.inc_button);
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // assert order > 1;
                if (BuildConfig.DEBUG && !(order > 1))
                    throw new AssertionError();
                order--;
                display();
            }
        });
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // assert order < MAX_ORDER;
                if (BuildConfig.DEBUG && !(order < MAX_ORDER))
                    throw new AssertionError();
                order++;
                display();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        display();
    }

    private void display() {
        orderView.setText(getString(R.string.order_view_format, order));
        hilbertView.setOrder(order);
        decButton.setEnabled(order > 1);
        incButton.setEnabled(order < MAX_ORDER);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ORDER", order);
        //change the line above
        // name => value to save
        //KEY_NAME => ket name to save in
    }}
