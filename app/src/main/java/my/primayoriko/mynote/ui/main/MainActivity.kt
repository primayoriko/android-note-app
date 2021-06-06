package my.primayoriko.mynote.ui.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ActivityMainBinding
import my.primayoriko.mynote.ui.about.AboutActivity
import my.primayoriko.mynote.ui.note.NoteActivity
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        val colorDrawable =
            ColorDrawable(ContextCompat.getColor(view.context, R.color.color_primary))

        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setBackgroundDrawable(colorDrawable)
//        actionBar?.setBackgroundDrawable(colorDrawable)

        binding.btnMain.setOnClickListener {
            val intent = Intent(view.context, NoteActivity::class.java)
            view.context.startActivity(intent)
        }

        binding.btnAbout.setOnClickListener {
            val intent = Intent(view.context, AboutActivity::class.java)
            view.context.startActivity(intent)
        }
    }

}