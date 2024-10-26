package spectrum.fittech.componentes.charts

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun BarChart(
    xData: List<String>,
    yData: List<Float>,
    dataLabel: String,
    modifier: Modifier = Modifier,
    barColor: Color = Color.Red,
    descriptionEnabled: Boolean = false,
    legendEnabled: Boolean = true
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        factory = { context ->
            val chart = com.github.mikephil.charting.charts.HorizontalBarChart(context)
            val entries = yData.mapIndexed { index, y -> BarEntry(index.toFloat(), y) }
            val dataSet = BarDataSet(entries, dataLabel)

            // Configurações de visualização
            dataSet.color = barColor.toArgb()
            val barData = BarData(dataSet)

            chart.data = barData
            chart.description.isEnabled = descriptionEnabled
            chart.legend.isEnabled = legendEnabled

            // Configurações da legenda
            chart.legend.textColor = Color.White.toArgb()
            barData.setValueTextColor(Color.White.toArgb())

            chart.xAxis.textColor = Color.White.toArgb()
            chart.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return xData[value.toInt()]
                }
            }
            chart.xAxis.granularity = 1f

            chart.axisLeft.textColor = Color.White.toArgb()
            chart.axisRight.textColor = Color.White.toArgb()

            chart.legend.textSize = 14f
            chart.barData.setValueTextSize(14f)

            // Formatação dos valores da barra para não ter casas decimais
            dataSet.valueFormatter = object : ValueFormatter() {
                override fun getBarLabel(barEntry: BarEntry): String {
                    return barEntry.y.toInt().toString()  // Converte o valor para inteiro
                }
            }

            chart.invalidate()
            chart
        }
    )
}




    @Composable
fun LineGraph(
    xData: List<String>,
    yData: List<Float>,
    yData2: List<Float>, // Segunda linha de dados
    dataLabel: String,
    dataLabel2: String, // Rótulo da segunda linha
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Red,
    lineColor2: Color = Color.White, // Cor da segunda linha
    axisTextColor: Color = Color.White,
    drawValues: Boolean = false,
    drawMarkers: Boolean = false,
    descriptionEnabled: Boolean = false,
    legendEnabled: Boolean = true,
    yAxisRightEnabled: Boolean = false,
    xAxisPosition: XAxis.XAxisPosition = XAxis.XAxisPosition.BOTTOM,
    animationDurationX: Int = 5000,
    animationDurationY: Int = 5000,
    lineThickness: Float = 1f
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        factory = { context ->
            val chart = LineChart(context)

            // Primeira linha de dados
            val entries1 = yData.mapIndexed { index, y -> Entry(index.toFloat(), y) }
            val dataSet1 = LineDataSet(entries1, dataLabel)
            dataSet1.color = lineColor.toArgb()
            dataSet1.setDrawFilled(false)
            dataSet1.setDrawValues(drawValues)
            dataSet1.setDrawCircles(drawMarkers)
            dataSet1.lineWidth = lineThickness

            // Segunda linha de dados
            val entries2 = yData2.mapIndexed { index, y -> Entry(index.toFloat(), y) }
            val dataSet2 = LineDataSet(entries2, dataLabel2)
            dataSet2.color = lineColor2.toArgb()
            dataSet2.setDrawFilled(false)
            dataSet2.setDrawValues(drawValues)
            dataSet2.setDrawCircles(drawMarkers)
            dataSet2.lineWidth = lineThickness

            // Adicionando ambas as linhas ao gráfico
            val lineData = LineData(dataSet1, dataSet2)
            chart.data = lineData

            // Configurações visuais
            chart.data.setValueTextColor(Color.White.toArgb())

            chart.xAxis.position = xAxisPosition
            chart.xAxis.textColor = axisTextColor.toArgb()
            chart.axisLeft.textColor = axisTextColor.toArgb()
            if (yAxisRightEnabled) {
                chart.axisRight.textColor = axisTextColor.toArgb()
            } else {
                chart.axisRight.isEnabled = false
            }

            chart.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return xData[value.toInt()]
                }
            }

            chart.description.isEnabled = descriptionEnabled
            chart.legend.isEnabled = legendEnabled
            chart.legend.textColor = Color.White.toArgb()
            chart.legend.textSize = 14f

            // Animação
            chart.animateXY(
                animationDurationX,
                animationDurationY,
                Easing.Linear,
                Easing.EaseInQuad
            )

            chart.invalidate()
            chart
        }
    )
}
