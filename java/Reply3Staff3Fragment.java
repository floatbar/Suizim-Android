package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class Reply3Staff3Fragment extends Fragment {
    
    private HashSet<String> hashSet;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> list;

    private ArrayAdapter<String> adapter;

    private ListView lvSend4;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ImageView imageView14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_reply3_staff3, container, false);

        lvSend4 = view.findViewById(R.id.lvSend4);
        imageView14 = view.findViewById(R.id.imageView14);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences11 = getContext().getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getContext().getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("staff_name", preferences11.
                getString("staff_name", ""));
        String student_surname2 = preferences13.getString("staff_surname", preferences11.
                getString("staff_surname", ""));

        imageView14.setImageResource(R.drawable.attach_blue);

        reference.child("Users").child("Staff").child(student_name2 + " " + student_surname2).child("full_name")
                .child("messages").child("coming").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        arrayList.add(snapshot.getValue().toString());
                        hashSet = new HashSet<>(arrayList);
                        list = new ArrayList<>(hashSet);

                        try {
                            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
                        }

                        catch (Exception e) {
                        }

                        lvSend4.setAdapter(adapter);
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

        lvSend4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String comingMessage = adapterView.getItemAtPosition(i).toString();

                int index1 = comingMessage.indexOf("-");
                int index2 = comingMessage.indexOf(":");

                if (index1 != -1 && index2 != -1) {
                    String partialPersonReplied = comingMessage.substring(0, index1 - 1);
                    String fullPersonReplied = comingMessage.substring(0, index2);

                    Intent intent = new Intent(getActivity(), Reply4Staff4Activity.class);
                    intent.putExtra("replied_partial", partialPersonReplied);
                    intent.putExtra("replied_full", fullPersonReplied);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
