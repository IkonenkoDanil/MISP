package ru.viknist.emotionsrest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.viknist.emotionsrest.auth.SettingsActivity
import ru.viknist.emotionsrest.models.PatchNoteModel
import ru.viknist.emotionsrest.models.StatsModel
import ru.viknist.emotionsrest.models.UserToken
import ru.viknist.emotionsrest.network.RestApi
import ru.viknist.emotionsrest.network.ServiceBuilder
import ru.viknist.horoshkeevaapp.R
import java.time.DayOfWeek
import java.time.LocalDate

class AppActivity : AppCompatActivity() {

    lateinit var userInfo: UserToken
    lateinit var stats: List<StatsModel>
    var mondayStat: StatsModel = StatsModel(null, null, "2023-01-23", null, null)
    var tuesdayStat: StatsModel = StatsModel(null, null, "2023-01-24", null, null)
    var wednesdayStat: StatsModel = StatsModel(null, null, "2023-01-25", null, null)
    var thursdayStat: StatsModel = StatsModel(null, null, "2023-01-26", null, null)
    var fridayStat: StatsModel = StatsModel(null, null, "2023-01-27", null, null)
    var saturdayStat: StatsModel = StatsModel(null, null, "2023-01-28", null, null)
    var sundayStat: StatsModel = StatsModel(null, null, "2023-01-29", null, null)

