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

public class SchoolBasedActivity extends AppCompatActivity {

    private Toolbar tbar;
    private ListView lv;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private SchoolInterface schoolInterface;

    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private int s5;
    private int s6;
    private int s7;
    private int s8;
    private int s9;
    private int s10;
    private int s11;
    private int s12;
    private int s13;
    private int s14;
    private int s15;
    private int s16;
    private int s17;
    private int s18;
    private int s19;
    private int s20;
    private int s21;
    private int s22;
    private int s23;
    private int s24;
    private int s25;
    private int s26;
    private int s27;
    private int s28;
    private int s29;
    private int s30;
    private int s31;
    private int s32;
    private int s33;
    private int s34;
    private int s35;
    private int s36;
    private int b37;
    private int b38;
    private int b39;
    private int b40;
    private int b1;
    private int b2;
    private int b3;
    private int b4;
    private int b5;
    private int b6;
    private int b7;
    private int b8;
    private int b9;
    private int b10;
    private int b11;
    private int b12;
    private int b13;
    private int b14;
    private int b15;
    private int b16;
    private int b17;
    private int b18;
    private int b19;
    private int b20;
    private int b21;
    private int b22;
    private int b23;
    private int b24;
    private int b25;
    private int b26;
    private int b27;
    private int b28;
    private int b29;
    private int b30;
    private int b31;
    private int b32;
    private int b33;
    private int b34;
    private int b35;
    private int b36;
    private int s37;
    private int s38;
    private int s39;
    private int s40;

