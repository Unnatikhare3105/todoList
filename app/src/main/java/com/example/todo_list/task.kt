package com.example.todo_list

import java.time.LocalDateTime

data class task (
    var id: Int,
    var taskname: String,
    var detail: String,
    var isedit: Boolean = false
)