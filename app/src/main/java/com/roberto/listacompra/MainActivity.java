package com.roberto.listacompra;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roberto.listacompra.ViewModel.ListaCompraViewModel;
import com.roberto.listacompra.modelo.Lista_Compra;
import com.roberto.listacompra.modelo.Producto;
import com.roberto.listacompra.modelo.Tienda;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ListaCompraViewModel listaCompraViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        Cargamos la base de datos desde los archivos JSON

         */
        //RECUERDO QUE ESTA INSTANCIACIÓN DEL VIEWMODEL SE REALIZA ASI, Y NO DIRECTAMENTE
        //DESDE EL CONSTRUCTOR PORQUE LO QUE HABILITA ESTO ES QUE POSTERIORES LLAMADAS
        //A ESTA INSTANCIACION NOS DEVUELVA LA INSTANCIA QUE EXISTIA DEL VIEWMODEL
        //RECORDAD EL CICLO DE VIDA DE UN VIEWMODEL, QUE EXTIENDE AL DE LA ACTIVITY
        listaCompraViewModel = new ViewModelProvider(this).get(ListaCompraViewModel.class);


        //Voy a añadir un observador al ViewModel para poder rellenar la tabla productos
        //Es necesario hacerlo en un observador porque no puedo acceder directamente al
        //LIVEDATA, es decir cuando intento acceder al LIVEDATA, al ser un acceso
        /* asincrono, no sé cuando se va a acceder a el, por lo tanto al definir un observador
        cuando se observen cambios en el Livedata (es decir cuando se cargue la base de datos)
        se ejecuta el metodo onchange, y es ahi donde verifico el numero de registros de productos
        si es 0 cargo del fichero JSON
        AL FINALIZAR EL PROCESO ELIMINO EL OBSERVER, PORQUE YA NO ME VA A INTERESAR OBSERVAR
        DESDE AQUI
         */
        listaCompraViewModel.getProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                //Se invoca cuando se intentan obtener los productos
                if(productos.size()==0)
                {
                    try {
                        String datos=leerFichero(getAssets().open("productos.json"));
                        //Preparo el objeto gson
                        TypeToken<List<Producto>> tk;
                        ArrayList<Producto> lista_productos;
                        Gson gson =new Gson();
                        //Necesario generar una clase anonima que extienda de TypeToken pero parametrizada
                        tk=new TypeToken<List<Producto>>(){};
                        //Convierto el String que representa un Json a un ArrayList de Productos
                        lista_productos=gson.fromJson(datos,tk.getType());

                        listaCompraViewModel.insertar_productos(lista_productos);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Cuando finalizo elimino el observador
                listaCompraViewModel.getProductos().removeObserver(this);
            }
        });
        listaCompraViewModel.getProductos();

        //Si no hay productos, se carga la base de datos de productos, en concreto
            //la tabla productos,






        //Hacemos lo mismo para las tiendas

        listaCompraViewModel.getTiendas().observe(this, new Observer<List<Tienda>>() {
                    @Override
                    public void onChanged(List<Tienda> tiendas) {
                        if(tiendas.size()==0)
                        {
                            try {
                                String datos=leerFichero(getAssets().open("tiendas.json"));
                                //Preparo el objeto gson
                                TypeToken<List<Tienda>> tk;
                                ArrayList<Tienda> lista_tiendas;
                                Gson gson =new Gson();
                                //Necesario generar una clase anonima que extienda de TypeToken pero parametrizada
                                tk=new TypeToken<List<Tienda>>(){};
                                //Convierto el String que representa un Json a un ArrayList de Productos
                                lista_tiendas=gson.fromJson(datos,tk.getType());

                                listaCompraViewModel.insertar_tiendas(lista_tiendas);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });









        FloatingActionButton fab = findViewById(R.id.fab);
        //Floating action BUTTON PARA AÑADIR UNA NUEVA LISTA DE LA COMPRA
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NUEVA LISTA DE LA COMPRA
                //Abrir dialogo para añadir una nuevo amigo a la lista de amigos
                final EditText nombre = new EditText(MainActivity.this);
                nombre.setHint("Nombre");
                LinearLayout mi_layout = new LinearLayout(MainActivity.this);
                mi_layout.setOrientation(LinearLayout.VERTICAL);
                mi_layout.addView(nombre);
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
                            Toast.makeText(MainActivity.this, "DEBES INTRODUCIR NOMBRE DE LA LISTA", Toast.LENGTH_SHORT).show();
                        } else {
                            //COMPRUEBO SI EL Grupo ya existe
                            String n = nombre.getText().toString();
                            Date fecha= Calendar.getInstance().getTime();

                                //Añado un elemento a la lista
                            Lista_Compra lista_compra=new Lista_Compra(fecha,n);
                        listaCompraViewModel.insert_listacompra(lista_compra);
                                //Cierro el cuadro de dialogo
                                dialogo.dismiss();
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

    /**
     * Metodo que recibe un fichero como InputStream y devuelve el contenido como String
     * @param fichero
     * @return
     */
    private String leerFichero(InputStream fichero)
    {
        StringBuilder cadena=new StringBuilder("");


            try {
                //Ahora leo del fichero
                BufferedReader entrada=new BufferedReader(new InputStreamReader(fichero));
                String linea;
                linea=entrada.readLine();
                while(linea!=null)
                {
                    cadena.append(linea);
                    linea=entrada.readLine();
                }
                //Cierro el fichero
             fichero.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return cadena.toString();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
