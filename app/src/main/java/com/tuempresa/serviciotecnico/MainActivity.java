package com.tuempresa.serviciotecnico;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDbHelper;
    EditText etCustomerName, etDescription, etTechnician;
    Button btnSave, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new DatabaseHelper();
        etCustomerName = findViewById(R.id.etCustomerName);
        etDescription = findViewById(R.id.etDescription);
        etTechnician = findViewById(R.id.etTechnician);
        btnSave = findViewById(R.id.btnSave);
        btnSend = findViewById(R.id.btnSend);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDbHelper.insertReport(
                        etCustomerName.getText().toString(),
                        etDescription.getText().toString(),
                        etTechnician.getText().toString()
                );
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Informe guardado exitosamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar el informe", Toast.LENGTH_LONG).show();
                }
            }
        });

        //envío de SMS

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "3178427961"; // Número del destinatario
                String message = "Aviso de Mantenimiento: " + etCustomerName.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(MainActivity.this, "Mensaje enviado", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error al enviar el mensaje", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbHelper.closeConnection();
    }
}

