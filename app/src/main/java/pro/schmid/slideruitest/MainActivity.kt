package pro.schmid.slideruitest

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.runtime.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MySlider(value = 0.3f) {
                    Log.d(TAG, "New value: $it")
                }
            }
        }
    }
}


@Composable
internal fun MySlider(
    value: Float,
    onChangeValue: (Float) -> Unit
) {
    var sliderState by remember(value) { mutableStateOf(value) }

    Slider(
        value = sliderState,
        onValueChange = { sliderState = it },
        onValueChangeFinished = { onChangeValue(sliderState) }
    )
}
