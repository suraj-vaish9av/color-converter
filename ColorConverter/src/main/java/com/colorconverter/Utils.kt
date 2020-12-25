package com.colorconverter

import java.util.regex.Pattern


/**
 * accepts hex code of (3,6 or 8 digit)
 * return 6 digit hex code
 */
fun String.toValidColorCode():String{

    if (!isValidColorCode())
        invalidColorCode()

    return when (length) {
        7 -> this
        9 -> {
            this.removeRange(1..2)
        }
        4 -> {
            // convert 3 digit hex code to 6 digit hex code
            convertTo6DigitHexCode()
        }
        else -> invalidColorCode()
    }
}


/**
 * Pattern to check the valid color code
 */
val colorCodePattern: Pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}|[A-Fa-f0-9]{8})\$")

/**
 * returning the status of color code, is it valid or not?
 */
fun String.isValidColorCode() = colorCodePattern.matcher(this).matches()


/**
 * Always throws IllegalArgumentException stating that provided color is not a valid color
 */
fun String.invalidColorCode():Nothing = throw IllegalArgumentException("$this is not a valid color code")


/**
 * accepts 3 digit hex code and converts it to 6 digit hex code
 */
fun String.convertTo6DigitHexCode():String{
    val stringBuilder = StringBuilder()
    stringBuilder.append("#")
    for (i in 1 until length){
        stringBuilder.append(get(i))
        stringBuilder.append(get(i))
    }
    return stringBuilder.toString()
}
