package com.example.chareta

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationHost {

    private val onNavigationItemSelectedLitsner = BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId) {
            R.id.bottom_posted -> {
                navigateTo(PostedItemFragment(), false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_bids -> {
                navigateTo(YourBidsFragment(), false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_manage -> {
                navigateTo(YourPostsFragment(), false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_status -> {
                navigateTo(StatusFragment(), false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedLitsner)

        hideBottomBar(false)


    }

    fun hideBottomBar(isHidden: Boolean) {
        bottom_navigation.visibility = if (isHidden) View.GONE else View.VISIBLE
    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    fun connected():Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }

}
