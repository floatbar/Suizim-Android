package wafoot.becoming.wafoot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editRegister2;
    private TextInputEditText editRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        FloatingActionButton fabRegister = findViewById(R.id.fabRegister);

        editRegister2 = findViewById(R.id.editRegister2);
        editRegister = findViewById(R.id.editRegister);

        editRegister2.setMovementMethod(null);
        editRegister.setMovementMethod(null);
        editRegister.setCursorVisible(false);
        editRegister.setFocusable(false);
        editRegister.setFocusableInTouchMode(false);
        editRegister2.setCursorVisible(false);
        editRegister2.setFocusable(false);
        editRegister2.setFocusableInTouchMode(false);

        SharedPreferences preferences = getSharedPreferences("LoginState", Context.MODE_PRIVATE);

        if (preferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(MainActivity.this, FrontendActivity1.class));
            finish();
        }

        editRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, editRegister2);
                popupMenu.getMenuInflater().inflate(R.menu.school_name, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        editRegister2.setText(menuItem.getTitle());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        editRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, editRegister);
                popupMenu.getMenuInflater().inflate(R.menu.school_position, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        editRegister.setText(menuItem.getTitle());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        fabRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editRegister.getText().toString().equals("") || editRegister2.getText().toString().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler5);
                    alertDialogBuilder.setMessage(R.string.hesap_kurulumu_i_in_okul_ismi_ve_pozisyonunuzu_giriniz5);
                    alertDialogBuilder.setIcon(R.drawable.account_circle);
                    alertDialogBuilder.setPositiveButton(R.string.tamam6, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                if (editRegister2.getText().toString().equals(getString(R.string.ykal)) && editRegister.getText().toString().equals(getString(R.string.personel2))) {
                    SharedPreferences preferences = getSharedPreferences("Position", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("position", editRegister.getText().toString());
                    editor.apply();

                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
                    alertDialogBuilder.setTitle(R.string.giri_y_ntemi);
                    alertDialogBuilder.setMessage(R.string.uygulamay_kullanmadan_nce_bir_giri_y_ntemi_se_iniz);
                    alertDialogBuilder.setIcon(R.drawable.login_black);
                    
                    alertDialogBuilder.setPositiveButton(R.string.giri_yap, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Login3AdminActivity.class);
                            intent.putExtra("staff", editRegister.getText().toString());
                            startActivity(intent);
                        }
                    });

                    alertDialogBuilder.setNegativeButton(R.string.kay_t_ol, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, CreateAccountAdminActivity.class);
                            intent.putExtra("staff", editRegister.getText().toString());
                            startActivity(intent);

                        }
                    });

                    alertDialogBuilder.create().show();

                }

                if (editRegister2.getText().toString().equals(getString(R.string.ykal)) && editRegister.getText().toString().equals(getString(R.string.retmen3))) {
                    SharedPreferences preferences = getSharedPreferences("Position", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("position", editRegister.getText().toString());
                    editor.apply();

                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
                    alertDialogBuilder.setTitle(R.string.giri_y_ntemi5);
                    alertDialogBuilder.setMessage(R.string.uygulamay_kullanmadan_nce_bir_giri_y_ntemi_se_iniz5);
                    alertDialogBuilder.setIcon(R.drawable.login_black);
                    
                    alertDialogBuilder.setPositiveButton(R.string.giri_yap5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Login2TeacherActivity.class);
                            intent.putExtra("teacher", editRegister.getText().toString());
                            startActivity(intent);

                        }
                    });

                    alertDialogBuilder.setNegativeButton(R.string.kay_t_ol5, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Create2AccountTeacherActivity.class);
                            intent.putExtra("teacher", editRegister.getText().toString());
                            startActivity(intent);

                        }
                    });

                    alertDialogBuilder.create().show();
                }

                if (editRegister2.getText().toString().equals(getString(R.string.ykal)) && editRegister.getText().toString().equals(getString(R.string.renci6))) {
                    SharedPreferences preferences = getSharedPreferences("Position", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("position", editRegister.getText().toString());
                    editor.apply();

                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
                    alertDialogBuilder.setTitle(R.string.giri_y_ntemi10);
                    alertDialogBuilder.setMessage(R.string.uygulamay_kullanmadan_nce_bir_giri_y_ntemi_se_iniz11);
                    alertDialogBuilder.setIcon(R.drawable.login_black);

                    alertDialogBuilder.setPositiveButton(R.string.giri_yap12, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.putExtra("student", editRegister.getText().toString());
                            startActivity(intent);
                        }
                    });

                    alertDialogBuilder.setNegativeButton(R.string.kay_t_ol12, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Create1AccountActivity.class);
                            intent.putExtra("student", editRegister.getText().toString());
                            startActivity(intent);

                        }
                    });

                    alertDialogBuilder.create().show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
