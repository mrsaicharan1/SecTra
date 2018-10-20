package org.freeflo.sashank.sectra.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.freeflo.sashank.sectra.R
import org.freeflo.sashank.sectra.fragments.DetailsFragment
import org.freeflo.sashank.sectra.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Enter Details"
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
}
