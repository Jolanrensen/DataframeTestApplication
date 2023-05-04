package nl.jolanrensen.dataframeTestApplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.jolanrensen.dataframeTestApplication.ui.theme.DataframeTestApplicationTheme
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.convertTo
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.api.update
import org.jetbrains.kotlinx.dataframe.api.with

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataframeTestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Row(Modifier.fillMaxWidth()) {
                            Greeting("Android")
                        }
                        Row(Modifier.fillMaxWidth()) {
                            DataFrameSample()
                        }
                    }
                }
            }
        }
    }
}

@DataSchema
interface TestSchema {
    val a: Int
    val b: Int
    val c: Int
}

@Composable
fun DataFrameSample(modifier: Modifier = Modifier) {
    val df = dataFrameOf("a", "b", "c")(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9
    ).convertTo<TestSchema>()
        .update { a and b and c }
        .with { it / 3 }

    val txt = df.toString()
    Text(text = txt, modifier)

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataframeTestApplicationTheme {
        DataFrameSample()
    }
}