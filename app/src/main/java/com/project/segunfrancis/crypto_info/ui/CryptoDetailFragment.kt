package com.project.segunfrancis.crypto_info.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.segunfrancis.crypto_info.R
import com.project.segunfrancis.crypto_info.model.BaseResponse
import kotlinx.android.synthetic.main.fragment_crypto_detail.*


/**
 * A simple [Fragment] subclass.
 */
class CryptoDetailFragment : BottomSheetDialogFragment() {

    override fun getTheme(): Int {
        return R.style.BottomSheetMenuTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinItem = arguments?.getSerializable("item") as BaseResponse
        bottomSheet_coin_name.text = coinItem.name
        bottomSheet_1_hour.text = "1 hour: ".plus(coinItem.quote.USD.percent_change_1h).plus("%")
        bottomSheet_24_hours.text =
            "24 hours: ".plus(coinItem.quote.USD.percent_change_24h).plus("%")
        bottomSheet_7_days.text = "7 days: ".plus(coinItem.quote.USD.percent_change_7d).plus("%")

        if (coinItem.quote.USD.percent_change_1h > 0)
            bottomSheet_1_hour.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorAccent,
                    null
                )
            )
        else
            bottomSheet_1_hour.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.textRed,
                    null
                )
            )

        if (coinItem.quote.USD.percent_change_24h > 0)
            bottomSheet_24_hours.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorAccent,
                    null
                )
            )
        else
            bottomSheet_24_hours.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.textRed,
                    null
                )
            )

        if (coinItem.quote.USD.percent_change_7d > 0)
            bottomSheet_7_days.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorAccent,
                    null
                )
            )
        else
            bottomSheet_7_days.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.textRed,
                    null
                )
            )

        /*val legend = line_chart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)*/
        line_chart.setDrawBorders(false)

        val values = ArrayList<Entry>()
        values.add(Entry(0F, coinItem.quote.USD.percent_change_1h.toFloat()))
        values.add(Entry(1F, coinItem.quote.USD.percent_change_24h.toFloat()))
        values.add(Entry(2F, coinItem.quote.USD.percent_change_7d.toFloat()))

        val dataSet = LineDataSet(values, "Percentage Change")
        dataSet.color = Color.GREEN
        dataSet.valueTextColor = Color.DKGRAY

        val lineData = LineData(dataSet)
        line_chart.data = lineData
    }
}
