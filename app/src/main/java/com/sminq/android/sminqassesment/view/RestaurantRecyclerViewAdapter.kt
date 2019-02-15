package com.sminq.android.sminqassesment.view

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sminq.android.sminqassesment.R
import com.sminq.android.sminqassesment.repository.api.model.Restaurant
import kotlinx.android.synthetic.main.fragment_restaurant_list_item.view.*

class RestaurantRecyclerViewAdapter(
    context: FragmentActivity?
) : RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {

    var mRestaurantist: List<Restaurant> = ArrayList<Restaurant>()
    var mContext:Context = context as Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.fragment_restaurant_list_item, parent, false);
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = mRestaurantist.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var restaurantName = mView.txtRestaurantName
        var openClosed = mView.txtOpenClosed
        var imageView = mView.imgRestaurant
        var rating = mView.txtRating
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = mRestaurantist.get(position)

        holder.restaurantName.setText(restaurant.name)
        holder.openClosed.setText("Price level : ${restaurant.price_level}")
        holder.rating.setText("Rating : ${restaurant.rating}");

        Glide.with(mContext)
            .load(restaurant.icon)
            .apply( RequestOptions()
            .placeholder(R.drawable.ic_not_found)
                .error(R.drawable.ic_not_found))
            .into(holder.imageView);
    }

    fun swapList(newFactList: List<Restaurant>?) {
        if (newFactList != null) {
            mRestaurantist = newFactList
            notifyDataSetChanged()
        }
    }
}
