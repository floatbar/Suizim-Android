package wafoot.becoming.wafoot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Result3ScreenAdminActivity extends AppCompatActivity {

    private TextInputEditText te1;
    private TextInputEditText te2;
    private TextInputEditText te3;

    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result3_screen_admin);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        te1 = findViewById(R.id.te1);
        te2 = findViewById(R.id.te2);
        te3 = findViewById(R.id.te3);

        fabButton = findViewById(R.id.fab);

        te1.setText(getString(R.string.personel));

        te2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result3ScreenAdminActivity.this, Individual3BasedAdmin3Activity.class));
            }
        });

        te3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result3ScreenAdminActivity.this, SchoolBasedActivity.class));
            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Result3ScreenAdminActivity.this);
                alertDialogBuilder.setIcon(R.drawable.menu_open);
                alertDialogBuilder.setTitle(R.string.geri_d_nme_lemi13223);
                alertDialogBuilder.setMessage(R.string.ana_sayfaya_d_nmek_istedi_inizden_emin_misiniz323232);
                alertDialogBuilder.setPositiveButton(R.string.evet13231232, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Result3ScreenAdminActivity.this, FrontendActivity1.class));
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.hay_r23132322332, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialogBuilder.create().show();
            }
        });
    }
}