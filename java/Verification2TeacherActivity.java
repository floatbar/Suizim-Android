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

public class Verification2TeacherActivity extends AppCompatActivity {

    private TextInputEditText editName;
    private TextInputEditText editSurname;
    private TextInputEditText editPassword;
    private FloatingActionButton fabLogin;

    private TeachersInterface teachersInterface;

    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification2_teacher);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();

        editSurname = findViewById(R.id.te2);
        editPassword = findViewById(R.id.te3);
        progressBar = findViewById(R.id.progressBar);
        fabLogin = findViewById(R.id.fab);
        editName = findViewById(R.id.te1);

        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences10 = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences10.edit();
        SharedPreferences.Editor editor2 = preferences11.edit();
        SharedPreferences.Editor editor3 = preferences13.edit();

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));
        String student_password2 = preferences10.getString("teacher_password", preferences11.
                getString("teacher_password", ""));

        SharedPreferences sharedPreferences2 = getSharedPreferences("TeacherURI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences2.edit();

        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().equals("") || editSurname.getText().toString().equals("")
                        || editPassword.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Verification2TeacherActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.account_circle);
                    alertDialogBuilder.setTitle(R.string.yetersiz_hesap_bilgileri1313);
                    alertDialogBuilder.setMessage(R.string.hesap_onay_i_in_gerekli_olan_t_m_alanlar_giriniz11133);
                    alertDialogBuilder.setPositiveButton(R.string.tamam133113, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();

                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    teachersInterface.loginTeacher(editName.getText().toString(), editSurname.getText().toString(),
                            editPassword.getText().toString()).enqueue(new Callback<TeachersResponse>() {
                        @Override
                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                            if (response.isSuccessful()) {
                                teachersInterface.deleteTeacherAccount(student_name2, student_surname2, student_password2)
                                        .enqueue(new Callback<TeachersResponse>() {
                                            @Override
                                            public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response2) {
                                                if (response2.isSuccessful()) {
                                                    reference.child("Users").child("Teacher").child(student_name2 + " " + student_surname2)
                                                            .removeValue(new DatabaseReference.CompletionListener() {
                                                                @Override
                                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                    if (error == null) {
                                                                        progressBar.setVisibility(View.GONE);

                                                                        SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = preferences.edit();
                                                                        editor.putBoolean("isLoggedIn", false);
                                                                        editor.apply();

                                                                        editor1.remove("teacher_password");
                                                                        editor2.remove("teacher_password");
                                                                        editor2.remove("teacher_name");
                                                                        editor2.remove("teacher_surname");
                                                                        editor3.remove("teacher_name");
                                                                        editor3.remove("teacher_surname");

                                                                        editor1.apply();
                                                                        editor2.apply();
                                                                        editor3.apply();
                                                                        editor5.apply();

                                                                        editor5.remove("teacher_uri");

                                                                        startActivity(new Intent(Verification2TeacherActivity.this, MainActivity.class));
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
                                            public void onFailure(Call<TeachersResponse> call, Throwable t) {
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
                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
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