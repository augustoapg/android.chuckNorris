/**
 * Project: Midterm Exam
 * Author: Augusto A P Goncalez
 * Date: Oct. 07, 2019
 *
 * Project Description:
 * This app has an activity (APIActivity) that gets information from an API to display quotes from
 * Chuck Norris, together with an image and the created date of that quote. Another activity
 * called memeGenerator allows the user to click a button to take a picture, which will then show
 * in an ImageView in the Activity.
 */

package sheridan.araujope.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mQuotesButton;
    private Button mMemesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuotesButton = findViewById(R.id.btnQuotes);
        mMemesButton = findViewById(R.id.btnMakeMemes);

        mQuotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, APIActivity.class);
                startActivity(intent);
            }
        });

        mMemesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, memeGenerator.class);
                startActivity(intent);
            }
        });
    }
}
