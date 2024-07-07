package com.test.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.assignment.databinding.FragmentApplicationBinding
import com.test.assignment.network.entity.Data
import com.test.assignment.ui.adapter.ApplicationListAdapter
import com.test.assignment.ui.adapter.SelectedListener
import java.util.Locale


class ApplicationFragment(private val getApiData: Data) : Fragment(), SelectedListener {


    private lateinit var binding: FragmentApplicationBinding

    private var adapter: ApplicationListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApplicationBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerItems.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerItems.setHasFixedSize(false)

        adapter = ApplicationListAdapter(
            requireContext(),
            getApiData.appList,
            this
        )
        binding.recyclerItems.adapter = adapter

        binding.edtSearchbar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(
                    newText?.trim()?.lowercase(Locale.getDefault())
                )
                return false
            }
        })

    }

    override fun selectItems(isSelect: Boolean, pos: Int) {
        if (!getApiData.appList.isNullOrEmpty()) {
            getApiData.appList[pos].isSelect = isSelect
            binding.recyclerItems.post {
                adapter?.notifyItemChanged(pos)
            }
        }
    }


}