    private List<School> stringList2;
    private List<School> schools2 = new ArrayList<>();
    private List<School> schools3 = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_based);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        tbar = findViewById(R.id.tbar21);
        progressBar = findViewById(R.id.progressBar);

        schoolInterface = ApiUtilsSchool.getSchoolInterface();

        lv = findViewById(R.id.lv20);

        setSupportActionBar(tbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.orange));

        progressBar.setVisibility(View.VISIBLE);

        schoolInterface.showWholeSchoolPrint().enqueue(new Callback<SchoolResponse>() {
            @Override
            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response2) {
                if (response2.isSuccessful()) {
                    arrayList.add(getString(R.string.yusuf_kalkavan_anadolu_lisesinin_g_nl_k_su_ayak_izi_ortalamas) +
                            response2.body().getSuccess() + getString(R.string.litre_g_n23323));

                    schoolInterface.showAllSchoolPrint().enqueue(new Callback<SchoolResponse>() {
                        @Override
                        public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
                            if (response.isSuccessful()) {
                                stringList2 = new ArrayList<>();
                                stringList2 = response.body().getList();

                                for (School school: stringList2) {
                                    if (school.getStudent_class().equals("9-A") && school.getWater_foot_print() != null) {
                                        b1 += Integer.parseInt(school.getWater_foot_print());
                                        s1++;
                                    }

                                    if (school.getStudent_class().equals("9-B") && school.getWater_foot_print() != null) {
                                        b2 += Integer.parseInt(school.getWater_foot_print());
                                        s2++;
                                    }

                                    if (school.getStudent_class().equals("9-C") && school.getWater_foot_print() != null) {
                                        b3 += Integer.parseInt(school.getWater_foot_print());
                                        s3++;
                                    }

                                    if (school.getStudent_class().equals("9-D") && school.getWater_foot_print() != null) {
                                        b4 += Integer.parseInt(school.getWater_foot_print());
                                        s4++;
                                    }

                                    if (school.getStudent_class().equals("9-D") && school.getWater_foot_print() != null) {
                                        b4 += Integer.parseInt(school.getWater_foot_print());
                                        s4++;
                                    }

                                    if (school.getStudent_class().equals("9-E") && school.getWater_foot_print() != null) {
                                        b5 += Integer.parseInt(school.getWater_foot_print());
                                        s5++;
                                    }

                                    if (school.getStudent_class().equals("9-F") && school.getWater_foot_print() != null) {
                                        b6 += Integer.parseInt(school.getWater_foot_print());
                                        s6++;
                                    }

                                    if (school.getStudent_class().equals("9-G") && school.getWater_foot_print() != null) {
                                        b7 += Integer.parseInt(school.getWater_foot_print());
                                        s7++;
                                    }

                                    if (school.getStudent_class().equals("9-H") && school.getWater_foot_print() != null) {
                                        b8 += Integer.parseInt(school.getWater_foot_print());
                                        s8++;
                                    }

                                    if (school.getStudent_class().equals("10-A") && school.getWater_foot_print() != null) {
                                        b9 += Integer.parseInt(school.getWater_foot_print());
                                        s9++;
                                    }

                                    if (school.getStudent_class().equals("10-B") && school.getWater_foot_print() != null) {
                                        b10 += Integer.parseInt(school.getWater_foot_print());
                                        s10++;
                                    }

                                    if (school.getStudent_class().equals("10-C") && school.getWater_foot_print() != null) {
                                        b11 += Integer.parseInt(school.getWater_foot_print());
                                        s11++;
                                    }

                                    if (school.getStudent_class().equals("10-D") && school.getWater_foot_print() != null) {
                                        b12 += Integer.parseInt(school.getWater_foot_print());
                                        s12++;
                                    }

                                    if (school.getStudent_class().equals("10-E") && school.getWater_foot_print() != null) {
                                        b13 += Integer.parseInt(school.getWater_foot_print());
                                        s13++;
                                    }

                                    if (school.getStudent_class().equals("10-F") && school.getWater_foot_print() != null) {
                                        b14 += Integer.parseInt(school.getWater_foot_print());
                                        s14++;
                                    }

                                    if (school.getStudent_class().equals("10-G") && school.getWater_foot_print() != null) {
                                        b15 += Integer.parseInt(school.getWater_foot_print());
                                        s15++;
                                    }

                                    if (school.getStudent_class().equals("10-H") && school.getWater_foot_print() != null) {
                                        b16 += Integer.parseInt(school.getWater_foot_print());
                                        s16++;
                                    }

                                    if (school.getStudent_class().equals("10-I") && school.getWater_foot_print() != null) {
                                        b17 += Integer.parseInt(school.getWater_foot_print());
                                        s17++;
                                    }

                                    if (school.getStudent_class().equals("10-J") && school.getWater_foot_print() != null) {
                                        b18 += Integer.parseInt(school.getWater_foot_print());
                                        s18++;
                                    }

                                    if (school.getStudent_class().equals("11-A") && school.getWater_foot_print() != null) {
                                        b19 += Integer.parseInt(school.getWater_foot_print());
                                        s19++;
                                    }

                                    if (school.getStudent_class().equals("11-B") && school.getWater_foot_print() != null) {
                                        b20 += Integer.parseInt(school.getWater_foot_print());
                                        s20++;
                                    }

                                    if (school.getStudent_class().equals("11-C") && school.getWater_foot_print() != null) {
                                        b21 += Integer.parseInt(school.getWater_foot_print());
                                        s21++;
                                    }

                                    if (school.getStudent_class().equals("11-D") && school.getWater_foot_print() != null) {
                                        b22 += Integer.parseInt(school.getWater_foot_print());
                                        s22++;
                                    }

                                    if (school.getStudent_class().equals("11-E") && school.getWater_foot_print() != null) {
                                        b23 += Integer.parseInt(school.getWater_foot_print());
                                        s23++;
                                    }

                                    if (school.getStudent_class().equals("11-F") && school.getWater_foot_print() != null) {
                                        b24 += Integer.parseInt(school.getWater_foot_print());
                                        s24++;
                                    }

                                    if (school.getStudent_class().equals("11-G") && school.getWater_foot_print() != null) {
                                        b25 += Integer.parseInt(school.getWater_foot_print());
                                        s25++;
                                    }

                                    if (school.getStudent_class().equals("11-H") && school.getWater_foot_print() != null) {
                                        b26 += Integer.parseInt(school.getWater_foot_print());
                                        s26++;
                                    }

                                    if (school.getStudent_class().equals("12-A") && school.getWater_foot_print() != null) {
                                        b27 += Integer.parseInt(school.getWater_foot_print());
                                        s27++;
                                    }

                                    if (school.getStudent_class().equals("12-B") && school.getWater_foot_print() != null) {
                                        b28 += Integer.parseInt(school.getWater_foot_print());
                                        s28++;
                                    }

                                    if (school.getStudent_class().equals("12-C") && school.getWater_foot_print() != null) {
                                        b29 += Integer.parseInt(school.getWater_foot_print());
                                        s29++;
                                    }

                                    if (school.getStudent_class().equals("12-D") && school.getWater_foot_print() != null) {
                                        b30 += Integer.parseInt(school.getWater_foot_print());
                                        s30++;
                                    }

                                    if (school.getStudent_class().equals("12-E") && school.getWater_foot_print() != null) {
                                        b31 += Integer.parseInt(school.getWater_foot_print());
                                        s31++;
                                    }

                                    if (school.getStudent_class().equals("12-F") && school.getWater_foot_print() != null) {
                                        b32 += Integer.parseInt(school.getWater_foot_print());
                                        s32++;
                                    }

                                    if (school.getStudent_class().equals("12-F") && school.getWater_foot_print() != null) {
                                        b32 += Integer.parseInt(school.getWater_foot_print());
                                        s32++;
                                    }

                                    if (school.getStudent_class().equals("12-G") && school.getWater_foot_print() != null) {
                                        b33 += Integer.parseInt(school.getWater_foot_print());
                                        s33++;
                                    }

                                    if (school.getStudent_class().equals("12-H") && school.getWater_foot_print() != null) {
                                        b34 += Integer.parseInt(school.getWater_foot_print());
                                        s34++;
                                    }

                                    if (school.getStudent_class().equals("12-J") && school.getWater_foot_print() != null) {
                                        b35 += Integer.parseInt(school.getWater_foot_print());
                                        s35++;
                                    }
                                }

                                try {
                                    arrayList.add("9-A sınıfının günlük su ayak izi ortalaması: " + b1 / s1 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-B sınıfının günlük su ayak izi ortalaması: " + b2 / s2 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-C sınıfının günlük su ayak izi ortalaması: " + b3 / s3 + " litre/gün");
                                }

                                catch (Exception e) {

                                }

                                try {
                                    arrayList.add("9-D sınıfının günlük su ayak izi ortalaması: " + b4 / s4 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-E sınıfının günlük su ayak izi ortalaması: " + b5 / s5 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-F sınıfının günlük su ayak izi ortalaması: " + b6 / s6 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-G sınıfının günlük su ayak izi ortalaması: " + b7 / s7 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("9-H sınıfının günlük su ayak izi ortalaması: " + b8 / s8 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-A sınıfının günlük su ayak izi ortalaması: " + b9 / s9 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-B sınıfının günlük su ayak izi ortalaması: " + b10 / s10 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-C sınıfının günlük su ayak izi ortalaması: " + b11 / s11 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-D sınıfının günlük su ayak izi ortalaması: " + b12 / s12 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-E sınıfının günlük su ayak izi ortalaması: " + b13 / s13 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-F sınıfının günlük su ayak izi ortalaması: " + b14 / s14 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-G sınıfının günlük su ayak izi ortalaması: " + b15 / s15 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-H sınıfının günlük su ayak izi ortalaması: " + b16 / s16 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-I sınıfının günlük su ayak izi ortalaması: " + b17 / s17 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("10-J sınıfının günlük su ayak izi ortalaması: " + b18 / s18 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-A sınıfının günlük su ayak izi ortalaması: " + b19 / s19 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-B sınıfının günlük su ayak izi ortalaması: " + b20 / s20 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-C sınıfının günlük su ayak izi ortalaması: " + b21 / s21 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-D sınıfının günlük su ayak izi ortalaması: " + b22 / s22 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-E sınıfının günlük su ayak izi ortalaması: " + b23 / s23 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-F sınıfının günlük su ayak izi ortalaması: " + b24 / s24 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-G sınıfının günlük su ayak izi ortalaması: " + b25 / s25 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("11-H sınıfının günlük su ayak izi ortalaması: " + b26 / s26 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-A sınıfının günlük su ayak izi ortalaması: " + b27 / s27 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-B sınıfının günlük su ayak izi ortalaması: " + b28 / s28 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-C sınıfının günlük su ayak izi ortalaması: " + b29 / s29 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-D sınıfının günlük su ayak izi ortalaması: " + b30 / s30 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-E sınıfının günlük su ayak izi ortalaması: " + b31 / s31 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-F sınıfının günlük su ayak izi ortalaması: " + b32 / s32 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-G sınıfının günlük su ayak izi ortalaması: " + b33 / s33 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-H sınıfının günlük su ayak izi ortalaması: " + b34 / s34 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                try {
                                    arrayList.add("12-J sınıfının günlük su ayak izi ortalaması: " + b35 / s35 + " litre/gün");
                                }

                                catch (Exception e) {
                                }

                                schoolInterface.showGender().enqueue(new Callback<SchoolResponse>() {
                                    @Override
                                    public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response3) {
                                        if (response3.isSuccessful()) {
                                            schools2 = new ArrayList<>();
                                            schools2 = response3.body().getList();

                                            for (School school: schools2) {
                                                if (school.getGender().equals(getString(R.string.erkek))) {
                                                    b36 += Integer.parseInt(school.getWater_foot_print());
                                                    s36++;
                                                }

                                                if (school.getGender().equals(getString(R.string.kad_n))) {
                                                    b37 += Integer.parseInt(school.getWater_foot_print());
                                                    s37++;
                                                }
                                            }

                                            try {

                                                arrayList.add(getString(R.string.erkek_bireylere_ait_g_nl_k_su_ayak_izi_ortalamas) + b36/s36 +
                                                        getString(R.string.litre_g_n11221));
                                            }

                                            catch (Exception e) {
                                            }

                                            try {

                                                arrayList.add(getString(R.string.kad_n_bireylere_ait_g_nl_k_su_ayak_izi_ortalamas) + b37/s37 +
                                                        getString(R.string.litre_g_n32332));
                                            }

                                            catch (Exception e) {
                                            }

                                            schoolInterface.showAge().enqueue(new Callback<SchoolResponse>() {
                                                @Override
                                                public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response4) {
                                                    if (response4.isSuccessful()) {
                                                        progressBar.setVisibility(View.GONE);

                                                        schools3 = new ArrayList<>();
                                                        schools3 = response4.body().getList();

                                                        for (School school: schools3) {
                                                            if (Integer.parseInt(school.getAge()) >= 13 && Integer.parseInt(school.getAge()) <= 15) {
                                                                b38 += Integer.parseInt(school.getWater_foot_print());
                                                                s38++;
                                                            }

                                                            if (Integer.parseInt(school.getAge()) >= 16 && Integer.parseInt(school.getAge()) <= 18) {
                                                                b39 += Integer.parseInt(school.getWater_foot_print());
                                                                s39++;
                                                            }

                                                            if (Integer.parseInt(school.getAge()) >= 19) {
                                                                b40 += Integer.parseInt(school.getWater_foot_print());
                                                                s40++;
                                                            }
                                                        }

                                                        try {
                                                            arrayList.add(getString(R.string._13_15_ya_aral_na_ait_bireylerin_g_nl_k_ortalama_su_ayak_izi) +
                                                                    b38/s38 + getString(R.string.litre_g_n123313221));
                                                        }

                                                        catch (Exception e) {
                                                        }

                                                        try {
                                                            arrayList.add(getString(R.string._16_18_ya_aral_na_ait_bireylerin_g_nl_k_ortalama_su_ayak_izi) +
                                                                    b39/s39 + getString(R.string.litre_g_n12122));
                                                        }

                                                        catch (Exception e) {
                                                        }

                                                        try {
                                                            arrayList.add(getString(R.string._19_ve_st_ya_aral_na_ait_bireylerin_g_nl_k_ortalama_su_ayak_izi22112) +
                                                                    b40/s40 + getString(R.string.litre_g_n1221));
                                                        }

                                                        catch (Exception e) {
                                                        }

                                                        arrayAdapter = new ArrayAdapter<>(SchoolBasedActivity.this, android.R.layout.simple_list_item_1,
                                                                android.R.id.text1, arrayList);
                                                        lv.setAdapter(arrayAdapter);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<SchoolResponse> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(SchoolBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2211212), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SchoolResponse> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(SchoolBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2211212), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<SchoolResponse> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SchoolBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2211212), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SchoolResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SchoolBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2211212), Toast.LENGTH_SHORT).show();
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