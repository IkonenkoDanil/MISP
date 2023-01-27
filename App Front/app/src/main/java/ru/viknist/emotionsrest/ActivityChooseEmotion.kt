package ru.viknist.emotionsrest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import ru.viknist.emotionsrest.activity.*
import ru.viknist.emotionsrest.models.UserToken
import ru.viknist.horoshkeevaapp.R

class ActivityChooseEmotion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_emotion)
        supportActionBar?.hide()


        val happyImageView = findViewById<ImageView>(R.id.happyImageView)
        val sadImageView = findViewById<ImageView>(R.id.sadImageView)
        val disappointmentImageView = findViewById<ImageView>(R.id.disappointmentImageView)
        val angryImageView = findViewById<ImageView>(R.id.angryImageView)
        val fearImageView = findViewById<ImageView>(R.id.fearImageView)

        happyImageView.setOnClickListener{
                openEmotionActivity(Emotion.HAPPY)
        }
        sadImageView.setOnClickListener {
                openEmotionActivity(Emotion.SAD)
        }
        disappointmentImageView.setOnClickListener {
                openEmotionActivity(Emotion.DISAPPOINTED)
        }
        angryImageView.setOnClickListener {
                openEmotionActivity(Emotion.ANGRY)
        }
        fearImageView.setOnClickListener {
                openEmotionActivity(Emotion.FEAR)
        }
    }

    private fun openEmotionActivity(emotion: Emotion){
        val intent = when(emotion) {
            Emotion.HAPPY -> Intent(this, HappyActivity::class.java)
            Emotion.ANGRY -> Intent(this, AngryActivity::class.java)
            Emotion.FEAR -> Intent(this, FearActivity::class.java)
            Emotion.SAD -> Intent(this, SadActivity::class.java)
            Emotion.DISAPPOINTED -> Intent(this, DisappointmentActivity::class.java)
        }
        intent.putExtra("userInfo", getIntent().getSerializableExtra("userInfo") as UserToken)
        startActivity(intent)
    }
}