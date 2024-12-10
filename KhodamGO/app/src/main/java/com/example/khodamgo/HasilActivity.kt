package com.example.khodamgo



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val khodamList = listOf( //I = introvert, E = extrovert, L = logis, N = intuitif;
            KhodamModel("Khodam IL","Aria","Aria adalah khodam pelindung yang tenang dan tangguh, cocok bagi mereka yang membutuhkan perlindungan baik secara fisik maupun gaib. Aria mungkin lebih menyukai keheningan dan memiliki kemampuan untuk berpikir rasional dalam situasi berbahaya.","Ia memiliki energi yang menenangkan dan mampu meningkatkan semangat spiritual tuannya.",R.drawable.ksatria_img),
            KhodamModel("Khodam EL","Ambatron","Ambatron memiliki fisik yang kuat dan persenjataan yang hebat. Sebagai khodam pelindung yang tangguh, Ambatron lebih terarah pada aksi dan pertempuran, dan lebih berfokus pada kekuatan fisik serta strategi yang logis.","Ambatron memiliki fisik yang kuat dan memiliki persenjataan yang bagus untuk melindungi tuannya ",R.drawable.ambatron_img),
            KhodamModel("Khodam EN","Mothertron","Mothertron memiliki kekuatan fisik luar biasa dan kemampuan memimpin pasukan. Sebagai ratu dari ras Cybertron, dia memanfaatkan kemampuan untuk berpikir ke depan dan merencanakan, serta cenderung memiliki visi besar untuk masa depan.","Mothertron memiliki kekuatan fisik dan persenjataan yang lebih bagus dan kuat dibanding dengan Ambatron, serta Mothertron mampu memerintah pasukan dari Cybertron",R.drawable.mothertron_img),
            KhodamModel("Khodam EL2","Azazel","Azazel adalah anak dari Raja Iblis yang memiliki kekuatan iblis dan kemampuan bertarung. Sebagai penerus tahta kerajaan kegelapan, dia akan berfokus pada taktik yang logis dan kekuatan fisik untuk mencapai tujuan dan memperluas kekuasaannya.","Azazel bisa menjadi khodam yang sangat kuat karena dia memiliki kekuatan iblis dan juga pandai bertarung",R.drawable.iblis_petarung),
            KhodamModel("Khodam IN","Dewi Malam","Dewi Malam memancarkan aura yang menenangkan namun kuat. Sebagai dewi kegelapan malam, Dewi Malam lebih cenderung introspektif, dengan kemampuan untuk memahami kedalaman dunia gaib dan memanfaatkan energi malam untuk kekuatan yang luar biasa.","Dewi Malam mampu menjaga kamu dengan sangat baik terlebih lagi disaat malam hari, karna dengan bantuan dari cahaya bulan dewi malam memiliki kekuatan yang lebih kuat dari khodam lainnya",R.drawable.dewi_ungu),
            KhodamModel("Khodam IN2","Purbakala","Purbakala memiliki kekuatan mistis luar biasa dan bisa memanipulasi waktu. Tipe khodam ini lebih tertarik pada konsep waktu dan sejarah, dengan kemampuan untuk merencanakan dan memahami dunia melalui lensa yang lebih luas.","Purbakala termasuk khodam yang memiliki kekuatan mistis yang luar biasa khodam ini cenderung menggunakan kekuatan waktu, Purbakala bisa memanipulasi waktu, khodam ini bisa pergi ke masa lalu ataupun ke masa depan",R.drawable.god),
            KhodamModel("Khodam EN2","Garuda","Garuda melambangkan kekuatan, kebijaksanaan, dan kehormatan. Sebagai khodam yang mengendalikan angin dan energi alam, Garuda memiliki visi besar dan kemampuan untuk memahami dan mengarahkan kekuatan alam dengan wawasan intuitif.","Garuda memiliki kekuatan untuk mengendalikan angin,dan juga memanipulasi energi alam,selain itu garuda juga memiliki kekuatan fisik yang sangat kuat, seseorang akan sangat beruntung jika dia memiliki khodam ini",R.drawable.raja_ayam_img),
            KhodamModel("Khodam IL2","Dewi Api","Dewi Api adalah khodam yang mengendalikan api dengan kecerdasan dan ketenangan. Ia tidak terburu-buru dalam tindakannya, melainkan lebih banyak merenung dan merencanakan dengan cermat sebelum menggunakan kekuatannya. Meskipun ia mengendalikan api, termasuk api hitam yang sangat panas, Dewi Api lebih memilih pendekatan yang terkontrol dan bijaksana.","Sesuai dengan namanya Dewi Api memiliki kekuatan untuk mengendalikan Api, bahkan api yang dia kendalikan bisa sampai ke tahap Api Hitam yang paling panas",R.drawable.dewi_api_img)
        )

        val khodamName = intent.getStringExtra("KHODAM_RESULT")


        val selectedKhodam = khodamList.find { it.khodamId == khodamName }


        val nameTextView: TextView = findViewById(R.id.tv_khodam_name)
        val descriptionTextView: TextView = findViewById(R.id.tv_khodam_description)
        val strengthTextView: TextView = findViewById(R.id.tv_khodam_strength)
        val khodamImageView: ImageView = findViewById(R.id.iv_khodam_image)

        if (selectedKhodam != null) {
            nameTextView.text = selectedKhodam.name
            descriptionTextView.text = selectedKhodam.description
            strengthTextView.text = selectedKhodam.power
            khodamImageView.setImageResource(selectedKhodam.image)
        } else {
            nameTextView.text = "Iblis Botak"
            descriptionTextView.text = "Iblis Botak merupakan salah satu khodam paling kejam, dia suka melakukan hal jahat, biasanya orang yang memiliki khodam ini adalah orang yang memiliki niat jahat"
            strengthTextView.text = "Kekuatan iblis ini antara lain mampu mengendalikan pikiran manusia,mampu membuat gas beracun disekitarnya"
            khodamImageView.setImageResource(R.drawable.iblis_botak_img)
        }

        val showKhodamBtn:Button = findViewById(R.id.btn_view_khodam);
        showKhodamBtn.setOnClickListener{
            val khodam = intent.getStringExtra("KHODAM_RESULT");
            val intent = Intent(this,KhodamActivity::class.java);
            intent.putExtra("KHODAM_RESULT",khodam);
            startActivity(intent);
            finish();
        }
    }
}
