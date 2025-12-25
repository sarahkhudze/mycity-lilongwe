package com.example.mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.data.DataSource
import com.example.mycityapp.model.Recommendation

@Composable
fun DetailsScreen(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(id = recommendation.titleResourceId),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Image(
                painter = painterResource(id = recommendation.imageResourceId),
                contentDescription = stringResource(id = recommendation.titleResourceId),
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Crop,

            )
            Text(
                text = stringResource(id = recommendation.addressResourceId),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                text = stringResource(id = recommendation.descriptionResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    MyCityAppTheme {
        Scaffold(
            topBar = {
                MyCityAppBar(
                    canNavigateBack = true,
                    headerResId = DataSource.defaultCategory.titleResourceId,
                    currentScreen = MyCityScreen.DETAIL,
                    navigateUp = {} // 👈 Add this line
                )
            }
        ) { innerPadding ->
            DetailsScreen(
                recommendation = DataSource.defaultRecommendation,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}