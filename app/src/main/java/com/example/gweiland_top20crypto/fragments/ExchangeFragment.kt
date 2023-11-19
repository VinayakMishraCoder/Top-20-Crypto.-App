package com.example.gweiland_top20crypto.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.gweiland_top20crypto.R
import com.example.gweiland_top20crypto.adapters.MainCryptoAdapter
import com.example.gweiland_top20crypto.databinding.FragmentExchangeBinding
import com.example.gweiland_top20crypto.utility.AnimationUtil.uniClick
import com.example.gweiland_top20crypto.utility.Constants
import com.example.gweiland_top20crypto.viewmodels.HomeViewModel
import com.google.android.material.textview.MaterialTextView

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
            if(it == true) {
                binding.mainTopCard.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                binding.loader.visibility = View.VISIBLE
            } else {
                binding.mainTopCard.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
            }
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
            val inflater = binding.root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.sort_pop_up_window, null) // pass custom layout
            val popupWindow = PopupWindow(view, 600, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)
            popupWindow.elevation = 40.0f

            view.findViewById<MaterialTextView>(R.id.price_dec).uniClick (true) {
                vm.currSortingCriteria = Constants.SORT_CRITERIA_PRICE
                vm.currSortingDirection = Constants.DESCENDING
                vm.getCryptoData {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            view.findViewById<MaterialTextView>(R.id.price_inc).uniClick (true) {
                vm.currSortingCriteria = Constants.SORT_CRITERIA_PRICE
                vm.currSortingDirection = Constants.ASCENDING
                vm.getCryptoData {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            view.findViewById<MaterialTextView>(R.id.vol_dec).uniClick (true) {
                vm.currSortingCriteria = Constants.SORT_CRITERIA_24H
                vm.currSortingDirection = Constants.DESCENDING
                vm.getCryptoData {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            view.findViewById<MaterialTextView>(R.id.vol_inc).uniClick (true) {
                vm.currSortingCriteria = Constants.SORT_CRITERIA_24H
                vm.currSortingDirection = Constants.ASCENDING
                vm.getCryptoData {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            view.findViewById<MaterialTextView>(R.id.default_sort_but).uniClick (true) {
                vm.currSortingCriteria = Constants.SORT_CRITERIA_DEFAULT_MARKET_CAP
                vm.currSortingDirection = Constants.DESCENDING
                vm.getCryptoData {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            popupWindow.showAsDropDown(binding.sortButton) // view to attach with
        }

        vm.getCryptoData({} , {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

        return binding.root;
    }
}