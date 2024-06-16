package wafoot.becoming.wafoot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectBranchActivity extends AppCompatActivity {

    private ListView listview;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        listview = findViewById(R.id.listview);

        arrayList.add(getString(R.string.almanca));
        arrayList.add(getString(R.string.beden_e_itimi));
        arrayList.add(getString(R.string.biyoloji));
        arrayList.add(getString(R.string.co_rafya));
        arrayList.add(getString(R.string.din_k_lt_r_ve_ahlak_bilgisi));
        arrayList.add(getString(R.string.donan_m_ve_yaz_l_m));
        arrayList.add(getString(R.string.felsefe));
        arrayList.add(getString(R.string.fizik));
        arrayList.add(getString(R.string.g_rsel_sanatlar));
        arrayList.add(getString(R.string.ngilizce));
        arrayList.add(getString(R.string.kimya));
        arrayList.add(getString(R.string.matematik));
        arrayList.add(getString(R.string.tarih));
        arrayList.add(getString(R.string.t_rk_dili_ve_edebiyat));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, arrayList);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item2 = adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(SelectBranchActivity.this, Create2AccountTeacherActivity.class);
                intent.putExtra("item2", item2);

                startActivity(intent);
            }
        });
    }
}