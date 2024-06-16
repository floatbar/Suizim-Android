package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Send3Teacher3Activity extends AppCompatActivity {

    private ProgressBar pBar6;
    private TextInputEditText edit4, edit5, edit6;
    private FloatingActionButton fabSend2;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ImageView imageView16;

    private boolean isStudent = false;
    private boolean isStaff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send3_teacher3);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        pBar6 = findViewById(R.id.pBar6);
        fabSend2 = findViewById(R.id.fabSend2);
        edit4 = findViewById(R.id.edit4);
        imageView16 = findViewById(R.id.imageView16);
        edit5 = findViewById(R.id.edit5);
        edit6 = findViewById(R.id.edit6);
        pBar6.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        String partialPersonReplied = getIntent().getStringExtra("partial_person_replied");
        String fullPersonReplied = getIntent().getStringExtra("full_person_replied");
        edit4.setText(fullPersonReplied);

        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));

        imageView16.setImageResource(R.drawable.send);

        reference.child("Users").child("Student").child(partialPersonReplied).child("full_name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals("student_class")) {
                    isStudent = true;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        reference.child("Users").child("Staff").child(partialPersonReplied).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot2, @Nullable String previousChildName) {
                if (snapshot2.getKey().equals("full_name")) {
                    isStaff = true;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        fabSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit5.getText().toString().equals("") || edit6.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Send3Teacher3Activity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler231213);
                    alertDialogBuilder.setMessage(R.string.mesaj_g_ndermek_i_in_isminizi_soy_isminizi_ve_ge_erli_bir_mesaj_giriniz3131313);

                    alertDialogBuilder.setPositiveButton(R.string.tamam11231221, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    pBar6.setVisibility(View.VISIBLE);

                    if (!edit6.getText().toString().equals("")) {
                    String key1 = reference.child("Users").child("Teacher").child(student_name2 + " " + student_surname2).child("full_name")
                            .child("messages").child("sent").push().getKey();

                    reference.child("Users").child("Teacher").child(student_name2 + " " + student_surname2).child("full_name")
                            .child("messages").child("sent").child(key1).setValue(getString(R.string.yan_tlanan_ki_i13133131) + fullPersonReplied + getString(R.string.adassas) + edit6.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        reference.child("Users").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot2, @Nullable String previousChildName) {
                                                if (isStudent) {
                                                    String key2 = reference.child("Users").child("Student").child(partialPersonReplied)
                                                            .child("full_name").child("messages").child("coming").push().getKey();

                                                    reference.child("Users").child("Student").child(partialPersonReplied)
                                                            .child("full_name").child("messages").child("coming").child(key2).setValue(student_name2 + " " + student_surname2 + getString(R.string.retmen12132123133) + edit6.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task2) {
                                                                    if (task2.isSuccessful()) {
                                                                        try {
                                                                            pBar6.setVisibility(View.GONE);
                                                                            edit6.setText("");
                                                                            Snackbar.make(view, getString(R.string.mesaj_ba_ar_l_bir_ekilde_g_nderildi), Snackbar.LENGTH_SHORT)
                                                                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(View view) {
                                                                                        }
                                                                                    }).show();
                                                                        }

                                                                        catch (Exception e) {
                                                                        }
                                                                    }

                                                                    else {
                                                                        try {
                                                                            pBar6.setVisibility(View.GONE);
                                                                            Snackbar.make(view, getString(R.string.mesaj_g_nderilirken_bir_hata_olu_tu), Snackbar.LENGTH_SHORT)
                                                                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(View view) {
                                                                                        }
                                                                                    }).show();
                                                                        }

                                                                        catch (Exception e) {
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                }

                                                if (isStaff) {
                                                    String key3 = reference.child("Users").child("Staff").child(partialPersonReplied)
                                                            .child("full_name").child("messages").child("coming").push().getKey();

                                                    reference.child("Users").child("Staff").child(partialPersonReplied)
                                                            .child("full_name").child("messages").child("coming").child(key3).setValue(student_name2 + " " + student_surname2 + getString(R.string.retmen12111) + edit6.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task3) {
                                                                    if (task3.isSuccessful()) {
                                                                        try {
                                                                            pBar6.setVisibility(View.GONE);
                                                                            Snackbar.make(view, getString(R.string.mesaj_ba_ar_l_bir_ekilde_g_nderildi), Snackbar.LENGTH_SHORT)
                                                                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(View view) {
                                                                                        }
                                                                                    }).show();
                                                                        }

                                                                        catch (Exception e) {
                                                                        }
                                                                    }

                                                                    else {
                                                                        try {
                                                                            pBar6.setVisibility(View.GONE);
                                                                            Snackbar.make(view, getString(R.string.mesaj_g_nderilirken_bir_hata_olu_tu), Snackbar.LENGTH_SHORT)
                                                                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(View view) {
                                                                                        }
                                                                                    }).show();
                                                                        }

                                                                        catch (Exception e) {
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }

                                    else {

                                        try {
                                            pBar6.setVisibility(View.GONE);
                                            Snackbar.make(view, getString(R.string.mesaj_g_nderilirken_bir_hata_olu_tu), Snackbar.LENGTH_SHORT)
                                                    .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                        }

                                        catch (Exception e) {
                                        }
                                    }
                                }
                            });
                    }
                }
            }
        });
    }
}