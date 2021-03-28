@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1


import kotlin.math.pow


val regex = Regex("""(-?\d+(?:\.\d+)?)([+-]\d+(?:\.\d+)?)i""")

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
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */

    companion object {
        fun Check(s: String, i: Int): Double {
            val result = regex.matchEntire(s) ?: throw IllegalArgumentException()
            return result.groupValues[i].toDouble()
        }

    }

    constructor(s: String) : this(Check(s, 1), Check(s, 2))

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
        if (this === other) return true
        if (other !is Complex) return false
        return other.im == im && other.re == re
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

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}





