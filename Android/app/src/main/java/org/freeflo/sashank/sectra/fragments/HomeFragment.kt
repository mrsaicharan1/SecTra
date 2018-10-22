package org.freeflo.sashank.sectra.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.fragment_home.*
import org.freeflo.sashank.sectra.R

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkInfoBT.setOnClickListener {
            val pnr = pnrET.text
            val dialog = ProgressDialog.show(activity, "Fetching Details", "Loading...")
            dialog.show()
            Handler().postDelayed((
                    Runnable {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.frame, DetailsFragment())?.commit()
                        dialog.dismiss()
                    }), 2000)

        }

    }
}