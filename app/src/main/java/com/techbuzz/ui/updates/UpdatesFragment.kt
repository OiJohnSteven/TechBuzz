package com.techbuzz.ui.updates

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.techbuzz.R
import com.techbuzz.WebActivity
import kotlinx.android.synthetic.main.fragment_updates.view.*

class UpdatesFragment : Fragment() {

    private lateinit var updatesViewModel: UpdatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updatesViewModel =
            ViewModelProviders.of(this).get(UpdatesViewModel::class.java)
         val root = inflater.inflate(R.layout.fragment_updates, container, false)
        /* val textView: TextView = root.findViewById(R.id.text_gallery)
        updatesViewModel.text.observe(this, Observer {
            textView.text = it
        })

*/
       root.medium.setOnClickListener {
                   val text:String ?= null
                   val obj = Intent(activity, WebActivity::class.java)
                   obj.putExtra("url", "https://www.medium.com")
                   startActivity(obj)
               }

              root.stack.setOnClickListener {
                   val text:String ?= null
                   val obj = Intent(activity, WebActivity::class.java)
                   obj.putExtra("url", "https://www.stackoverflow.com")
                   startActivity(obj)
               }

               root.linked.setOnClickListener  {
                   val text:String ?= null
                   val obj = Intent(activity, WebActivity::class.java)
                   obj.putExtra("url", "https://www.linkedin.com")
                   startActivity(obj)
               }

              root.quora.setOnClickListener  {
                   val text:String ?= null
                   val obj = Intent(activity, WebActivity::class.java)
                   obj.putExtra("url", "https://www.quora.com")
                   startActivity(obj)
               }

        return root
    }
}