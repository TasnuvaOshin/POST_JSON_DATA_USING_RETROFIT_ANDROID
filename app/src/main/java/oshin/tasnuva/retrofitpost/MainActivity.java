package oshin.tasnuva.retrofitpost;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import oshin.tasnuva.retrofitpost.Connection.RetrofitConnection;
import oshin.tasnuva.retrofitpost.DataModel.DataSet;
import oshin.tasnuva.retrofitpost.RetrofitInterface.OurRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText EditTextName, EditTextHobby;
    private String name, hobby;
    private TextView tresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //we are calling our client

        //find all the xml layout file view
        EditTextName = findViewById(R.id.name);
        EditTextHobby = findViewById(R.id.hobby);
        tresponse = findViewById(R.id.response);

    }

    public void PostRequestCall(View view) {
        //getting the value from edit text
        name = EditTextName.getText().toString();
        hobby = EditTextHobby.getText().toString();


        DataSet dataSet = new DataSet(name, hobby);
        OurRetrofit client = RetrofitConnection.GetOurRetrofit(OurRetrofit.class);
        //this dataset is our object for posting data
        Call<DataSet> call = client.SendPostRequest(dataSet);
        call.enqueue(new Callback<DataSet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<DataSet> call, Response<DataSet> response) {

                if (response.body() != null) {

                    String name = response.body().getJson().getName();
                    String hobby = response.body().getJson().getHobby();
                    tresponse.setText("your name is" + name + " And Your Hobby is" + hobby);
                } else {

                    tresponse.setText("no response come");
                }
            }

            @Override
            public void onFailure(Call<DataSet> call, Throwable t) {
                Log.i("Error", "Error while calling enqueue");
            }
        });

    }
}
