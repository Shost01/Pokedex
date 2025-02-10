package com.nathan.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure a Toolbar como a ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Obtenha o NavController e configure o suporte à navegação
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as? androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment?.navController

        navController?.let {
            setupActionBarWithNavController(it)
        } ?: throw IllegalStateException("NavController não encontrado!")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as? androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment?.navController
        return navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }
}
