package com.clone.whats.admin.whatsclone.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.clone.whats.admin.whatsclone.R;
import com.clone.whats.admin.whatsclone.helper.Preferencias;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class LoginActivity extends Activity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText ddd;
    private Button cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone  = (EditText) findViewById(R.id.edit_telefone);
        codPais   = (EditText) findViewById(R.id.editTextArea);
        ddd       = (EditText) findViewById(R.id.editTextDDD);
        cadastrar = (Button) findViewById(R.id.btCadastrar);


        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener( maskTelefone );

        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        MaskTextWatcher maskArea = new MaskTextWatcher(codPais, simpleMaskCodPais);
        codPais.addTextChangedListener( maskArea );

        SimpleMaskFormatter simpleMaskddd= new SimpleMaskFormatter("(NN)");
        MaskTextWatcher maskDDD = new MaskTextWatcher(ddd, simpleMaskddd);
        ddd.addTextChangedListener(maskDDD);



        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                        codPais.getText().toString() +
                        ddd.getText().toString() +
                        telefone.getText().toString();

               String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
               telefoneSemFormatacao = telefoneSemFormatacao.replace("(", "");
               telefoneSemFormatacao = telefoneSemFormatacao.replace(")", "");
               telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");

               Log.i("telefoneCompleto", "T:" + telefoneCompleto);
               Log.i("telefoneSemFormatacao", "T:" + telefoneSemFormatacao);

                Random numeroAleat = new Random();
                int numeroRandomico = numeroAleat.nextInt( 999 - 1000) + 1000;

                String token = String.valueOf(numeroRandomico);


                Preferencias preferences = new Preferencias(LoginActivity.this);
                preferences.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                HashMap<String, String> usuario = preferences.getDadosUsuario();
                Log.i("TOKEN", "NOME:" + usuario.get("nome") + "FONE: " + usuario.get("telefone"));

            }
        });




    }
}
