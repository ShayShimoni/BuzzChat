package com.msapps.buzzchat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.msapps.buzzchat.databinding.ActivityMainBinding
import com.msapps.buzzchat.utils.Constants

class MainActivity: AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        determineFlow()
        adjustPaddingForKeyboardListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        when (navController.currentDestination?.id) {
            R.id.LoginFragment -> menu?.findItem(R.id.action_settings)?.isVisible = false
            R.id.HomeFragment -> menu?.findItem(R.id.action_settings)?.isVisible = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun adjustPaddingForKeyboardListener() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val keyboardHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            v.updatePadding(bottom = keyboardHeight + 32)
            insets
        }
    }

    private fun determineFlow() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        if (intent.getBooleanExtra(Constants.EXTRA_IS_LOGGED_IN, false)) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.setStartDestination(R.id.HomeFragment)
            navController.graph = navGraph
        }

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}