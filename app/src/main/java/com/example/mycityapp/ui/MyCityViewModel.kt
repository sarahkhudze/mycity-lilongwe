package com.example.mycityapp.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.mycityapp.R
import com.example.mycityapp.data.DataSource
import com.example.mycityapp.data.MyCityUiState
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {

    /**
     * This first block sets up the ui state to be mutable and initializes category list
     * */
    private val _uiState = MutableStateFlow(MyCityUiState(categoryList = DataSource.getCategoryData()))
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()


    /**
     * Set the category that is clicked by the user and update header
     * */
    fun setCategory(selectedCategory: Category) {
        _uiState.update {
            it.copy(
                currentCategory = selectedCategory,
                headerTitleId = selectedCategory.titleResourceId
            )
        }
        updateRecommendationListData(selectedCategory.titleResourceId)
    }

    /**
     * Set the recommendation that is clicked by the user
     * */
    fun setRecommendation(selectedRecommendation: Recommendation) {
        _uiState.update {
            it.copy(currentRecommendation = selectedRecommendation)
        }
    }

    private fun updateRecommendationListData(@StringRes categoryTitleId: Int) {

        val recommendationList: List<Recommendation> = when (categoryTitleId) {

            R.string.restaurant_category -> DataSource.getRestaurantData()
            R.string.coffee_category -> DataSource.getCoffeeShopData()
            R.string.parks_category -> DataSource.getParksData()
            R.string.kids_category -> DataSource.getKidsData()
            R.string.shopping_category -> DataSource.getShoppingData()
            else -> DataSource.getRestaurantData()
        }

        _uiState.update {
            it.copy(recommendationList = recommendationList)
        }
    }
}