package iut.montpellier.androidprojects;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText txtNumber;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar pgbScore;
    private TextView lblHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Le xml a était lus et traité

        txtNumber = (EditText) findViewById(R.id.txtNumber); // Classe qui dérive de la classe Viw (text, progress bar, boutton...)
        btnCompare = (Button) findViewById(R.id.btnCompare);
        lblResult = (TextView) findViewById(R.id.lblResult);
        pgbScore = (ProgressBar) findViewById(R.id.pgbScore);
        lblHistory = (TextView) findViewById(R.id.lblHistory);

        btnCompare.setOnClickListener(btnCompareLister); // Déclanché onClick() on clique sur le boutton btnCompare

        init();

    }
    private void init()

    {

    }

    private View.OnClickListener btnCompareLister = new View.OnClickListener() // Classe anonyme
    {
        @Override
        public void onClick(View v)
        {
            Log.i("DEBUG", "Bouton cliqué");  // Classe android qui permet de faire des logs pour suivre l'activité du programme

            String txtNumber = txtNumber.getText().toString();
            if (txtNumber.equals('"')) return;

            int enteredValue = Integer.parseInt(txtNumber);
            if (enteredValue == searchedValue){
                congratulation();
            }
            else if (enteredValue < searchedValue){
            lblResult.setText(R.string.strGreater);
        }
    };
















}