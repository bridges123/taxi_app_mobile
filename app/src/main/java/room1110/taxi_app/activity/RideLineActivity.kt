package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.adapter.RideLineAdapter
import room1110.taxi_app.api.Common
import room1110.taxi_app.api.RideApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.RideSerialized
import room1110.taxi_app.data.RideSerializer


class RideLineActivity : AppCompatActivity(), RideLineAdapter.ItemListener {

    lateinit var api: RideApiInterface
    lateinit var adapter: RideLineAdapter
    lateinit var rcView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_line)

        rcView = findViewById(R.id.rideLineRC)
        api = Common.retrofitService
        rcView.layoutManager = LinearLayoutManager(this)
        getRideList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getRideList() {
        api.getRideList().enqueue(object : Callback<MutableList<RideSerialized>> {
            override fun onFailure(call: Call<MutableList<RideSerialized>>, t: Throwable) {
//                adapter = RideLineAdapter(
//                    listOf(
//                        Ride(
//                            1,
//                            90,
//                            1.0,
//                            User(1, "Max", "Abashin"),
//                            4,
//                            arrayListOf(User(11)),
//                            call.request().body().toString(),
//                            t.message.toString(),
//                            "OPEN",
//                            "yandex",
//                            LocalDateTime.now().toString(),
//                            LocalDateTime.now().toString()
//                        )
//                    )
//                )
//                adapter.notifyDataSetChanged()
//                rcView.adapter = adapter

                Toast.makeText(
                    this@RideLineActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<MutableList<RideSerialized>>,
                response: Response<MutableList<RideSerialized>>
            ) {
                adapter = RideLineAdapter((response.body() as MutableList<RideSerialized>).map {
                        rideSerialized -> RideSerializer.deserialize(rideSerialized)
                }, this@RideLineActivity)
                adapter.notifyDataSetChanged()
                rcView.adapter = adapter
            }
        })
    }

    override fun onClickItem(ride: Ride) {
        val intent = Intent(this, RoomActivity::class.java).putExtra("rode", ride)
        startActivity(intent)
    }
}