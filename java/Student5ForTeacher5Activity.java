package wafoot.becoming.wafoot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student5ForTeacher5Activity extends AppCompatActivity {

    private Toolbar tbar;
    private ListView lv;

    private StudentsInterface studentsInterface;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private StaffInterface staffInterface;
    private TeachersInterface teachersInterface;

    private List<Student> studentList;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student5_for_teacher5);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        tbar = findViewById(R.id.tbar21);
        lv = findViewById(R.id.lv20);
        progressBar = findViewById(R.id.progressBar);

        setSupportActionBar(tbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.orange));

        studentsInterface = ApiUtils.getStudentsInterface();
        staffInterface = ApiUtilsStaff.getStaffInterface();
        teachersInterface = ApiUtilsTeachers.getTeachersInterface();

        progressBar.setVisibility(View.VISIBLE);

        studentsInterface.showAllStudents().enqueue(new Callback<StudentsResponse>() {
            @Override
            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                if (response.isSuccessful()) {
                    studentList = new ArrayList<>();
                    studentList = response.body().getList();

                    for (Student student: studentList) {
                        arrayList.add(student.getStudent_name() + " " + student.getStudent_surname() + " - " +
                                student.getStudent_class() + ": " + student.getWater_foot_print() + " litre/gün");
                    }

                    teachersInterface.showAllTeachers().enqueue(new Callback<TeachersResponse>() {
                        @Override
                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response2) {
                            if (response2.isSuccessful()) {
                                List<Teacher> teacherList;
                                teacherList = response2.body().getList();

                                for (Teacher teacher: teacherList) {
                                    arrayList.add(teacher.getTeacher_name() + " " + teacher.getTeacher_surname() + " - " +
                                            teacher.getTeacher_branch() + ": " + teacher.getWater_foot_print() + " litre/gün");
                                }

                                staffInterface.showAllStaff().enqueue(new Callback<StaffResponse>() {
                                    @Override
                                    public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response3) {
                                        if (response3.isSuccessful()) {
                                            progressBar.setVisibility(View.GONE);

                                            List<Staff> staffList;
                                            staffList = response3.body().getList();

                                            for (Staff staff: staffList) {
                                                arrayList.add(staff.getStaff_name() + " " + staff.getStaff_surname() + getString(R.string.personel1312112) +
                                                        staff.getWater_foot_print() + " litre/gün");
                                            }

                                            arrayAdapter = new ArrayAdapter<>(Student5ForTeacher5Activity.this, android.R.layout.simple_list_item_1,
                                                    android.R.id.text1, arrayList);
                                            lv.setAdapter(arrayAdapter);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<StaffResponse> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Student5ForTeacher5Activity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu1331), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Student5ForTeacher5Activity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu1331), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Student5ForTeacher5Activity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu1331), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    arrayAdapter.getFilter().filter(query);
                }

                catch (Exception e) {
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    arrayAdapter.getFilter().filter(newText);
                }

                catch (Exception e) {
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}