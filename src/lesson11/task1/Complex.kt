@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.math.abs
import kotlin.math.pow

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x.toInt().toDouble(), x - x.toInt())

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(getRe(s), getIm(s))

    companion object {
        private fun getRe(s: String): Double = s.substring(0, getIndex(s)).toDouble()

        private fun getIm(s: String): Double {
            val i = getIndex(s);
            val im = s.substring(i, s.length - 1).toDouble()
            return if (s.toCharArray()[i] == '+') im
            else abs(im) * -1
        }

        private fun getIndex(s: String): Int {
            val plus = s.indexOf('+')
            return if (plus != -1) plus
            else s.indexOf('-')
        }
    }

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex {
        val r = re + other.re
        val i = im + other.im
        return Complex(r, i)
    }

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex {
        val r = re - other.re
        val i = im - other.im
        return Complex(r, i)
    }

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex {
        val r = re * other.re - im * other.im
        val i = re * other.im + im * other.re
        return Complex(r, i)
    }


    /**
     * Деление
     */

    operator fun div(other: Complex): Complex {
        val bot = (other.re.pow(2) + other.im.pow(2))
        val r = ((re * other.re) + (im * other.im)) / bot
        val i = ((im * other.re) - (re * other.im)) / bot
        return Complex(r, i)
    }


    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Complex) return false
        val comp = other
        return re == comp.re && im == comp.im
    }


    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        if (im == 0.0) return "$re"
        if (re == 0.0) return "${im}i"
        if (im < 0.0) return "$re-${-im}i"
        return "$re+${im}i"
    }

}







