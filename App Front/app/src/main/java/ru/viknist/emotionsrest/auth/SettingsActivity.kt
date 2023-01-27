package ru.viknist.emotionsrest.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.KeyListener
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.viknist.emotionsrest.models.Password
import ru.viknist.emotionsrest.models.PatchUserData
import ru.viknist.emotionsrest.models.UserToken
import ru.viknist.emotionsrest.network.RestApi
import ru.viknist.emotionsrest.network.ServiceBuilder
import ru.viknist.emotionsrest.utils.PasswordUtils
import ru.viknist.horoshkeevaapp.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.hide()

        val emailButtonImageView = findViewById<ImageView>(R.id.emailEditButtonImageView)
        val nameButtonImageView = findViewById<ImageView>(R.id.nameEditButtonImageView)
        val passwordButtonImageView = findViewById<ImageView>(R.id.passwordEditButtonImageView)
        val passwordShowButtonImageView = findViewById<ImageView>(R.id.showPasswordButtonImageView)
        val emailEditText = findViewById<EditText>(R.id.emailSettingsEditText)
        val nameEditText = findViewById<EditText>(R.id.nameSettingsEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordSettingsEditText)
        val backLayout = findViewById<ConstraintLayout>(R.id.backLayout)

        val userInfo: UserToken =
            intent.getSerializableExtra("userInfo") as UserToken

        val patchUserData = PatchUserData(userInfo.user?.email, userInfo.user?.name)

        emailEditText.setText(userInfo.user?.email)
        emailEditText.tag = emailEditText.keyListener
        emailEditText.keyListener = null

        nameEditText.setText(userInfo.user?.name)
        nameEditText.tag = nameEditText.keyListener
        nameEditText.keyListener = null

        passwordEditText.setText(userInfo.user?.password)
        passwordEditText.tag = passwordEditText.keyListener
        passwordEditText.keyListener = null

        backLayout.setOnClickListener {
            onBackPressed()
        }

        emailButtonImageView.setOnClickListener {
            if (emailEditText.keyListener != null) {
                lifecycleScope.launch {
                    try {
                        patchUserData.email = emailEditText.text.toString()
                        ServiceBuilder.buildService(RestApi::class.java)
                            .changeEmailName(patchUserData, "Bearer ${userInfo.accessToken}")
                        userInfo.user?.email =  emailEditText.text.toString()
                        emailEditText.keyListener = null
                        emailButtonImageView.setImageResource(R.drawable.edit)
                    } catch (e: Exception) {
                        patchUserData.email = userInfo.user?.email
                        println(e.message)
                    }
                }
            } else {
                emailEditText.keyListener = emailEditText.tag as KeyListener?
                emailButtonImageView.setImageResource(R.drawable.savebutton)
            }
        }

        nameButtonImageView.setOnClickListener {
            if (nameEditText.keyListener != null) {
                lifecycleScope.launch {
                    try {
                        patchUserData.name = nameEditText.text.toString()
                        ServiceBuilder.buildService(RestApi::class.java)
                            .changeEmailName(patchUserData, "Bearer ${userInfo.accessToken}")
                        userInfo.user?.name = nameEditText.text.toString()
                        nameEditText.keyListener = null
                        nameButtonImageView.setImageResource(R.drawable.edit)
                    } catch (e: Exception) {
                        patchUserData.name = userInfo.user?.name
                        println(e.message)
                    }
                }
            } else {
                nameEditText.keyListener = nameEditText.tag as KeyListener?
                nameButtonImageView.setImageResource(R.drawable.savebutton)
            }
        }

        passwordButtonImageView.setOnClickListener {
            if (passwordEditText.keyListener != null) {
                passwordEditText.keyListener = null
                passwordButtonImageView.setImageResource(R.drawable.edit)
                val password = Password(
                    passwordEditText.text.toString(),
                    passwordEditText.text.toString()
                )

                lifecycleScope.launch {
                    try {
                        ServiceBuilder.buildService(RestApi::class.java)
                            .changePassword(password, "Bearer ${userInfo.accessToken}")
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            } else {
                passwordEditText.keyListener = passwordEditText.tag as KeyListener?
                passwordButtonImageView.setImageResource(R.drawable.savebutton)
            }
        }

        passwordShowButtonImageView.setOnClickListener {
            println(passwordEditText.inputType)
            PasswordUtils.showHidePassword(passwordEditText)
        }

    }
}