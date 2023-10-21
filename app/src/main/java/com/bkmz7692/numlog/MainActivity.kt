package com.bkmz7692.numlog

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
        //18.10
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var const = 2 //Глобальные переменные
        fun calculateNumerologicalMatrix() {
            print("fun start\n")
            val input = findViewById<EditText>(R.id.date2).text.toString()
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


            var finalrow = digits + const.toString() + sumA.toString() + sumB.toString() +diffV.toString() + sumG.toString()

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
            val one_str = findViewById<TextView>(R.id.one)
            val two_str = findViewById<TextView>(R.id.two)
            val three_str = findViewById<TextView>(R.id.three)
            val four_str = findViewById<TextView>(R.id.four)
            val five_str = findViewById<TextView>(R.id.five)
            val six_str = findViewById<TextView>(R.id.six)
            val seven_str = findViewById<TextView>(R.id.seven)
            val eight_str = findViewById<TextView>(R.id.eight)
            val nine_str = findViewById<TextView>(R.id.nine)
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
                itog.text = "Итоговая цифра: $itog_val"

            }
            else{ itog.text = "Итоговая цифра: $sumA"}

            //Вычисление психотипа
            val psih = findViewById<TextView>(R.id.psih)
            if (one > two){psih.text = "Психотип: I Психотип"}
            if (one < two){psih.text = "Психотип: II Психотип"}
            if (one == two){psih.text = "Психотип: III Психотип"}

            //Вычисление возраста:
            val formatter = SimpleDateFormat("yyyy")
            val date = Date()
            val current = formatter.format(date)
            var age = current.toInt() - year.toInt()
            val age_str = findViewById<TextView>(R.id.age)
            age_str.text = "Возраст: $age"
        }
        //Конец функции



        val calc = findViewById<Button>(R.id.calc)
        calc.setOnClickListener{calculateNumerologicalMatrix()}
    }
}


