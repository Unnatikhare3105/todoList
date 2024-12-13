package com.example.todo_list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun edittask(task: task,
             onEditting: (String, String) -> Unit){
    var isdialog by remember { mutableStateOf(false) }
    var editedname by remember { mutableStateOf(task.taskname) }
    var editedtime by remember { mutableStateOf(task.detail) }
    var context = LocalContext.current

    AlertDialog(onDismissRequest = { isdialog = false },
        confirmButton = {
            Button(onClick = {
                Toast.makeText(context, "Editted", Toast.LENGTH_LONG).show()

                onEditting(editedname, editedtime)
            }) {
                Text("Complete")
            }
        },
        title = { Text("Edit Task") },
        text ={
            Column {
                OutlinedTextField(value = editedname,
                    onValueChange = { editedname = it },
                    label = { Text("Task Name") })
                Spacer(modifier =  Modifier.height(10.dp))
                OutlinedTextField(value = editedtime,
                    onValueChange = { editedtime = it },
                    label = { Text("Task Time") })
            }
        }
    )
}
