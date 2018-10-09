package com.ntkhanh.myinterestedapp.ui.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent

import com.ntkhanh.myinterestedapp.R
import com.ntkhanh.myinterestedapp.binding.FragmentDataBindingComponent
import com.ntkhanh.myinterestedapp.util.autoCleared
import com.ntkhanh.myinterestedapp.databinding.FragmentSearchBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment() {


    private var mDataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var mBinding by autoCleared<FragmentSearchBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


}
