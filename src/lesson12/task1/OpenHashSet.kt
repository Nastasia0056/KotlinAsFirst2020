@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }
    //var count = 0

    /**
     * Число элементов в хеш-таблице
     */
    override fun hashCode() = elements.sumBy { it.hashCode() }
    var size: Int = 0

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = size == 0
    private fun Any?.hashCode(): Int = this?.hashCode() ?: 0


    fun place(element: T, searchElem: T?): Int {
        var i = 0
        var space = (element.hashCode() + i) % capacity
        val hashOfElem = space
        if (this.elements[space] == searchElem) return space
        do {
            i++
            space = (hashOfElem + i) % capacity
        } while (this.elements[space] != searchElem && space != hashOfElem)
        return space
    }

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        if (size != capacity) {
            return if (this.contains(element)) {
                false
            } else {
                this.elements[place(element, null)] = element
                size++
                true
            }
        }
        return false
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean =
        this.elements[place(element, element)] == element

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OpenHashSet<*>) return false
        if (other.size != this.size) return false

        for (i in 0 until this.size) {
            if (this.elements[i] != null) {
                var index = (this.elements[i].hashCode() % this.capacity)
                var j = 0
                var count = 0
                var flag = false
                while (other.elements[index + j] != null) {
                    if (other.elements[index + j] == this.elements[i]) {
                        flag = true
                    }
                    j++
                    count++
                    if ((index + j) == other.capacity) {
                        index = 0
                        j = 0
                    }
                    if (count == other.capacity)
                        break
                }
                if (!flag)
                    return false
            }
        }
        return true
    }
}