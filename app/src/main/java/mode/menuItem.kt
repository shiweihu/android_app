package mode

import java.util.*


class menuItem {
    var title = ""
    var tips = ""
    private val list: MutableList<DropDownBox> = ArrayList()
    fun getList():MutableList<DropDownBox>
    {
        return list;
    }
    fun addDropDownBox(d: DropDownBox) {
        list.add(d)
    }
    fun getDropDownBoxByIndex(index:Int):DropDownBox
    {
        return list.get(index)
    }

    var content = ""
}


