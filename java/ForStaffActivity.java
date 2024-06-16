package wafoot.becoming.wafoot;

import android.content.Context;
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

public class ForStaffActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar tbar7;
    private NavHostFragment nh7;
    private NavigationView nv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_staff);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        tbar7 = findViewById(R.id.tbar7);
        setSupportActionBar(tbar7);

        drawer = findViewById(R.id.drawer);
        nv7 = findViewById(R.id.nv7);
        nh7 = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nh7);

        NavigationUI.setupWithNavController(nv7, nh7.getNavController());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tbar7, 0, 0);

        tbar7.setTitleTextAppearance(ForStaffActivity.this, R.style.customToolbarTitleTextAppearance);

        toggle.setDrawerSlideAnimationEnabled(false);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View view = nv7.inflateHeaderView(R.layout.account_info_layout);
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

        SharedPreferences preferences1 = getSharedPreferences("StudentPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences3 = getSharedPreferences("StudentAccount", Context.MODE_PRIVATE);

        String student_name = preferences3.getString("student_name", preferences1.
                getString("student_name", ""));
        String student_surname = preferences3.getString("student_surname", preferences1.
                getString("student_surname", ""));

        SharedPreferences preferences11 = getSharedPreferences("TeacherPassword2", Context.MODE_PRIVATE);
        SharedPreferences preferences13 = getSharedPreferences("RegisterTeacher", Context.MODE_PRIVATE);

        String student_name2 = preferences13.getString("teacher_name", preferences11.
                getString("teacher_name", ""));
        String student_surname2 = preferences13.getString("teacher_surname", preferences11.
                getString("teacher_surname", ""));

        SharedPreferences preferences12 = getSharedPreferences("StaffAccount", Context.MODE_PRIVATE);
        SharedPreferences preferences14 = getSharedPreferences("RegisterStaff", Context.MODE_PRIVATE);

        String student_name3 = preferences12.getString("staff_name", preferences14.
                getString("staff_name", ""));
        String student_surname3 = preferences12.getString("staff_surname", preferences14.
                getString("staff_surname", ""));

        SharedPreferences sharedPreferences = getSharedPreferences("Position", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("position", "").equals(getString(R.string.renci))) {
            textView.setText(student_name + " " + student_surname);
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.retmen))) {
            textView.setText(student_name2 + " " + student_surname2);
        }

        if (sharedPreferences.getString("position", "").equals(getString(R.string.personel))) {
            textView.setText(student_name3 + " " + student_surname3);
        }

        getWindow().setStatusBarColor(getResources().getColor(R.color.orange));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            super.onBackPressed();
        }
    }
}