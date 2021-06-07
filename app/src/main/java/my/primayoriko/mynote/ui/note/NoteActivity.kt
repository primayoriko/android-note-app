package my.primayoriko.mynote.ui.note

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ActivityNoteBinding
import androidx.preference.PreferenceManager
import my.primayoriko.mynote.constant.SeedData
import timber.log.Timber

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private val viewModel: NoteViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seedData()

        binding = ActivityNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_note)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_note)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun seedData() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (prefs.getBoolean("isFirstTime", true)) {
            viewModel.seedData(SeedData.seedList)
            Timber.d("executed\n")
            Timber.d("executed\n")
            Timber.d("executed\n")
            Timber.d("executed\n")
            val editor = prefs.edit()
            editor.putBoolean("isFirstTime", false)
            editor.apply()
        }
    }

}