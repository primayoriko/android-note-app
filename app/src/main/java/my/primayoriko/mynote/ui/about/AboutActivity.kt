package my.primayoriko.mynote.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.primayoriko.mynote.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)
    }
}