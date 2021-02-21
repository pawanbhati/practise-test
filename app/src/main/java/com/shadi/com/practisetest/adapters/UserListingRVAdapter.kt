package com.shadi.com.practisetest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shadi.com.practisetest.R
import com.shadi.com.practisetest.models.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem_user_list.view.*


/**
 * This is recyclerview adapter class which is used to load recyclerview included in MainActivity
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
class UserListingRVAdapter(context: Context, itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<UserListingRVAdapter.UserListViewHolder>() {

    private var context: Context? = null
    var itemClickListener: ItemClickListener? = null
    private var userList: List<ResultsItem?>? = null

    init {
        this.itemClickListener = itemClickListener
        this.context = context
    }

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data1: ResultsItem?, context: Context, itemClickListener: ItemClickListener) {

            data1?.let {
                Picasso.with(context).load(it.picture?.large)
                    .into(itemView.img_listitem_users_image)
                itemView.txt_listitem_users_name?.text =
                    "${it.name?.title} ${it.name?.first} ${it.name?.last}"
                itemView.txt_listitem_users_height?.text = "${it.dob?.age}, ${it.gender}"
                itemView.txt_listitem_users_address?.text =
                    "${it.location?.street?.number}, ${it.location?.city}, ${it.location?.country}"

                if (it.isInvitationAccepted != null) {
                    itemView.linear_listitem_users_cta?.visibility = View.GONE
                    itemView.txt_listitem_users_status?.visibility = View.VISIBLE
                    if (!it.isInvitationAccepted!!) {
                        itemView.txt_listitem_users_status?.text =
                            context.getString(R.string.declined)
                        itemView.txt_listitem_users_status?.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.red
                            )
                        )
                    } else {
                        itemView.txt_listitem_users_status?.text =
                            context.getString(R.string.accepted)
                        itemView.txt_listitem_users_status?.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.green
                            )
                        )
                    }
                }
                itemView.linear_listitem_users_decline?.setOnClickListener { _ ->
                    it.isInvitationAccepted = false
                    itemClickListener.onDeclinedClick(it)
                }
                itemView.linear_listitem_users_accept?.setOnClickListener { _ ->
                    it.isInvitationAccepted = true
                    itemClickListener.onAcceptedClick(it)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return if (userList != null) userList!!.size else 0
    }

    /**
     * Method is used to set list to recyclerview and it is called from MainActivity
     * @param userList: List retrieved from database
     * */
    fun setResult(userList: List<ResultsItem?>?) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.onBind(userList?.get(position), context!!, itemClickListener!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_user_list, parent, false)
        return UserListViewHolder(view)

    }

    interface ItemClickListener {
        fun onDeclinedClick(resultsItem: ResultsItem?)
        fun onAcceptedClick(resultsItem: ResultsItem?)
    }
}