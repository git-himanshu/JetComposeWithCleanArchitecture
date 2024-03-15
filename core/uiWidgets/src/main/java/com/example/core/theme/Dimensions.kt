package com.example.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val grid0p25: Dp,
    val grid0p5: Dp,
    val grid1: Dp,
    val grid1p5: Dp,
    val grid2: Dp,
    val grid2p5: Dp,
    val grid3: Dp,
    val grid3p5: Dp,
    val grid4: Dp,
    val grid4p5: Dp,
    val grid5: Dp,
    val grid5p5: Dp,
    val grid6: Dp,
    val plane0: Dp,
    val plane1: Dp,
    val plane2: Dp,
    val plane3: Dp,
    val plane4: Dp,
    val plane5: Dp,
    val minimumTouchTarget: Dp = 48.dp,
)

val smallDimensions = Dimensions(
    grid0p25 = 1.5f.dp,
    grid0p5 = 3.dp,
    grid1 = 6.dp,
    grid1p5 = 9.dp,
    grid2 = 12.dp,
    grid2p5 = 15.dp,
    grid3 = 18.dp,
    grid3p5 = 21.dp,
    grid4 = 24.dp,
    grid4p5 = 27.dp,
    grid5 = 30.dp,
    grid5p5 = 33.dp,
    grid6 = 36.dp,
    plane0 = 0.dp,
    plane1 = 1.dp,
    plane2 = 2.dp,
    plane3 = 3.dp,
    plane4 = 6.dp,
    plane5 = 12.dp,
)

val sw360Dimensions = Dimensions(
    grid0p25 = 2.dp,
    grid0p5 = 4.dp,
    grid1 = 8.dp,
    grid1p5 = 12.dp,
    grid2 = 16.dp,
    grid2p5 = 20.dp,
    grid3 = 24.dp,
    grid3p5 = 28.dp,
    grid4 = 32.dp,
    grid4p5 = 36.dp,
    grid5 = 40.dp,
    grid5p5 = 44.dp,
    grid6 = 48.dp,
    plane0 = 0.dp,
    plane1 = 1.dp,
    plane2 = 2.dp,
    plane3 = 4.dp,
    plane4 = 8.dp,
    plane5 = 16.dp,
)

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}