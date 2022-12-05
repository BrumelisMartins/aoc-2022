package day_05

data class Ship(val craneOperations: List<CraneOperation>, val crateStacks: List<CrateStack>) {
    companion object {
        fun fromRawValues(rawValues: List<String>): Ship {
            val crateList = arrayListOf<String>()
            val crateOperationList = arrayListOf<String>()
            var sortingCrates = true
            rawValues.forEach {
                if (it.isBlank()) {
                    sortingCrates = false
                    return@forEach
                }
                if (sortingCrates) {
                    crateList.add(it)
                } else {
                    crateOperationList.add(it)
                }
            }

            val listOfCratesStack = convertRawDataToCrateStack(crateList)
            val listOfOperations = crateOperationList.map { it.toOperation() }
            return Ship(listOfOperations, listOfCratesStack)
        }

        private fun convertRawDataToCrateStack(list: List<String>): List<CrateStack> {
            val operationList = list.last()

            val listOfCrateStacks = operationList.filter { it.isDigit() }
                .map {
                    CrateStack(it.digitToInt(), arrayListOf())
                }

            list.forEach { rawString ->
                rawString.forEachIndexed { index, crateName ->
                    if (crateName.isLetter()) {
                        val stackId = operationList[index].digitToInt()
                        listOfCrateStacks.find { crateStack -> crateStack.id == stackId }?.listOfCrates?.add(Crate(crateName.toString()))
                    }
                }
            }
            return listOfCrateStacks
        }
    }

    fun sortShip(newCraneVersion: Boolean) {
        craneOperations.forEach {
            moveCrate(it, newCraneVersion)
        }
    }

    fun getTopCrates(): String {
        return crateStacks.flatMap { it.listOfCrates.take(1) }
            .joinToString(separator = "") { it.id }
    }

    private fun moveCrate(operation: CraneOperation, newCraneVersion: Boolean) {
        val stackFrom = crateStacks.find { it.id == operation.from }
        val movableCrates = stackFrom?.listOfCrates?.take(operation.move)

        if (movableCrates != null) {
            stackFrom.listOfCrates.removeFirstElements(operation.move)
            val newCrateList = if (newCraneVersion) movableCrates else movableCrates.reversed()
            crateStacks.find { it.id == operation.to }?.listOfCrates?.addAll(0, newCrateList)
        }
    }
}