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
        val khodamList = listOf(
            KhodamModel("Khodam A","Aria","Aria adalah khodam pelindung yang kuat dan tangguh, sangat cocok bagi mereka yang membutuhkan perlindungan gaib sekaligus fisik.","Ia memiliki energi yang menenangkan dan mampu meningkatkan semangat spiritual tuannya.",R.drawable.ksatria_img),
            KhodamModel("Khodam B","Ambatron","Ambatron adalah khodam pelindung yang memiliki bentuk manusia setengah robot","Ambatron memiliki fisik yang kuat dan memiliki persenjataan yang bagus untuk melindungi tuannya ",R.drawable.ambatron_img),
            KhodamModel("Khodam C","Mothertron","Mothertron adalah khodam yang memiliki ras yang sama dengan Ambatron, Mothertron adalah ratu dari ras Cybertron","Mothertron memiliki kekuatan fisik dan persenjataan yang lebih bagus dan kuat dibanding dengan Ambatron, serta Mothertron mampu memerintah pasukan dari Cybertron",R.drawable.mothertron_img),
            KhodamModel("Khodam D","Azazel","Azazel adalah anak dari Raja Iblis di neraka, ditakdirkan sebagai penerus tahta di kerajaan kegelapan. Dengan darah murni iblis yang mengalir dalam dirinya, Azazel memiliki kekuatan luar biasa yang bahkan membuat para penghuni neraka tunduk padanya sejak usia muda","Azazel bisa menjadi khodam yang sangat kuat karena dia memiliki kekuatan iblis dan juga pandai bertarung",R.drawable.iblis_petarung),
            KhodamModel("Khodam E","Dewi Malam","Dewi Malam adalah sosok dewi yang melambangkan kegelapan malam yang misterius dan penuh kekuatan. Dengan wajah yang cantik dan anggun, ia memancarkan aura ungu yang tenang namun mematikan","Dewi Malam mampu menjaga kamu dengan sangat baik terlebih lagi disaat malam hari, karna dengan bantuan dari cahaya bulan dewi malam memiliki kekuatan yang lebih kuat dari khodam lainnya",R.drawable.dewi_ungu),
            KhodamModel("Khodam F","Purbakala","Purbakala adalah khodam yang sudah ada sejak dahulu kala,khodam ini sangat dihormati oleh khodam lainnya","Purbakala termasuk khodam yang memiliki kekuatan mistis yang luar biasa khodam ini cenderung menggunakan kekuatan waktu, Purbakala bisa memanipulasi waktu, khodam ini bisa pergi ke masa lalu ataupun ke masa depan",R.drawable.god),
            KhodamModel("Khodam G","Iblis Neraka","Iblis Neraka merupakan khodam yang berasal dari neraka sama seperti khodam Azazel,bisa dibilang iblis neraka ini merupakan panglima dari kerajaan neraka.","Kekuatan yang dimiliki khodam ini adalah kekuatan api neraka yang sangat panas, dan bisa membuat pasukan dari kerangka manusia",R.drawable.iblis_neraka_img),
            KhodamModel("Khodam H","Garuda","Garuda Raksasa adalah khodam yang berasal dari tanah Indonesia, melambangkan kekuatan, kebijaksanaan, dan kehormatan. Dengan tubuh manusia berotot yang tegap dan kepala elang yang perkasa, Garuda Raksasa memiliki aura yang sangat disegani oleh semua makhluk, baik manusia maupun roh gaib.","Garuda memiliki kekuatan untuk mengendalikan angin,dan juga memanipulasi energi alam,selain itu garuda juga memiliki kekuatan fisik yang sangat kuat, seseorang akan sangat beruntung jika dia memiliki khodam ini",R.drawable.raja_ayam_img),
            KhodamModel("Khodam I","Naga Bonar","Naga Bonar memiliki bentuk naga setengah manusia dengan warna kulit biru yang bersisik","Naga Bonar merupakan Khodam tipe udara, yang memiliki kekuatan untuk mengendalikan angin, Naga Bonar memiliki kisah menarik dengan Garuda",R.drawable.naga_biru_img),
            KhodamModel("Khodam J","Dewi Api","Dewi Api merupakan saudara dari Dewi Malam, dewi api sendiri memiliki kepribadian yang baik dan suka menolong","Sesuai dengan namanya Dewi Api memiliki kekuatan untuk mengendalikan Api, bahkan api yang dia kendalikan bisa sampai ke tahap Api Hitam yang paling panas",R.drawable.dewi_api_img),
            KhodamModel("Khodam K","Iblis Botak","Iblis Botak merupakan salah satu khodam paling kejam, dia suka melakukan hal jahat, biasanya orang yang memiliki khodam ini adalah orang yang memiliki niat jahat","Kekuatan iblis ini antara lain mampu mengendalikan pikiran manusia,mampu membuat gas beracun disekitarnya",R.drawable.iblis_botak_img)
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