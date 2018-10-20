package org.freeflo.sashank.sectra.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Menu
import android.view.MenuItem
import org.freeflo.sashank.sectra.R
import org.freeflo.sashank.sectra.fragments.DetailsFragment
import org.freeflo.sashank.sectra.fragments.HomeFragment
import android.view.WindowManager



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame, HomeFragment())
            .commit()

    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments

        if (fragmentList.size != 0) {
            val activeFragment = fragmentList[fragmentList.size - 1]
            if (activeFragment is DetailsFragment)
                supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()
            else
                super.onBackPressed()
        } else
            super.onBackPressed()
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.logout) true
        else super.onOptionsItemSelected(item)
    }*/
}
