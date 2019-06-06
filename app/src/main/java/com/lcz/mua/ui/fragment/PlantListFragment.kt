package com.lcz.mua.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lcz.mua.databinding.FragmentPlantListBinding
import com.lcz.mua.ui.adapter.PlantAdapter

/**
 * desc 植物列表
 * Created by lcz on 2019/6/5.
 */
class PlantListFragment : androidx.fragment.app.Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentPlantListBinding.inflate(inflater,container,false)

        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.mRecyclerView.adapter = adapter
        subscribeUi(adapter)


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun subscribeUi(adapter: PlantAdapter) {

    }
}