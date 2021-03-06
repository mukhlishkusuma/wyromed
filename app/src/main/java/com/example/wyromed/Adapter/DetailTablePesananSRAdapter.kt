package com.example.wyromed.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.DetailTablePesanan
import com.example.wyromed.R
import com.example.wyromed.Response.DetailMessageBooking.DataDetailMessageBooking
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.DataDetailMessageStockReq
import com.example.wyromed.ViewHolder.DetailTablePesananViewHolder

class DetailTablePesananSRAdapter (private var mContext: Context, mListDetailTablePesanan: ArrayList<DataDetailMessageStockReq>) :
    RecyclerView.Adapter<DetailTablePesananViewHolder>() {
    private val mListDetailTablePesanan: ArrayList<DataDetailMessageStockReq>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTablePesananViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_pesanan, parent, false)
        return DetailTablePesananViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailTablePesananViewHolder, position: Int) {
        if (mListDetailTablePesanan != null && mListDetailTablePesanan!!.size > 0) {
            holder.namaBarang!!.setText(mListDetailTablePesanan!![position].productName)
            holder.jumlahBarang!!.setText(mListDetailTablePesanan!![position].qtyRequested.toString())
            Log.d("qty req", mListDetailTablePesanan!![position].qtyRequested.toString())
        } else {
            return
        }
    }

    override fun getItemCount(): Int {
        return mListDetailTablePesanan!!.size
    }

    init {
        this.mContext = mContext
        this.mListDetailTablePesanan = mListDetailTablePesanan
    }
}