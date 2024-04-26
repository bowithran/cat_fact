package jp.speakbuddy.edisonandroidexercise.feature.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import jp.speakbuddy.edisonandroidexercise.core.model.FactUiState
import jp.speakbuddy.edisonandroidexercise.feature.fact.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactListScreen(
    viewModel: FactViewModel = hiltViewModel(),
    navController: NavHostController
) {
    viewModel.getLocalFacts()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(Screen.FactList.name)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go home"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 0.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            if (viewModel.localFacts.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .paint(
                                painterResource(id = R.drawable.eto_tatsu_fukubukuro),
                                contentScale = ContentScale.None
                            )
                    ) {
                        Text(text = "No Cats! (´;︵;`) But got a dragon!")
                    }
                }
            } else {
                items(viewModel.localFacts) {
                    LocalFactItem(item = it)
                }
            }
        }
    }
}

@Composable
fun LocalFactItem(
    item: FactUiState
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)
        .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        val modifier = if (item.isMultipleCats) {
            Modifier.paint(
                painterResource(id = R.drawable.cat_kotatsu_neko),
                contentScale = ContentScale.None
            )
        } else {
            Modifier
        }
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(text = item.fact)
            Text(text = "Length: ${item.length}")
        }
    }
}