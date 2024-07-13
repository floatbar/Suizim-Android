package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification3AdminActivity extends AppCompatActivity {

    private TextInputEditText editName;
    private TextInputEditText editSurname;
    private TextInputEditText editPassword;
    private FloatingActionButton fabLogin;

    private StaffInterface staffInterface;

    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification3_admin);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        editSurname = findViewById(R.id.te2);
        editPassword = findViewById(R.id.te3);
        progressBar = findViewById(R.id.progressBar);
        fabLogin = findViewById(R.id.fab);
        editName = findViewById(R.id.te1);

        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences10 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences10.edit();
        SharedPreferences.Editor editor2 = preferences11.edit();
        SharedPreferences.Editor editor3 = preferences13.edit();

        String student_name2 = preferences13.getString("staff_name", preferences11.
                getString("staff_name", ""));
        String student_surname2 = preferences13.getString("staff_surname", preferences11.
                getString("staff_surname", ""));
        String student_password2 = preferences10.getString("staff_password", preferences11.
                getString("staff_password", ""));

        SharedPreferences sharedPreferences3 = getSharedPreferences("StaffURI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedPreferences3.edit();

        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().equals("") || editSurname.getText().toString().equals("") || editPassword.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Verification3AdminActivity.this);
                    alertDialogBuilder.setTitle(R.string.yetersiz_hesap_bilgileri131312);
                    alertDialogBuilder.setIcon(R.drawable.account_circle);
                    alertDialogBuilder.setMessage(R.string.hesap_onay_i_in_gerekli_olan_t_m_alanlar_giriniz331);
                    alertDialogBuilder.setPositiveButton(R.string.tamam311313, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();

                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    staffInterface.loginStaff(editName.getText().toString(), editSurname.getText().toString(),
                            editPassword.getText().toString()).enqueue(new Callback<StaffResponse>() {
                        @Override
                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                            if (response.isSuccessful()) {
                                staffInterface.deleteStaffAccount(student_name2, student_surname2, student_password2)
                                        .enqueue(new Callback<StaffResponse>() {
                                            @Override
                                            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response2) {
                                                if (response2.isSuccessful()) {
                                                    reference.child("Users").child("Staff").child(student_name2 + " " + student_surname2)
                                                            .removeValue(new DatabaseReference.CompletionListener() {
                                                                @Override
                                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                    if (error == null) {
                                                                        progressBar.setVisibility(View.GONE);

                                                                        SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = preferences.edit();
                                                                        editor.putBoolean("isLoggedIn", false);
                                                                        editor.apply();

                                                                        editor1.remove("staff_password");
                                                                        editor2.remove("staff_password");
                                                                        editor2.remove("staff_name");
                                                                        editor2.remove("staff_surname");
                                                                        editor3.remove("staff_name");
                                                                        editor3.remove("staff_surname");

                                                                        editor1.apply();
                                                                        editor2.apply();
                                                                        editor3.apply();
                                                                        editor6.apply();

                                                                        editor6.remove("staff_uri");

                                                                        startActivity(new Intent(Verification3AdminActivity.this, MainActivity.class));
                                                                        finish();
                                                                    }

                                                                    else {
                                                                        progressBar.setVisibility(View.GONE);
                                                                        Snackbar.make(view, getString(R.string.hesap_silinirken_bir_hata_olu_tu323232),
                                                                                Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam3231), new View.OnClickListener() {
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
                                                Snackbar.make(view, getString(R.string.hesap_silinirken_bir_hata_olu_tu323232),
                                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam3231), new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                    }
                                                }).show();
                                            }
                                        });
                            }
                        }

                        @Override
                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Snackbar.make(view, getString(R.string.hesap_silinirken_bir_hata_olu_tu323232),
                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam3231), new View.OnClickListener() {
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
