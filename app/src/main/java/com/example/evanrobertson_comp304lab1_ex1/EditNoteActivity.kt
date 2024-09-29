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

class EditNoteActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the note ID passed through Intent
        val noteId = intent.getIntExtra("noteId", -1)

        setContent {
            EditNoteLayout(
                //Use noteId to find selected note for editing
                note = NotesRepository.notes[noteId],

                //Save Event
                onSaveClick = { title, content ->

                    //Create new note to replace old one
                    val updatedNote : Note = Note(noteId, title, content)

                    //Update note list
                    NotesRepository.notes[noteId] = updatedNote

                    //Return to home activity
                    finish()
                }
            )
        }
    }
}

/*
    Layout for editing notes
 */
@Composable
fun EditNoteLayout(note: Note, onSaveClick : (title : String, content : String) -> Unit) {

    //Setup title and content variables using passed in note title and note content
    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }

    //Column for textfields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Title field
        TextField(
            //Populate field with title
            value = title,

            //Update title to entered text
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Content field
        TextField(
            //Populate field with content
            value = content,

            //Update content to entered text
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