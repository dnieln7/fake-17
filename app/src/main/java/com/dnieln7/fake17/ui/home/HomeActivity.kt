package com.dnieln7.fake17.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dnieln7.fake17.Fake17Application
import com.dnieln7.fake17.R
import com.dnieln7.fake17.SplashActivity
import com.dnieln7.fake17.databinding.ActivityHomeBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val userDataSource = (application as Fake17Application).serviceLocator.userDataSource
        val catLocalDataSource = (application as Fake17Application).serviceLocator.catDbDataSource

        val host = supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment
        navController = host.navController

        val headerView: View = binding.drawerContent.getHeaderView(0)

        userDataSource.getFirstUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                (headerView.findViewById(R.id.name) as TextView).text = it.name
                (headerView.findViewById(R.id.email) as TextView).text = it.email
            }

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawer)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.drawerContent.setupWithNavController(navController)
        binding.drawerContent.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.logout -> {
                    userDataSource.deleteAll()
                        .flatMap { deleted ->
                            if (deleted > 0) {
                                return@flatMap catLocalDataSource.deleteAll()
                            }

                            return@flatMap Single.just(deleted)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { deleted ->
                            if (deleted > 0) {
                                startActivity(Intent(this, SplashActivity::class.java))
                                finish()
                            }
                        }

                }
                R.id.catsFragment -> navController.navigate(it.itemId)
            }

            binding.drawer.closeDrawer(GravityCompat.START)

            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}