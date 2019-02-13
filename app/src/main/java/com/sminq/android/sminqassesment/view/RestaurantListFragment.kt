package com.sminq.android.sminqassesment.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sminq.android.sminqassesment.R
import com.sminq.android.sminqassesment.viewmodel.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
Fragment class bound to view model and listen to live data
 */
class RestaurantListFragment : Fragment() {
    private var columnCount: Int = 1;
    private lateinit var viewModel: RestaurantViewModel
    //private lateinit var adapter: RestaurantRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_restaurant_list, container,
            false)

       /* lateinit var layoutManager: RecyclerView.LayoutManager;
        columnCount = resources.getInteger(R.integer.column_count)
        when (columnCount) {
            1 -> layoutManager = LinearLayoutManager(context)
            else -> layoutManager = GridLayoutManager(context, columnCount)
        }
        binding.listFacts.layoutManager = layoutManager;
        adapter = RestaurantRecyclerViewAdapter(activity)
        binding.listFacts.adapter = adapter

        viewModel = ViewModelProviders.of(this, FactViewModelFactory(activity?.application!!))
            .get(RestaurantViewModel::class.java)

        fetchDataFromRepository();
        binding.swipeRefresh.setOnRefreshListener {
            fetchDataFromRepository()
        }*/
        return view
    }

    /*fun fetchDataFromRepository() {

        if(!ConnectivityUtils.isNetworkAvailable(activity?.applicationContext!!)){
            showToast(getString(R.string.internet_unavailable))
        }
        viewModel.getFactList().observe(this, Observer { factList ->

            Log.e("RestaurantListFragment", "In observer :: fact size"+ factList?.size);
            adapter.swapList(factList)
            if (factList != null && factList.size != 0){
                hideLoading()
            }
            else{
                if(ConnectivityUtils.isNetworkAvailable(activity?.applicationContext!!)) {
                    showLoading()
                }else{
                    showToast(getString(R.string.cache_empty))
                    if(swipe_refresh.isRefreshing){
                        hideLoading()
                    }
                }
            }
        })
        viewModel.getAppTitle().observe(this, Observer { appTitle ->
            (activity as FactsActivity).title = appTitle?.title;
        })
    }*/

    private fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    private fun showToast(text:String) {
        Toast.makeText(this.context, text,Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RestaurantListFragment().apply {}
    }
}
