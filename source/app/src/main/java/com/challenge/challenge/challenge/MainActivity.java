package com.challenge.challenge.challenge;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    httpcon httpc;
    ArrayList<MyItem> ar=new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        progressBar= (ProgressBar)findViewById(R.id.progressBar);
        httpc=new httpcon();
        httpc.execute();



    }

    class httpcon extends AsyncTask<Object,Object,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            if(s!=null)
            {
                Log.d("sss",s);
                try {
                    JSONArray jsonArray=new JSONArray(s);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getJSONObject("commit").getJSONObject("author").optString("name");
                        String message=jsonObject.getJSONObject("commit").optString("message");
                        ar.add(new MyItem(""+i,name,"Commit :"+jsonObject.getString("sha"),message));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyAdapter myAdapter=new MyAdapter(MainActivity.this,ar);
                listView.setAdapter(myAdapter);



            }

        }

        @Override
        protected String doInBackground(Object... objects) {
            try {
                URL url=new URL("https://api.github.com/repos/rails/rails/commits");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer=new StringBuffer();
                String  line;
                while((line=bufferedReader.readLine())!=null)
                    stringBuffer.append(line);

                return stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
