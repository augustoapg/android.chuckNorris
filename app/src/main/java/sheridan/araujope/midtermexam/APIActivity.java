package sheridan.araujope.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class APIActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ImageView mImgChuckNorris;
    private TextView mQuote;
    private TextView mCreatedAt;
    private Button mGenerateButton;
    private Spinner mCategory;
    private String[] categories = new String[]{"", "animal", "career", "celebrity", "dev", "explicit",
            "fashion", "food", "history", "money", "movie", "music", "political", "religion",
            "science", "sport", "travel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        mQueue = Volley.newRequestQueue(this);
        mImgChuckNorris = findViewById(R.id.imgChuckNorris);
        mQuote = findViewById(R.id.txtQuote);
        mCreatedAt = findViewById(R.id.txtCreatedAt);
        mGenerateButton = findViewById(R.id.btnGenerate);
        mCategory = findViewById(R.id.categories);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        mCategory.setAdapter(adapter);

        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCategory.getSelectedItem() != null &&
                        !mCategory.getSelectedItem().toString().isEmpty()) {
                    getQuote(mCategory.getSelectedItem().toString());
                } else {
                    Toast toast = Toast.makeText(APIActivity.this,
                            getString(R.string.categoryToast), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void getQuote(String category) {
        String url = getString(R.string.httphost) + category;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject chuckNorrisJSON = response;
                            String quote = chuckNorrisJSON.getString("value");
                            String createdAt = chuckNorrisJSON.getString("created_at");
                            String imageUrl = chuckNorrisJSON.getString("icon_url");

                            // NOTE: I am getting the imageUrl from the JSON, but I was not able to
                            // display the image based on the URL
//                            URL url = new URL(imageUrl);
//                            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection()
//                                    .getInputStream());
//                            mImgChuckNorris.setImageBitmap(bmp);

                            mQuote.setText(quote);
                            mCreatedAt.setText(createdAt);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
