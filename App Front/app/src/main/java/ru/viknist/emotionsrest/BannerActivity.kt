package ru.viknist.emotionsrest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.viknist.emotionsrest.models.UserToken
import ru.viknist.horoshkeevaapp.R

class BannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        supportActionBar?.hide()

        val chooseEmotionBannerTextView = findViewById<TextView>(R.id.bannerTextView)

        chooseEmotionBannerTextView.setOnClickListener {
            val userInfo: UserToken =
                intent.getSerializableExtra("userInfo") as UserToken
            openChooseEmotionActivity(userInfo)
        }
    }

    private fun openChooseEmotionActivity(userInfo: UserToken){
        val intent = Intent(this, ActivityChooseEmotion::class.java)
        intent.putExtra("userInfo", userInfo)
        startActivity(intent)
    }
}