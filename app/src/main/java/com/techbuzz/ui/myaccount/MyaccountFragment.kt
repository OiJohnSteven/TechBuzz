package com.techbuzz.ui.myaccount


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.techbuzz.PostActivity
import com.techbuzz.R
import com.techbuzz.UploadsActivity
import kotlinx.android.synthetic.main.fragment_myaccount.view.*

class MyaccountFragment : Fragment() {

    private lateinit var myaccountViewModel: MyaccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myaccountViewModel =
            ViewModelProviders.of(this).get(MyaccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_myaccount, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_tools)
        myaccountViewModel.text.observe(this, Observer {
            textView.text = it
        })
        */

        root.upload.setOnClickListener {
            startActivity(Intent(activity, UploadsActivity::class.java))
        }

        root.post.setOnClickListener {
            startActivity(Intent(activity, PostActivity::class.java))
        }
        return root
    }
}