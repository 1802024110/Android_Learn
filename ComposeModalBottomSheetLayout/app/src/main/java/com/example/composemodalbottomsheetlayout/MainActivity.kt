package com.example.composemodalbottomsheetlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemodalbottomsheetlayout.ui.theme.ComposeModalBottomSheetLayoutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeModalBottomSheetLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeModalBottomSheetLayoutTheme {
        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        ModalBottomSheetLayout(
            sheetState = state,
            sheetContent = {
                Column{
                    ListItem{
                        Text("选择分享到哪里吧~")
                    }
                    ListItem(
                        text = { Text("github") },
                        icon = {
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFF181717)
                            ) {
                                Icon(
                                    painterResource(R.drawable.github),
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        },
                    )
                    ListItem(
                        text = { Text(text = "微信")},
                        icon = {
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFF07C160)
                            ) {
                                Icon(
                                    painterResource(R.drawable.wechat),
                                null,
                                    tint = Color.White,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {scope.launch { state.show() }}) {
                    Text(text = "点击展开")
                }
            }
            BackHandler(
                enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                        || state.currentValue == ModalBottomSheetValue.Expanded),
                onBack = {
                    scope.launch {
                        state.hide()
                    }
                }
            )
        }
    }
}

@Composable
fun AppScaffold() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("主页", "我喜欢的", "设置")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(Icons.Filled.Menu, null)
                    }
                },
                title = {
                    Text("魔卡沙的炼金工坊")
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigation {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = {
                                when(index){
                                    0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                    1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                                    else -> Icon(Icons.Filled.Settings, contentDescription = null)
                                }
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        },
        drawerContent = {
            AppDrawerContent(scaffoldState, scope)
        }
    ) {
        // 此处需要编写主界面

        // 这里的例子只调用了一个 AppContent
        // 要和 BottomNavigation 合理搭配，显示不同的界面的话
        // 考虑使用 Jetpack Compose Navigation 来实现会更加合理一些
        // 会在文档的后面介绍 Jetpack Compose Navigation

        // 这里的 AppContent 是个伪界面
        // 如果你要先简单的实现多界面，你可以这样编写
        /*
           when(selectedItem) {
                0 -> { Home() }
                1 -> { Favorite() }
                else -> { Settings() }
           }
         */
        // Home(), Favorite(), Settings() 都是单独的 Composable 函数

        AppContent(item = items[selectedItem])
    }
}

@Composable
fun AppContent(
    item: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("当前界面是 $item")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppDrawerContent(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp)
                    .border(BorderStroke(1.dp, Color.Gray), CircleShape)
            )
            Spacer(Modifier.padding(vertical = 8.dp))
            Text(
                text = "游客12345",
                style = MaterialTheme.typography.body2
            )
        }
    }

    ListItem(
        icon = {
            Icon(Icons.Filled.Home, null)
        },
        modifier = Modifier
            .clickable {

            }
    ) {
        Text("首页")
    }

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment= Alignment.BottomStart
    ) {
        TextButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onSurface),
        ) {
            Icon(Icons.Filled.Settings, null)
            Text("设置")
        }
    }

    // 编写逻辑
    // 如果 drawer 已经展开了，那么点击返回键收起而不是直接退出 app

    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}