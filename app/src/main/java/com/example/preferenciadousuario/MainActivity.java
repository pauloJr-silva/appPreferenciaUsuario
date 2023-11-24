    package com.example.preferenciadousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

    public class MainActivity extends AppCompatActivity {

    Button btnSalvar;
    EditText edtNome;
    TextView txtResultado;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        edtNome = findViewById(R.id.edtNome);
        txtResultado = findViewById(R.id.txtResultado);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
               public void onClick(View view) {

                //salvar nas preferencias do usuario (no aparelho do usuario

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                SharedPreferences.Editor editor = preferences.edit();

                if (edtNome.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(),
                            "Prencher o campo nome",Toast.LENGTH_LONG).show();
                }else {
                    //salvar no celular
                    String nome = edtNome.getText().toString();
                    editor.putString("nome",nome);
                    editor.commit();
                    txtResultado.setText("Olá, " + nome);
                }
            }
        });

        //recuperar arquivo salvo
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        // validar se temos o nome em preferencias
        if (preferences.contains("nome")){
                String nome = preferences.getString("nome","olá, usuário não definido");
                txtResultado.setText("olá, " + nome);
        }else {
            txtResultado.setText("olá, usuário não definido");
        }

    }
}