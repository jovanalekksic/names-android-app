package com.jovanaleksic.apiimena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView stats;
    EditText drzava;
    ImageView slika;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stats = findViewById(R.id.prikazPodataka);
        drzava = findViewById(R.id.ime);
        slika = findViewById(R.id.slika);
        progressBar = findViewById(R.id.progressBar);
    }

    public void search(View view) {
        Statistike statistike = new Statistike();
        statistike.execute();
    }

    public class Statistike extends AsyncTask<String,Integer, JSONObject>{


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject json;
            try {

                URL url = new URL("https://api.genderize.io/?name="+drzava.getText()+"&country_id=us");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                publishProgress(20);

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responce = "";
                publishProgress(60);

                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){

                    responce = br.readLine();
                    json = new JSONObject(responce);
                    publishProgress(100);
                    return json;
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            JSONObject js=new JSONObject();
            if(jsonObject == null){
                Toast.makeText(MainActivity.this,"Pogresan zahtev",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    String drzavaKod = "";
                    String vrvt = "";
                    String pol ="";
                    String ime ="";
                    String count="";

                    String name= js.getString("name");
                    String gender= js.getString("gender");
                    String verovatnoca = js.getString("probability");
                    String broj = js.getString("count");
                    String kodZemlje = js.getString("country_id");



                    ime+=name+",";
                    pol+=gender+",";
                    vrvt+=verovatnoca+",";
                    count+=broj+",";
                    drzavaKod+=kodZemlje+",";


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String text = "";

                try {
                    text+="Atributi za trazeno ime "+jsonObject.getString("name")+" su:\nPol: "
                            +jsonObject.getString("gender")+"\nVerovatnoca: "
                            +jsonObject.getString("probability")+"\nBroj: "
                            +jsonObject.getString("count")+"\nKod zemlje: "
                            +jsonObject.getString("country_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stats.setText(text);
                Picasso.get().load("https://www.chronic-wanderlust.com/wp-content/uploads/elementor/thumbs/USA-an-was-ich-mich-nicht-gewo%CC%88hnen-kann-nswyj8rogw4dyjgy8ca9xjl2yyd57wcjpduze0kbjk.jpg").into(slika);

            }



        }
    }
    public void otvoriDrugiActivity(View view) {


        Intent i = new Intent(this,SecondActivity.class);
        startActivity(i);


    }
}