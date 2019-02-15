package com.sminq.android.sminqassesment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sminq.android.sminqassesment.R
import com.sminq.android.sminqassesment.util.ConnectivityUtils
import com.sminq.android.sminqassesment.viewmodel.RestaurantViewModel
import com.sminq.android.sminqassesment.viewmodel.RestaurantViewModelFactory

/**
Fragment class bound to view model and listen to live data
 */
class RestaurantListFragment : Fragment() {
    private var columnCount: Int = 1;
    private lateinit var viewModel: RestaurantViewModel
    private lateinit var adapter: RestaurantRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeView: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_restaurant_list, container,
            false
        )

        recyclerView = view.findViewById(R.id.listRestaurant) as RecyclerView
        swipeView = view.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
        lateinit var layoutManager: RecyclerView.LayoutManager;
        columnCount = 1//resources.getInteger(R.integer.column_count)
        when (columnCount) {
            1 -> layoutManager = LinearLayoutManager(context)
            else -> layoutManager = GridLayoutManager(context, columnCount)
        }
        recyclerView.layoutManager = layoutManager;
        adapter = RestaurantRecyclerViewAdapter(activity)
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this, RestaurantViewModelFactory(activity?.application!!))
            .get(RestaurantViewModel::class.java)

        swipeView.setOnRefreshListener {
            fetchDataFromRepository()
        }
        fetchDataFromRepository();
        return view
    }


    fun fetchDataFromRepository() {

        val lat = 18.516726
        val log = 73.856255
        val key = getString(R.string.places_api_key)
        val radius = 1000
        val type = "restaurant"

        viewModel.fetchRestaurantList(lat, log, key, radius, type).observe(this, Observer { factList ->

            adapter.swapList(factList)
            if (factList != null && factList.size != 0) {
                hideLoading()
            } else {
                if (ConnectivityUtils.isNetworkAvailable(activity?.applicationContext!!)) {
                    showLoading()
                } else {
                    showToast(getString(R.string.cache_empty))
                    if (swipeView.isRefreshing) {
                        hideLoading()
                    }
                }
            }


        })

    }


    private fun showLoading() {
        swipeView.isRefreshing = true
    }

    private fun hideLoading() {
        swipeView.isRefreshing = false
    }

    private fun showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RestaurantListFragment().apply {}
    }
}
