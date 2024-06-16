package wafoot.becoming.wafoot;

import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageSelector {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private final Activity activity;
    private final ImageView imageView;
    private String currentPhotoPath;

    public ImageSelector(Activity activity, ImageView imageView) {
        this.activity = activity;
        this.imageView = imageView;
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectImageFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void selectImageFromGallery() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            loadImageToImageView();
        } else if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            currentPhotoPath = selectedImageUri.getPath();
            loadImageToImageView();
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        assert storageDir != null;
        storageDir.mkdirs();
        try {
            File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            currentPhotoPath = imageFile.getAbsolutePath();
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadImageToImageView() {
        /* if (currentPhotoPath != null) {
        *    File imageFile = new File(currentPhotoPath);
        *}
        */
    }
}