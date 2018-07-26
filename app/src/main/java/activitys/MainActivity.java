package activitys;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abyte.proyectoii.R;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvRegistrese;
    Button btnIngresar;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRegistrese =(TextView) findViewById(R.id.tvRegistrar);

        tvRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);

            }
        });


        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtusu= (EditText) findViewById(R.id.txtUsuario);
                EditText txtpas= (EditText) findViewById(R.id.txtPassword);
                try{
                    Cursor cursor =  helper.ConsultarUsuPas(txtusu.getText().toString(), txtpas.getText().toString());

                    if (cursor.getCount()>0){
                        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario o Correo Incorrecto",Toast.LENGTH_LONG).show();
                    }
                    txtusu.setText("");
                    txtpas.setText("");
                    txtusu.findFocus();

                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });
    }
}
