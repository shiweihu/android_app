package mode

import java.util.*

class TabFive : menuItem() {
    private val list: MutableList<DropDownBox> = ArrayList()
    fun addDropDownBox(d: DropDownBox) {
        list.add(d)
    }

    fun getDropDownBoxByIndex(index: Int): DropDownBox {
        return list[index]
    }
}