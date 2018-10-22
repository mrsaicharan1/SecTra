package org.freeflo.sashank.sectra.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_details.*
import org.freeflo.sashank.sectra.R
import org.freeflo.sashank.sectra.activities.ScanActivity
import org.jetbrains.anko.support.v4.startActivity

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBT.setOnClickListener { startActivity<ScanActivity>() }
    }
}