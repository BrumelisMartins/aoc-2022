package day_05

import readInput


fun main() {

    fun String.toOperation(): CrateOperation {
        val idMap = Regex("[0-9]+").findAll(this)
            .map(MatchResult::value)
            .toList()
        return CrateOperation(move = idMap[0].toInt(), from = idMap[1].toInt(), to = idMap[2].toInt())
    }

    fun <T> ArrayList<T>.removeFirstElements(count: Int){
        for (i in 1..count) {
            if (isNotEmpty()) removeAt(0)
        }
    }

    fun convertRawDataToCrateStack(list: List<String>): List<CrateStack> {
        val operationList = list.last().chunked(1)

        val listOfCrateStacks =
            operationList.filterNot { it.isBlank() }.map { CrateStack(it.toInt(), arrayListOf()) }

        list.forEach { rawString ->
            rawString.chunked(1).forEachIndexed { index, crateName ->
                if (crateName.single().isLetter()) {
                    val stackId = operationList[index].toInt()
                    listOfCrateStacks.find { crateStack -> crateStack.id == stackId }?.listOfCrates?.add(Crate(crateName))
                }
            }
        }
        return listOfCrateStacks
    }

    fun moveCrate(operation: CrateOperation, crateList: List<CrateStack>, newCraneVersion: Boolean){
        val stackFrom = crateList.find { it.id == operation.from }
        val movableCrates = stackFrom?.listOfCrates?.take(operation.move)

        if (movableCrates != null){
            stackFrom.listOfCrates.removeFirstElements(operation.move)
            val newCrateList = if(newCraneVersion) movableCrates else movableCrates.reversed()
            crateList.find { it.id == operation.to }?.listOfCrates?.addAll(0, newCrateList)
        }
    }

    fun createDock(rawValues: List<String>): Dock {
        val crateList = arrayListOf<String>()
        val crateOperationList = arrayListOf<String>()
        var sortingCrates = true
        rawValues.forEach {
            if (it.isBlank()) {
                sortingCrates = false
                return@forEach
            }
            if (sortingCrates){
                crateList.add(it)
            } else {
                crateOperationList.add(it)
            }
        }

        val listOfCratesStack = convertRawDataToCrateStack(crateList)
        val listOfOperations = crateOperationList.map { it.toOperation() }
        return Dock(listOfOperations, listOfCratesStack)
    }

    fun sortDock(dock: Dock, newCraneVersion: Boolean){
        dock.crateOperations.forEach {
            moveCrate(it, dock.crateStacks, newCraneVersion)
        }
    }

    fun getTopCrateName(dock: Dock): String{
        var crateName = ""
        dock.crateStacks.forEach {
            if (it.listOfCrates.isNotEmpty()) crateName += it.listOfCrates.first().id
        }
        return crateName
    }

    val input = readInput("day_05/Day05")
    val dock = createDock(input)
    sortDock(dock, false)
    val newDockWithNewCrane = createDock(input)
    sortDock(newDockWithNewCrane, true)

    println(getTopCrateName(dock))
    println(getTopCrateName(newDockWithNewCrane))

}

data class Crate(val id: String)
data class CrateStack(val id: Int, val listOfCrates: ArrayList<Crate>)
data class CrateOperation(val from: Int, val to: Int, val move: Int)
data class Dock(val crateOperations: List<CrateOperation>,val crateStacks: List<CrateStack>)