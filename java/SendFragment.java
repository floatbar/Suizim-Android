package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
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

public class SendFragment extends Fragment {

    private TextInputEditText editSend1, editSend2, editSend3;
    private FloatingActionButton fabSend;
    private ProgressBar pBar5;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private String student_class = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_send, container, false);

        editSend1 = view.findViewById(R.id.editSend1);
        editSend2 = view.findViewById(R.id.editSend2);
        editSend3 = view.findViewById(R.id.editSend3);
        fabSend = view.findViewById(R.id.fabSend);
        pBar5 = view.findViewById(R.id.pBar5);

        pBar5.setVisibility(View.GONE);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences1 = getContext().getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getContext().getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.getString("student_surname", ""));

        editSend3.setText(R.string.retmenler3232);

        editSend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), editSend1);
                popupMenu.getMenuInflater().inflate(R.menu.send_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        editSend1.setText(menuItem.getTitle());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        reference.child("Users").child("Student").child(student_name + " " + student_surname).child("full_name").addChildEventListener(new ChildEventListener() {
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

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editSend1.getText().toString().equals("") || editSend2.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler12122131);
                    alertDialogBuilder.setMessage(R.string.mesaj_g_ndermek_i_in_s_n_f_n_z_ve_ge_erli_bir_mesaj_giriniz13121123);
                    alertDialogBuilder.setPositiveButton(R.string.tamam313113, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();

                }

                else {
                    pBar5.setVisibility(View.VISIBLE);

                    if (!editSend2.getText().toString().equals("")) {
                        String key1 = reference.child("Users").child("Student").child(student_name + " " + student_surname).child("full_name")
                                .child("messages").child("sent").push().getKey();

                        reference.child("Users").child("Student").child(student_name + " " + student_surname).child("full_name")
                                .child("messages").child("sent").child(key1).setValue(getString(R.string.sorulan_ki_i_retmenler133131) + editSend2.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            reference.child("Users").child("Teacher").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot snapshot2, @Nullable String previousChildName) {
                                                    String key2 = reference.child("Users").child("Teacher").child(snapshot2.getKey()).child("full_name")
                                                            .child("messages").child("coming").push().getKey();

                                                    reference.child("Users").child("Teacher").child(snapshot2.getKey()).child("full_name")
                                                            .child("messages").child("coming").child(key2).setValue(student_name + " " + student_surname + " - " + student_class + ": " + editSend2.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task2) {
                                                                    if (task2.isSuccessful()) {
                                                                        try {
                                                                            pBar5.setVisibility(View.GONE);
                                                                            editSend2.setText("");
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
                                                                            pBar5.setVisibility(View.GONE);
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
                                                pBar5.setVisibility(View.GONE);
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

        return view;
    }
}
