package my.primayoriko.mynote.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ActivityMainBinding
import my.primayoriko.mynote.ui.about.AboutActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

        binding.btnMain.setOnClickListener {

        }

        binding.btnAbout.setOnClickListener {
            val intent = Intent(view.context, AboutActivity::class.java)
//            intent.putExtra("url", news.url)
            view.context.startActivity(intent)
        }
    }

}