package com.example.createuiondemand

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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


        //----------------------- U P D A T E -------------------------------------------


        val createButton:Button = findViewById<Button>(R.id.createButton)
        val label:EditText = findViewById<EditText>(R.id.label)
        val viewType:EditText = findViewById<EditText>(R.id.viewType)
        val possibleTypes = listOf("text", "checkbox", "button")
        var canCreate:Boolean = false
        var labelFilled : Boolean= false
        var typeFilled : Boolean= false
        var eleType : String?=""
        var eleLabel:String?
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                 labelFilled = label.text.toString().isNotEmpty()
                 typeFilled = viewType.text.toString().isNotEmpty()
                 eleType = viewType.text.toString().lowercase().trim()
                if (labelFilled && typeFilled && eleType in possibleTypes) canCreate= true
                else canCreate= false

                if(canCreate){
                    createButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.green))
                }else{
                    createButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.grey))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }



        label.addTextChangedListener(watcher)
        viewType.addTextChangedListener(watcher)

        createButton.setOnClickListener {
           // eleType = viewType.text.toString().lowercase().trim()
            eleLabel= label.text.toString()
            if (canCreate) {
                when (eleType) {
                    "text" -> {
                        val editText = EditText(this).apply {
                            hint = eleLabel
                        }
                        container.addView(editText)
                    }

                    "checkbox" -> {
                        val checkBox = CheckBox(this).apply {
                            text = eleLabel
                        }
                        container.addView(checkBox)
                    }

                    "button" -> {
                        val button = Button(this).apply {
                            text = eleLabel
                        }
                        container.addView(button)
                    }
                }
                label.text.clear()
                viewType.text.clear()
            }
        }

    }


}