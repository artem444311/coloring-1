package de.frype.coloring.picture_gallery;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import de.frype.coloring.R;

/**
 * A picture gallery (book specific). Sorted by page and date. Ability to show stored images, delete images and
 * share images.
 */
public class PictureGalleryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_gallery);

        // back button action: go back
        ImageButton imageButton = findViewById(R.id.backButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // view button action:
        imageButton = findViewById(R.id.viewButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO show the currently selected image in full screen
            }
        });

        // if the settings say so, remove the share and delete button from the gallery view
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // share button action
        imageButton = findViewById(R.id.shareButton);
        if (!sharedPref.getBoolean("setting_sharing_allowed", true)) {
            ((RelativeLayout) imageButton.getParent()).removeView(imageButton);
        } else {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO share the currently selected image
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("image/png");
                    // sharingIntent.putExtra(Intent.EXTRA_STREAM, _newImageUri);
                    // startActivity(Intent.createChooser(sharingIntent, getString( R.string.dialog_share )));
                }
            });
        }

        // delete button action: delete the currently selected image
        imageButton = findViewById(R.id.deleteButton);
        if (!sharedPref.getBoolean("setting_deletion_allowed", true)) {
            ((RelativeLayout) imageButton.getParent()).removeView(imageButton);
        } else {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO not yet implemented
                }
            });
        }

        // TODO viewanimator or grid view as before?
    }
}
