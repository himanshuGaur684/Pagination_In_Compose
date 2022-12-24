package com.gaur.pagination

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.gaur.pagination.model.Data
import com.gaur.pagination.ui.theme.PaginationTheme
import dagger.hilt.android.AndroidEntryPoint

// https://api.instantwebtools.net/v1/passenger?page=0&size=1

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaginationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    HomeScreen()

                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val res = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(res) { item ->
            FakeItem(item!!)
        }
        res.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(
                                        Alignment.Center
                                    )
                            )
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(
                                        Alignment.Center
                                    )
                            )
                        }
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(
                                        Alignment.Center
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FakeItem(item: Data) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = item.airline[0].country, style = TextStyle(
                color = Color.Black, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp,
            ), modifier = Modifier.padding(8.dp)
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = item.airline[0].slogan,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium, fontSize = 16.sp)
        )
        Divider()

    }

}
















