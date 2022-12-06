package room1110.taxi_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import java.time.format.DateTimeFormatter

class RideLineAdapter(private val rideList: List<Ride>, var listener: ItemListener) :
    RecyclerView.Adapter<RideLineAdapter.RideViewHolder>() {
    class RideViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val dtFrom: TextView = itemView.findViewById(R.id.dtFrom)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val membersCount: TextView = itemView.findViewById(R.id.membersCount)
        private val status: TextView = itemView.findViewById(R.id.status)

        private val addressFrom: TextView = itemView.findViewById(R.id.addressFrom)
        private val addressTo: TextView = itemView.findViewById(R.id.addressTo)

        private val owner: ImageView = itemView.findViewById(R.id.owner)
        private val member1: ImageView = itemView.findViewById(R.id.member1)
        private val member2: ImageView = itemView.findViewById(R.id.member2)
        private val member3: ImageView = itemView.findViewById(R.id.member3)

        @SuppressLint("SetTextI18n")
        fun bind(ride: Ride, listener: ItemListener) {
            dtFrom.text = ride.getDtFrom()!!.format(DateTimeFormatter.ofPattern("HH:mm"))
            if (ride.price == 0) {
                price.visibility = View.GONE
            } else {
                price.text = "${ride.price} ₽"
            }
            membersCount.text = "${ride.members.size + 1}/${ride.rideSize}"
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            status.text = ride.owner.getAvatar().toString()

            val avatarBytes = ride.owner.getAvatar()
            if (avatarBytes != null) {
                owner.setImageBitmap(byteArrayToBitmap(avatarBytes))
                owner.setPadding(5)
                owner.setBackgroundColor(Color.BLACK)
            }

            // доделать динамическое кол-во members
            // member1.text = ride.members.

            itemView.setOnClickListener {
                listener.onClickItem(ride)
            }
        }

        private fun byteArrayToBitmap(data: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(data, 0, data.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_line_item, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = rideList[position]
        holder.bind(ride, listener)
    }

    override fun getItemCount() = rideList.size

    interface ItemListener {
        fun onClickItem(ride: Ride)
    }

}
