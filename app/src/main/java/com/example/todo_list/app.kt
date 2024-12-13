package com.example.todo_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun app() {

    var detail by remember { mutableStateOf("") }
    var taskName by remember { mutableStateOf("") }
    var isdialog by remember { mutableStateOf(false) }
    var list by remember { mutableStateOf(listOf<task>()) }
var context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { isdialog = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ){
        Column(modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("My Tasks", style = TextStyle(fontSize = 45.sp,
                fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(thickness =  0.8.dp, color = Color.Black)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(list) { task ->
                    if(task.isedit){
                        edittask(task = task)
                        { edittedName, editteddetail ->
                            list = list.map { it.copy(isedit = false) }
                            var editedtask = list.find { it.id == task.id }
                            editedtask?.let {
                                editedtask.taskname = edittedName
                                editedtask.detail = editteddetail
                            }
                        }
                    }
                    else{
                        showTaskList(task = task,
                            { list = list.map { it.copy(isedit = it.id == it.id)} },
                            { list = list - task}
                        )
                    }
                }
            }
        }
    }
 if(isdialog){
     AlertDialog(onDismissRequest = {},
         confirmButton = {
             Row(modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.End){
                 Button(onClick = {isdialog = false}) {
                     Text("cancel")
                 }
                 Spacer(modifier = Modifier.width(10.dp))
                 Button(onClick = {
                     Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
                     if(taskName != "" && detail != ""){
                         var newtask = task(list.size+1, taskName, detail, false)
                         list = list + newtask
                         isdialog = false
                         taskName = ""
                         detail = ""
                     }}){
                     Text("Save")
                 }
             }
         },
         title = { Text("Add Task") },
         text = {
             Column {
                 OutlinedTextField(value = taskName,
                     onValueChange = { taskName = it },
                     label = { Text("Task Name")})
                 Spacer(modifier = Modifier.height(10.dp))
                 OutlinedTextField(value = detail,
                     onValueChange = { detail = it },
                     label = { Text("Task details")})
             }
         }
         )
    }

}