package wafoot.becoming.wafoot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class InfoFragment extends Fragment {

    private ListView lvInfo;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        lvInfo = view.findViewById(R.id.lvInfo);

        arrayList.add(getString(R.string.suizim_uygulamas_na_ait_yaz_l_m_bilgileri));
        arrayList.add(getString(R.string._1_uygulamay_geli_tirmek_in_kullan_lan_programlama_dilleri));
        arrayList.add(getString(R.string.suizim_uygulamas_frontend_tasar_m_taraf_nda_java_backend_sunucu_taraf_nda_ise_php_laravel_ve_javascript_node_js_olmak_zere_toplamda_3_tane_programlama_diliyle_geli_tirilmi_tir));
        arrayList.add(getString(R.string._2_uygulamay_geli_tirmek_in_yaz_lan_kod_miktar));
        arrayList.add(getString(R.string.uygulaman_n_geli_tirilmesi_i_in_backend_ve_frontend_taraflar_dahil_olmak_zere_toplamda_yakla_k_15_bin_sat_r_kod_yaz_lm_t_r));
        arrayList.add(getString(R.string._3_uygulama_sunucular));
        arrayList.add(getString(R.string.uygulamaya_ait_iki_sunucu_makinelerinden_biri_lokasyon_olarak_amerika_birle_ik_devletleri_ndeki_veri_merkezlerinde_bulunmaktad_r_di_er_sunucu_ise_t_rkiye_bursa_daki_veri_merkezlerinde_bulunmaktad_r));
        arrayList.add(getString(R.string._4_verilerin_saklanmas));
        arrayList.add(getString(R.string.girdi_iniz_t_m_veriler_uygulamaya_ait_veri_tabanlar_nda_g_venli_bir_ekilde_depolanmaktad_r_ve_saklanmaktad_r));
        arrayList.add(getString(R.string.uygulama_i_erisinde_a_m_oldu_unuz_t_m_hesaplar_binlerce_karakter_uzunluktaki_token_ad_verilen_zel_ifreler_ile_suizim_veri_tabanlar_nda_g_venli_bir_ekilde_saklanmaktad_r));
        arrayList.add(getString(R.string.ayr_ca_uygulamaya_girmi_oldu_unuz_t_m_ifreler_g_venli_ve_karma_k_algoritmalar_ile_ifrelenmektedir_bu_nedenle_ifrelerinize_siz_haricinde_uygulama_kurucusu_dahil_olmak_zere_kimse_eri_emez));
        arrayList.add(getString(R.string._5_uygulama_kodlar_n_n_eri_ilebilirli_i));
        arrayList.add(getString(R.string.suizim_uygulamas_a_k_kaynak_kodlu_bir_uygulamad_r_bkz_open_source_code_yani_uygulaman_n_frontend_taraf_ndaki_t_m_java_kodlar_yak_n_zamanda_herkes_i_in_eri_ime_sunulacakt_r));
        arrayList.add(getString(R.string.backend_taraf_backend_kodlar_n_n_yaz_l_msal_olarak_g_venli_bir_ekilde_saklanmas_gerekti_i_bir_taraft_r_bu_nedenle_g_venlik_sebeplerinden_dolay_uygulamaya_ait_hi_bir_backend_kodu_ve_hi_bir_sunucu_bilgisi_eri_ime_sunulmayacakt_r));

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        lvInfo.setAdapter(adapter);

        return view;
    }
}