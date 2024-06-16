package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourthQuestionActivity extends AppCompatActivity {
    private TextInputEditText question1;
    private TextInputEditText question2;
    private TextInputEditText question3;

    private StudentsInterface studentsInterface;
    private TeachersInterface teachersInterface;
    private StaffInterface staffInterface;

    private double showerAmount = 12/7;
    private double handWashingValue = 25.0;
    private double frontWashingAmount = 10/7;
    private double washingAmount = 100/7;
    private double handDishAmount = 30/7;
    private double frontDishAmount = 57/7;
    private double dishAmount = 15/7;
    private double kitchenAmount = 10;
    private double vehicleAmount = 200/30;
    private double kitchenoutcome2 = 15;
    private double kitchenOutcome = kitchenoutcome2/30;
    private double clotheoutcome2 = 10;
    private double clotheOutcome = clotheoutcome2/30;
    private double prerinsingAmount = 60.56658848;
    private double result = 0.0;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        question1 = findViewById(R.id.soru1);
        question2 = findViewById(R.id.soru2);
        question3 = findViewById(R.id.soru3);

        FloatingActionButton fabQuestion = findViewById(R.id.fabSoru);
        progressBar = findViewById(R.id.progressBar);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();
        teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        progressBar.setVisibility(View.GONE);

        // Start

        SharedPreferences sper = getSharedPreferences("Question1", Context.MODE_PRIVATE);
        SharedPreferences spr = getSharedPreferences("Question2", Context.MODE_PRIVATE);

        double showeramount = Double.parseDouble(spr.getString("gender2", ""));
        double washingamount = Double.parseDouble(spr.getString("shower2", ""));

        String washingmethod = spr.getString("family2", "");

        SharedPreferences s = getSharedPreferences("Question3", Context.MODE_PRIVATE);

        double frontwashing = Double.parseDouble(s.getString("gender3", ""));
        double kitchenamount = Double.parseDouble(s.getString("shower3", ""));

        String dishmethod = s.getString("family3", "");

        SharedPreferences spe = getSharedPreferences("Question5", Context.MODE_PRIVATE);

        double dishamount = Double.parseDouble(spe.getString("gender5", ""));
        double frontdish = Double.parseDouble(spe.getString("shower5", ""));
        double familyamount = Double.parseDouble(sper.getString("family", ""));

        String prerinsingState = spe.getString("family5", "");

        // End

        fabQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question1.getText().toString().equals("") || question2.getText().toString().equals("") ||
                        question3.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(FourthQuestionActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler);
                    alertDialogBuilder.setMessage(R.string.hesaplama_yapabilmek_i_in_t_m_sorular_cevaplay_n_z);
                    alertDialogBuilder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {

                    SharedPreferences sharedPreferences = getSharedPreferences("GeneralQuestion",
                            Context.MODE_PRIVATE);
                    String position = sharedPreferences.getString("position", "");

                    if (position.equals(getString(R.string.renci))) {
                        // Start

                        SharedPreferences sharedPreferences1 = getSharedPreferences("GeneralQuestion", Context.MODE_PRIVATE);
                        SharedPreferences preferences10 = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                        SharedPreferences preferences11 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
                        SharedPreferences preferences13 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

                        String student_name2 = preferences13.getString("student_name", preferences11.
                                getString("student_name", ""));
                        String student_surname2 = preferences13.getString("student_surname", preferences11.
                                getString("student_surname", ""));
                        String student_password2 = preferences10.getString("student_password", preferences11.
                                getString("studentPassword", ""));

                        SharedPreferences sharedPreferences2 = getSharedPreferences("Question1", Context.MODE_PRIVATE);

                        // End

                        progressBar.setVisibility(View.VISIBLE);

                        studentsInterface.insertStudentAge(student_name2, student_surname2, student_password2, sharedPreferences1.getString("age", "")).enqueue(new Callback<StudentsResponse>() {
                            @Override
                            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response5) {
                                if (response5.isSuccessful()) {
                                    studentsInterface.insertStudentGender(student_name2, student_surname2, student_password2, sharedPreferences2.getString("gender", "")).enqueue(new Callback<StudentsResponse>() {
                                        @Override
                                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response6) {
                                            if (response6.isSuccessful()) {
                                                if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {
                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + frontdish * prerinsingAmount;

                                                    }

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_irmem))) {
                                                        result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("student_name", preferences1.
                                                            getString("student_name", ""));
                                                    String student_surname = preferences3.getString("student_surname", preferences1.
                                                            getString("student_surname", ""));
                                                    String student_password = preferences.getString("student_password", preferences1.
                                                            getString("studentPassword", ""));

                                                    studentsInterface.insertFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<StudentsResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, ResultScreenActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.elde_y_kama))) {
                                                    result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                            + washingamount * washingAmount + handDishAmount * familyamount * dishamount + dishAmount * dishamount
                                                            + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("student_name", preferences1.
                                                            getString("student_name", ""));
                                                    String student_surname = preferences3.getString("student_surname", preferences1.
                                                            getString("student_surname", ""));
                                                    String student_password = preferences.getString("student_password", preferences1.
                                                            getString("studentPassword", ""));

                                                    studentsInterface.insertFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<StudentsResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, ResultScreenActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, "Bilgiler alınırken bir hata oluştu.",
                                                                    Snackbar.LENGTH_SHORT).setAction("Tamam", new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + dishamount * prerinsingAmount;
                                                    }

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_irmem))) {
                                                        result = showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("student_name", preferences1.
                                                            getString("student_name", ""));
                                                    String student_surname = preferences3.getString("student_surname", preferences1.
                                                            getString("student_surname", ""));
                                                    String student_password = preferences.getString("student_password", preferences1.
                                                            getString("studentPassword", ""));

                                                    studentsInterface.insertFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<StudentsResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, ResultScreenActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.elde_y_kama))) {

                                                    result = handWashingValue * familyamount * washingamount + handDishAmount * familyamount * dishamount
                                                            + showerAmount * showeramount + dishAmount * dishamount + washingAmount * washingamount
                                                            + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("student_name", preferences1.
                                                            getString("student_name", ""));
                                                    String student_surname = preferences3.getString("student_surname", preferences1.
                                                            getString("student_surname", ""));
                                                    String student_password = preferences.getString("student_password", preferences1.
                                                            getString("studentPassword", ""));

                                                    studentsInterface.insertFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<StudentsResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, ResultScreenActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StudentsResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });
                                                }
                                            }

                                            else {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });

                    }

                    else if (position.equals(getString(R.string.retmen))) {

                        // Start

                        SharedPreferences sharedPreferences1 = getSharedPreferences("GeneralQuestion",
                                Context.MODE_PRIVATE);
                        SharedPreferences preferences10 = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
                        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
                        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);
                        SharedPreferences sharedPreferences2 = getSharedPreferences("Question1", Context.MODE_PRIVATE);

                        String student_name2 = preferences13.getString("teacher_name", preferences11.
                                getString("teacher_name", ""));
                        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                                getString("teacher_surname", ""));
                        String student_password2 = preferences10.getString("teacher_password", preferences11.
                                getString("teacher_password", ""));

                        // End

                        progressBar.setVisibility(View.VISIBLE);

                        teachersInterface.insertTeacherAge(student_name2, student_surname2, student_password2, sharedPreferences1.getString("age", "")).enqueue(new Callback<TeachersResponse>() {
                            @Override
                            public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response7) {
                                if (response7.isSuccessful()) {
                                    teachersInterface.insertTeacherGender(student_name2, student_surname2, student_password2, sharedPreferences2.getString("gender", "")).enqueue(new Callback<TeachersResponse>() {
                                        @Override
                                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response8) {
                                            if (response8.isSuccessful()) {
                                                if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + dishamount * prerinsingAmount;

                                                    }

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_irmem))) {
                                                        result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("teacher_name", preferences1.
                                                            getString("teacher_name", ""));
                                                    String student_surname = preferences3.getString("teacher_surname", preferences1.
                                                            getString("teacher_surname", ""));
                                                    String student_password = preferences.getString("teacher_password", preferences1.
                                                            getString("teacher_password", ""));

                                                    teachersInterface.insertTeacherFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<TeachersResponse>() {
                                                        @Override
                                                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result2ScreenTeacherActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.elde_y_kama))) {

                                                    result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                            + washingamount * washingAmount + handDishAmount * dishamount * familyamount + dishAmount * dishamount
                                                            + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("teacher_name", preferences1.
                                                            getString("teacher_name", ""));
                                                    String student_surname = preferences3.getString("teacher_surname", preferences1.
                                                            getString("teacher_surname", ""));
                                                    String student_password = preferences.getString("teacher_password", preferences1.
                                                            getString("teacher_password", ""));

                                                    teachersInterface.insertTeacherFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<TeachersResponse>() {
                                                        @Override
                                                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result2ScreenTeacherActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });
                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {
                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + dishamount * prerinsingAmount;
                                                    }

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_irmem))) {
                                                        result = showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("teacher_name", preferences1.
                                                            getString("teacher_name", ""));
                                                    String student_surname = preferences3.getString("teacher_surname", preferences1.
                                                            getString("teacher_surname", ""));
                                                    String student_password = preferences.getString("teacher_password", preferences1.
                                                            getString("teacher_password", ""));

                                                    teachersInterface.insertTeacherFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<TeachersResponse>() {
                                                        @Override
                                                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result2ScreenTeacherActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.elde_y_kama))) {
                                                    result = handWashingValue * familyamount * washingamount + handDishAmount * dishamount * dishAmount + showerAmount * showeramount + washingamount * washingAmount
                                                            + dishAmount * dishamount + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences1 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences3 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

                                                    String student_name = preferences3.getString("teacher_name", preferences1.
                                                            getString("teacher_name", ""));
                                                    String student_surname = preferences3.getString("teacher_surname", preferences1.
                                                            getString("teacher_surname", ""));
                                                    String student_password = preferences.getString("teacher_password", preferences1.
                                                            getString("teacher_password", ""));

                                                    teachersInterface.insertTeacherFootPrint(student_name, student_surname, student_password, String
                                                            .valueOf((long) result)).enqueue(new Callback<TeachersResponse>() {
                                                        @Override
                                                        public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result2ScreenTeacherActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<TeachersResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });
                                                }
                                            }

                                            else {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });

                    } else if (position.equals("Personel")) {
                        // Start

                        SharedPreferences preferences = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
                        SharedPreferences preferences1 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
                        SharedPreferences preferences3 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);
                        SharedPreferences sharedPreferences2 = getSharedPreferences("Question1", Context.MODE_PRIVATE);

                        String student_name = preferences3.getString("staff_name", preferences1.
                                getString("staff_name", ""));
                        String student_surname = preferences3.getString("staff_surname", preferences1.
                                getString("staff_surname", ""));
                        String student_password = preferences.getString("staff_password", preferences1.
                                getString("staff_password", ""));

                        // End

                        progressBar.setVisibility(View.VISIBLE);

                        SharedPreferences sharedPreferences1 = getSharedPreferences("GeneralQuestion",
                                Context.MODE_PRIVATE);

                        staffInterface.insertStaffAge(student_name, student_surname, student_password, sharedPreferences1.getString("age", "")).enqueue(new Callback<StaffResponse>() {
                            @Override
                            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response9) {
                                if (response9.isSuccessful()) {
                                    staffInterface.insertStaffGender(student_name, student_surname, student_password, sharedPreferences2.getString("gender", "")).enqueue(new Callback<StaffResponse>() {
                                        @Override
                                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response10) {
                                            if (response10.isSuccessful()) {
                                                if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {

                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + dishamount * prerinsingAmount;
                                                    }

                                                    else {
                                                        result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                                + washingamount * washingAmount + frontdish * frontDishAmount + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences10 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

                                                    String student_name2 = preferences13.getString("staff_name", preferences11.
                                                            getString("staff_name", ""));
                                                    String student_surname2 = preferences13.getString("staff_surname", preferences11.
                                                            getString("staff_surname", ""));
                                                    String student_password2 = preferences10.getString("staff_password", preferences11.
                                                            getString("staff_password", ""));

                                                    staffInterface.insertStaffFootPrint(student_name2, student_surname2, student_password2, String
                                                            .valueOf((long) result)).enqueue(new Callback<StaffResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result3ScreenAdminActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.ama_r_makinesi)) && dishmethod.equals(getString(R.string.elde_y_kama))) {
                                                    result = showerAmount * showeramount + frontWashingAmount * frontwashing
                                                            + washingamount * washingAmount + handDishAmount * dishamount * familyamount + dishAmount * dishamount
                                                            + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences10 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

                                                    String student_name2 = preferences13.getString("staff_name", preferences11.
                                                            getString("staff_name", ""));
                                                    String student_surname2 = preferences13.getString("staff_surname", preferences11.
                                                            getString("staff_surname", ""));
                                                    String student_password2 = preferences10.getString("staff_password", preferences11.
                                                            getString("staff_password", ""));

                                                    staffInterface.insertStaffFootPrint(student_name2, student_surname2, student_password2, String
                                                            .valueOf((long) result)).enqueue(new Callback<StaffResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result3ScreenAdminActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.bula_k_makinesi))) {
                                                    if (prerinsingState.equals(getString(R.string.makineye_atmadan_nce_ge_iririm))) {
                                                        result = (showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome) + dishamount * prerinsingAmount;
                                                    }

                                                    else {
                                                        result = showerAmount * showeramount + washingamount * washingAmount
                                                                + handWashingValue * washingamount * familyamount + frontDishAmount * frontdish + dishAmount * dishamount
                                                                + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                                + Double.parseDouble(question3.getText().toString()) * clotheOutcome;
                                                    }

                                                    SharedPreferences preferences10 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

                                                    String student_name2 = preferences13.getString("staff_name", preferences11.
                                                            getString("staff_name", ""));
                                                    String student_surname2 = preferences13.getString("staff_surname", preferences11.
                                                            getString("staff_surname", ""));
                                                    String student_password2 = preferences10.getString("staff_password", preferences11.
                                                            getString("staff_password", ""));

                                                    staffInterface.insertStaffFootPrint(student_name2, student_surname2, student_password2, String
                                                            .valueOf((long) result)).enqueue(new Callback<StaffResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result3ScreenAdminActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });

                                                }

                                                else if (washingmethod.equals(getString(R.string.elde_y_kama)) && dishmethod.equals(getString(R.string.elde_y_kama))) {
                                                    result = handWashingValue * familyamount * washingamount + handDishAmount * dishamount * dishAmount + showerAmount * showeramount + washingamount * washingAmount
                                                            + dishAmount * dishamount + kitchenamount * kitchenAmount + Double.parseDouble(question1.getText().toString()) * vehicleAmount + Double.parseDouble(question2.getText().toString()) * kitchenOutcome
                                                            + Double.parseDouble(question3.getText().toString()) * clotheOutcome;

                                                    SharedPreferences preferences10 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences11 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
                                                    SharedPreferences preferences13 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

                                                    String student_name2 = preferences13.getString("staff_name", preferences11.
                                                            getString("staff_name", ""));
                                                    String student_surname2 = preferences13.getString("staff_surname", preferences11.
                                                            getString("staff_surname", ""));
                                                    String student_password2 = preferences10.getString("staff_password", preferences11.
                                                            getString("staff_password", ""));

                                                    staffInterface.insertStaffFootPrint(student_name2, student_surname2, student_password2, String
                                                            .valueOf((long) result)).enqueue(new Callback<StaffResponse>() {
                                                        @Override
                                                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                                                            if (response.isSuccessful()) {
                                                                progressBar.setVisibility(View.GONE);
                                                                startActivity(new Intent(FourthQuestionActivity.this, Result3ScreenAdminActivity.class));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                                                            progressBar.setVisibility(View.GONE);
                                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                                                    Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                }
                                                            }).show();
                                                        }
                                                    });
                                                }
                                            }

                                            else {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                            Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
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
                                progressBar.setVisibility(View.GONE);
                                Snackbar.make(view, getString(R.string.bilgiler_al_n_rken_bir_hata_olu_tu2),
                                        Snackbar.LENGTH_SHORT).setAction(getString(R.string.tamam), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                }).show();
                            }
                        });
                    }
                }
            }
        });
    }
}