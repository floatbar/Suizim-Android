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

public class Password5Student5Activity extends AppCompatActivity {
    private FloatingActionButton fabPassword;
    private TextView tvPassword;
    private TextInputEditText editPassword;
    private TextInputEditText editConfirm;

    private StudentsInterface studentsInterface;

    private TextInputEditText editProxy;
    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password5_student5);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        fabPassword = findViewById(R.id.fabPassword3);
        tvPassword = findViewById(R.id.tvPassword3);
        editConfirm = findViewById(R.id.editConfirm3);
        editProxy = findViewById(R.id.editProxy);
        progressBar = findViewById(R.id.progressBar);

        editProxy.setMovementMethod(null);
        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        editProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Password5Student5Activity.this, ProxyServerActivity.class));
            }
        });

        editPassword = findViewById(R.id.editPassword3);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();

        SpannableString spannableString = new SpannableString(tvPassword.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(foregroundColorSpan, 26, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPassword.setText(spannableString);

        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Password5Student5Activity.this, TermsOfServicesActivity.class));
            }
        });

        fabPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editPassword.getText().toString().equals(editConfirm.getText().toString())) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password5Student5Activity.this);
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_e_le_me_sorunu33);
                    alertDialogBuilder.setMessage(R.string.her_iki_alanda_da_ifrelerinizin_e_le_mesi_gerekmektedir3);
                    alertDialogBuilder.setPositiveButton(R.string.tamam33, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword.getText().toString().equals(editConfirm.getText().toString()) && editPassword.getText().toString()
                        .length() < 8) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password5Student5Activity.this);
                    alertDialogBuilder.setIcon(R.drawable.baseline_error);
                    alertDialogBuilder.setTitle(R.string.ifre_basamak_say_s_sorunu44);
                    alertDialogBuilder.setMessage(R.string.olu_turdu_unuz_ifrenin_minimum_8_haneli_olmas_gerekmektedir44);
                    alertDialogBuilder.setPositiveButton(R.string.tamam44444444444, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword.getText().toString().equals(editConfirm.getText().toString()) &&
                        editPassword.getText().toString().length() >= 8) {
                    progressBar.setVisibility(View.VISIBLE);

                    String student_class = getIntent().getStringExtra("student_class");
                    String name = getIntent().getStringExtra("name");
                    String surname = getIntent().getStringExtra("surname");

                    SharedPreferences sharedPreferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("student_password", editPassword.getText().toString());
                    editor.apply();

                    studentsInterface.saveStudent(student_class, name, surname, editPassword.getText().toString()).enqueue(new Callback<StudentsResponse>() {
                        @Override
                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                            if (response.isSuccessful()) {
                                reference.child("Users").child("Student").child(name + " " + surname)
                                        .child("full_name").setValue(name + " " + surname).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    reference.child("Users").child("Student").child(name + " " + surname).child("full_name")
                                                            .child("student_class").setValue(student_class).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task2) {
                                                                    if (task2.isSuccessful()) {
                                                                        progressBar.setVisibility(View.GONE);

                                                                        startActivity(new Intent(Password5Student5Activity.this, FrontendActivity1.class));

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