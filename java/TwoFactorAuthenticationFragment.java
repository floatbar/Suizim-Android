package wafoot.becoming.wafoot;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoFactorAuthenticationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();

        final View view = inflater.inflate(R.layout.fragment_two_factor_authentication, container, false);

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

        TextInputEditText passwordTextInputEditText = view.findViewById(R.id.passwordTextInputEditText);
        TextInputEditText passwordConfirmTextInputEditText = view.findViewById(R.id.passwordConfirmationTextInputEditText);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        StudentsInterface studentsInterface = ApiUtils.getNodejsStudentsInterface();
        TeachersInterface teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        StaffInterface staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!passwordTextInputEditText.getText().toString().equals(passwordConfirmTextInputEditText.getText().toString())) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setIcon(R.drawable.check_circle);
                    alertDialogBuilder.setTitle(R.string.ifre_e_le_me_sorunu233132);
                    alertDialogBuilder.setMessage(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turabilmeniz_i_in_her_iki_alanda_da_ifrelerinizin_e_le_mesi_gerekmektedir13311331);
                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (passwordTextInputEditText.getText().toString().length() < 8 && passwordConfirmTextInputEditText.getText().toString().length() < 8) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                    alertDialogBuilder.setTitle(R.string.ifre_basamak_say_s_sorunu1331);
                    alertDialogBuilder.setMessage(R.string.olu_turdu_unuz_ifrenin_minimum_8_haneli_olmas_gerekmektedir11313);
                    alertDialogBuilder.setPositiveButton(getString(R.string.tamam), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else if (passwordTextInputEditText.getText().toString().length() >= 8 && passwordConfirmTextInputEditText.getText().toString().length() >= 8) {
                    if (sharedPreferences.getString("position", "").equals(getString(R.string.renci131331))) {
                        progressBar.setVisibility(View.VISIBLE);

                        studentsInterface.showIsStudentWith2FAPassword(student_name, student_surname,
                                student_password).enqueue(new Callback<StudentsResponse>() {
                            @Override
                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                if (response.isSuccessful()) {
                                    studentsInterface.change2FAStudentPassword(student_name, student_surname,
                                            student_password, passwordTextInputEditText.getText().toString())
                                            .enqueue(new Callback<StudentsResponse>() {
                                                @Override
                                                public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response2) {
                                                    if (response2.isSuccessful()) {
                                                        progressBar.setVisibility(View.GONE);

                                                        passwordTextInputEditText.setText("");
                                                        passwordConfirmTextInputEditText.setText("");

                                                        Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_de_i_tirildi),
                                                                Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                            }
                                                        }).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<StudentsResponse> call, Throwable t) {

                                                    progressBar.setVisibility(View.GONE);

                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_de_i_tirilirken_bir_hata_olu_tu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                studentsInterface.generate2FAStudentPassword(student_name, student_surname,
                                                student_password, passwordTextInputEditText.getText().toString())
                                        .enqueue(new Callback<StudentsResponse>() {
                                            @Override
                                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                                if (response.isSuccessful()) {
                                                    progressBar.setVisibility(View.GONE);

                                                    passwordTextInputEditText.setText("");
                                                    passwordConfirmTextInputEditText.setText("");

                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_olu_turuldu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turulurken_bir_hata_olu_tu),
                                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                    }
                                                }).show();
                                            }
                                        });
                            }
                        });
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen1313))) {
                        progressBar.setVisibility(View.VISIBLE);

                        teachersInterface.showIsTeacherWith2FAPassword(student_name2, student_surname2,
                                student_password2).enqueue(new Callback<TeachersResponse>() {
                            @Override
                            public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                if (response.isSuccessful()) {
                                    teachersInterface.change2FATeacherPassword(student_name2, student_surname2,
                                                    student_password2, passwordTextInputEditText.getText().toString())
                                            .enqueue(new Callback<TeachersResponse>() {
                                                @Override
                                                public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response2) {
                                                    if (response2.isSuccessful()) {
                                                        progressBar.setVisibility(View.GONE);

                                                        passwordTextInputEditText.setText("");
                                                        passwordConfirmTextInputEditText.setText("");

                                                        Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_de_i_tirildi),
                                                                Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                            }
                                                        }).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turulurken_bir_hata_olu_tu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                teachersInterface.generate2FATeacherPassword(student_name2, student_surname2,
                                                student_password2, passwordTextInputEditText.getText().toString())
                                        .enqueue(new Callback<TeachersResponse>() {
                                            @Override
                                            public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                                if (response.isSuccessful()) {
                                                    progressBar.setVisibility(View.GONE);

                                                    passwordTextInputEditText.setText("");
                                                    passwordConfirmTextInputEditText.setText("");

                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_olu_turuldu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turulurken_bir_hata_olu_tu),
                                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                    }
                                                }).show();
                                            }
                                        });
                            }
                        });
                    }

                    if (sharedPreferences.getString("position", "").equals(getString(R.string.personel23332))) {
                        progressBar.setVisibility(View.VISIBLE);

                        staffInterface.showIsStaffWith2FAPassword(student_name3, student_surname3,
                                student_password3).enqueue(new Callback<StaffResponse>() {
                            @Override
                            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                if (response.isSuccessful()) {
                                    staffInterface.change2FAStaffPassword(student_name2, student_surname2,
                                                    student_password2, passwordTextInputEditText.getText().toString())
                                            .enqueue(new Callback<StaffResponse>() {
                                                @Override
                                                public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response2) {
                                                    if (response2.isSuccessful()) {
                                                        progressBar.setVisibility(View.GONE);

                                                        passwordTextInputEditText.setText("");
                                                        passwordConfirmTextInputEditText.setText("");

                                                        Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_de_i_tirildi),
                                                                Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                            }
                                                        }).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);

                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turulurken_bir_hata_olu_tu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFailure(Call<StaffResponse> call, Throwable t) {
                                staffInterface.generate2FAStaffPassword(student_name3, student_surname3,
                                                student_password3, passwordTextInputEditText.getText().toString())
                                        .enqueue(new Callback<StaffResponse>() {
                                            @Override
                                            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                                if (response.isSuccessful()) {
                                                    progressBar.setVisibility(View.GONE);

                                                    passwordTextInputEditText.setText("");
                                                    passwordConfirmTextInputEditText.setText("");

                                                    Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_ba_ar_l_bir_ekilde_olu_turuldu),
                                                            Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    }).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.ki_fakt_rl_kimlik_do_rulama_ifresi_olu_turulurken_bir_hata_olu_tu),
                                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                    }
                                                }).show();
                                            }
                                        });
                            }
                        });
                    }
                }
            }
        });

        return view;
    }
}