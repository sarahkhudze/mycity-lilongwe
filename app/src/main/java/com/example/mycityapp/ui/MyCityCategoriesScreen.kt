package com.example.mycityapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.R
import com.example.mycityapp.data.DataSource
import com.example.mycityapp.model.Category

@Composable
fun CategoryListScreen(
    categoryList: List<Category>,
    modifier: Modifier = Modifier,
    onCardClick: (Category) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(categoryList) { category ->
            CategoryCard(
                category = category,
                selected = false,
                onCardClick = { onCardClick(category) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category,
    selected: Boolean,
    onCardClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onCardClick(category) },
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.tertiaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                modifier = Modifier
                    .size(24.dp),
                contentDescription = stringResource(id = category.titleResourceId)
            )
            Text(
                text = stringResource(id = category.titleResourceId),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    MyCityAppTheme {
        CategoryCard(
            category = DataSource.defaultCategory,
            selected = false,
            onCardClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CategoryListContentPreview() {
    MyCityAppTheme {
        Scaffold(
            topBar = {
                MyCityAppBar(
                    canNavigateBack = false,
                    headerResId = R.string.app_name,
                    currentScreen = MyCityScreen.CATEGORY,
                    navigateUp = {} // ✅ Added missing parameter
                )
            }
        ) { innerPadding ->
            CategoryListScreen(
                categoryList = DataSource.getCategoryData(),
                onCardClick = { /* Handle click if needed */ },
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}