package pro.schmid.slideruitest

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySliderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

@Test
fun sliderCallsCallback() {

    var newValue: Float? = null
    val callback = { value: Float -> newValue = value }

    composeTestRule.setContent {
        MaterialTheme {
            MySlider(value = 0.3f, onChangeValue = callback)
        }
    }

    // Find slider and move it
    val rangeInfoStart = ProgressBarRangeInfo(0.3f, 0.0f..1.0f)
    composeTestRule.onNode(hasProgressBarRangeInfo(rangeInfoStart))
        .performSemanticsAction(SemanticsActions.SetProgress) { it(0.8f) }

    // Make sure the slider was moved
    val endRangeInfo = ProgressBarRangeInfo(0.8f, 0.0f..1.0f)
    composeTestRule.onNode(hasProgressBarRangeInfo(endRangeInfo))
        .assertExists()

    // Make sure the callback was called
    assertNotNull(newValue)
}
}
