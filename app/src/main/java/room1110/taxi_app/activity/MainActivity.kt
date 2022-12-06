package room1110.taxi_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import room1110.taxi_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var authButt = findViewById<Button>(R.id.authButton)
//        authButt.setBackgroundColor(Color.parseColor("#FFB300"))
//        val intent = Intent(this, RideLineActivity::class.java)
//        startActivity(intent)
    }

    fun onClickProfile(view: View){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
    fun onClickRideLine(view: View){
        var intent = Intent(this, RideLineActivity::class.java)
        startActivity(intent)
    }
}