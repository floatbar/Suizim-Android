package wafoot.becoming.wafoot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectClassActivity extends AppCompatActivity {

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

        arrayList.add(getString(R.string._9_a));
        arrayList.add(getString(R.string._9_b));
        arrayList.add(getString(R.string._9_c));
        arrayList.add(getString(R.string._9_d));
        arrayList.add(getString(R.string._9_e));
        arrayList.add(getString(R.string._9_f));
        arrayList.add(getString(R.string._9_g));
        arrayList.add(getString(R.string._9_h));
        arrayList.add(getString(R.string._10_a));
        arrayList.add(getString(R.string._10_b));
        arrayList.add(getString(R.string._10_c));
        arrayList.add(getString(R.string._10_d));
        arrayList.add(getString(R.string._10_e));
        arrayList.add(getString(R.string._10_f));
        arrayList.add(getString(R.string._10_g));
        arrayList.add(getString(R.string._10_h));
        arrayList.add(getString(R.string._10_i));
        arrayList.add(getString(R.string._10_j));
        arrayList.add(getString(R.string._11_a));
        arrayList.add(getString(R.string._11_b));
        arrayList.add(getString(R.string._11_c));
        arrayList.add(getString(R.string._11_d));
        arrayList.add(getString(R.string._11_e));
        arrayList.add(getString(R.string._11_f));
        arrayList.add(getString(R.string._11_g));
        arrayList.add(getString(R.string._11_h));
        arrayList.add(getString(R.string._12_a));
        arrayList.add(getString(R.string._12_b));
        arrayList.add(getString(R.string._12_c));
        arrayList.add(getString(R.string._12_d));
        arrayList.add(getString(R.string._12_e));
        arrayList.add(getString(R.string._12_f));
        arrayList.add(getString(R.string._12_g));
        arrayList.add(getString(R.string._12_h));
        arrayList.add(getString(R.string._12_j));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, arrayList);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item1 = adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(SelectClassActivity.this, Create1AccountActivity.class);
                intent.putExtra("item1", item1);

                startActivity(intent);
            }
        });
    }
}