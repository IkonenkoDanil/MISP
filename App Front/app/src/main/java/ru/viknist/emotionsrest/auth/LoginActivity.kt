package ru.viknist.emotionsrest.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.viknist.emotionsrest.ActivityChooseEmotion
import ru.viknist.emotionsrest.models.UserToken
import ru.viknist.emotionsrest.models.UserData
import ru.viknist.emotionsrest.network.RestApi
import ru.viknist.emotionsrest.network.ServiceBuilder
import ru.viknist.emotionsrest.utils.PasswordUtils
import ru.viknist.horoshkeevaapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val registerButtonText = findViewById<TextView>(R.id.registerButtonTextView)
        val loginButtonText = findViewById<TextView>(R.id.loginButtonTextView)

        val showHideButton = findViewById<ImageView>(R.id.showHidePasswordButton)

        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordLogin)
        val loginEditText = findViewById<EditText>(R.id.emailLoginEditText)

        val incorrectTextView = findViewById<TextView>(R.id.incorrectTextView)

        registerButtonText.setOnClickListener {
            openRegisterActivity()
        }

        loginButtonText.setOnClickListener {

            val userInfo = UserData(idUser = null,
                username = loginEditText.text.toString(),
                email = null,
                password = passwordEditText.text.toString(),
                name = null)

            lifecycleScope.launch{
                try {
                    val tokenInfo = ServiceBuilder.buildService(RestApi::class.java).loginUser(userInfo)
                    println(tokenInfo.toString())
                    openChooseEmotionActivity(tokenInfo)
                    incorrectTextView.visibility = View.INVISIBLE
                } catch (e: HttpException){
                    if (e.code() == 400)
                        incorrectTextView.visibility = View.VISIBLE
                }
            }
        }

        showHideButton.setOnClickListener {
            PasswordUtils.showHidePassword(passwordEditText)
        }
    }

    private fun openRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun openChooseEmotionActivity(userInfo: UserToken){
        val intent = Intent(this, ActivityChooseEmotion::class.java)
        intent.putExtra("userInfo", userInfo)
        startActivity(intent)
    }

}