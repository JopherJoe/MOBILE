    package com.example.traveltour

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import androidx.activity.ComponentActivity


    class MainActivity :  ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            setContentView(R.layout.activity_main)


                val continueBtn = findViewById<Button>(R.id.continueBtn)
            continueBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, CreateLoginActivity::class.java )
                startActivity(intent)
            }
        }
    }