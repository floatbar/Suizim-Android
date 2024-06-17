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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndividualBasedActivity extends AppCompatActivity {
    
    private Toolbar tbar;
    private ListView lv;

    private StudentsInterface studentsInterface;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_based);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        tbar = findViewById(R.id.tbar21);
        lv = findViewById(R.id.lv20);
        progressBar = findViewById(R.id.progressBar);
        studentsInterface = ApiUtils.getNodejsStudentsInterface();

        setSupportActionBar(tbar);

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

        studentsInterface.showStudentPrint(student_name, student_surname, student_password)
                .enqueue(new Callback<StudentsResponse>() {
                    @Override
                    public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                        if (response.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);

                            arrayList.add(getString(R.string.bir_g_ndeki_yakla_k_ortalama_su_ayak_iziniz3) + response.body().getMessage() + getString(R.string.litre_g_n4));
                            arrayList.add(getString(R.string._1_su_tasarrufu));
                            arrayList.add(getString(R.string.ortalama_du_s_renizi_k_salt_n));
                            arrayList.add(getString(R.string.su_tasarruflu_cihazlar_ve_armat_rleri_tercih_edin));
                            arrayList.add(getString(R.string._2_s_rd_r_lebilir_tar_m_ve_beslenme));
                            arrayList.add(getString(R.string.su_ayak_izi_y_ksek_olan_r_nleri_t_ketmek_yerine_d_k_olanlar_tercih_edin));
                            arrayList.add(getString(R.string.et_t_ketimini_azalt_p_m_mk_nse_organik_yerel_ve_s_rd_r_lebilir_g_da_kaynaklar_na_y_nelin));
                            arrayList.add(getString(R.string._3_su_kaynaklar_n_koruma));
                            arrayList.add(getString(R.string.su_kaynaklar_na_zarar_verebilecek_t_m_faaliyetlerden_ka_n_n));
                            arrayList.add(getString(R.string.su_havzalar_n_ve_ekosistemleri_korumak_i_in_yerel_su_koruma_projelerine_kat_labilir_veya_bunlar_destekleyin));
                            arrayList.add(getString(R.string._4_geri_d_n_m_ve_at_k_y_netimi));
                            arrayList.add(getString(R.string.at_klar_ayr_t_r_n_ve_geri_d_n_m_sistemlerini_kullan_n));
                            arrayList.add(getString(R.string.plastik_kullan_m_n_azalt_n_ve_geri_d_n_ml_r_nleri_tercih_edin));
                            arrayList.add(getString(R.string._5_enerji_verimlili_ini_art_rma));
                            arrayList.add(getString(R.string.enerji_verimlili_i_y_ksek_olan_cihazlar_kullanarak_enerji_t_ketimini_azalt_n));
                            arrayList.add(getString(R.string.temiz_enerji_kaynaklar_na_ge_i_yaparak_suyu_koruyun));
                            arrayList.add(getString(R.string._6_daha_az_su_yo_un_r_nleri_tercih_etme));
                            arrayList.add(getString(R.string.malat_s_reci_boyunca_daha_az_su_kullan_lan_r_nleri_tercih_edin));
                            arrayList.add(getString(R.string._7_bah_e_sulamas_n_ak_ll_ca_yapma));
                            arrayList.add(getString(R.string.bah_e_sulamas_n_ak_ll_sulama_sistemleri_ve_ya_mur_suyu_toplama_y_ntemleri_ile_optimize_edin));
                            arrayList.add(getString(R.string.bitkilerinizi_uygun_bir_ekilde_sulayarak_su_israf_n_nleyin));
                            arrayList.add(getString(R.string._8_tek_kullan_ml_k_r_nleri_s_n_rlama));
                            arrayList.add(getString(R.string.tek_kullan_ml_k_plastik_r_nlerden_ka_nmaya_zen_g_sterin));
                            arrayList.add(getString(R.string.kendi_ta_nabilir_su_i_elerinizi_ve_kahve_fincanlar_n_z_kullanarak_tek_kullan_ml_k_ambalajlar_azalt_n));
                            arrayList.add(getString(R.string._9_ev_temizli_inde_do_a_dostu_r_nleri_kullanma));
                            arrayList.add(getString(R.string.ev_temizli_i_i_in_do_a_dostu_ve_evre_i_in_daha_az_zararl_olan_r_nleri_tercih_edin));
                            arrayList.add(getString(R.string._10_toplumda_fark_ndal_k_olu_turma));
                            arrayList.add(getString(R.string.su_tasarrufu_konusunda_toplumunuzda_fark_ndal_k_yarat_n));
                            arrayList.add(getString(R.string.su_tasarrufunu_ele_alan_seminerler_d_zenleyin));
                            arrayList.add(getString(R.string.sosyal_medya_zerinden_bilgi_payla_n_ve_evresel_sorunlara_dikkat_ekmek_i_in_yerel_projelere_kat_l_n));

                            arrayAdapter = new ArrayAdapter<>(IndividualBasedActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1, arrayList);
                            lv.setAdapter(arrayAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<StudentsResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(IndividualBasedActivity.this, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
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
