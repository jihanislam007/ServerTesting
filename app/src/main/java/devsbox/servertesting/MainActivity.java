package devsbox.servertesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

TextView show_name;
TextView show_address;
    String nam = "";
    String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   String myURL = "http://mrubel.com/tuntuninews/api/gettingnews.php";
      //  String myURL = "http://servertesting.devsbox.com/Info_data.php";

        show_name = (TextView) findViewById(R.id.showName);
        show_address = (TextView) findViewById(R.id.showAddress);

        fatchingData();
    }

    void fatchingData(){

        String myURL = "http://servertesting.devsbox.com/Info_data.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);

                        nam =nam+ jsonObject.getString("name")+"\n\n";
                        address =address+ jsonObject.getString("Address")+"\n\n\n";

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                show_name.setText(nam);
                show_name.setText(address);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log",error);
            }
        });

        devsbox.servertesting.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplication(),"Data Loaded ...." , Toast.LENGTH_LONG).show();

    }

}