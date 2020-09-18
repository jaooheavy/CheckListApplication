package jp.solucoes.checklistapplication.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue


object Metrics {
    fun dipToPixels(context: Context, dipValue: Float): Float {
        val metrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    }
}