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

public class Reply2ForStudents2Activity extends AppCompatActivity {
    
    private ProgressBar pBar12;
    private FloatingActionButton fabSend5;
    private TextInputEditText edi9, edi10, edi11;
    private ImageView imageView11;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private String student_class = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply2_for_students2);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        pBar12 = findViewById(R.id.pBar12);
        imageView11 = findViewById(R.id.imageView11);
        edi9 = findViewById(R.id.edi9);
        edi10 = findViewById(R.id.edi10);
        edi11 = findViewById(R.id.edi11);
        fabSend5 = findViewById(R.id.fabSend5);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));

        pBar12.setVisibility(View.GONE);
        imageView11.setImageResource(R.drawable.send);

        reference.child("Users").child("Student").child(student_name + " " + student_surname)
                        .child("full_name").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.getKey().equals("student_class")) {
                            student_class = snapshot.getValue().toString();
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

        String partialPersonReplied = getIntent().getStringExtra("partial_replied");
        String fullPersonReplied = getIntent().getStringExtra("full_replied");
        edi9.setText(fullPersonReplied);

        fabSend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edi10.getText().toString().equals("") || edi11.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Reply2ForStudents2Activity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler33);
                    alertDialogBuilder.setMessage(R.string.mesaj_g_ndermek_i_in_isminizi_soy_isminizi_ve_ge_erli_bir_mesaj_giriniz33);
                    alertDialogBuilder.setPositiveButton(R.string.tamam333, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    pBar12.setVisibility(View.VISIBLE);

                    if (!edi11.getText().toString().equals("")) {
                    String key1 = reference.child("Users").child("Student").child(student_name + " " + student_surname)
                            .child("full_name").child("messages").child("sent").push().getKey();

                    reference.child("Users").child("Student").child(student_name + " " + student_surname)
                            .child("full_name").child("messages").child("sent").child(key1).setValue(getString(R.string.yan_tlanan_ki_i) + partialPersonReplied + getString(R.string.w) + edi11.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        String key2 = reference.child("Users").child("Teacher").child(partialPersonReplied).child("full_name")
                                                .child("messages").child("coming").push().getKey();

                                        reference.child("Users").child("Teacher").child(partialPersonReplied).child("full_name")
                                                .child("messages").child("coming").child(key2).setValue(student_name + " " + student_surname + " - " + student_class + ": " + edi11.getText().toString() + " ~ " + partialPersonReplied + " kişisine verilen cevap")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task2) {
                                                        if (task2.isSuccessful()) {
                                                            try {
                                                                pBar12.setVisibility(View.GONE);
                                                                edi11.setText("");
                                                                Snackbar.make(view, getString(R.string.mesaj_ba_ar_l_bir_ekilde_g_nderildi2), Snackbar.LENGTH_SHORT)
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
                                                                pBar12.setVisibility(View.GONE);
                                                                Snackbar.make(view, R.string.mesaj_g_nderilirken_bir_hata_olu_tu2, Snackbar.LENGTH_SHORT)
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

                                    else {

                                        try {
                                            pBar12.setVisibility(View.GONE);
                                            Snackbar.make(view, R.string.mesaj_g_nderilirken_bir_hata_olu_tu2, Snackbar.LENGTH_SHORT)
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
