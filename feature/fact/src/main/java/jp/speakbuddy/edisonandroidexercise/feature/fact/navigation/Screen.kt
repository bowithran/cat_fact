package jp.speakbuddy.edisonandroidexercise.feature.fact.navigation

sealed class Screen(val route: String, val name: String) {
    object FactMain: Screen(route = "fact_main", name = "HOME")
    object FactList: Screen(route = "fact_list", name = "豆まとめ")
}