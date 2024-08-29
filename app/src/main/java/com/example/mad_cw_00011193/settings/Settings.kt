package com.example.mad_cw_00011193.settings

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings() {
    val sharedPref = LocalContext.current.getSharedPreferences("SharedPref", MODE_PRIVATE)
    val colorStr = sharedPref.getString("btnColor", "").toString()

    var color by remember { mutableStateOf( if(colorStr.isNotEmpty()) stringToColor(colorStr) else Color.Cyan ) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.offset(0.dp, (-40).dp),
            fontSize = 25.sp,
            text = "Shared Preferences",
            color = Color.Black
        )
        Button(
            onClick = {
                color = reverseColor(color)
                sharedPref.edit().putString("btnColor", colorToString(color)).apply() },
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text("Click to change btn color")
        }
    }

}

private fun colorToString (color: Color) : String {
    if (color == Color.Cyan)
        return "Cyan"
    return "Yellow"
}

private fun stringToColor (str: String) : Color {
    if (str == "Cyan")
        return Color.Cyan
    return Color.Yellow
}

private fun reverseColor (color: Color) : Color {
    if (color == Color.Cyan)
        return Color.Yellow
    return Color.Cyan
}
