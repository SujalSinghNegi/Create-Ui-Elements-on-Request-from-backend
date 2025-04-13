package com.example.createuiondemand

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
data class UIElement(
    val type: String,
    val label: String
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val responseFromBackend = listOf(
            UIElement("text", "Enter your name"),
            UIElement("checkbox", "Subscribe to newsletter"),
            UIElement("button", "Submit")
        )

        val container = findViewById<LinearLayout>(R.id.dynamicLayout)

        for (element in responseFromBackend) {
            when (element.type) {
                "text" -> {
                    val editText = EditText(this).apply {
                        hint = element.label
                    }
                    container.addView(editText)
                }
                "checkbox" -> {
                    val checkBox = CheckBox(this).apply {
                        text = element.label
                    }
                    container.addView(checkBox)
                }
                "button" -> {
                    val button = Button(this).apply {
                        text = element.label
                    }
                    container.addView(button)
                }
            }
        }


    }


}