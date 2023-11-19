package com.example.gweiland_top20crypto.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.gweiland_top20crypto.R
import com.example.gweiland_top20crypto.adapters.MainCryptoAdapter
import com.example.gweiland_top20crypto.databinding.FragmentExchangeBinding
import com.example.gweiland_top20crypto.utility.AnimationUtil.uniClick
import com.example.gweiland_top20crypto.viewmodels.HomeViewModel

class ExchangeFragment : Fragment() {

    lateinit var binding: FragmentExchangeBinding
    lateinit var adapter: MainCryptoAdapter

    private val vm: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExchangeBinding.inflate(inflater,container,false);

        adapter = MainCryptoAdapter()

        adapter.setOnItemClickListener {
            // handle item click.
        }

        binding.recyclerView.adapter = adapter

        vm.loading.observe(viewLifecycleOwner) {
            // handle loading
        }

        binding.searchQuery.doOnTextChanged { query, _, _, _ ->
            // handle searching
        }

        vm.newCryptoData.observe(viewLifecycleOwner) {
            // handle the first time loading the data.
            if(!it.isNullOrEmpty()) {
                binding.currListing = it?.get(0)
                binding.currQuote = it?.get(0)?.quote?.get("USD")
                binding.price = String.format("%.2f", it?.get(0)?.quote?.get("USD")?.price)
                adapter.setUpRecyclerView(it)
            }
        }

        binding.sortButton.uniClick(true) {
            // handle sorting the list by volume_24h (asc/desc), price (asc/desc) or default.
//            val inflater = binding.root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val view = inflater.inflate(R.layout.filter_popup_window, null) // pass custom layout
//            val popupWindow = PopupWindow(view, 500, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)
//            popupWindow.elevation = 40.0f
//            view.findViewById<CardView>(R.id.under24Filter).setOnClickListener {
//                viewModel.setFilter(UNDER_24_FILTER)
//            }
//            view.findViewById<CardView>(R.id.before24Filter).setOnClickListener {
//                viewModel.setFilter(LATER_CONTESTS_FILTER)
//            }
//            view.findViewById<CardView>(R.id.allFilter).setOnClickListener {
//                viewModel.setFilter(ALL_CONTESTS_FILTER)
//            }
//            popupWindow.showAsDropDown(binding.sortButton) // view to attach with
        }

        vm.getCryptoData({} , {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

        return binding.root;
    }
}