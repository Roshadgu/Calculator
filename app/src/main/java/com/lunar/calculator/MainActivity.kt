package com.lunar.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

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

  private fun removeZeroAferDot(result: String): String
  {
    var value = result
    if(result.contains(".0"))
      value = result.substring(0, result.length - 2) //get's rid of the last to characters of the result string 99.0 -> 99 and 0123 -> 01
    return value
  }

  fun onEqual(view: View)
  {
    if(lastNumeric)
    {
      val tvInput = findViewById<TextView>(R.id.tvInput)
      var tvValue = tvInput.text.toString()
      var prefix = ""

      try
      {
        if(tvValue.startsWith("-"))
        {
          prefix = "-"
          tvValue = tvValue.substring(1) //ignores the minus if it exist
        }

        if(tvValue.startsWith("+"))
        {
          prefix = "+"
          tvValue = tvValue.substring(1) //ignores the plus if it exist
        }

        if(tvValue.startsWith("*"))
        {
          prefix = "*"
          tvValue = tvValue.substring(1) //ignores the multiplication symbol if it exist
        }

        if(tvValue.startsWith("/"))
        {
          prefix = "/"
          tvValue = tvValue.substring(1) //ignores the division slash symbol if it exist
        }
        //Splitting the operations by the symbols
        if(tvValue.contains("-")) //Subtraction
        {
          val splitValue = tvValue.split("-") // splits the operation into 2 parts, before and after the minus symbol

          var one = splitValue[0] //before the -
          var two = splitValue[1] //after the -

          if(!prefix.isEmpty())
          {
            one = prefix + one
          }

          tvInput.text = removeZeroAferDot((one.toDouble() - two.toDouble()).toString()) //turns the values into doubles and turns the result into a string that the text field can accept
        }

        if(tvValue.contains("+")) //Addition
        {
          val splitValue = tvValue.split("+") // splits the operation into 2 parts, before and after the addition symbol

          var one = splitValue[0] //before the +
          var two = splitValue[1] //after the +

          if(!prefix.isEmpty())
          {
            one = prefix + one
          }

          tvInput.text = removeZeroAferDot((one.toDouble() + two.toDouble()).toString()) //turns the values into doubles and turns the result into a string that the text field can accept
        }

        if(tvValue.contains("*")) // Multiplication
        {
          val splitValue = tvValue.split("*") // splits the operation into 2 parts, before and after the multiplication symbol

          var one = splitValue[0] //before the *
          var two = splitValue[1] //after the *

          if(!prefix.isEmpty())
          {
            one = prefix + one
          }

          tvInput.text = removeZeroAferDot((one.toDouble() * two.toDouble()).toString()) //turns the values into doubles and turns the result into a string that the text field can accept
        }

        if(tvValue.contains("/")) //Division
        {
          val splitValue = tvValue.split("/") // splits the operation into 2 parts, before and after the division symbol

          var one = splitValue[0] //before the /
          var two = splitValue[1] //after the /

          if(!prefix.isEmpty())
          {
            one = prefix + one
          }

          tvInput.text = removeZeroAferDot((one.toDouble() / two.toDouble()).toString()) //turns the values into doubles and turns the result into a string that the text field can accept
        }
      }

      catch (e: ArithmeticException) //In case the operation makes no sense
      {
        e.printStackTrace()
      }
    }
  }

  fun onOperator(view: View)
  {
    val tvInput = findViewById<TextView>(R.id.tvInput)
    if(lastNumeric && !isOperatorAdded(tvInput.text.toString()))
    {
      tvInput.append((view as Button).text)
      lastNumeric = false
      lastDot = false
    }
  }

  private fun isOperatorAdded(value: String): Boolean
  {
    return if(value.startsWith("-"))
    {
      false
    }
    else
    {
      value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
    }
  }
}