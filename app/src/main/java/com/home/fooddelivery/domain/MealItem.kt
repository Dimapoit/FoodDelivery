package com.home.fooddelivery.domain

data class MealItem(
    val id: Int,
    val name: String,
    var category: String = "",
    val instructions: String = "With the idea of imparting programming\n" +
            "      knowledge, Mr. Sandeep Jain, an IIT Roorkee alumnus started a\n" +
            "      dream, GeeksforGeeks. Whether programming excites you or you feel\n" +
            "      stifled, wondering how to prepare for interview questions or how to\n" +
            "      ace data structures and algorithms, GeeksforGeeks is a one-stop solution.",
    val imagePath: String
)
