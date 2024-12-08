package falcon

class Day5(val input:List<String>) {
    val rules = readRules()   // Map listing which pages must NOT appear before a given page
    val updates = readUpdates()


    fun readRules() : Map<Int,List<Int>> {
        val ret = mutableMapOf<Int, MutableList<Int>>()
        for (line in input) {
            if (line.contains("|")) {
                val (before, after) = line.split("|").map { it.toInt() }
                ret.getOrPut(before) { mutableListOf() }.add(after)
            }
        }
        return ret;
    }

    fun readUpdates() : List<List<Int>> {
        val ret = mutableListOf<List<Int>>()
        for (line in input) {
            if (line.contains(",")) {
                val list = line.split(",").map { it.toInt() }
                ret.add(list)
            }
        }
        return ret
    }

    fun checkUpdate(update:List<Int>) : Int {
        print("Update: $update : ")
        var seenAlready = mutableSetOf<Int>()
        for(page in update) {
            val rule = rules[page]
            if (rule != null) {
                for (pageBefore in rule) {
                    if (seenAlready.contains(pageBefore)) {
                        println("Fails as $pageBefore before $page")
                        return 0
                    }
                }
            }
            seenAlready.add(page)
        }
        val middle = update[update.size / 2]
        println("Passes $middle")
        return middle
    }

    fun fixError(inp:List<Int>) : List<Int> {
        val update = inp.toMutableList()
        print("Update: $update : ")
        do {
            var madeChange = false
            for (index1 in 0..update.size - 2) {
                for (index2 in index1 + 1..update.size - 1) {
                    val page1 = update[index1]
                    val page2 = update[index2]
                    val rule = rules[page2]
                    if (rule != null) {
                        if (rule.contains(page1)) {
                            print("$page1<->$page2 ")
                            update[index1] = page2
                            update[index2] = page1
                            madeChange = true
                        }
                    }
                }
            }
        } while (madeChange)
        println("Now OK")
        return update
    }

    fun part1() {
        var total = 0
        for (update in updates) {
            total += checkUpdate(update)
        }
        println("Total: $total")
    }

    fun part2() {
        var total = 0
        for (update in updates) {
            if (checkUpdate(update)==0) {
                val fixed = fixError(update)
                total += checkUpdate(fixed)
            }
        }
        println("Total: $total")
    }



    fun run() {
//        for (rule in rules)
//            println("Rule: $rule")
//        for(update in updates)
//            println("Update: $update")
        part1()

        part2()

    }
}