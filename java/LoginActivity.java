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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editName;
    private TextInputEditText editSurname;
    private TextInputEditText editPassword;

    private StudentsInterface studentsInterface;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        editName = findViewById(R.id.te1);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();

        editSurname = findViewById(R.id.te2);
        progressBar = findViewById(R.id.progressBar);
        editPassword = findViewById(R.id.te3);
        FloatingActionButton fabLogin = findViewById(R.id.fab);

        progressBar.setVisibility(View.GONE);

        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().trim().equals("") || editSurname.getText().toString().trim().equals("") || editPassword.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(LoginActivity.this);
                    alertDialogBuilder.setTitle(R.string.yetersiz_hesap_bilgileri4);
                    alertDialogBuilder.setIcon(R.drawable.account_circle);
                    alertDialogBuilder.setMessage(R.string.giri_yapabilmek_i_in_t_m_alanlar_girmeniz_gerekmektedir2);
                    alertDialogBuilder.setPositiveButton(R.string.tamam5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();

                }

                else {
                    progressBar.setVisibility(View.VISIBLE);

                    studentsInterface.loginStudent(editName.getText().toString(), editSurname.getText().toString(),
                            editPassword.getText().toString()).enqueue(new Callback<StudentsResponse>() {
                        @Override
                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                            if (response.isSuccessful()) {
                                studentsInterface.showIsStudentWith2FAPassword(editName.getText().toString(),
                                        editSurname.getText().toString(), editPassword.getText().toString())
                                        .enqueue(new Callback<StudentsResponse>() {
                                            @Override
                                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response2) {
                                                if (response2.isSuccessful()) {
                                                    progressBar.setVisibility(View.GONE);

                                                    startActivity(new Intent(LoginActivity.this, TwoFactorAuthenticationActivity.class));

                                                    SharedPreferences sharedPreferences = getSharedPreferences("StudentPassword2",
                                                            Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("student_name", editName.getText().toString());
                                                    editor.putString("student_surname", editSurname.getText().toString());
                                                    editor.putString("studentPassword", editPassword.getText().toString());
                                                    editor.apply();
                                                }

                                                else {
                                                    progressBar.setVisibility(View.GONE);

                                                    Intent intent = new Intent(LoginActivity.this, FrontendActivity1.class);
                                                    startActivity(intent);

                                                    SharedPreferences sharedPreferences = getSharedPreferences("StudentPassword2",
                                                            Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("student_name", editName.getText().toString());
                                                    editor.putString("student_surname", editSurname.getText().toString());
                                                    editor.putString("studentPassword", editPassword.getText().toString());
                                                    editor.apply();

                                                    SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor preferencesEditor = preferences.edit();
                                                    preferencesEditor.putBoolean("isLoggedIn", true);
                                                    preferencesEditor.apply();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<StudentsResponse> call, Throwable t) {
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

                        @Override
                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
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
