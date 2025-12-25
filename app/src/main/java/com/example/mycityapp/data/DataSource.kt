package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation

object DataSource {
    val defaultRecommendation: Recommendation = getRestaurantData()[0]

    fun getRestaurantData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1,
                titleResourceId = R.string.blue_ginger_title,
                addressResourceId = R.string.blue_ginger_address,
                descriptionResourceId = R.string.blue_ginger_description,
                imageResourceId = R.drawable.blue_ginger
            ),
            Recommendation(
                id = 2,
                titleResourceId = R.string.four_seasons_title,
                addressResourceId = R.string.four_seasons_address,
                descriptionResourceId = R.string.four_seasons_description,
                imageResourceId = R.drawable.four_seasons
            ),
            Recommendation(
                id = 3,
                titleResourceId = R.string.mamma_mia_title,
                addressResourceId = R.string.mamma_mia_address,
                descriptionResourceId = R.string.mamma_mia_description,
                imageResourceId = R.drawable.mamma_mia
            )
        )
    }

    fun getCoffeeShopData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 4,
                titleResourceId = R.string.lark_cafe_title,
                addressResourceId = R.string.lark_cafe_address,
                descriptionResourceId = R.string.lark_cafe_description,
                imageResourceId = R.drawable.lark_coffee
            ),
            Recommendation(
                id = 5,
                titleResourceId = R.string.ama_khofi_title,
                addressResourceId = R.string.ama_khofi_address,
                descriptionResourceId = R.string.ama_khofi_description,
                imageResourceId = R.drawable.ama_khofi
            ),
            Recommendation(
                id = 6,
                titleResourceId = R.string.urban_cafe_title,
                addressResourceId = R.string.urban_cafe_address,
                descriptionResourceId = R.string.urban_cafe_description,
                imageResourceId = R.drawable.urban_cafe
            )
        )
    }

    fun getParksData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 7,
                titleResourceId = R.string.wildlife_title,
                addressResourceId = R.string.wildlife_address,
                descriptionResourceId = R.string.wildlife_description,
                imageResourceId = R.drawable.wildlife_centre
            ),
            Recommendation(
                id = 8,
                titleResourceId = R.string.kamuzu_dam_title,
                addressResourceId = R.string.kamuzu_dam_address,
                descriptionResourceId = R.string.kamuzu_dam_description,
                imageResourceId = R.drawable.kamuzu_dam
            ),
            Recommendation(
                id = 9,
                titleResourceId = R.string.havilah_park_title,
                addressResourceId = R.string.havilah_park_address,
                descriptionResourceId = R.string.havilah_park_description,
                imageResourceId = R.drawable.havilah_park
            )
        )
    }

    fun getKidsData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 10,
                titleResourceId = R.string.kids_wildlife_title,
                addressResourceId = R.string.kids_wildlife_address,
                descriptionResourceId = R.string.kids_wildlife_description,
                imageResourceId = R.drawable.wildlife_centre  // Reused — it's the same place!
            ),
            Recommendation(
                id = 11,
                titleResourceId = R.string.eden_estates_title,
                addressResourceId = R.string.eden_estates_address,
                descriptionResourceId = R.string.eden_estates_description,
                imageResourceId = R.drawable.eden_estates
            ),
            Recommendation(
                id = 12,
                titleResourceId = R.string.kukuye_title,
                addressResourceId = R.string.kukuye_address,
                descriptionResourceId = R.string.kukuye_description,
                imageResourceId = R.drawable.kukuye
            ),
            Recommendation(
                id = 13,
                titleResourceId = R.string.botanical_gardens_title,
                addressResourceId = R.string.botanical_gardens_address,
                descriptionResourceId = R.string.botanical_gardens_description,
                imageResourceId = R.drawable.botanical_gardens
            ),
            Recommendation(
                id = 14,
                titleResourceId = R.string.city_mall_kids_title,  // ← Fixed name!
                addressResourceId = R.string.city_mall_kids_address,
                descriptionResourceId = R.string.city_mall_kids_description,
                imageResourceId = R.drawable.city_mall
            )
        )
    }

    fun getShoppingData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 15,
                titleResourceId = R.string.lilongwe_city_mall_title,
                addressResourceId = R.string.lilongwe_city_mall_address,
                descriptionResourceId = R.string.lilongwe_city_mall_description,
                imageResourceId = R.drawable.lilongwe_city_mall
            ),
            Recommendation(
                id = 16,
                titleResourceId = R.string.gateway_mall_title,
                addressResourceId = R.string.gateway_mall_address,
                descriptionResourceId = R.string.gateway_mall_description,
                imageResourceId = R.drawable.gateway_mall
            ),
            Recommendation(
                id = 17,
                titleResourceId = R.string.city_mall_shopping_title,  // ← Fixed name!
                addressResourceId = R.string.city_mall_shopping_address,
                descriptionResourceId = R.string.city_mall_shopping_description,
                imageResourceId = R.drawable.city_mall  // Same mall, different context
            ),

            Recommendation(
                id = 18,
                titleResourceId = R.string.kapani_kanengo_mall_title,
                addressResourceId = R.string.kapani_kanengo_mall_address,
                descriptionResourceId = R.string.kapani_kanengo_mall_description,
                imageResourceId = R.drawable.kapani_kanengo_mall
            )
            // You can add more if needed — you have 10+ in strings.xml!
        )
    }

    val defaultCategory: Category = getCategoryData()[0]

    fun getCategoryData(): List<Category> {
        return listOf(
            Category(
                titleResourceId = R.string.restaurant_category,
                icon = R.drawable.ic_restaurant  // Use vector or PNG
            ),
            Category(
                titleResourceId = R.string.coffee_category,
                icon = R.drawable.ic_coffee
            ),
            Category(
                titleResourceId = R.string.parks_category,
                icon = R.drawable.ic_park
            ),
            Category(
                titleResourceId = R.string.kids_category,
                icon = R.drawable.ic_kids
            ),
            Category(
                titleResourceId = R.string.shopping_category,
                icon = R.drawable.ic_shopping
            )
        )
    }
}