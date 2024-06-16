package wafoot.becoming.wafoot;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Password3AdminActivity extends AppCompatActivity {

    private TextInputEditText editPassword3;
    private TextInputEditText editConfirm3;

    private StaffInterface staffInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password3_admin);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        editPassword3 = findViewById(R.id.editPassword3);
        TextView tvPassword3 = findViewById(R.id.tvPassword3);

        tvPassword3.setText(getString(R.string.devam_ederek_kullan_m_ko_ullar_m_z_ve) + getString(R.string.gizlilik_politikam_z_onaylam_say_l_rs_n_z));

        SpannableString spannableString = new SpannableString(tvPassword3.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan foregroundcolorspan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(foregroundColorSpan, 13, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(foregroundcolorspan, 40, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPassword3.setText(spannableString);

        FloatingActionButton fabPassword3 = findViewById(R.id.fabPassword3);
        editConfirm3 = findViewById(R.id.editConfirm3);

        fabPassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editPassword3.getText().toString().equals(editConfirm3.getText().toString())) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password3AdminActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_e_le_me_sorunu2);
                    alertDialogBuilder.setMessage(R.string.her_iki_alanda_da_ifrelerinizin_e_le_mesi_gerekmektedir2);
                    alertDialogBuilder.setPositiveButton(R.string.tamam233, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword3.getText().toString().equals(editConfirm3.getText().toString()) && editPassword3.getText().toString().length() < 8) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password3AdminActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_basamak_say_s_sorunu3);
                    alertDialogBuilder.setMessage(R.string.olu_turdu_unuz_ifrenin_minimum_8_haneli_olmas_gerekmektedir3);
                    alertDialogBuilder.setPositiveButton(R.string.tamam111, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword3.getText().toString().equals(editConfirm3.getText().toString()) && editPassword3.getText().toString().length() >= 8) {
                    String staff_name = getIntent().getStringExtra("staff_name");
                    String staff_surname = getIntent().getStringExtra("staff_surname");

                    staffInterface.addStaff(staff_name, staff_surname, editPassword3.getText().toString()).enqueue(new Callback<StaffResponse>() {
                        @Override
                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(Password3AdminActivity.this, FrontendActivity1.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                            Toast.makeText(Password3AdminActivity.this, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
