package com.example.mealselectorapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Declare the TextView and ImageView for meal suggestions
    private lateinit var suggestionText: TextView
    private lateinit var foodImage: ImageView

    // Define meal data as constants or data classes
    private val breakfastMeals = listOf(
        Meal("Pancakes with maple syrup and berries", R.drawable.breakfast_pancakes),
        Meal("Oatmeal with fresh fruit and honey", R.drawable.breakfast_oatmeal),
        Meal("Avocado toast with poached eggs", R.drawable.breakfast_avocado_toast)
    )

    private val lunchMeals = listOf(
        Meal("Chicken sandwich with salad", R.drawable.lunch_chicken_sandwich),
        Meal("Grilled cheese and tomato soup", R.drawable.lunch_grilled_cheese),
        Meal("Turkey club sandwich with fries", R.drawable.lunch_turkey_club)
    )

    private val dinnerMeals = listOf(
        Meal("Spaghetti and meatballs with garlic bread", R.drawable.dinner_spaghetti),
        Meal("Grilled salmon with vegetables", R.drawable.dinner_grilled_salmon),
        Meal("Butter chicken with rice and/or naan", R.drawable.dinner_butter_chicken)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the UI elements
        suggestionText = findViewById(R.id.suggestionText)
        foodImage = findViewById(R.id.imageView)

        // Initialize the buttons and set click listeners
        val breakfastBtn: Button = findViewById(R.id.breakfastBtn)
        val lunchBtn: Button = findViewById(R.id.lunchBtn)
        val dinnerBtn: Button = findViewById(R.id.dinnerBtn)

        breakfastBtn.setOnClickListener {
            showMealSuggestion(mealType = "breakfast")
        }

        lunchBtn.setOnClickListener {
            showMealSuggestion(mealType = "lunch")
        }

        dinnerBtn.setOnClickListener {
            showMealSuggestion(mealType = "dinner")
        }
    }

    // Method to show a random meal suggestion based on the meal type
    private fun showMealSuggestion(mealType: String) {
        val selectedMeal = when (mealType) {
            "breakfast" -> getRandomMeal(breakfastMeals)
            "lunch" -> getRandomMeal(lunchMeals)
            "dinner" -> getRandomMeal(dinnerMeals)
            else -> null
        }

        selectedMeal?.let {
            suggestionText.text = it.name
            foodImage.setImageResource(it.imageResId)
        }
    }

    // Helper function to get a random meal from a list
    private fun getRandomMeal(mealList: List<Meal>): Meal {
        return mealList[Random.nextInt(mealList.size)]
    }

    // Data class to represent each meal with its name and associated image resource ID
    data class Meal(val name: String, val imageResId: Int)
}