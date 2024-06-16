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

public class Password2TeacherActivity extends AppCompatActivity {

    private TextInputEditText editPassword2;
    private TextInputEditText editConfirm2;
    private FloatingActionButton fabPassword2;

    private TeachersInterface teachersInterface;

    private TextView tvPassword3;
    private TextInputEditText editProxy;
    private ProgressBar progressBar;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2_teacher);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        fabPassword2 = findViewById(R.id.fabPassword3);
        progressBar = findViewById(R.id.progressBar);
        tvPassword3 = findViewById(R.id.tvPassword3);
        editConfirm2 = findViewById(R.id.editConfirm3);
        editPassword2 = findViewById(R.id.editPassword3);
        editProxy = findViewById(R.id.editProxy);

        editProxy.setMovementMethod(null);
        progressBar.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        editProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Password2TeacherActivity.this, ProxyServerActivity.class));
            }
        });

        SpannableString spannableString = new SpannableString(tvPassword3.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(foregroundColorSpan, 26, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPassword3.setText(spannableString);

        tvPassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Password2TeacherActivity.this, TermsOfServicesActivity.class));
            }
        });

        fabPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editPassword2.getText().toString().equals(editConfirm2.getText().toString())) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password2TeacherActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_e_le_me_sorunu);
                    alertDialogBuilder.setMessage(R.string.her_iki_alanda_da_ifrelerinizin_e_le_mesi_gerekmektedir);
                    alertDialogBuilder.setPositiveButton(R.string.tamam22, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword2.getText().toString().equals(editConfirm2.getText().toString()) && editPassword2.getText().toString()
                        .length() < 8) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Password2TeacherActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.baseline_error);
                    alertDialogBuilder.setTitle(R.string.ifre_basamak_say_s_sorunu);
                    alertDialogBuilder.setMessage(R.string.olu_turdu_unuz_ifrenin_minimum_8_haneli_olmas_gerekmektedir);
                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (editPassword2.getText().toString().equals(editConfirm2.getText().toString()) &&
                        editPassword2.getText().toString().length() >= 8) {
                    progressBar.setVisibility(View.VISIBLE);

                    String teacher_branch = getIntent().getStringExtra("teacher_branch");
                    String teacher_name = getIntent().getStringExtra( "teacher_name");
                    String teacher_surname = getIntent().getStringExtra("teacher_surname");

                    SharedPreferences sharedPreferences = getSharedPreferences("ResultTeacherInfo",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("teacher_branch", teacher_branch);
                    editor.putString("teacher_name", teacher_name);
                    editor.putString("teacher_surname", teacher_surname);
                    editor.putString("teacher_password", editPassword2.getText().toString());
                    editor.apply();

                    teachersInterface.addTeacher(teacher_branch, teacher_name, teacher_surname,
                            editPassword2.getText().toString()).enqueue(new Callback<TeachersResponse>() {
                        @Override
                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                            if (response.isSuccessful()) {
                                reference.child("Users").child("Teacher").child(teacher_name + " " + teacher_surname)
                                        .child("full_name").setValue(teacher_name + " " + teacher_surname).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    reference.child("Users").child("Teacher").child(teacher_name + " " + teacher_surname)
                                                            .child("teacher_branch").setValue(teacher_branch).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task2) {
                                                                    if (task2.isSuccessful()) {
                                                                        progressBar.setVisibility(View.GONE);

                                                                        startActivity(new Intent(Password2TeacherActivity.this, FrontendActivity1.class));

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
                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
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