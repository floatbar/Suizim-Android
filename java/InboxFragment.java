package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class InboxFragment extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ArrayList<String> arrayList1 = new ArrayList<>();
    private ArrayList<String> arrayList2 = new ArrayList<>();
    private ArrayList<String> arrayList3 = new ArrayList<>();

    private HashSet<String> hashSet1;
    private HashSet<String> hashSet2;
    private HashSet<String> hashSet3;

    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;

    private MessageAdapter adapter;

    private RecyclerView rvIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        rvIn = view.findViewById(R.id.rvIn);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences1 = getContext().getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getContext().getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));

        SharedPreferences preferences11 = getContext().getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getContext().getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));

        SharedPreferences preferences12 = getContext().getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getContext().getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences14.getString("staff_name", preferences12.
                getString("staff_name", ""));
        String student_surname3 = preferences14.getString("staff_surname", preferences12.
                getString("staff_surname", ""));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Position", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
            reference.child("Users").child("Student").child(student_name + " " + student_surname).child("full_name")
                    .child("messages").child("coming").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                arrayList1.add(snapshot.getValue().toString());
                                hashSet1 = new HashSet<>(arrayList1);
                                list1 = new ArrayList<>(hashSet1);

                                try {
                                    adapter = new MessageAdapter(getActivity(), list1);
                                }

                                catch (Exception e) {
                                }

                                rvIn.setHasFixedSize(true);
                                rvIn.setLayoutManager(new LinearLayoutManager(getActivity()));
                                rvIn.setAdapter(adapter);
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

        if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
            reference.child("Users").child("Teacher").child(student_name2 + " " + student_surname2).child("full_name")
                    .child("messages").child("coming").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot2, @Nullable String previousChildName) {
                            arrayList2.add(snapshot2.getValue().toString());
                            hashSet2 = new HashSet<>(arrayList2);
                            list2 = new ArrayList<>(hashSet2);

                            try {
                                adapter = new MessageAdapter(getActivity(), list2);
                            }

                            catch (Exception e) {
                            }

                            rvIn.setHasFixedSize(true);
                            rvIn.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvIn.setAdapter(adapter);
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

        if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
            reference.child("Users").child("Staff").child(student_name3 + " " + student_surname3).child("full_name")
                    .child("messages").child("coming").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot3, @Nullable String previousChildName) {
                            arrayList3.add(snapshot3.getValue().toString());
                            hashSet3 = new HashSet<>(arrayList3);
                            list3 = new ArrayList<>(hashSet3);

                            try {
                                adapter = new MessageAdapter(getActivity(), list3);
                            }

                            catch (Exception e) {
                            }

                            rvIn.setHasFixedSize(true);
                            rvIn.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvIn.setAdapter(adapter);
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

        return view;
    }
}