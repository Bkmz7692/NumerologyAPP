package com.bkmz7692.numlog

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.widget.addTextChangedListener
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import java.util.Date

class MainActivity : AppCompatActivity() {
        //18.10
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            WindowCompat.setDecorFitsSystemWindows(window, false)
            setContentView(R.layout.activity_main)
            var const: Int  //Глобальные переменные
            val day =findViewById<EditText>(R.id.day)
            val month = findViewById<EditText>(R.id.month)
            val years = findViewById<EditText>(R.id.years)
            val one_str = findViewById<TextView>(R.id.one)
            val two_str = findViewById<TextView>(R.id.two)
            val three_str = findViewById<TextView>(R.id.three)
            val four_str = findViewById<TextView>(R.id.four)
            val five_str = findViewById<TextView>(R.id.five)
            val six_str = findViewById<TextView>(R.id.six)
            val seven_str = findViewById<TextView>(R.id.seven)
            val eight_str = findViewById<TextView>(R.id.eight)
            val nine_str = findViewById<TextView>(R.id.nine)
            val age_str = findViewById<TextView>(R.id.age)
            val one_ras = findViewById<TextView>(R.id.one_ras)
            val two_ras = findViewById<TextView>(R.id.two_ras)
            val three_ras = findViewById<TextView>(R.id.three_ras)
            val five_ras = findViewById<TextView>(R.id.five_ras)
            val six_ras = findViewById<TextView>(R.id.six_ras)
            val seven_ras = findViewById<TextView>(R.id.seven_ras)
            val eight_ras = findViewById<TextView>(R.id.eight_ras)
            val four_ras = findViewById<TextView>(R.id.four_ras)
            val nine_ras = findViewById<TextView>(R.id.nine_ras)
            val calc = findViewById<Button>(R.id.calc)
            val date = Date()
            day.addTextChangedListener{
                if (day.text.toString().length == 2){
                    month.requestFocus()
                    month.setSelection(month.length())
                }

            }
            month.addTextChangedListener{
                if (month.text.toString().length == 2){
                    years.requestFocus()

                }
                if (month.text.toString().length == 0){
                    day.requestFocus()
                    day.setSelection(day.length())
                }
            }
            years.addTextChangedListener{
                if (years.text.toString().length == 0){
                    month.requestFocus()
                    month.setSelection(month.length())
                }
            }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun calculateNumerologicalMatrix(day: EditText, month: EditText, years: EditText ) {
            val num_pos = day.text.toString() + month.text.toString() + years.text.toString()
            var digits: String
            var year: String
            if (day.text.toString().toInt() in 1..31 && month.text.toString().toInt()<=12 && years.text.toString().toInt()>=1900 ){
                if (!(day.text.toString().toInt()>28 && month.text.toString().toInt() == 2)) {
                    println("BKLOG $num_pos")

                    year = num_pos.slice(num_pos.length - 4 until num_pos.length)
                    println("Year = $year\n") // "ld!"
                    const = if (year.toInt() >= 2000) {
                        19
                    } else {
                        -2
                    }
                    // Оставляем только цифры из входных данных
                    digits = num_pos.filter { it.isDigit() }
                    // Считаем сумму А
                    val sumA = digits.map { it.toString().toInt() }.sum()
                    // Считаем сумму Б
                    val sumB = sumA.toString().map { it.toString().toInt() }.sumOf {
                        if (it < 10) it else 0
                    }
                    val diffV = sumA + const
                    val sumG = diffV.toString().map { it.toString().toInt() }.sumOf {
                        if (it < 10) it else 0
                    }
                    val finalrow =
                        digits + const.toString() + sumA.toString() + sumB.toString() + diffV.toString() + sumG.toString()

                    var charArray = finalrow.toCharArray()
                    charArray.sort()
                    val resultingString = charArray.joinToString(separator = "")



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
                    if (one > 0) {
                        one_str.text = "1".repeat(one)
                    } else {
                        one_str.text = "-"
                    }
                    println("two = $two")

                    if (two > 0) {
                        two_str.text = "2".repeat(two)
                    } else {
                        two_str.text = "-"
                    }
                    println("three = $three")
                    if (three > 0) {
                        three_str.text = "3".repeat(three)
                    } else {
                        three_str.text = "-"
                    }
                    println("four = $four")
                    if (four > 0) {
                        four_str.text = "4".repeat(four)
                    } else {
                        four_str.text = "-"
                    }

                    println("five = $five")
                    if (five > 0) {
                        five_str.text = "5".repeat(five)
                    } else {
                        five_str.text = "-"
                    }
                    println("six = $six")
                    if (six > 0) {
                        six_str.text = "6".repeat(six)
                    } else {
                        six_str.text = "-"
                    }
                    println("seven = $seven")
                    if (seven > 0) {
                        seven_str.text = "7".repeat(seven)
                    } else {
                        seven_str.text = "-"
                    }
                    println("eight = $eight")
                    if (eight > 0) {
                        eight_str.text = "8".repeat(eight)
                    } else {
                        eight_str.text = "-"
                    }
                    println("nine = $nine")
                    if (nine > 0) {
                        nine_str.text = "9".repeat(nine)
                    } else {
                        nine_str.text = "-"
                    }
                    //Вычисление итоговой цифры
                    val itog = findViewById<TextView>(R.id.itog)
                    var itog_val = sumA

                    if (sumA >= 10) {
                        while (itog_val >= 10) {
                            val arr = itog_val.toString().toList()
                            val arrOfInt = arr.map { it.toString().toInt() }
                            itog_val = arrOfInt.sum()

                        }
                        itog.text = itog_val.toString()
                    } else {
                        itog.text = sumA.toString()
                    }
                    //Вычисление психотипа
                    val psih = findViewById<TextView>(R.id.psih)
                    if (one > two) {
                        psih.text = "I"
                    }
                    if (one < two) {
                        psih.text = "II"
                    }
                    if (one == two) {
                        psih.text = "III"
                    }
                    //Вычисление возраста:
                    val formatter = SimpleDateFormat("yyyy")
                    val current = formatter.format(date)
                    val age = current.toInt() - year.toInt()
                    age_str.text = age.toString()
                    //Расшифровка ============================================================
                    if (one == 2) {
                        one_ras.text =
                            "1 — инициативность, сила мысли. Единица символизирует желания и стремления — «я хочу». Человек, в тесте которого много Единиц, умело руководит другими людьми, фонтанирует креативными идеями, убедительно говорит, проявляет изобретательность, имеет склонность ктворческому самовыражению."
                    }
                    if (one > 2) {
                        one_ras.text = "Тут будет больше нормы для 1"
                    }
                    if (one < 2) {
                        one_ras.text = "Тут будет больше нормы для 1"
                    }
                    if (two == 2) {
                        two_ras.text =
                            "2 - плотная энергия тела, энергия действий. Мышечная сила, работоспособность, сила воли, упрямство, агрессия, способность манипулировать, управлять другими людьми. Количество двоек показывает величину природного биополя, защитного “скафандра” вокруг человека. Это 'что МОГУ сделать'."
                    }
                    if (two > 2) {
                        two_ras.text = "Тут будет больше нормы для 2"
                    }
                    if (two < 2) {
                        two_ras.text = "Тут будет меньше нормы для 2"
                    }
                    //====
                    if (three == 1) {
                        three_ras.text =
                            "3- показывает степень хозяйственности человека, характеризует его общительность, контактность и способность к адаптации. Качества Тройки дополняются Шестеркой. Тройки — это знания человека обо всем по чуть-чуть, интерес и приобретение знаний. Все интересно, все нужно. Чем больше троек — тем дальше человек ходит, со всеми дружит, все знает и ничего не делает. Тройка — это некие привычки, некие моменты, элементы, которые мы привыкли видеть определенным образом. Без Тройки они непонятны."
                    }
                    if (three > 1) {
                        three_ras.text = "Тут будет больше нормы для 3"
                    }
                    if (three == 0) {
                        three_ras.text = "Тут будет Меньше нормы для 3"
                    }
                    //====
                    if (four == 1) {
                        four_ras.text =
                            "4 – чувство долга перед обществом, коллективизм, внутренняя дисциплина. Патриотизм – как служение делу, государству, идее, людям и т. д. Сила духа, твёрдость, жёсткость, сила противостояния. Честность, умение брать ответственность за других. Это главное качество из составляющих альтруизма. Желание спасать и служить другим. Понимание рамок поведения «здесь и сейчас»"
                    }
                    if (four > 1) {
                        four_ras.text = "Тут будет больше нормы для 4"
                    }
                    if (four < 1) {
                        four_ras.text = "Тут будет меньше нормы для 4"
                    }
                    //====
                    if (five == 1) {
                        five_ras.text =
                                "5 – сексапильность, привлекательность, эмоциональность, чувственность, ранимость, обидчивость, вспыльчивость, внешнее проявление эмоций, жалость, сострадание, любовь. Артистизм, манерность, чувство формы"
                    }
                    if (five > 1) {
                        five_ras.text = "Тут будет больше нормы для 5"
                    }
                    if (five < 1) {
                        five_ras.text = "Тут будет меньше нормы для 5"
                    }
                    //====
                    if (six == 1) {
                        six_ras.text =
                            "6– логика больших систем, мужская логика, аналитический ум, умение структурировать, систематизировать, разбираться до деталей. Профессионализм, перспективное мышление. Самооценка, принципиальность, умение отстаивать и аргументировать свою точку зрения. “Я как индивидуальность, неповторимость” – принципы, своя система жизни."
                    }
                    if (six > 1) {
                        six_ras.text = "Тут будет больше нормы для 6"
                    }
                    if (six < 1) {
                        six_ras.text = "Тут будет меньше нормы для 6"
                    }
                    //====
                    if (seven == 1) {
                        seven_ras.text =
                            "7 – интуиция: “знаю, что надо делать так, но не знаю почему”. Это полезная подсказка в данный момент жизни, “здесь и сейчас”, не основанная на фактах."
                    }
                    if (seven > 1) {
                        seven_ras.text = "Тут будет больше нормы для 7"
                    }
                    if (seven < 1) {
                        seven_ras.text = "Тут будет меньше нормы для 7"
                    }
                    //====
                    if (eight == 1) {
                        eight_ras.text =
                            "8 - подарки судьбы, везение, здоровый авантюризм, внутренне оправданный. Это право просить и получать без особого напряжения"
                    }
                    if (eight > 1) {
                        eight_ras.text = "Тут будет больше нормы для 8"
                    }
                    if (eight < 1) {
                        eight_ras.text = "Тут будет меньше нормы для 8"
                    }
                    //====
                    if (nine == 2) {
                        nine_ras.text =
                            "9 – целеустремлённость, дипломатичность, “знаю, чего хочу на каждый конкретный момент”, смысл жизни, гибкость в поведении, хитрость, ложь, “цель любой ценой”, предательство."
                    }
                    if (nine > 2) {
                        nine_ras.text = "Тут будет больше нормы для 9"
                    }
                    if (nine < 2) {
                        nine_ras.text = "Тут будет меньше нормы для 9"
                    }

                    //Графики
                    //Настройка данных ===============================================
                    val lineChart = findViewById<LineChart>(R.id.lineChart)
                    val sudba_arr = mutableListOf<Entry>()
                    val volya_arr = mutableListOf<Entry>()

                    //Настройки графа===================================================
                    println("ОТЛАДКА ДЛЯ ГРАФОВ")
                    println(digits)
                    val day_monnth = digits.substring(0..3)
                    val proizv_sud = day_monnth.toInt() * year.toInt()

                    val proizv_sud_str = proizv_sud.toString()
                    if (proizv_sud.toString().length == 7) {

                        val sud_st = proizv_sud_str.get(0) + ".0"
                        val sud_nd = proizv_sud_str.get(1) + ".0"
                        val sud_four = proizv_sud_str.get(3) + ".0"
                        val sud_five = proizv_sud_str.get(4) + ".0"
                        val sud_three = proizv_sud_str.get(2) + ".0"
                        val sud_six = proizv_sud_str.get(5) + ".0"
                        val sud_seven = proizv_sud_str.get(6) + ".0"
                        sudba_arr.add(Entry(0f, sud_st.toFloat()))
                        sudba_arr.add(Entry(12f, sud_nd.toFloat()))
                        sudba_arr.add(Entry(24f, sud_three.toFloat()))
                        sudba_arr.add(Entry(36f, sud_four.toFloat()))
                        sudba_arr.add(Entry(48f, sud_five.toFloat()))
                        sudba_arr.add(Entry(64f, sud_six.toFloat()))
                        sudba_arr.add(Entry(72f, sud_seven.toFloat()))
                    } else {
                        sudba_arr.add(Entry(0f, 0f))
                        val sud_nd = proizv_sud_str[0] + ".0"
                        val sud_three = proizv_sud_str[1] + ".0"
                        val sud_four = proizv_sud_str[2] + ".0"
                        val sud_five = proizv_sud_str[3] + ".0"
                        val sud_six = proizv_sud_str[4] + ".0"
                        val sud_seven = proizv_sud_str[5] + ".0"
                        sudba_arr.add(Entry(12f, sud_nd.toFloat()))
                        sudba_arr.add(Entry(24f, sud_three.toFloat()))
                        sudba_arr.add(Entry(36f, sud_four.toFloat()))
                        sudba_arr.add(Entry(48f, sud_five.toFloat()))
                        sudba_arr.add(Entry(64f, sud_six.toFloat()))
                        sudba_arr.add(Entry(72f, sud_seven.toFloat()))
                    }
                    val digit_for_volya = digits.replace("0", "1")
                    val v_proizv = digit_for_volya.substring(0..3).toInt() * digit_for_volya.slice(
                        digit_for_volya.length - 4 until digit_for_volya.length
                    ).toInt()
                    val vol_st = v_proizv.toString().get(0) + ".0"
                    val vol_nd = v_proizv.toString().get(1) + ".0"
                    val vol_three = v_proizv.toString().get(2) + ".0"
                    val vol_four = v_proizv.toString().get(3) + ".0"
                    val vol_five = v_proizv.toString().get(4) + ".0"
                    val vol_six = v_proizv.toString().get(5) + ".0"
                    val vol_seven = v_proizv.toString().get(6) + ".0"
                    volya_arr.add(Entry(0f, vol_st.toFloat()))
                    volya_arr.add(Entry(12f, vol_nd.toFloat()))
                    volya_arr.add(Entry(24f, vol_three.toFloat()))
                    volya_arr.add(Entry(36f, vol_four.toFloat()))
                    volya_arr.add(Entry(48f, vol_five.toFloat()))
                    volya_arr.add(Entry(64f, vol_six.toFloat()))
                    volya_arr.add(Entry(72f, vol_seven.toFloat()))
                    println(v_proizv.toString().get(0))

                    val volya = LineDataSet(volya_arr, "Воля")
                    volya.color = Color.RED
                    val sudba = LineDataSet(sudba_arr, "Судьба")
                    sudba.color = Color.CYAN
                    val lineData = LineData(sudba, volya)
                    lineChart.data = lineData
                    lineChart.setDrawBorders(false)
                    lineData.setDrawValues(false)
                    lineChart.description.isEnabled = false
                    lineChart.legend.isEnabled = true
                    lineChart.legend.isWordWrapEnabled = false
                    lineChart.setTouchEnabled(false)
                    lineChart.legend.textColor = Color.BLACK
                    lineChart.xAxis.labelCount = 8
                    lineChart.axisLeft.labelCount = 10
                    lineChart.axisLeft.mAxisMaximum = 10f
                    lineChart.xAxis.mAxisMaximum = 92f
                    lineChart.axisLeft.textColor = Color.WHITE
                    lineChart.xAxis.textColor = Color.WHITE
                    lineChart.xAxis.granularity = 12f
                    lineChart.axisRight.isEnabled = false
                    lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                    lineChart.axisRight.setDrawGridLines(false)
                    lineChart.invalidate()


                    //Конец Графика ===========================================================================================


                }

            }
            else{
                val text = "Введите правильную дату!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()}


        }
        //Конец функции ============================================================================




            val current = LocalDateTime.now()
            val c_day = current.get(ChronoField.DAY_OF_MONTH)
            val c_month = current.get(ChronoField.MONTH_OF_YEAR)
            val c_year = current.get(ChronoField.YEAR)
            println("DATE "+c_day.toString()+c_month.toString() + c_year.toString())
            day.setText(c_day.toString())
            month.setText(c_month.toString())
            years.setText(c_year.toString())
            calculateNumerologicalMatrix(day, month, years)
            years.setSelection(years.length())

        calc.setOnClickListener{calculateNumerologicalMatrix(day, month, years)}
    }
}



