package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

public class Reply4Staff4Activity extends AppCompatActivity {

    private ProgressBar pBar13;
    private FloatingActionButton fabSend6;
    private ImageView imageView15;
    private TextInputEditText edi12, edi13, edi14;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply4_staff4);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        pBar13 = findViewById(R.id.pBar13);
        fabSend6 = findViewById(R.id.fabSend6);
        imageView15 = findViewById(R.id.imageView15);
        edi12 = findViewById(R.id.edi12);
        edi13 = findViewById(R.id.edi13);
        edi14 = findViewById(R.id.edi14);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        String partialPersonReplied = getIntent().getStringExtra("replied_partial");
        String fullPersonReplied = getIntent().getStringExtra("replied_full");

        SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("staff_name", preferences11.
                getString("staff_name", ""));
        String student_surname2 = preferences13.getString("staff_surname", preferences11.
                getString("staff_surname", ""));

        pBar13.setVisibility(View.GONE);
        edi12.setText(fullPersonReplied);
        imageView15.setImageResource(R.drawable.send);

        fabSend6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edi13.getText().toString().equals("") || edi14.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Reply4Staff4Activity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler2323);
                    alertDialogBuilder.setMessage(R.string.mesaj_g_ndermek_i_in_isminizi_soy_isminizi_ve_ge_erli_bir_mesaj_giriniz2323232);
                    alertDialogBuilder.setPositiveButton(R.string.tamam3223223, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    pBar13.setVisibility(View.VISIBLE);

                    if (!edi14.getText().toString().equals("")) {
                    String key1 = reference.child("Users").child("Staff").child(student_name2 + " " + student_name2).child("full_name")
                            .child("messages").child("sent").push().getKey();

                    reference.child("Users").child("Staff").child(student_name2 + " " + student_name2).child("full_name")
                            .child("messages").child("sent").child(key1).setValue(getString(R.string.yan_tlanan_ki_i233232) + partialPersonReplied + getString(R.string.addas) + edi14.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        String key2 = reference.child("Users").child("Teacher").child(partialPersonReplied).child("full_name")
                                                .child("messages").child("coming").push().getKey();
                                        
                                        reference.child("Users").child("Teacher").child(partialPersonReplied).child("full_name")
                                                .child("messages").child("coming").child(key2).setValue(student_name2 + " " + student_surname2 + " - " + "Personel: " + edi14.getText().toString() + " ~ " + partialPersonReplied + " kişisine verilen cevap")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task2) {
                                                        if (task2.isSuccessful()) {
                                                            try {
                                                                pBar13.setVisibility(View.GONE);
                                                                edi14.setText("");
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
                                                                pBar13.setVisibility(View.GONE);
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
                                            pBar13.setVisibility(View.GONE);
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
