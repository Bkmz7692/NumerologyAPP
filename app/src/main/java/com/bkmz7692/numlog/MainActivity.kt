package com.bkmz7692.numlog

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
        //18.10
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var const = 2 //Глобальные переменные
            //val backspace = findViewById<ImageView>(R.id.back)
            var textEdit = findViewById<EditText>(R.id.editTextText)
            val day =findViewById<EditText>(R.id.day)
            val month = findViewById<EditText>(R.id.month)
            val years = findViewById<EditText>(R.id.years)
            var firt_str= textEdit.text
           // val zero_str = findViewById<TextView>(R.id.zero_str)
            val one_str = findViewById<TextView>(R.id.one)
            val two_str = findViewById<TextView>(R.id.two)
            val three_str = findViewById<TextView>(R.id.three)
            val four_str = findViewById<TextView>(R.id.four)
            val five_str = findViewById<TextView>(R.id.five)
            val six_str = findViewById<TextView>(R.id.six)
            val seven_str = findViewById<TextView>(R.id.seven)
            val eight_str = findViewById<TextView>(R.id.eight)
            val nine_str = findViewById<TextView>(R.id.nine)
            day.addTextChangedListener{
                if (day.text.toString().length == 2){
                    month.requestFocus()
                }
            }
            month.addTextChangedListener{
                if (month.text.toString().length == 2){
                    years.requestFocus()
                }
            }
        fun calculateNumerologicalMatrix() {
            var num_pos = ""
            if (day.text.toString().toInt() <=31 && day.text.toString().toInt() >=1 && month.text.toString().toInt()<=12 && years.text.toString().toInt()>=1900){

                num_pos = day.text.toString() + month.text.toString() + years.text.toString()
                val input = num_pos
                println("BKLOG $input")

                val year = input.slice(input.length - 4 until input.length)
                println("Year = $year\n") // "ld!"
                if (year.toInt() >= 2000){
                    const = 19
                }
                else{const = -2}
                // Оставляем только цифры из входных данных
                val digits = input.filter { it.isDigit() }

                // Считаем сумму А
                val sumA = digits.map { it.toString().toInt() }.sum()

                // Считаем сумму Б
                val sumB = sumA.toString().map { it.toString().toInt() }.sumBy {
                    if (it < 10) it else 0
                }


                val diffV = sumA + const


                val sumG = diffV.toString().map { it.toString().toInt() }.sumBy {
                    if (it < 10) it else 0
                }


                val finalrow = digits + const.toString() + sumA.toString() + sumB.toString() +diffV.toString() + sumG.toString()

                var charArray = finalrow.toCharArray()
                charArray.sort()
                val resultingString = charArray.joinToString(separator = "")
                println(resultingString)
                //val s = "11222333444456778899999"

                val counts = mutableMapOf<Char, Int>()

                for (c in resultingString) {
                    counts[c] = counts.getOrDefault(c, 0) + 1
                }

                val one = counts['1'] ?: 0
                val two = counts['2'] ?: 0
                val three = counts['3'] ?: 0
                val four = counts['4'] ?: 0
                val five = counts['5'] ?: 0
                val six = counts['6'] ?: 0
                val seven = counts['7'] ?: 0
                val eight = counts['8'] ?: 0
                val nine = counts['9'] ?: 0

                println("one = $one")
                if (one >0){
                    one_str.text = "1".repeat(one)
                }
                else{one_str.text = "-"}
                println("two = $two")

                if (two >0){
                    two_str.text = "2".repeat(two)
                }
                else{two_str.text = "-"}

                println("three = $three")
                if (three >0){
                    three_str.text = "3".repeat(three)
                }
                else{three_str.text = "-"}

                println("four = $four")
                if (four >0){
                    four_str.text = "4".repeat(four)
                }
                else{four_str.text = "-"}

                println("five = $five")
                if (five >0){
                    five_str.text = "5".repeat(five)
                }
                else{five_str.text = "-"}
                println("six = $six")
                if (six >0){
                    six_str.text = "6".repeat(six)
                }
                else{six_str.text = "-"}
                println("seven = $seven")
                if (seven >0){
                    seven_str.text = "7".repeat(seven)
                }
                else{seven_str.text = "-"}
                println("eight = $eight")
                if (eight >0){
                    eight_str.text = "8".repeat(eight)
                }
                else{eight_str.text = "-"}
                println("nine = $nine")
                if (nine >0){
                    nine_str.text = "9".repeat(nine)
                }
                else{nine_str.text = "-"}

                //Вычисление итоговой цифры
                val itog = findViewById<TextView>(R.id.itog)
                var itog_val=sumA

                if (sumA >=10){
                    while (itog_val>=10){
                        val arr = itog_val.toString().toList()
                        val arrOfInt = arr.map{ it.toString().toInt()}
                        itog_val = arrOfInt.sum()

                    }
                    itog.text = itog_val.toString()

                }
                else{ itog.text = sumA.toString()
                }

                //Вычисление психотипа
                val psih = findViewById<TextView>(R.id.psih)
                if (one > two){psih.text = "I"}
                if (one < two){psih.text = "II"}
                if (one == two){psih.text = "III"}

                //Вычисление возраста:
                val formatter = SimpleDateFormat("yyyy")
                val date = Date()
                val current = formatter.format(date)
                var age = current.toInt() - year.toInt()
                val age_str = findViewById<TextView>(R.id.age)
                age_str.text = age.toString()


            }
            else{val text = "Введите правильную дату!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()}



            //Графики
            //Настройка данных
            val lineChart = findViewById(R.id.lineChart) as LineChart

            val sudba_arr = mutableListOf<Entry>()
            sudba_arr.add(Entry(0f, 4f))
            sudba_arr.add(Entry(12f, 9f))
            sudba_arr.add(Entry(24f, 3f))
            sudba_arr.add(Entry(36f, 9f))
            sudba_arr.add(Entry(48f, 8f))
            sudba_arr.add(Entry(64f, 6f))
            sudba_arr.add(Entry(72f, 0f))

            var sudba = LineDataSet(sudba_arr, "Судьба")
            sudba.color = Color.CYAN

            val volya_arr = mutableListOf<Entry>()
            volya_arr.add(Entry(0f, 4f))
            volya_arr.add(Entry(12f, 9f))
            volya_arr.add(Entry(24f, 5f))
            volya_arr.add(Entry(36f, 9f))
            volya_arr.add(Entry(48f, 5f))
            volya_arr.add(Entry(64f, 8f))
            volya_arr.add(Entry(72f, 0f))

            var volya = LineDataSet(volya_arr, "Воля")
            volya.color = Color.RED


            val lineData = LineData(sudba,volya)
            lineChart.data = lineData

            //Настройки графа
            lineChart.setDrawBorders(false)
            lineData.setDrawValues(false)
            lineChart.description.isEnabled = false
            lineChart.legend.isEnabled = true
            lineChart.legend.isWordWrapEnabled = false
            lineChart.setTouchEnabled(false)
            lineChart.legend.textColor = Color.WHITE
            lineChart.xAxis.setDrawGridLines(false)
            lineChart.axisLeft.setDrawGridLines(false)
            lineChart.axisLeft.mAxisMaximum = 10f
            lineChart.xAxis.mAxisMaximum = 72f
            lineChart.axisLeft.textColor = Color.WHITE
            
            //lineChart.xAxis.setDrawLabels(false)
            lineChart.xAxis.textColor = Color.WHITE
            lineChart.axisRight.isEnabled = false
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.axisRight.setDrawGridLines(false)


            lineChart.invalidate()
        }
        //Конец функции



        val calc = findViewById<Button>(R.id.calc)
        calc.setOnClickListener{calculateNumerologicalMatrix()}
    }
}


