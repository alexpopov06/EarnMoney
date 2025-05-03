
package com.example.earnmoney

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var balance = 0
    private lateinit var cash: TextView
    private lateinit var click: MaterialButton
    private lateinit var withDrawal: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cash = findViewById(R.id.balance)
        click = findViewById(R.id.click)
        withDrawal = findViewById(R.id.WithDrawal)

        balance = getSharedPreferences("MoneyBalance", MODE_PRIVATE)
            .getInt("balance", 0)
        updateBalanceText()

        click.setOnClickListener {
            balance += 1
            updateBalanceText()
            saveBalance(balance)
        }

        withDrawal.setOnClickListener {
            startActivity(Intent(this, WithDrawalActivity::class.java))
        }
    }

    private fun updateBalanceText() {
        cash.text = "🟡 $balance"
    }

    private fun saveBalance(value: Int) {
        getSharedPreferences("MoneyBalance", MODE_PRIVATE).edit()
            .putInt("balance", value)
            .apply()
    }
}