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
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutFragment extends Fragment {

    private ProgressBar progressBar;

    private StudentsInterface studentsInterface;
    private TeachersInterface teachersInterface;
    private StaffInterface staffInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Position", Context.MODE_PRIVATE);
        SharedPreferences preferences10 = getContext().getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
        SharedPreferences preferences1 = getContext().getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getContext().getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));
        String student_password = preferences10.getString("student_password", preferences1.
                getString("studentPassword", ""));

        SharedPreferences preferences = getContext().getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getContext().getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getContext().getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));
        String student_password2 = preferences.getString("teacher_password", preferences11.
                getString("teacher_password", ""));

        SharedPreferences preferences2 = getContext().getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences12 = getContext().getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getContext().getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences12.getString("staff_name", preferences14.
                getString("staff_name", ""));
        String student_surname3 = preferences12.getString("staff_surname", preferences14.
                getString("staff_surname", ""));
        String student_password3 = preferences2.getString("staff_password", preferences12.
                getString("staff_password", ""));

        Button button5 = view.findViewById(R.id.button5);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();
        teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        button5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
               alertDialogBuilder.setIcon(R.drawable.logout_black);
               alertDialogBuilder.setTitle(R.string.k_yapma_lemi);
               alertDialogBuilder.setMessage(R.string.hesab_n_zdan_k_yapmak_istedi_inizden_emin_misiniz);
               alertDialogBuilder.setPositiveButton(R.string.evet2, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
                           progressBar.setVisibility(View.VISIBLE);

                           studentsInterface.logoutStudent(student_name, student_surname,
                                           student_password).enqueue(new Callback<StudentsResponse>() {
                                       @Override
                                       public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                           if (response.isSuccessful()) {
                                               progressBar.setVisibility(View.GONE);

                                               startActivity(new Intent(getActivity(), MainActivity.class));
                                               getActivity().finish();

                                               SharedPreferences preferences = getContext().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                               SharedPreferences.Editor editor = preferences.edit();
                                               editor.putBoolean("isLoggedIn", false);
                                               editor.apply();
                                           }
                                       }

                                       @Override
                                       public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                           progressBar.setVisibility(View.GONE);
                                           Snackbar.make(view, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Snackbar.LENGTH_SHORT)
                                                   .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View view) {
                                                       }
                                                   }).show();
                                       }
                                   });
                       }

                       if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
                           progressBar.setVisibility(View.VISIBLE);

                           teachersInterface.logoutTeacher(student_name2, student_surname2,
                                   student_password2).enqueue(new Callback<TeachersResponse>() {
                               @Override
                               public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                   if (response.isSuccessful()) {
                                       progressBar.setVisibility(View.GONE);

                                       startActivity(new Intent(getActivity(), MainActivity.class));
                                       getActivity().finish();

                                       SharedPreferences preferences = getContext().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                       SharedPreferences.Editor editor = preferences.edit();
                                       editor.putBoolean("isLoggedIn", false);
                                       editor.apply();
                                   }
                               }

                               @Override
                               public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                   progressBar.setVisibility(View.GONE);
                                   Snackbar.make(view, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Snackbar.LENGTH_SHORT)
                                           .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                               }
                                           }).show();
                               }
                           });
                       }

                       if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
                           progressBar.setVisibility(View.VISIBLE);

                           staffInterface.logoutStaff(student_name3, student_surname3,
                                   student_password3).enqueue(new Callback<StaffResponse>() {
                               @Override
                               public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                   if (response.isSuccessful()) {
                                       progressBar.setVisibility(View.GONE);

                                       startActivity(new Intent(getActivity(), MainActivity.class));
                                       getActivity().finish();

                                       SharedPreferences preferences = getContext().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
                                       SharedPreferences.Editor editor = preferences.edit();
                                       editor.putBoolean("isLoggedIn", false);
                                       editor.apply();
                                   }
                               }

                               @Override
                               public void onFailure(Call<StaffResponse> call, Throwable t) {
                                   progressBar.setVisibility(View.GONE);
                                   Snackbar.make(view, getString(R.string.hesap_bilgileri_al_n_rken_bir_hata_olu_tu2), Snackbar.LENGTH_SHORT)
                                           .setAction(getString(R.string.tamam), new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                               }
                                           }).show();
                               }
                           });
                       }
                   }
               });

               alertDialogBuilder.setNegativeButton(R.string.vazge2, new DialogInterface.OnClickListener() {
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