    override fun onResume() {
        super.onResume()

        val welcomeNameTextView = findViewById<TextView>(R.id.welcomeNameTextView)
        val mondayLayout = findViewById<ConstraintLayout>(R.id.mondayLayout)
        val mondayDayTextView = findViewById<TextView>(R.id.mondayDayTextView)
        val mondayImageView = findViewById<ImageView>(R.id.mondayImageView)
        val tuesdayLayout = findViewById<ConstraintLayout>(R.id.tuesdayLayout)
        val tuesdayDayTextView = findViewById<TextView>(R.id.tuesdayDayTextView)
        val tuesdayImageView = findViewById<ImageView>(R.id.tuesdayImageView)
        val wednesdayLayout = findViewById<ConstraintLayout>(R.id.wednesdayLayout)
        val wednesdayDayTextView = findViewById<TextView>(R.id.wednesdayDayTextView)
        val wednesdayImageView = findViewById<ImageView>(R.id.wednesdayImageView)
        val thursdayLayout = findViewById<ConstraintLayout>(R.id.thursdayLayout)
        val thursdayDayTextView = findViewById<TextView>(R.id.thursdayDayTextView)
        val thursdayImageView = findViewById<ImageView>(R.id.thursdayImageView)
        val fridayLayout = findViewById<ConstraintLayout>(R.id.fridayLayout)
        val fridayDayTextView = findViewById<TextView>(R.id.fridayDayTextView)
        val fridayImageView = findViewById<ImageView>(R.id.fridayImageView)
        val saturdayLayout = findViewById<ConstraintLayout>(R.id.saturdayLayout)
        val saturdayDayTextView = findViewById<TextView>(R.id.saturdayDayTextView)
        val saturdayImageView = findViewById<ImageView>(R.id.saturdayImageView)
        val sundayLayout = findViewById<ConstraintLayout>(R.id.sundayLayout)
        val sundayDayTextView = findViewById<TextView>(R.id.sundayDayTextView)
        val sundayImageView = findViewById<ImageView>(R.id.sundayImageView)
        val learnMoreTextView = findViewById<TextView>(R.id.learnMoreTextView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val chooseEmotionTextView = findViewById<TextView>(R.id.addNewEmotionTextView)
        val saveNoteTextView = findViewById<TextView>(R.id.saveNoteTextView)
        val noteEditText = findViewById<EditText>(R.id.noteEditText)
        val bannerLayout = findViewById<ConstraintLayout>(R.id.bannerLayout)
        val service = ServiceBuilder.buildService(RestApi::class.java)
        val context: Context = this

        var selectedDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek


        lifecycleScope.launch {
            try {
                userInfo.user = service.getUserInfo("Bearer ${userInfo.accessToken}")
                stats = service.getStats("Bearer ${userInfo.accessToken}")
                println(stats)
                for (day in stats) {
                    when (LocalDate.parse(day.date).dayOfWeek) {
                        DayOfWeek.MONDAY -> {
                            mondayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                mondayDayTextView,
                                mondayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.TUESDAY -> {
                            tuesdayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                tuesdayDayTextView,
                                tuesdayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.WEDNESDAY -> {
                            wednesdayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                wednesdayDayTextView,
                                wednesdayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.THURSDAY -> {
                            thursdayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                thursdayDayTextView,
                                thursdayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.FRIDAY -> {
                            fridayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                fridayDayTextView,
                                fridayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.SATURDAY -> {
                            saturdayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                saturdayDayTextView,
                                saturdayImageView,
                                day.overallEmotion
                            )
                        }

                        DayOfWeek.SUNDAY -> {
                            sundayStat =
                                service.getStatFromId(day.id!!, "Bearer ${userInfo.accessToken}")
                            changeDayData(
                                sundayDayTextView,
                                sundayImageView,
                                day.overallEmotion
                            )
                        }
                    }
                }
                val adapter = when (selectedDayOfWeek) {
                    DayOfWeek.MONDAY -> mondayStat.notes
                    DayOfWeek.TUESDAY -> tuesdayStat.notes
                    DayOfWeek.WEDNESDAY -> wednesdayStat.notes
                    DayOfWeek.THURSDAY -> thursdayStat.notes
                    DayOfWeek.FRIDAY -> fridayStat.notes
                    DayOfWeek.SATURDAY -> saturdayStat.notes
                    DayOfWeek.SUNDAY -> sundayStat.notes
                }?.let { NotesAdapter(context, it) }

                recyclerView.adapter = adapter

                val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
                decoration.setDrawable(ContextCompat.getDrawable(context, R.color.black)!!)
                recyclerView.addItemDecoration(decoration)

                welcomeNameTextView.text = "Привет, ${userInfo.user?.name}"
            } catch (e: Exception) {
                println(e.message)
            }
        }

        learnMoreTextView.setOnClickListener {
            openBannerActivity(userInfo)
        }

        chooseEmotionTextView.setOnClickListener {
            openChooseEmotionActivity(userInfo)
        }

        mondayLayout.setOnClickListener {
            val adapter = mondayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(mondayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.MONDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        tuesdayLayout.setOnClickListener {
            val adapter = tuesdayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(tuesdayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.TUESDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        wednesdayLayout.setOnClickListener {
            val adapter = wednesdayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(wednesdayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.WEDNESDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        thursdayLayout.setOnClickListener {
            val adapter = thursdayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(thursdayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.THURSDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        fridayLayout.setOnClickListener {
            val adapter = fridayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(fridayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.FRIDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        saturdayLayout.setOnClickListener {
            val adapter = saturdayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(saturdayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.SATURDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }

        sundayLayout.setOnClickListener {
            val adapter = sundayStat.notes?.let { it -> NotesAdapter(context, it) }
            noteEditText.setText(sundayStat.text)

            recyclerView.adapter = adapter
            if (selectedDayOfWeek != DayOfWeek.SUNDAY){
                bannerLayout.visibility = View.GONE
                chooseEmotionTextView.visibility = View.GONE
                saveNoteTextView.visibility = View.GONE
            } else {
                saveNoteTextView.visibility = View.VISIBLE
                bannerLayout.visibility = View.VISIBLE
                chooseEmotionTextView.visibility = View.VISIBLE
            }
        }



        saveNoteTextView.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val stat: StatsModel =
                    when(selectedDayOfWeek) {
                        DayOfWeek.MONDAY -> mondayStat
                        DayOfWeek.TUESDAY -> tuesdayStat
                        DayOfWeek.WEDNESDAY -> wednesdayStat
                        DayOfWeek.THURSDAY -> thursdayStat
                        DayOfWeek.FRIDAY -> fridayStat
                        DayOfWeek.SATURDAY -> saturdayStat
                        DayOfWeek.SUNDAY -> sundayStat
                    }

                    service.patchNote(stat.id!!, PatchNoteModel(noteEditText.text.toString()), "Bearer ${userInfo.accessToken}")
                }catch (e: Exception){
                    println(e.message)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        supportActionBar?.hide()

        val settingsButtonImageView = findViewById<ImageView>(R.id.settingsButton)

        userInfo =
            intent.getSerializableExtra("userInfo") as UserToken

        settingsButtonImageView.setOnClickListener {
            openSettingsActivity()
        }
    }

    private fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java).apply {
            putExtra("userInfo", intent.getSerializableExtra("userInfo") as UserToken)
        }
        startActivity(intent)
    }

    private fun changeDayData(dayTextView: TextView, dayImageView: ImageView, emotion: String?) {
        when (emotion) {
            null -> {

            }
            "happy" -> {
                dayTextView.text = ""
                dayImageView.setImageResource(R.drawable.happyface)
            }
            "sad" -> {
                dayTextView.text = ""
                dayImageView.setImageResource(R.drawable.sadface)
            }
            "scared" -> {
                dayTextView.text = ""
                dayImageView.setImageResource(R.drawable.fearface)
            }
            "disappointed" -> {
                dayTextView.text = ""
                dayImageView.setImageResource(R.drawable.disappointmentface)
            }
            "angry" -> {
                dayTextView.text = ""
                dayImageView.setImageResource(R.drawable.angryface)
            }
        }
    }

    private fun openChooseEmotionActivity(userInfo: UserToken) {
        val intent = Intent(this, ActivityChooseEmotion::class.java)
        intent.putExtra("userInfo", userInfo)
        startActivity(intent)
    }

    private fun openBannerActivity(userInfo: UserToken) {
        val intent = Intent(this, BannerActivity::class.java)
        intent.putExtra("userInfo", userInfo)
        startActivity(intent)
    }


}