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

public class CalculationFragment extends Fragment {

    private FloatingActionButton fab1;
    private TextInputEditText edit1;
    private TextInputEditText edit2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_calculation, container, false);

        fab1 = view.findViewById(R.id.fab1);
        edit1 = view.findViewById(R.id.edit1);
        edit2 = view.findViewById(R.id.edit2);
        edit1.setMovementMethod(null);

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), edit1);
                popupMenu.getMenuInflater().inflate(R.menu.school_position2, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String itemTitle = menuItem.getTitle().toString();
                        edit1.setText(itemTitle);
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit1.getText().toString().trim().equals("") || edit2.getText().toString().trim().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler);
                    alertDialogBuilder.setMessage(R.string.heatmaply_i_lemi_i_in_t_m_alanlar_doldurunuz);

                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.position), Context.MODE_PRIVATE);
                    startActivity(new Intent(getActivity(), FirstQuestionActivity.class));

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.generalquestion),
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("position", preferences.getString("position", ""));
                    editor.putString("age", edit2.getText().toString());
                    editor.apply();

                }
            }
        });

        return view;
    }
}