package com.example.atoz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.atoz.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottonNavigation.apply {
            background = null
            setOnItemSelectedListener { item ->
                when(item.itemId){
                    R.id.home -> openFragment(HomeFragment())
                    R.id.profile -> openFragment(ProfileFragment())
                    R.id.cart -> openFragment(CartFragment())
                    R.id.menu -> openFragment(MenuFragment())
                    R.id.add -> Toast.makeText(this@MainActivity, "Categories", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
        
//        binding.fab.setOnClickListener {
//            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.prime -> openFragment(PrimeFragment())
            R.id.fashion -> openFragment(FashionFragment())
            R.id.electronics -> openFragment(ElectronicFragment())
            R.id.fresh -> Toast.makeText(this, "Fresh", Toast.LENGTH_SHORT).show()
            R.id.beauty -> Toast.makeText(this, "Beauty", Toast.LENGTH_SHORT).show()
            R.id.furniture -> Toast.makeText(this, "Furniture", Toast.LENGTH_SHORT).show()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
            super.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}