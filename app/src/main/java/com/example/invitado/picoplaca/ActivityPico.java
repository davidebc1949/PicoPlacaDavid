package com.example.invitado.picoplaca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static com.example.invitado.picoplaca.R.id.parent;

public class ActivityPico extends AppCompatActivity {

    private Spinner num;
    private TextView tv_resul;
    private TextView tv_pico;
    private TextView tv_placa;
    private TextView tv1;
    private static final int[] FESTIVO;
    static{
        FESTIVO = new int[]{9, 79, 103, 104, 121, 149, 170, 177, 184, 201, 219, 233, 289, 310, 317, 342, 259};
    }
    private static int[] pico1;
    private static int[] pico2;
    private static int[] pico3;
    private static int[] pico4;
    private static int[] pico5;
    private static final String[] PYP = {"4 - 5", "6 - 7", "8 - 9", "0 - 1", "2 - 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pico);
        tv_resul = (TextView) findViewById(R.id.tv_resul);
        tv_pico = (TextView) findViewById(R.id.tv_pico);
        tv_placa = (TextView) findViewById(R.id.tv_placa);
        tv1 = (TextView) findViewById(R.id.tv1);

        num = (Spinner) findViewById(R.id.num);
        String[] opciones = {"0-1", "2-3", "4-5", "6-7", "8-9"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        num.setAdapter(adapter);


        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        int i = prefe.getInt("placa_pos", 0);

        num.setSelection(i);
        num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id) {
                SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("placa", parent.getItemAtPosition(pos).toString());
                editor.putInt("placa_pos", pos);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        String picoYplacaHoy;
        pico();
        Calendar localCalendar = Calendar.getInstance();
        int diaanio = localCalendar.get(Calendar.DAY_OF_YEAR);
        int diasemana = localCalendar.get(Calendar.DAY_OF_WEEK);

        if (Arrays.asList(FESTIVO).contains(diaanio) || diasemana == 1 || diasemana == 7) {// si es festivo o sabado o domingo
            picoYplacaHoy = "NO APLICA";
        } else {
            int j = diaanio;
            while (j > 7) {
                j = localCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ? j - 11 : j - 6;//si es viernes resta 11 y si es otro dia resta 6
                localCalendar.set(Calendar.DAY_OF_YEAR, j);// cambia la fecha a la fecha restada
            }
            picoYplacaHoy = PYP[localCalendar.get(Calendar.DAY_OF_YEAR) - 2];//se resta dos porque lunes comenzo el dia 2
        }
        tv1.setText(" " + picoYplacaHoy + " ");
        //próximo pico y placa ... solo cuando el mismo dia corresponde con el pico y placa
        localCalendar.set(Calendar.DAY_OF_YEAR, diaanio);//vuelve al dia actual
    }

    public void calcular(View view) {
        String picoYplacaHoy;
        pico();
        Calendar localCalendar = Calendar.getInstance();
        int diaanio = localCalendar.get(Calendar.DAY_OF_YEAR);
        int diasemana = localCalendar.get(Calendar.DAY_OF_WEEK);

        if (Arrays.asList(FESTIVO).contains(diaanio) || diasemana == 1 || diasemana== 7) {// si es festivo o sabado o domingo
            picoYplacaHoy = "NO APLICA";
        } else {
            int j = diaanio;
            while (j > 7) {
                j = localCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ? j - 11 : j - 6;//si es viernes resta 11 y si es otro dia resta 6
                localCalendar.set(Calendar.DAY_OF_YEAR, j);// cambia la fecha a la fecha restada
            }
            picoYplacaHoy = PYP[localCalendar.get(Calendar.DAY_OF_YEAR) - 2];//se resta dos porque lunes comenzo el dia 2
        }
        tv1.setText(" " + picoYplacaHoy + " ");
        //próximo pico y placa ... solo cuando el mismo dia corresponde con el pico y placa
        localCalendar.set(Calendar.DAY_OF_YEAR, diaanio);//vuelve al dia actual
    }


    public void pico(){
        pico1 = new int[]{5,11,17,23,34,40,46,52,58,69,75,81,87,93,110,116,122,128,139,145,151,157,163,174,180,186,192,198,209,215,221,227,244,250,256,262,268,279,285,291,297,303,314,320,326,332,338,349,355,361};
        pico2 = new int[]{6,12,18,24,30,41,47,53,59,65,76,82,88,94,100,111,117,123,129,135,146,152,158,164,181,187,193,199,205,216,222,228,234,240,251,257,263,269,275,286,292,298,304,321,327,333,339,345,356,362};
        pico3 = new int[]{2,13,19,25,31,37,48,54,60,66,72,83,89,95,101,107,118,124,130,136,142,123,158,165,171,177,188,194,200,206,212,223,229,235,241,247,258,264,270,276,282,293,299,305,311,328,334,340,346,352,363};
        pico4 = new int[]{3,20,26,32,38,44,55,61,67,73,90,96,102,108,114,125,131,137,143,160,166,172,178,195,207,213,230,236,242,248,254,265,271,277,283,300,306,312,318,324,335,341,347,353};
        pico5 = new int[]{4,10,16,27,33,39,45,51,62,68,74,80,86,97,109,115,132,138,144,150,156,167,173,179,185,191,202,208,214,220,226,237,243,249,255,261,272,278,284,290,296,307,313,319,325,331,348,354,360};

        Calendar calendario = Calendar.getInstance();
        int diaanio = calendario.get(Calendar.DAY_OF_YEAR);
        String selec=num.getSelectedItem().toString();

        if (selec.equals("0-1")) {

            for (int i = 0; i < pico1.length; i++)
            {
                int var = pico1[i];
                if (diaanio == var)
                {

                    tv_resul.setText("Tienes pico y placa");


                }else if(diaanio>var){
                    tv_resul.setText("No Tienes pico y placa");
                    int j = i+1;
                    if (diaanio>pico1[i]){
                        calendario.set(6,pico1[j]);
                        Date fecha = calendario.getTime();
                        SimpleDateFormat formato = new SimpleDateFormat("E dd/MM/yyyy");
                        String dia= formato.format(fecha);
                        tv_pico.setText("Su proximo dia: "+dia);

                    }
                }

            }
        }

        if(selec.equals("2-3")){
            for(int j=0;j<pico2.length;j++){
                int var1 = pico2[j];
                if(diaanio==var1){
                    tv_resul.setText("Tienes pico y placa");

                }else if (diaanio>var1){
                    tv_resul.setText("No tienes pico y placa");
                    int d = j+1;
                    if (diaanio>pico2[j]){
                        calendario.set(6,pico2[d]);
                        Date fecha = calendario.getTime();
                        SimpleDateFormat formato = new SimpleDateFormat("E dd/MM/yyyy");
                        String dia= formato.format(fecha);
                        tv_pico.setText("Su proximo dia: "+dia);

                    }
                }
            }
        }


        if(selec.equals("4-5")){
            for(int j=0;j<pico3.length;j++){
                int var2 = pico3[j];
                if(diaanio==var2){
                    tv_resul.setText("Tienes pico y placa");

                }else if (diaanio>var2){
                    tv_resul.setText("No tienes pico y placa");
                    int t = j+1;
                    if (diaanio>pico3[j]){
                        calendario.set(6,pico3[t]);
                        Date fecha = calendario.getTime();
                        SimpleDateFormat formato = new SimpleDateFormat("E dd/MM/yyyy");
                        String dia= formato.format(fecha);
                        tv_pico.setText("Su proximo dia: "+dia);
                    }
                }
            }
        }

        if(selec.equals("6-7")){
            for(int j=0;j<pico4.length;j++){
                int var2 = pico4[j];
                if(diaanio==var2){
                    tv_resul.setText("Tienes pico y placa");

                }else if (diaanio>var2){
                    tv_resul.setText("No tienes pico y placa");
                    int c = j+1;
                    if (diaanio>pico4[j]){
                        calendario.set(6,pico4[c]);
                        Date fecha = calendario.getTime();
                        SimpleDateFormat formato = new SimpleDateFormat("E dd/MM/yyyy");
                        String dia= formato.format(fecha);
                        tv_pico.setText("Su proximo dia: "+dia);
                    }
                }
            }
        }

        if(selec.equals("8-9")){
            for(int j=0;j<pico5.length;j++){
                int var2 = pico5[j];
                if(diaanio==var2){
                    tv_resul.setText("Tienes pico y placa");

                }else if (diaanio>var2){
                    tv_resul.setText("No tienes pico y placa");
                    int e = j+1;
                    if (diaanio>pico5[j]){
                        calendario.set(6,pico5[e]);
                        Date fecha = calendario.getTime();
                        SimpleDateFormat formato = new SimpleDateFormat("E dd/MM/yyyy");
                        String dia= formato.format(fecha);
                        tv_pico.setText("Su proximo dia: "+dia);
                    }
                }
            }
        }
    }
    public void ver(View v) {
        Intent i=new Intent(this,AcercaDe.class);
        startActivity(i);
    }
}