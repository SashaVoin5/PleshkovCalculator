package com.example.pleshkovcalculator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    var viewCalculator :TextView? = null;
    var op1 :Double = 0.0
    var op2 :Double = 0.0
    var sign :Signs = Signs.plus;
    var CanInpSign :Boolean = false;

    enum class Signs
    {
        plus,
        minus,
        div,
        mult
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var viewCalculator = findViewById<TextView>(R.id.TextScreen);
        viewCalculator.setText("");

    }

    fun getPlus(op1: Double , op2 :Double): Double
    {
        var total:Double = op1 + op2;

        return total;
    }
    fun getMinus(op1: Double , op2 :Double): Double
    {
        var total:Double = op1 - op2;

        return total;
    }
    fun getDiv(op1: Double , op2 :Double): Double
    {
        var total:Double = op1 / op2;

        return total;
    }
    fun getMult(op1: Double , op2 :Double): Double
    {
        var total:Double = op1 * op2;

        return total;
    }
    fun charToSign(sign: Char):Signs?
    {
        when(sign)
        {
            '+' -> return Signs.plus
            '-' -> return Signs.minus
            '/' -> return Signs.div
            '*' -> return Signs.mult
        }
        return null;
    }
    fun signToChar(sign: Signs):Char
    {
        when(sign)
        {
            Signs.plus -> return '+'
            Signs.minus -> return '-'
            Signs.div  -> return '/'
            Signs.mult -> return '*'
        }
    }
    fun getCalculation(op1: Double , op2 :Double, sign: Signs?) :Double
    {
        when(sign)
        {
            Signs.plus -> return getPlus(op1, op2);
            Signs.minus -> return getMinus(op1, op2) ;
            Signs.div -> return getDiv(op1, op2);
            Signs.mult -> return getMult(op1, op2);
            else -> return 0.0
        }

    }
    fun ShowToast(str: String?)
    {
        var newToast  = Toast.makeText(this,str,Toast.LENGTH_SHORT)
        newToast.show();
    }
    fun removeLastChar(str: String?): String? {
        return str?.replaceFirst(".$".toRegex(), "")
    }

    // обработка нажатий на кнопки
    fun btnClick(view: View) {
        var viewCalculator = findViewById<TextView>(R.id.TextScreen);
        // ввод значений в textview
        fun modifViewCalc(str: String) {
            val viewCalc = viewCalculator.text.toString();
            var strModif = viewCalc + str;
            viewCalculator.setText(strModif);
        }
        // удаление последнего значения (для кнопки del)
        fun delLastValue() {
            val viewCalc = viewCalculator.text.toString();
            var strModif = removeLastChar(viewCalc);
            viewCalculator.setText(strModif);

        }
        fun signIsContains() :Boolean
        {
            val viewCalc = viewCalculator.text.toString();
            return(viewCalc.contains('+') ||
                    viewCalc.contains('-') ||
                    viewCalc.contains('/') ||
                    viewCalc.contains('*'))

        }
        fun changeCanInpSign()
        {
            if(viewCalculator.text.toString().length < 0 && signIsContains())
            {
                CanInpSign = false;
            }
            else if (viewCalculator.text.toString().length > 0 && !signIsContains())
            {
                CanInpSign = true;
            }
        }
        fun checkCanInpSign(): Boolean {
            when (CanInpSign) {
                false ->
                {
                    ShowToast("Запрещено")
                    return false
                }
                true -> {
                    CanInpSign = false;
                    return true;
                }
            }
        }

        changeCanInpSign();
        when (view.id) {
            R.id.btnNamba1 -> modifViewCalc("1")
            R.id.btnNamba2 -> modifViewCalc("2")
            R.id.btnNamba3 -> modifViewCalc("3")
            R.id.btnNamba4 -> modifViewCalc("4")
            R.id.btnNamba5 -> modifViewCalc("5")
            R.id.btnNamba6 -> modifViewCalc("6")
            R.id.btnNamba7 -> modifViewCalc("7")
            R.id.btnNamba8 -> modifViewCalc("8")
            R.id.btnNamba9 -> modifViewCalc("9")
            R.id.btnNamba0 -> modifViewCalc("0")
            R.id.btnPlus -> {
                if (checkCanInpSign() == true) {
                    sign = Signs.plus
                    modifViewCalc("+")

                }
            }
            R.id.btnMinus -> {
                if (checkCanInpSign()) {
                    sign = Signs.minus
                    modifViewCalc("-")
                }
            }
            R.id.btnDiv -> {
                if (checkCanInpSign()) {
                    sign = Signs.div
                    modifViewCalc("/")
                }
            }
            R.id.btnMultip -> {
                if (checkCanInpSign()) {
                    sign = Signs.mult
                    modifViewCalc("*")
                }
            }
            R.id.btnDel -> {
                val viewCalc = viewCalculator.text.toString();
                delLastValue()
                changeCanInpSign();
            }
            R.id.btnEnter ->
            {
                if (signIsContains())
                {
                    var viewCalc = viewCalculator.text.toString();
                    var arr = viewCalc.split(signToChar(sign)).toTypedArray()
                    op1 = arr[0].toDouble();
                    op2 = arr[1].toDouble();
                    var total = getCalculation(op1,op2,sign);
                    viewCalculator.setText(total.toString())
                }
                else
                {
                    ShowToast("Запрещено")
                }
            }
        }
    }
}










