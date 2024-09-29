package com.example.evanrobertson_comp304lab1_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CreateNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateNoteLayout(
                //Save event
                onSaveClick = { title, content ->
                    //Create new note
                    val newNote = Note(
                        id = NotesRepository.notes.size,
                        title = title,
                        content = content
                    )
                    //Add note to global list
                    NotesRepository.notes.add(newNote)

                    //Return to home activity
                    finish()
                }
            )
        }
    }
}

/*
    Layout for creating notes
 */
@Composable
fun CreateNoteLayout(onSaveClick: (title : String, content : String) -> Unit) {
    //Set title and content variables to empty string until text is entered
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    //Column for textfields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Title field
        TextField(
            value = title,
            //Update title with entered text
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Content field
        TextField(
            value = content,
            //Update content with entered text
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Save button
        Button(onClick = { onSaveClick(title, content) }) {
            Text("Save")
        }
    }
}