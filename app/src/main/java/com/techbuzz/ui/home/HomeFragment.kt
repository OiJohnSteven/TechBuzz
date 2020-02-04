package com.techbuzz.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.techbuzz.FieldsActivity
import com.techbuzz.R
import com.techbuzz.ui.myaccount.MyaccountFragment
import com.techbuzz.ui.updates.UpdatesFragment
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.fields.setOnClickListener {
            startActivity(Intent(activity, FieldsActivity::class.java))
        }

        root.update.setOnClickListener {
            setTargetFragment(UpdatesFragment(), R.id.linear)
        }

        root.account.setOnClickListener {
            getFragmentManager()!!.beginTransaction().replace(R.id.linear, MyaccountFragment()).commit()
        }
        return root
    }
}