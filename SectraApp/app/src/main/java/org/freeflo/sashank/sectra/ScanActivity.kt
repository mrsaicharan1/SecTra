package org.freeflo.sashank.sectra

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.NavUtils
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_scan.*


class ScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        title = "Scan Your Baggage"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setViewPager()

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }*/

    private fun setViewPager() {
        navigationStrip.setTitles("Carry", "Check-In")
        autoViewPager.adapter = EventDetailViewPagerAdapter(supportFragmentManager)
        navigationStrip.setViewPager(autoViewPager)
    }

    private inner class EventDetailViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val fragments = listOf(CarryScanFragment(), CheckInScanFragment())
        override fun getItem(position: Int): Fragment  = fragments[position]
        override fun getCount(): Int = fragments.size
    }
}
