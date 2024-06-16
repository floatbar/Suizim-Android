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

public class VerificationActivity extends AppCompatActivity {

    private TextInputEditText editName;
    private TextInputEditText editSurname;
    private TextInputEditText editPassword;
    private FloatingActionButton fabLogin;

    private StudentsInterface studentsInterface;

    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        editName = findViewById(R.id.te1);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();

        editSurname = findViewById(R.id.te2);
        progressBar = findViewById(R.id.progressBar);
        editPassword = findViewById(R.id.te3);
        fabLogin = findViewById(R.id.fab);

        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences10 = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences10.edit();
        SharedPreferences.Editor editor2 = preferences11.edit();
        SharedPreferences.Editor editor3 = preferences13.edit();

        String student_name = preferences13.getString("student_name", preferences11.
                getString("student_name", ""));
        String student_surname = preferences13.getString("student_surname", preferences11.
                getString("student_surname", ""));
        String student_password = preferences10.getString("student_password", preferences11.
                getString("studentPassword", ""));

        SharedPreferences sharedPreferences1 = getSharedPreferences("StudentURI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences1.edit();

        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().equals("") || editSurname.getText().toString().equals("")
                        || editPassword.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(VerificationActivity.this);
                    alertDialogBuilder.setTitle(R.string.yetersiz_hesap_bilgileri133131);
                    alertDialogBuilder.setIcon(R.drawable.account_circle);
                    alertDialogBuilder.setMessage(R.string.hesap_onay_i_in_gerekli_olan_t_m_alanlar_giriniz313131);
                    alertDialogBuilder.setPositiveButton(R.string.tamam31131313, new DialogInterface.OnClickListener() {
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
                                studentsInterface.deleteStudentAccount(student_name, student_surname, student_password)
                                        .enqueue(new Callback<StudentsResponse>() {
                                            @Override
                                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response2) {
                                                if (response2.isSuccessful()) {
                                                    reference.child("Users").child("Student").child(student_name + " " + student_surname)
                                                            .removeValue(new DatabaseReference.CompletionListener() {
                                                                @Override
                                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                    if (error == null) {
                                                                        progressBar.setVisibility(View.GONE);

                                                                        SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = preferences.edit();
                                                                        editor.putBoolean("isLoggedIn", false);
                                                                        editor.apply();

                                                                        editor1.remove("student_password");
                                                                        editor2.remove("student_password");
                                                                        editor2.remove("student_name");
                                                                        editor2.remove("student_surname");
                                                                        editor3.remove("student_name");
                                                                        editor3.remove("student_surname");

                                                                        editor1.apply();
                                                                        editor2.apply();
                                                                        editor3.apply();
                                                                        editor4.apply();

                                                                        editor4.remove("student_uri");

                                                                        startActivity(new Intent(VerificationActivity.this, MainActivity.class));
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
                                            public void onFailure(Call<StudentsResponse> call, Throwable t) {
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
                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
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