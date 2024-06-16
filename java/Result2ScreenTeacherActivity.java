package wafoot.becoming.wafoot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Result2ScreenTeacherActivity extends AppCompatActivity {

    private TextInputEditText te1;
    private TextInputEditText te2;
    private TextInputEditText te3;

    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2_screen_teacher);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        te1 = findViewById(R.id.te1);
        te2 = findViewById(R.id.te2);
        te3 = findViewById(R.id.te3);

        fabButton = findViewById(R.id.fab);

        te1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result2ScreenTeacherActivity.this, Individual2BasedTeacher2Activity.class));
            }
        });

        te2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result2ScreenTeacherActivity.this, Student5ForTeacher5Activity.class));
            }
        });

        te3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result2ScreenTeacherActivity.this, SchoolBasedActivity.class));
            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Result2ScreenTeacherActivity.this);
                alertDialogBuilder.setIcon(R.drawable.menu_open);
                alertDialogBuilder.setTitle(R.string.geri_d_nme_lemi);
                alertDialogBuilder.setMessage(R.string.ana_sayfaya_d_nmek_istedi_inizden_emin_misiniz);
                alertDialogBuilder.setPositiveButton(R.string.evet12312, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Result2ScreenTeacherActivity.this, FrontendActivity1.class));
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.hay_r1212, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialogBuilder.create().show();
            }
        });
    }
}