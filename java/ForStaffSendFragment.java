package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class ForStaffSendFragment extends Fragment {
    
    private ProgressBar pBar10;
    private TextInputEditText editSend4, editSend5, editSend6;
    private FloatingActionButton fabSend3;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_for_staff_send, container, false);

        pBar10 = view.findViewById(R.id.pBar10);
        editSend4 = view.findViewById(R.id.editSend4);
        editSend5 = view.findViewById(R.id.editSend5);
        editSend6 = view.findViewById(R.id.editSend6);
        fabSend3 = view.findViewById(R.id.fabSend3);

        pBar10.setVisibility(View.GONE);

        editSend4.setText("Personel");
        editSend6.setText("Öğretmenler");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences12 = getContext().getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getContext().getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences14.getString("staff_name", preferences12.
                getString("staff_name", ""));
        String student_surname3 = preferences14.getString("staff_surname", preferences12.
                getString("staff_surname", ""));

        fabSend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editSend5.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler);
                    alertDialogBuilder.setMessage(R.string.mesaj_g_ndermek_i_in_ge_erli_bir_mesaj_giriniz);
                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    pBar10.setVisibility(View.VISIBLE);

                    if (!editSend5.getText().toString().equals("")) {
                    String key1 = reference.child("Users").child("Staff").child(student_name3 + " " + student_surname3).child("full_name").child("messages")
                            .child("sent").push().getKey();

                    reference.child("Users").child("Staff").child(student_name3 + " " + student_surname3).child("full_name").child("messages")
                            .child("sent").child(key1).setValue(getString(R.string.sorulan_ki_i_retmenler) + editSend5.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        reference.child("Users").child("Teacher").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                                String key2 = reference.child("Users").child("Teacher").child(snapshot.getKey()).child("full_name")
                                                        .child("messages").child("coming").push().getKey();

                                                reference.child("Users").child("Teacher").child(snapshot.getKey()).child("full_name")
                                                        .child("messages").child("coming").child(key2).setValue(student_name3 + " " + student_surname3 + getString(R.string.personel_) + editSend5.getText().toString())
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task2) {
                                                                if (task2.isSuccessful()) {
                                                                    try {
                                                                        Snackbar.make(view, getString(R.string.mesaj_ba_ar_l_bir_ekilde_g_nderildi), Snackbar.LENGTH_SHORT)
                                                                                .setAction(R.string.tamam, new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View view) {
                                                                                    }
                                                                                }).show();

                                                                        pBar10.setVisibility(View.GONE);
                                                                        editSend5.setText("");
                                                                    }

                                                                    catch (Exception e) {
                                                                    }

                                                                }

                                                                else {
                                                                    try {
                                                                        pBar10.setVisibility(View.GONE);

                                                                        Snackbar.make(view, getString(R.string.mesaj_g_nderilirken_bir_hata_olu_tu), Snackbar.LENGTH_SHORT)
                                                                                .setAction(R.string.tamam, new View.OnClickListener() {
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
                                            pBar10.setVisibility(View.GONE);

                                            Snackbar.make(view, getString(R.string.mesaj_g_nderilirken_bir_hata_olu_tu), Snackbar.LENGTH_SHORT)
                                                    .setAction(R.string.tamam, new View.OnClickListener() {
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

        return view;
    }
}
