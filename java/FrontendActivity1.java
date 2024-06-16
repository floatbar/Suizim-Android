package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrontendActivity1 extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavHostFragment navHostFragment;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private StudentsInterface studentsInterface;
    private TeachersInterface teachersInterface;
    private StaffInterface staffInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontend1);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        drawer = findViewById(R.id.drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentsInterface = ApiUtils.getNodejsStudentsInterface();
        teachersInterface = ApiUtilsTeachers.getNodejsTeachersInterface();
        staffInterface = ApiUtilsStaff.getNodejsStaffInterface();

        navigationView = findViewById(R.id.navigationView);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);

        toolbar.setTitleTextAppearance(FrontendActivity1.this, R.style.customToolbarTitleTextAppearance);

        toggle.setDrawerSlideAnimationEnabled(false);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        NavigationUI.setupWithNavController(navigationView, navHostFragment.getNavController());
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View view = navigationView.inflateHeaderView(R.layout.account_info_layout);
        ShapeableImageView imageView17 = view.findViewById(R.id.imageView17);

        try {
            final String stringValueOfImageUri = getSharedPreferences("ImageURI", Context.MODE_PRIVATE).getString("image_uri", "");

            if (!stringValueOfImageUri.isEmpty()) {
                imageView17.setImageURI(Uri.parse(stringValueOfImageUri));
            }

            else {
                imageView17.setStrokeColor(getColorStateList(R.color.black));
                imageView17.setImageResource(R.drawable.account_circle);
            }
        }

        catch (Exception ignored) {}

        TextView textView = view.findViewById(R.id.tview1);

        SharedPreferences sharedPreferences = getSharedPreferences("Position", Context.MODE_PRIVATE);
        SharedPreferences preferences10 = getSharedPreferences("StudentPassword1", Context.MODE_PRIVATE);
        SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));
        String student_password = preferences10.getString("student_password", preferences1.
                getString("studentPassword", ""));

        SharedPreferences preferences = getSharedPreferences("ResultTeacherInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));
        String student_password2 = preferences.getString("teacher_password", preferences11.
                getString("teacher_password", ""));

        SharedPreferences preferences2 = getSharedPreferences("ResultStaffInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences12 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences12.getString("staff_name", preferences14.
                getString("staff_name", ""));
        String student_surname3 = preferences12.getString("staff_surname", preferences14.
                getString("staff_surname", ""));
        String student_password3 = preferences2.getString("staff_password", preferences12.
                getString("staff_password", ""));

        if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
            textView.setText(student_name + " " + student_surname);
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
            textView.setText(student_name2 + " " + student_surname2);
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
            textView.setText(student_name3 + " " + student_surname3);
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
            studentsInterface.verifyStudentToken(student_name, student_surname, student_password).enqueue(new Callback<StudentsResponse>() {
                @Override
                public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                    if (!response.isSuccessful()) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<StudentsResponse> call, Throwable t) {
                    if (!(t instanceof ConnectException)) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
            teachersInterface.verifyTeacherToken(student_name2, student_surname2, student_password2).enqueue(new Callback<TeachersResponse>() {
                @Override
                public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
                    if (!response.isSuccessful()) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<TeachersResponse> call, Throwable t) {
                    if (!(t instanceof ConnectException)) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
            staffInterface.verifyStaffToken(student_name3, student_surname3, student_password3).enqueue(new Callback<StaffResponse>() {
                @Override
                public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                    if (!response.isSuccessful()) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<StaffResponse> call, Throwable t) {
                    if (!(t instanceof ConnectException)) {
                        getSharedPreferences("LoginState", Context.MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                        startActivity(new Intent(FrontendActivity1.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }

        getWindow().setStatusBarColor(getResources().getColor(R.color.orange));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            finishAffinity();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}