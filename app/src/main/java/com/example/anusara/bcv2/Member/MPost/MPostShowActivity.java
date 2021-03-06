package com.example.anusara.bcv2.Member.MPost;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anusara.bcv2.DataManager.DataAccountManager;
import com.example.anusara.bcv2.Member.MComment.CommentActivity;
import com.example.anusara.bcv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MPostShowActivity extends AppCompatActivity {
    String id,name,content,date;
    TextView text1,messege,dateshow;
    Button add;
    ListView listView;
    String user;

    ArrayList<String> listid = new ArrayList<>();
    ArrayList<String> listname = new ArrayList<>();
    ArrayList<String> listcontent = new ArrayList<>();
    ArrayList<String> listdate = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpost_show);

        id = getIntent().getStringExtra("p_id");

        Log.e("onCreate: ", id+"");

        user = DataAccountManager.getInstance().getUsername();

        listView = (ListView) findViewById(R.id.listView2);
        add = (Button)findViewById(R.id.addcomment);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.addcomment) {
                    Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                    intent.putExtra("p_id",id);
                    intent.putExtra("username",user);
//                    startActivityForResult(intent, 1);
                    startActivityForResult(intent, 1);

                }

            }
        });

        getList();

        adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, listname);
        listView.setAdapter(adapter);

        name = getIntent().getStringExtra("name");
        content = getIntent().getStringExtra("content");
        date = getIntent().getStringExtra("date");

        text1 = (TextView) findViewById(R.id.textView11);
        text1.setText(name);

        messege = (TextView) findViewById(R.id.messege);
        messege.setText(content);

        dateshow = (TextView) findViewById(R.id.useradd);
        dateshow.setText(date);

    }

    private void getList() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                getHttp http = new getHttp();
                String response = null;

                try {

//                    response = http.run("http://192.168.43.180/breast-cancer/postcomment.php");
//                    response = http.run("http://10.10.11.105/breast-cancer/postcomment.php");
//                    response = http.run("http://192.168.43.180/breast-cancer/postcomment.php");
                    //response = http.run("http://192.168.1.33/breast-cancer/postcomment.php");
                    response = http.run("http://192.168.1.35/breast-cancer/postcomment.php");
//                    response = http.run("http://192.168.1.5/breast-cancer/postcomment.php");
//                    response = http.run("http://192.168.1.37/breast-cancer/postcomment.php");
//                    response = http.run("http://172.19.237.81/breast-cancer/postcomment.php");
//                    response = http.run("http://192.168.1.2/breast-cancer/postcomment.php");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                try {
                    listname.clear();

                    JSONArray jsonArray = new JSONArray(string);

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject json_data = jsonArray.getJSONObject(i);
                        listname.add(i, json_data.getString("c_message"));
                        listcontent.add(i, json_data.getString("username"));
                        Log.e( "json_data: ", json_data.getString("c_message"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //adapter.notifyDataSetChanged();

                adapter.notifyDataSetChanged();
                Log.e( "onPostExecute: ", string);
            }
        }.execute();
    }
    public class getHttp {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("p_id", id)
                .build();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            Log.e("onActivityResult: ", "doo");
            //adapter.notifyDataSetChanged();
            getList();
            //finish();


        }
    }

}
