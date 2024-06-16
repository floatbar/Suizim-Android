package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoFactorAuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_factor_authentication);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        SharedPreferences sharedPreferences = getSharedPreferences("Position", Context.MODE_PRIVATE);
        SharedPreferences preferences10 = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
        SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));
        String student_password = preferences10.getString("student_password", preferences1.
                getString("studentPassword", ""));

        SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));
        String student_password2 = preferences.getString("teacher_password", preferences11.
                getString("teacher_password", ""));

        SharedPreferences preferences2 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences12 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences12.getString("staff_name", preferences14.
                getString("staff_name", ""));
        String student_surname3 = preferences12.getString("staff_surname", preferences14.
                getString("staff_surname", ""));
        String student_password3 = preferences2.getString("staff_password", preferences12.
                getString("staff_password", ""));

        TextInputEditText te1 = findViewById(R.id.te1);
        TextInputEditText te2 = findViewById(R.id.te2);
        FloatingActionButton fab = findViewById(R.id.fab);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        StudentsInterface studentsInterface = ApiUtils.getNodejsStudentsInterface();
        TeachersInterface teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        StaffInterface staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (te1.getText().toString().isEmpty()) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(TwoFactorAuthenticationActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler133113);
                    alertDialogBuilder.setMessage(R.string.hesaba_giri_yapabilmek_i_in_hesaba_tan_ml_olan_iki_fakt_rl_kimlik_do_rulama_ifresini_girmeniz_gerekmektedir13131313);
                    alertDialogBuilder.setPositiveButton(R.string.tamam313131, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
                        studentsInterface.verify2FAStudentPassword(student_name, student_surname,
                                student_password, te1.getText().toString()).enqueue(new Callback<StudentsResponse>() {
                            @Override
                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                if (response.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);

                                    startActivity(new Intent(TwoFactorAuthenticationActivity.this, FrontendActivity1.class));

                                    SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor preferencesEditor = preferences.edit();
                                    preferencesEditor.putBoolean("isLoggedIn", true);
                                    preferencesEditor.apply();
                                }
                            }

                            @Override
                            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_ifresi_do_rulan_rken_bir_hata_olu_tu311312),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
                        teachersInterface.verify2FATeacherPassword(student_name2, student_surname2,
                                student_password2, te1.getText().toString()).enqueue(new Callback<TeachersResponse>() {
                            @Override
                            public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                if (response.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);

                                    startActivity(new Intent(TwoFactorAuthenticationActivity.this, FrontendActivity1.class));

                                    SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor preferencesEditor = preferences.edit();
                                    preferencesEditor.putBoolean("isLoggedIn", true);
                                    preferencesEditor.apply();
                                }
                            }

                            @Override
                            public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_ifresi_do_rulan_rken_bir_hata_olu_tu311312),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
                        staffInterface.verify2FAStaffPassword(student_name3, student_surname3,
                                student_password3, te1.getText().toString()).enqueue(new Callback<StaffResponse>() {
                            @Override
                            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                if (response.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);

                                    startActivity(new Intent(TwoFactorAuthenticationActivity.this, FrontendActivity1.class));

                                    SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor preferencesEditor = preferences.edit();
                                    preferencesEditor.putBoolean("isLoggedIn", true);
                                    preferencesEditor.apply();
                                }
                            }

                            @Override
                            public void onFailure(Call<StaffResponse> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_ifresi_do_rulan_rken_bir_hata_olu_tu311312),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });
                    }
                }
            }
        });

        te2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TwoFactorAuthenticationActivity.this, ProxyServerActivity.class));
            }
        });
    }
}