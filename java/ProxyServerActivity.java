package wafoot.becoming.wafoot;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class ProxyServerActivity extends AppCompatActivity {
    private TextInputEditText eProxy1;
    private TextInputEditText eProxy2;
    private TextInputEditText eProxy3;

    private Proxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_server);

        NavigationBarColorChanger.changeNavigationBarColor(this);
        LightModeManager.setLightMode();

        eProxy1 = findViewById(R.id.eProxy1);
        eProxy2 = findViewById(R.id.eProxy2);
        eProxy3 = findViewById(R.id.eProxy3);

        FloatingActionButton fabProxy = findViewById(R.id.fabProxy);

        eProxy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ProxyServerActivity.this, eProxy3);
                popupMenu.getMenuInflater().inflate(R.menu.proxy, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        eProxy3.setText(menuItem.getTitle());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        fabProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eProxy3.getText().toString().trim().equals("") || eProxy1.getText().toString().trim().equals("") || eProxy2.getText().toString().trim().equals("")) {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(ProxyServerActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.info);
                    alertDialogBuilder.setTitle(R.string.yetersiz_bilgiler55);
                    alertDialogBuilder.setMessage(R.string.proxy_sunucu_konfig_rasyonu_i_in_t_m_alanlar_doldurunuz55);
                    alertDialogBuilder.setPositiveButton(R.string.tamam55, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    alertDialogBuilder.create().show();
                }

                else {
                    if (eProxy3.getText().toString().equals("HTTP")) {
                        try {
                            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(eProxy1.getText().toString().trim(), Integer.parseInt(eProxy2.getText().toString())));
                            Toast.makeText(ProxyServerActivity.this, getString(R.string.proxy_sunucusuna_ba_ar_l_bir_ekilde_ba_lan_ld), Toast.LENGTH_SHORT).show();
                        }

                        catch (Exception e) {
                            Toast.makeText(ProxyServerActivity.this, getString(R.string.proxy_sunucusuna_ba_lan_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (eProxy3.getText().toString().equals("SOCKS")) {
                        Socket socket = null;

                        try {
                            proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(eProxy1.getText().toString().trim(), Integer.parseInt(eProxy2.getText().toString())));
                            socket = new Socket(proxy);

                            try {
                                socket.connect(new InetSocketAddress(eProxy2.getText().toString().trim(), Integer.parseInt(eProxy3.getText().toString())));
                                Toast.makeText(ProxyServerActivity.this, getString(R.string.proxy_sunucusuna_ba_ar_l_bir_ekilde_ba_lan_ld), Toast.LENGTH_SHORT).show();
                            }

                            catch (Exception e) {
                                Toast.makeText(ProxyServerActivity.this, getString(R.string.proxy_sunucusuna_ba_lan_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (Exception e) {
                            Toast.makeText(ProxyServerActivity.this, getString(R.string.proxy_sunucusuna_ba_lan_rken_bir_hata_olu_tu), Toast.LENGTH_SHORT).show();
                        }

                        finally {
                            if (socket != null) {
                                try {
                                    socket.close();
                                }

                                catch (Exception e) {
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}