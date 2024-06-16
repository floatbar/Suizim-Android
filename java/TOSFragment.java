package wafoot.becoming.wafoot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TOSFragment extends Fragment {

    private ListView lv21;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NavigationBarColorChanger.changeNavigationBarColor(requireActivity());
        LightModeManager.setLightMode();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.orange));

        View view = inflater.inflate(R.layout.fragment_t_o_s, container, false);
        lv21 = view.findViewById(R.id.lv21);

        arrayList.add("\n" +
                "TARAFLAR:\n" +
                "\n" +
                "KULLANICI:\n" +
                "Uygulamada hesap açan ve uygulamayı kullanan gerçek veya tüzel kişiler, bundan böyle KULLANICI olarak anılacak.\n" +
                "\n" +
                "SAHİP:\n" +
                "Uygulamayı kurup geliştiren (uygulamanın kurucusu), bundan böyle SAHİP olarak anılacak.\n" +
                "\n" +
                "SÖZLEŞME KONUSU:\n" +
                "6698 Sayılı Kişisel Verilerin Korunması Hakkında Kanun gereğince verilerin otomatik ya da otomatik olmayan yollarla kaydedilmesi, işlenmesi, tasniflenmesi, açıklanması, aktarılması, anonim hale getirilmesi ve devredilmesidir.\n" +
                "MADDELER\n" +
                "1- KULLANICI; 14/01/2024 tarihinden itibaren,\n" +
                "•\t      Okuduğu eğitim kurumunun ismini,\n" +
                "•       Okuduğu eğitim kurumundaki pozisyonunu,\n" +
                "•       Adını ve soy adını,\n" +
                "•       Uygulamaya ait hesap şifresini,\n" +
                "•       Yaş bilgisini,\n" +
                "•       Kendisinin ve/veya ailesinin günlük / haftalık / aylık / yıllık aktiviteleri hakkındaki bilgileri,\n" +
                "•       Okuduğu eğitim kurumunda öğrenci ise sınıf bilgisini,\n" +
                "•       Okuduğu eğitim kurumunda öğretmen ise branş bilgisini,\n" +
                "•       Okuduğu eğitim kurumunda personel ise personel olduğuna dair bilgiyi\n" +
                "kendi özgür iradesiyle ve açık rızasıyla kanuni amaçlar doğrultusunda kullanılmak üzere SAHİP'e vermiştir.\n" +
                "\n" +
                "2- KULLANICI, uygulama gerekleri açısından özel bilgileri SAHİP'e kendi özgür iradesiyle ve açık rızasıyla verecektir. Verilerinin doğruluğu ve güncelliğinden KULLANICI sorumlu olacaktır.\n" +
                "\n" +
                "3- KULLANICI, kişisel verilerinde daha sonra meydana gelecek değişiklikleri SAHİP'e kendi özgür iradesiyle ve açık rızasıyla bildirecektir. Verilerinin doğruluğu ve güncelliğinden KULLANICI sorumlu olacaktır.\n" +
                "\n" +
                "4- SAHİP, KULLANICI'ya ait ırk, etnik köken, siyasi düşünce, felsefi inanç, dini, mezhep veya diğer inançlar, kılık ve kıyafet, dernek, vakıf ya da sendika üyeliği, sağlık, cinsel hayat, sabıka kaydı ve güvenlik tedbirleriyle ilgili veriler ile biyometrik ve genetik veriler vb. özel nitelikli kişisel verileri üçüncü şahıslarla paylaşmayacaktır. Söz konusu özel nitelikli veriler işçinin açık rızası alınmak suretiyle otomatik ya da otomatik olmayan yollarla işlenebilecektir.\n" +
                "\n" +
                "5- SAHİP, iş bu sözleşmede yazılı kişisel verileri;\n" +
                "•\tVeri Sorumlusu,\n" +
                "•\tSGK yetkilileri,\n" +
                "•\tMaliye yetkilileri\n" +
                "•\tT.C. Aile, Çalışma ve Sosyal Hizmetler Bakanlığı Yetkilileri\n" +
                "•\tT.C. Hazine ve Maliye Bakanlığı yetkilileri,\n" +
                "•\tTÜİK yetkilileri\n" +
                "•\tKamu Kurum ve Kuruluşları yetkilileri,\n" +
                "•\tTeknokent Firması yetkilileri\n" +
                "•\tBES firmaları,\n" +
                "•\tKULLANICI'nın (eğer varsa) çalışmakta olan insan kaynakları yetkileri,\n" +
                "•\tKULLANICI'nın (eğer varsa) muhasebe işlerine bakan yetkilileri (Meslek Mensupları) dışında üçüncü şahıslara vermeyecektir ve işlemeyecektir.\n" +
                "\n" +
                "6- SAHİP; KULLANICI'ya ait verileri,\n" +
                "* kağıt ortamında,\n" +
                "* elektronik ortamda,\n" +
                "* elektronik araçlarda (harddisk, flash bellek, CD, DVD  vb.),\n" +
                "* sanal ortamlarda (internet, bulut sistemleri),\n" +
                "bulundurabilir, depolayabilir, işleyebilir.\n" +
                "\n" +
                "7- SAHİP; KULLANICI'ya ait söz konusu verilerin güvenli ortamlarda saklanması için idari, teknik ve hukuki önlemleri alacaktır. Yine söz konusu verilerin üçüncü şahısların ele geçirmesinden sorumlu olacaktır. KULLANICI'ya ait kişisel veriler hukuka uygun ve meşru amaçlar doğrultusunda otomatik ya da otomatik olmayan yollarla işlenecektir. KULLANICI'nın aleyhine haksızlığa yol açacak şekilde kullanılmayacaktır.\n" +
                "\n" +
                "8- KULLANICI'ya ait söz konusu tüm veriler ilgili kanunlarında yazılı süreler dolduktan sonra SAHİP tarafından kağıt, elektronik ortamdan, elektronik araçlardan ve sanal ortamlardan döndürülemeyecek şekilde silinecek ve yok edilecektir.\n" +
                "\n" +
                "9- KULLANICI'ya iş bu sözleşmede yazılı bütün maddeler okunup, bilgilendirilmiş ve izah edilmiştir. KULLANICI, tüm maddelerde geçen kişisel verilerinin otomatik ya da otomatik olmayan yollarla kaydedilmesine, işlenmesine, tasniflenmesine, açıklanmasına, aktarılmasına, anonim hale getirilmesi ve devredilmesine kendi özgür iradesiyle ve açık rızasıyla onay vermiş ve kabul etmiştir.\n" +
                "\n" +
                "10- İş bu sözleşme 14/01/2024 tarihinde oluşturulmuştur ve 10 maddeden oluşmaktadır.\n" +
                "\n" +
                "İş bu sözleşmede geçen kişisel verilerimi işlenmek üzere kendi özgür irademle ve açık rızamla vermiş bulunmaktayım.");

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        lv21.setAdapter(adapter);

        return view;
    }
}