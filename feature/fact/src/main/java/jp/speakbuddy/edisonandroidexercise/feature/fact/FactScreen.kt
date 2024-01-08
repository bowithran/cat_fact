package jp.speakbuddy.edisonandroidexercise.feature.fact

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import jp.speakbuddy.edisonandroidexercise.feature.fact.navigation.Screen
import jp.speakbuddy.edisonandroidexercise.feature.fact.theme.EdisonAndroidExerciseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactScreen(
    viewModel: FactViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    )
                ,
                title = {
                    Text(
                        Screen.FactMain.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(route = Screen.FactList.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Go facts list"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Text(
                    text = "Fact",
                    style = MaterialTheme.typography.titleLarge
                )

                if (viewModel.factUiState.value.isMultipleCats) {
                    Text(
                        text = "MultipleCats!!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = viewModel.factUiState.value.fact,
                    style = MaterialTheme.typography.bodyLarge
                )

                if (viewModel.factUiState.value.isShowLength) {
                    Text(
                        text = "Length: ${viewModel.factUiState.value.length}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.End)
                    )
                }

                val onClick: () -> Unit = {
                    viewModel.updateFact {
                        Log.d("Fact", "updateFact is done")
                    }
                }

                Button(onClick = onClick) {
                    Text(text = "Update fact")
                }
            }
        }
    }

}

@Preview
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        FactScreen(
            viewModel = hiltViewModel(),
            navController = rememberNavController()
        )
    }
}
