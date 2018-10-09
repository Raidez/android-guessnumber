package com.example.alexis.guess_number;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {
    Map<ImageButton, Integer> boutons;
    int randomNumber;

    private void init() {
        randomNumber = (int) (Math.random() * (99 - 1) + 1);
        //Log.d("init", "nombre aléatoire : " + randomNumber);

        boutons = new HashMap<>();
        boutons.put((ImageButton) findViewById(R.id.btnZero), new Integer(0));
        boutons.put((ImageButton) findViewById(R.id.btnOne), new Integer(1));
        boutons.put((ImageButton) findViewById(R.id.btnTwo), new Integer(2));
        boutons.put((ImageButton) findViewById(R.id.btnThree), new Integer(3));
        boutons.put((ImageButton) findViewById(R.id.btnFour), new Integer(4));
        boutons.put((ImageButton) findViewById(R.id.btnFive), new Integer(5));
        boutons.put((ImageButton) findViewById(R.id.btnSix), new Integer(6));
        boutons.put((ImageButton) findViewById(R.id.btnSeven), new Integer(7));
        boutons.put((ImageButton) findViewById(R.id.btnEight), new Integer(8));
        boutons.put((ImageButton) findViewById(R.id.btnNine), new Integer(9));

        for (ImageButton ib : boutons.keySet()) {
            ib.setOnClickListener(this);
        }

        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.txtSaisie)).setText("");
                if ("trouvé !!!".equals(((Button) findViewById(R.id.btnReset)).getText().toString())) {
                    randomNumber = (int) (Math.random() * (99 - 1) + 1);
                    ((Button) findViewById(R.id.btnReset)).setText("?");
                    ((Button) findViewById(R.id.btnReset)).setTextColor(Color.BLACK);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void onClick(View v) {
        int value = boutons.get((ImageButton) v).intValue();
        Log.d("onclick", "Valeur du bouton appuyé : "+ value);

        // changement du contenu de la saisie
        TextView txtSaisie = (TextView) findViewById(R.id.txtSaisie);
        if (txtSaisie.getText().length() == 0 || txtSaisie.getText().length() >= 2) {
            // nouvelle saisie
            txtSaisie.setText("" + value);
        } else {
            // saisie 2ème chiffre
            txtSaisie.setText(txtSaisie.getText().toString() + value);
        }

        // comparer la saisie avec le nombre aléatoire
        int saisie =  Integer.parseInt(txtSaisie.getText().toString());
        Button btnReset = (Button) findViewById(R.id.btnReset);
        if (saisie == randomNumber) {
            btnReset.setText("trouvé !!!");
            btnReset.setTextColor(Color.GREEN);
        } else if (saisie > randomNumber) {
            btnReset.setText("-" + saisie);
            btnReset.setTextColor(Color.RED);
        } else if (saisie < randomNumber) {
            btnReset.setText("+" + saisie);
            btnReset.setTextColor(Color.BLUE);
        }
    }
}
