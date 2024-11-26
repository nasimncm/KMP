package com.app.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MyTopAppBar()
                        BottomNavigationBar()

                    }

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar() {
        TopAppBar(title = {
            Text(
                text = "My App",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }, navigationIcon = {
            IconButton(onClick = { /* Handle back or menu button action */ }) {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "Menu"
                )
            }
        }, actions = {
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
            IconButton(onClick = { /* Handle more options */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options",
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        )
        )
    }


    @Composable
    fun MessageCard(msg: Message) {
        var selected by remember { mutableStateOf(false) } // Correct state delegate usage

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left section: Profile picture and text
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.restaurant),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, Color.Red, RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = msg.author,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = msg.author,
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = "Open",
                            color = Color(0xFF009933),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.ellipse_704),
                            contentDescription = "Clock icon",
                            modifier = Modifier
                                .size(7.dp)
                                .offset(y = 10.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Closes at 10pm",
                            color = Color.Gray,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }


            }

            Column {
                Text(
                    text = "60",
                    color = Color.Red,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
                Text(
                    text = "min",
                    color = Color.Red,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.offset(y = (-6).dp),
                    fontSize = 21.sp
                )
            }

        }

    }

    @Composable
    fun View() {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "View Customer Review",
                color = Color(0xFFF44336),
                style = MaterialTheme.typography.titleSmall
            )
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_up_24),
                contentDescription = "Clock icon"
            )
        }
    }

    @Composable
    fun UserOne() {
        var isExpanded by remember { mutableStateOf(false) } // Track expanded state
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.5.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.user_image),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Jack Panther",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Marvellous service and",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "food",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }


            }
            Row(modifier = Modifier.padding(all = 10.dp)) {
                Image(
                    painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Contact profile picture",
                )
                Text(
                    text = "4.7",
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }

    @Composable
    fun UserTwo() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.5.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.peter),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Peter Mike",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Marvellous service and",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "food",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }


            }
            Row(modifier = Modifier.padding(all = 10.dp)) {
                Image(
                    painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Contact profile picture",
                )
                Text(
                    text = "4.7",
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }

    @Composable
    fun ViewTwo() {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Menu Items",
                color = Color.DarkGray,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 21.sp
            )
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MyViewPager() {
        val pages = listOf("Appetizer", "Mains", "Desserts") // Define your pages
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        var selectedIndex by remember { mutableStateOf(0) } // Track the selected index

        // Observe changes in pagerState and update selectedIndex
        LaunchedEffect(pagerState.currentPage) {
            selectedIndex = pagerState.currentPage
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                pages.forEachIndexed { index, title ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                selectedIndex = index
                                scope.launch { pagerState.animateScrollToPage(index) }
                            }
                    ) {
                        Text(
                            text = title,
                            color = if (selectedIndex == index) Color.Red else Color.Gray, // Change color on selection
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp)) // Add gap between text and underline
                        if (selectedIndex == index) {
                            Box(
                                modifier = Modifier
                                    .height(2.dp) // Height of the underline
                                    .width(80.dp) // Width of the underline
                                    .background(Color.Red)
                            )
                        }
                    }
                }
            }

            // Horizontal Pager
            HorizontalPager(
                count = pages.size, // Number of pages
                state = pagerState,
                modifier = Modifier.height(300.dp) // Pager takes up available space
            ) { page ->
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    MenuOne()
                    Spacer(modifier = Modifier.height(5.dp))
                    MenuTwo()
                }
            }

            // Pager Indicators
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.Black,
                inactiveColor = Color.Gray
            )
        }
    }


    @Composable
    fun MenuOne() {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.5.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side (image and description)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.beef_burger),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Beef Burger",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Grilled beef patty with",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "View Details",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            // Right side (price and button)
            Column(
                modifier = Modifier.padding(all = 10.dp),
                horizontalAlignment = Alignment.End // Align text and button to the end
            ) {
                // Price in a row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "GBP",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "50",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                // Spacer between price and button
                Spacer(modifier = Modifier.height(8.dp))

                // Add to Cart Button
                val context = LocalContext.current
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(1.dp)
                        .background(Color.Red, shape = RoundedCornerShape(8.dp))
                        .clickable {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Saved")
                            }
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp) // Adjust padding for the text
                ) {
                    Text(
                        text = "Add To Cart",
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        color = Color.White // White text on red background
                    )
                }
            }
        }
    }

    @Composable
    fun MenuTwo() {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.5.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side (image and description)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.pizza),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Pepperoni Pizza",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Pizza with pepperoni",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "View Details",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            // Right side (price and button)
            Column(
                modifier = Modifier.padding(all = 10.dp),
                horizontalAlignment = Alignment.End // Align text and button to the end
            ) {
                // Price in a row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "GBP",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "80",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                // Spacer between price and button
                Spacer(modifier = Modifier.height(8.dp))

                // Add to Cart Button
                val context = LocalContext.current
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(1.dp)
                        .background(Color.Red, shape = RoundedCornerShape(8.dp))
                        .clickable {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Saved")
                            }
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp) // Adjust padding for the text
                ) {
                    Text(
                        text = "Add To Cart",
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        color = Color.White // White text on red background
                    )
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar() {
        val navController = rememberNavController()

        Scaffold(bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White // Background color for the navigation bar
            ) {
                // List of items for the bottom navigation bar
                val items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Restaurant,
                    BottomNavItem.Wines,
                    BottomNavItem.Orders,
                    BottomNavItem.Profile
                )

                // Current route for the selected state
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                items.forEach { item ->
                    BottomNavigationItem(icon = {
                        Icon(
                            imageVector = item.icon, contentDescription = item.title
                        )
                    }, label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp // Adjust the font size of the label
                        )
                    }, selected = currentRoute == item.route, onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }, selectedContentColor = Color.Red, // Red color for selected item
                        unselectedContentColor = Color.Gray // Gray color for unselected items
                    )
                }
            }
        }) { innerPadding ->
            // Navigation graph for your screens
            NavigationGraph(
                navController = navController, modifier = Modifier.padding(innerPadding)
            )
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = modifier
        ) {
            composable(BottomNavItem.Home.route) {
                val scrollState = rememberScrollState() // For vertical scrolling
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MessageCard(Message("Palm Tree", "Teneriffe EC88 9NG"))
                    View()
                    UserOne()
                    UserTwo()
                    ViewTwo()
                    MyViewPager()

                }
            }
            composable(BottomNavItem.Restaurant.route) { ScreenContent("Restaurant Screen") }
            composable(BottomNavItem.Wines.route) { ScreenContent("Wines Screen") }
            composable(BottomNavItem.Orders.route) { ScreenContent("Orders Screen") }
            composable(BottomNavItem.Profile.route) { ScreenContent("Profile Screen") }
        }
    }

    @Composable
    fun ScreenContent(text: String) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = MaterialTheme.typography.headlineMedium)
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MenuOne()
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object Restaurant : BottomNavItem("Restaurant", Icons.Default.ThumbUp, "restaurant")
    object Wines : BottomNavItem("Wines", Icons.Default.Delete, "wines")
    object Orders : BottomNavItem("Orders", Icons.Default.ShoppingCart, "orders")
    object Profile : BottomNavItem("Profile", Icons.Default.Person, "profile")
}

