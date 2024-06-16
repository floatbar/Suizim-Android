package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DeleteAccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_delete_account, container, false);
        Button button = view.findViewById(R.id.button);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Position", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                alertDialogBuilder.setIcon(R.drawable.delete);
                alertDialogBuilder.setTitle(R.string.hesap_silme_lemi);
                alertDialogBuilder.setMessage(R.string.hesab_n_z_silmek_istedi_inizden_emin_misiniz);

                alertDialogBuilder.setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
                            startActivity(new Intent(getActivity(), VerificationActivity.class));
                        }

                        if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
                            startActivity(new Intent(getActivity(), Verification2TeacherActivity.class));
                        }

                        if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
                            startActivity(new Intent(getActivity(), Verification3AdminActivity.class));
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.vazge, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialogBuilder.create().show();
            }
        });

        return view;
    }
}