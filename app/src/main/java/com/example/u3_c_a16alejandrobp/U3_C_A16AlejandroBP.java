package com.example.u3_c_a16alejandrobp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class U3_C_A16AlejandroBP extends Activity {

    // Usado por si necesitamos diferentes permisos, para identificar cual de ellos es
    private final int CODIGO_IDENTIFICADOR=1;

    private void chamarTelefono(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:123456789"));
        startActivity(callIntent);
    }

    private void xestionarEventos(){

        Button boton = (Button)findViewById(R.id.btnChamar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT>=23){
                    int permiso = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if (permiso == PackageManager.PERMISSION_GRANTED){
                        chamarTelefono();
                    }
                    else{
                        U3_C_A16AlejandroBP.this.requestPermissions( new String[]{Manifest.permission.CALL_PHONE},CODIGO_IDENTIFICADOR);
                    }

                }
                else{
                    int permiso = checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE);
                    if (permiso == PackageManager.PERMISSION_GRANTED){
                        chamarTelefono();
                    }
                }
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        xestionarEventos();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CODIGO_IDENTIFICADOR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chamarTelefono();
                } else {

                    Toast.makeText(this,"É NECESARIO O PERMISO PARA CHAMAR POR TELÉFONO",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_telefono_carrion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}