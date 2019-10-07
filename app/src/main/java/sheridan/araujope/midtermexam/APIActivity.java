package sheridan.araujope.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ImageView mImgChuckNorris;
    private TextView mQuote;
    private TextView mCreatedAt;
    private Button mGenerateButton;
    private Spinner mCategory;
    private String[] categories = new String[]{"animal", "career", "celebrity", "dev", "explicit",
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
                if (mCategory.getSelectedItem() != null) {
                    getQuote();
                } else {
                    Toast toast = Toast.makeText(APIActivity.this,
                            getString(R.string.categoryToast), Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void getQuote() {

    }
}
