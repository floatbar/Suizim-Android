package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class FootPrintResultFragment extends Fragment {
    
    private TextInputEditText edit15, edit16;
    private FloatingActionButton fab3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_foot_print_result, container, false);

        edit15 = view.findViewById(R.id.edit15);
        edit16 = view.findViewById(R.id.edit16);
        fab3 = view.findViewById(R.id.fab3);

        edit15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), edit15);
                popupMenu.getMenuInflater().inflate(R.menu.school_name, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.ykal) {
                            edit15.setText(getString(R.string.yusuf_kalkavan_anadolu_lisesi));
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        edit16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProxyServerActivity.class));
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit15.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler);
                    alertDialogBuilder.setMessage(R.string.hesaplama_sonucunu_g_rmek_i_in_en_az_ndan_okul_isminizi_giriniz);
                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("Position", Context.MODE_PRIVATE);
                    if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
                        startActivity(new Intent(getActivity(), ResultScreenActivity.class));
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
                        startActivity(new Intent(getActivity(), Result2ScreenTeacherActivity.class));
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
                        startActivity(new Intent(getActivity(), Result3ScreenAdminActivity.class));
                    }
                }
            }
        });

        return view;
    }
}
