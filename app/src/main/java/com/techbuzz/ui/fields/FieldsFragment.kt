package com.techbuzz.ui.fields

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.techbuzz.FieldsActivity
import com.techbuzz.R
import kotlinx.android.synthetic.main.fragment_field.view.*

class FieldsFragment : Fragment() {

    private lateinit var fieldsViewModel: FieldsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fieldsViewModel =
            ViewModelProviders.of(this).get(FieldsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_field, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_slideshow)
        fieldsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        */

        root.learn.setOnClickListener {
            startActivity(Intent(activity, FieldsActivity::class.java))
        }
        return root
    }
}