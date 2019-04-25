package net.virtualmon.mycleankt.CurrencyMain

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.main_historical_data.*
import net.virtualmon.mycleankt.CurrencyMain.presenters.CurrencyListPresenter
import net.virtualmon.mycleankt.base.BaseActivity
import net.virtualmon.mycleankt.models.EuroDollarEntityApp
import org.jetbrains.anko.alert
import javax.inject.Inject


class MainActivity : BaseActivity(), CurrencyListView {

    @Inject lateinit var presenter: CurrencyListPresenter

    //location

    private var hasNetwork = false

    private val startDate : String = "2019-01-01"
    private val endDate : String = "2019-04-30"


    override var layout = net.virtualmon.mycleankt.R.layout.main_historical_data


    override fun onViewLoaded() {
        showLoader()
        configView()

        //we can create an edittext or calendar to choose the dates before make a petition.
        //lets do it static to make it easier, this kind of complexity is not important for the test
        presenter.execute(startDate,endDate)

    }

    private fun configView() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }



    override fun showError() {
        alert(getString(net.virtualmon.mycleankt.R.string.error_network)) {
            title = getString(net.virtualmon.mycleankt.R.string.error)
            positiveButton(net.virtualmon.mycleankt.R.string.accept) {finish() }
        }.show()
    }

    override fun renderHistoricalCurrency(euroDollarEntityApp: EuroDollarEntityApp) {

        val days = arrayListOf<String>()
        val currencyInfo:  Map<String,Double>


        var n: Float = 0.0f
        val prices = arrayListOf<BarEntry>()
        for((k,v) in euroDollarEntityApp.currencyInfo) {
            days.add(k)
            for ((k,v) in v){
                prices.add(BarEntry(n, v.toFloat()))
                n++
            }
        }

        val bardataset = BarDataSet(prices, "EUR/USD (last 3Months)") //move it to strings.xml
        barchart.animateY(2000)

        barchart.xAxis.valueFormatter = IndexAxisValueFormatter(days)
        barchart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        barchart.setFitBars(true)
        val data = BarData(bardataset)

        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)
        barchart.setData(data)
        barchart.setVisibleXRangeMaximum(10F)
        barchart.invalidate()
        hideLoader()
    }


}
