package com.lunar.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
  private var lastNumeric: Boolean = false
  private var lastDot: Boolean = false //to see if there already is a last dot in the text field

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onDigit(view: View)
  {
    val tvInput = findViewById<TextView>(R.id.tvInput)
    tvInput.append((view as Button).text)
    lastNumeric = true
  }

  fun onClear(view: View)
  {
    val tvInput = findViewById<TextView>(R.id.tvInput)
    tvInput.text = ""
    lastNumeric = false
    lastDot = false
  }

  fun onDecimalPoint(view: View)
  {
    if(lastNumeric && !lastDot)
    {
      val tvInput = findViewById<TextView>(R.id.tvInput)
      tvInput.append(".")
      lastNumeric = false
      lastDot = true
    }
  }
}