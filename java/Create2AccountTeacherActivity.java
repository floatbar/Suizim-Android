package wafoot.becoming.wafoot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class Create2AccountTeacherActivity extends AppCompatActivity {
    
    private ShapeableImageView ivAccount;
    private FloatingActionButton fabAccount;
    private TextInputEditText editAccount2;
    private TextInputEditText editAccount3;
    private TextInputEditText editAccount;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create2_account_teacher);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        ivAccount = findViewById(R.id.imageView6);
        fabAccount = findViewById(R.id.fab);
        editAccount2 = findViewById(R.id.te2);
        editAccount = findViewById(R.id.te1);
        editAccount3 = findViewById(R.id.te3);

        editAccount.setCursorVisible(false);
        editAccount.setFocusable(false);
        editAccount.setFocusableInTouchMode(false);

        String item2 = getIntent().getStringExtra("item2");
        editAccount.setText(item2);

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create2AccountTeacherActivity.this, SelectBranchActivity.class);
                startActivity(intent);
            }
        });

        ivAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        fabAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editAccount.getText().toString().trim().equals("") || editAccount2.getText().toString().trim().equals("") || editAccount3.getText().toString().trim().equals("")) {
                    MaterialAlertDialogBuilder alertdialog = new MaterialAlertDialogBuilder(Create2AccountTeacherActivity.this);
                    alertdialog.setIcon(R.drawable.global);
                    alertdialog.setTitle(R.string.yetersiz_hesap_bilgileri);
                    alertdialog.setMessage(R.string.hesap_olu_turmak_i_in_isminizi_soy_isminizi_ve_bran_n_z_girmeniz_gerekmektedir);
                    alertdialog.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertdialog.create().show();
                }

                else {
                    requestPermissions();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ivAccount.setImageBitmap(bitmap);
                SharedPreferences preferences = getSharedPreferences("ImageURI", Context.MODE_PRIVATE);
                preferences.edit().putString("image_uri", uri.toString()).apply();
            }

            catch (Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.foto_raf_y_klerken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {}

            else {
                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Create2AccountTeacherActivity.this);
                alertDialogBuilder.setIcon(R.drawable.baseline_photo_library);
                alertDialogBuilder.setTitle(R.string.eri_im_sorunu);
                alertDialogBuilder.setMessage(R.string.l_tfen_medyaya_eri_im_izni_verin);
                alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialogBuilder.create().show();
            }
        }
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(Create2AccountTeacherActivity.this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Create2AccountTeacherActivity.this, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_CODE);
            }

            else {
                Intent intent = new Intent(Create2AccountTeacherActivity.this, Password2TeacherActivity.class);
                intent.putExtra("teacher_branch", editAccount.getText().toString());
                intent.putExtra("teacher_name", editAccount2.getText().toString());
                intent.putExtra("teacher_surname", editAccount3.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("teacher_name", editAccount2.getText().toString());
                editor.putString("teacher_surname", editAccount3.getText().toString());
                editor.apply();

                startActivity(intent);
            }
        }

        else {
            if (ActivityCompat.checkSelfPermission(Create2AccountTeacherActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Create2AccountTeacherActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

            else if (ActivityCompat.checkSelfPermission(Create2AccountTeacherActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Create2AccountTeacherActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

            else if (ActivityCompat.checkSelfPermission(Create2AccountTeacherActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(Create2AccountTeacherActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Create2AccountTeacherActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                ActivityCompat.requestPermissions(Create2AccountTeacherActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

            else {
                Intent intent = new Intent(Create2AccountTeacherActivity.this, Password2TeacherActivity.class);
                intent.putExtra("teacher_branch", editAccount.getText().toString());
                intent.putExtra("teacher_name", editAccount2.getText().toString());
                intent.putExtra("teacher_surname", editAccount3.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("teacher_name", editAccount2.getText().toString());
                editor.putString("teacher_surname", editAccount3.getText().toString());
                editor.apply();

                startActivity(intent);
            }
        }
    }
}
