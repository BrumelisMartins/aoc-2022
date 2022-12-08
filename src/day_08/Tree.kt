package day_08

data class Tree(
    val frontScene: Scene,
    val backScene: Scene,
    val leftScene: Scene,
    val rightScene: Scene) {

    val isVisible = frontScene.visibility || backScene.visibility || leftScene.visibility || rightScene.visibility
    val totalScore = frontScene.score * backScene.score * leftScene.score * rightScene.score
}