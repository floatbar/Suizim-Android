package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Password4ActivityStaff extends AppCompatActivity {

    private TextInputEditText editPassword3;
    private TextInputEditText editConfirm3;
    private FloatingActionButton fabPassword3;

    private StaffInterface staffInterface;

    private TextView tvPassword3;
    private TextInputEditText editProxy;
    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password4_staff);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();
        editPassword3 = findViewById(R.id.editPassword3);
        tvPassword3 = findViewById(R.id.tvPassword3);
        editProxy = findViewById(R.id.editProxy);
        progressBar = findViewById(R.id.progressBar);

        editProxy.setMovementMethod(null);
        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        editProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProxy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Password4ActivityStaff.this, ProxyServerActivity.class));
                    }
                });
            }
        });

        SpannableString spannableString = new SpannableString(tvPassword3.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(foregroundColorSpan, 26, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPassword3.setText(spannableString);

        tvPassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Password4ActivityStaff.this, TermsOfServicesActivity.class));
            }
        });

        fabPassword3 = findViewById(R.id.fabPassword3);
        editConfirm3 = findViewById(R.id.editConfirm3);

        fabPassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editPassword3.getText().toString().equals(editConfirm3.getText().toString())) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password4ActivityStaff.this);
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_e_le_me_sorunu1);
                    alertDialogBuilder.setMessage(R.string.her_iki_alanda_da_ifrelerinizin_e_le_mesi_gerekmektedir11);
                    alertDialogBuilder.setPositiveButton(R.string.tamam1111, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                if (editPassword3.getText().toString().equals(editConfirm3.getText().toString()) && editPassword3.getText().toString()
                        .length() < 8) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password4ActivityStaff.this);
                    alertDialogBuilder.setIcon(R.drawable.baseline_error);
                    alertDialogBuilder.setTitle(R.string.ifre_basamak_say_s_sorunu222);
                    alertDialogBuilder.setMessage(R.string.olu_turdu_unuz_ifrenin_minimum_8_haneli_olmas_gerekmektedir222);
                    alertDialogBuilder.setPositiveButton(R.string.tamam22222222222, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                if (editPassword3.getText().toString().equals(editConfirm3.getText().toString()) &&
                        editPassword3.getText().toString().length() >= 8) {
                    progressBar.setVisibility(View.VISIBLE);

                    SharedPreferences sharedPreferences = getSharedPreferences("ResultStaffInfo",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("staff_password", editPassword3.getText().toString());
                    editor.apply();

                    String staff_name = getIntent().getStringExtra("staff_name");
                    String staff_surname = getIntent().getStringExtra("staff_surname");

                    staffInterface.addStaff(staff_name, staff_surname, editPassword3.getText().toString()).enqueue(new Callback<StaffResponse>() {
                        @Override
                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                            if (response.isSuccessful()) {
                                reference.child("Users").child("Staff").child(staff_name + " " + staff_surname)
                                        .child("full_name").setValue(staff_name + " " + staff_surname).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    progressBar.setVisibility(View.GONE);

                                                    startActivity(new Intent(Password4ActivityStaff.this, FrontendActivity1.class));

                                                    SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor preferencesEditor = preferences.edit();
                                                    preferencesEditor.putBoolean("isLoggedIn", true);
                                                    preferencesEditor.apply();
                                                }

                                                else {
                                                    progressBar.setVisibility(View.GONE);
                                                    Snackbar.make(view, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Snackbar.LENGTH_SHORT)
                                                            .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                }
                                            }
                                        });
                            }
                        }

                        @Override
                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Snackbar.make(view, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Snackbar.LENGTH_SHORT)
                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    }).show();
                        }
                    });
                }
            }
        });
    }
}