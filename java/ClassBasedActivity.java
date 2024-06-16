package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.SharedPreferences;
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

public class ClassBasedActivity extends AppCompatActivity {

    private StudentsInterface studentsInterface;

    private ListView lv;

    private final ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private int value = 0;

    private final List<String> stringList = new ArrayList<>();
    private final List<String> studentList = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_based);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        studentsInterface = ApiUtils.getNodejsStudentsInterface();
        progressBar = findViewById(R.id.progressBar);

        Toolbar tbar = findViewById(R.id.tbar21);
        setSupportActionBar(tbar);

        lv = findViewById(R.id.lv20);

        getWindow().setStatusBarColor(getResources().getColor(R.color.orange));

        SharedPreferences preferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
        SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));
        String student_password = preferences.getString("student_password", preferences1.
                getString("studentPassword", ""));

        progressBar.setVisibility(View.VISIBLE);

        studentsInterface.showStudentClass(student_name, student_surname, student_password).enqueue(new Callback<StudentsResponse>() {
            @Override
            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                if (response.isSuccessful()) {
                    studentsInterface.showAritmeticClassPrint(response.body().getStudent_class()).enqueue(new Callback<StudentsResponse>() {
                        @Override
                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response2) {
                            if (response2.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                List<Student> students = response2.body().getList();

                                for (Student student: students) {
                                    for (int i = 0; i < students.size(); i++) {
                                        studentList.add(i, student.getWater_foot_print());
                                        stringList.add(student.getStudent_class());
                                        value += Integer.parseInt(student.getWater_foot_print());
                                    }
                                }

                                try {

                                    arrayList.add(stringList.get(0) + getString(R.string.s_n_f_n_n_g_nl_k_su_ayak_izi_ortalamas)
                                            + value/studentList.size() + getString(R.string.litre_g_n));
                                }

                                catch (Exception e) {
                                }

                                arrayAdapter = new ArrayAdapter<>(ClassBasedActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        android.R.id.text1, arrayList);
                                lv.setAdapter(arrayAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ClassBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ClassBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
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