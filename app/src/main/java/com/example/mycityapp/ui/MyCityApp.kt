package com.example.mycityapp.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mycityapp.R
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import kotlinx.coroutines.delay

enum class MyCityScreen {
    CATEGORY,
    RECOMMENDATION,
    DETAIL
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    @StringRes headerResId: Int,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            AnimatedContent(
                targetState = stringResource(id = headerResId)
            ) { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.CATEGORY.name
    )

    val uiState by viewModel.uiState.collectAsState()

    var showBackground by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(400)
        showBackground = true
    }

    Box(modifier = Modifier.fillMaxSize()) {

        /* ---------- BACKGROUND IMAGE ---------- */
        AnimatedVisibility(
            visible = showBackground,
            enter = fadeIn(tween(1200)),
            exit = fadeOut(tween(600)),
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        /* ---------- SOFT OVERLAY ---------- */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background.copy(alpha = 0.35f),
                            MaterialTheme.colorScheme.background.copy(alpha = 0.55f)
                        )
                    )
                )
        )

        /* ---------- MAIN UI ---------- */
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                Surface(
                    color = Color.Transparent,
                    shadowElevation = 6.dp
                ) {
                    Box(
                        modifier = Modifier.background(
                            Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary
                                )
                            )
                        )
                    ) {
                        MyCityAppBar(
                            currentScreen = currentScreen,
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() },
                            headerResId = if (currentScreen == MyCityScreen.CATEGORY)
                                R.string.app_name
                            else
                                uiState.headerTitleId
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MyCityScreen.CATEGORY.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(MyCityScreen.CATEGORY.name) {
                    CategoryListScreen(
                        categoryList = uiState.categoryList,
                        onCardClick = {
                            viewModel.setCategory(it)
                            navController.navigate(MyCityScreen.RECOMMENDATION.name)
                        }
                    )
                }

                composable(MyCityScreen.RECOMMENDATION.name) {
                    RecommendationListScreen(
                        recommendationList = uiState.recommendationList,
                        onCardClick = {
                            viewModel.setRecommendation(it)
                            navController.navigate(MyCityScreen.DETAIL.name)
                        }
                    )
                }

                composable(MyCityScreen.DETAIL.name) {
                    DetailsScreen(
                        recommendation = uiState.currentRecommendation
                    )
                }
            }
        }
    }
}
