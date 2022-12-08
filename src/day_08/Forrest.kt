package day_08

data class Forrest(val trees: List<Tree>) {

    val numberOfVisibleTrees = trees.sumOf { if (it.isVisible) 1.toInt() else 0 }

    companion object {
        fun fromRawData(rawDat: List<String>): Forrest {
            val trees = rawDat.map {
                it.toList()
                    .map(Char::digitToInt)
            }

            val mutableListOfTrees = mutableListOf<Tree>()
            (trees.indices).flatMap { row ->
                (trees[row].indices).map { column ->
                    val current = trees[row][column]
                    val maxRowSize = trees[row].size

                    val front = getScene(trees.slice(row - 1 downTo 0)
                        .map { it[column] }, current)
                    val back = getScene(trees.slice(row + 1 until trees.size)
                        .map { it[column] }, current)
                    val left = getScene(trees[row].slice(column - 1 downTo 0), current)
                    val right = getScene(trees[row].slice(column + 1 until maxRowSize), current)
                    mutableListOfTrees.add(Tree(front, back, left, right))
                }
            }
            return Forrest(mutableListOfTrees)
        }

        private fun getScene(data: List<Int>, current: Int): Scene {
            val visibility = data.firstOrNull { it >= current } == null
            val indexOfObstruction = data.indexOfFirst { it >= current }
            val scenicScore = if (indexOfObstruction == -1) data.size else indexOfObstruction + 1
            return Scene(scenicScore, visibility)
        }
    }

}