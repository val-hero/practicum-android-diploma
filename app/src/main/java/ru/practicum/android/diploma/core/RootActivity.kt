package ru.practicum.android.diploma.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private var binding: ActivityRootBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.bottomNavBar?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toggleBottomNavBarVisibility(destination.id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun toggleBottomNavBarVisibility(destinationId: Int) {
        binding?.bottomNavBar?.isVisible = when (destinationId) {
            R.id.favoritesFragment, R.id.searchFragment, R.id.teamFragment -> true
            else -> false
        }
    }
}