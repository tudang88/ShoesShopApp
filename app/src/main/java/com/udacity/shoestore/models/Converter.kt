package com.udacity.shoestore.models

import androidx.databinding.InverseMethod

/**
 * Implement Converter for two-way binding
 * using on EditText with model field type not string
 * ex: AddProductViewModel
 * field:
 */
object Converter {
    @InverseMethod("doubleToString")
    @JvmStatic
    fun stringToDouble(oldValue: String): Double {
        return try {
            val newValue = oldValue.replace(" cm","")
            newValue.toDouble()
        } catch (e: java.lang.Exception) {
            0.0
        }
    }

    @JvmStatic
    fun doubleToString(oldValue: Double): String {
        return "%.6f cm".format(oldValue)
    }
}