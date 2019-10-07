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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class memeGenerator extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button mSelectImageButton;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_generator);

        mSelectImageButton = findViewById(R.id.btnClickImage);
        mImageView = findViewById(R.id.imageView);

        addImageButtonHandler();
    }

    private void addImageButtonHandler() {
        mSelectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
