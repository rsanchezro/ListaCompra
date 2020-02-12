package com.roberto.listacompra;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        //Floating action BUTTON PARA AÑADIR UNA NUEVA LISTA DE LA COMPRA
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NUEVA LISTA DE LA COMPRA
                //Abrir dialogo para añadir una nuevo amigo a la lista de amigos
                final EditText nombre = new EditText(MainActivity.this);
                nombre.setHint("Nombre");
                final EditText email = new EditText(MainActivity.this);
                email.setHint("Email");
                LinearLayout mi_layout = new LinearLayout(MainActivity.this);
                mi_layout.setOrientation(LinearLayout.VERTICAL);
                mi_layout.addView(nombre);
                mi_layout.addView(email);
                AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);

                builderDialog.setTitle("NUEVA LISTA DE LA COMPRA ");
                //De esta forma evitamos que al pulsar el boton de atras salgamos del cuadro de dialogo
                builderDialog.setCancelable(false);
                builderDialog.setView(mi_layout);
                builderDialog.setPositiveButton("AÑADIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Sobrescribo para no hacer nada, para validar la entrada y controllar
                        //que el usuario introduzca texto
                    }
                });
                builderDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialogo = builderDialog.create();
                //Para evitar salir del cuadro de dialogo
                dialogo.setCanceledOnTouchOutside(false);
                dialogo.show();
                //Sobrescribo la acción OnClick del boton positivo para controlar la entrada
                dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Compruebo si el usuario ha introducido un texto
                        if (nombre.getText().toString().compareTo("") == 0) {
                            Toast.makeText(getActivity(), "DEBES INTRODUCIR NOMBRE DEL MIEMBRO", Toast.LENGTH_SHORT).show();
                        } else {
                            //COMPRUEBO SI EL Grupo ya existe
                            String n = nombre.getText().toString();

                            if (MainActivity.adaptador.getElementoLista(posicion).buscarMiembro(n)) {
                                Toast.makeText(getActivity(), "LO SIENTO PERO " + n + " YA EXISTE EN ESE GRUPO", Toast.LENGTH_SHORT).show();
                            } else {
                                //Añado un elemento a la lista
                                MainActivity.adaptador.getElementoLista(posicion).añadir_Miembro(new Miembro(n, email.getText().toString(), null));
                                // adaptador.añadir_elementoLista(new Miembro(n,email.getText().toString(),null));
                                adaptador.setElementosLista(MainActivity.adaptador.getElementoLista(posicion).getMiembros());
                                //Cierro el cuadro de dialogo
                                dialogo.dismiss();
                            }
                        }
                    }
                });
            }

        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_listacompra,R.id.nav_productos, R.id.nav_tiendas)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
