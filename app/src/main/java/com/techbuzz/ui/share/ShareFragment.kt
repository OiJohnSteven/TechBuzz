package com.techbuzz.ui.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.techbuzz.R

class ShareFragment : Fragment() {

    private lateinit var shareViewModel: ShareViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel =
            ViewModelProviders.of(this).get(ShareViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_share)
        shareViewModel.text.observe(this, Observer {
            textView.text = it
        })

        */

        val share = root.findViewById<Button>(R.id.share)

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Invite to Download TECHBUZZ APP")
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.share)))


        }
        return root
    }
}