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

public class ChatRoomFragment extends Fragment {
    private TextInputEditText editChat1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_chat_room, container, false);
        editChat1 = view.findViewById(R.id.editChat1);
        TextInputEditText editChat2 = view.findViewById(R.id.editChat2);
        FloatingActionButton fabChat = view.findViewById(R.id.fabChat);

        SharedPreferences preferences = getContext().getSharedPreferences("Position", Context.MODE_PRIVATE);

        editChat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProxyServerActivity.class));
            }
        });

        editChat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), editChat1);
                popupMenu.getMenuInflater().inflate(R.menu.school_name, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.ykal) {
                            editChat1.setText(R.string.yusuf_kalkavan_anadolu_lisesi);
                        }

                        return false;

                    }
                });

                popupMenu.show();
            }
        });

        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editChat1.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(getString(R.string.yetersiz_bilgiler));
                    alertDialogBuilder.setMessage(R.string.leti_im_ve_destek_b_l_m_ne_kat_lmak_i_in_en_az_ndan_okul_pozisyonunuzu_giriniz);
                    alertDialogBuilder.setPositiveButton(getString(R.string.tamam), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    if (preferences.getString("position", "").equals(getString(R.string.renci))) {
                        startActivity(new Intent(getActivity(), Chat2AdminActivity.class));
                    }

                    if (preferences.getString("position", "").equals(getString(R.string.retmen))) {
                        startActivity(new Intent(getActivity(), Contact3Teacher3Activity.class));
                    }

                    if (preferences.getString("position", "").equals(getString(R.string.personel))) {
                        startActivity(new Intent(getActivity(), ForStaffActivity.class));
                    }
                }
            }
        });

        return view;
    }
}