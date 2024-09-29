package com.example.evanrobertson_comp304lab1_ex1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Global notes list
object NotesRepository {
    val notes = mutableStateListOf<Note>()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Home Page
            HomeLayout(
                //Get all notes in global list
                notes = NotesRepository.notes,

                //Edit Note event
                onNoteClick = { note ->
                    val intent = Intent(this, EditNoteActivity::class.java).apply {
                        //Pass note ID
                        putExtra("noteId", note.id)
                    }
                    //Start Edit Note Activity
                    startActivity(intent)
                },
                //Create Note Event
                onCreateNoteClick = {
                    val intent = Intent(this, CreateNoteActivity::class.java)
                    //Start Create Note Activity
                    startActivity(intent)
                }
            )
        }
    }

    /*
        Home Layout using Jetpack Compose
     */
    @Composable
    fun HomeLayout(
        //Pass in notes and event callbacks
        notes: List<Note>,
        onNoteClick: (note: Note) -> Unit,
        onCreateNoteClick: () -> Unit
    ) {
        NoteColumn(notes, onNoteClick)
        CreateNoteBtn(onCreateNoteClick)
    }

    /*
        LazyColumn to hold all notes
     */
    @Composable
    fun NoteColumn(notes: List<Note>, onNoteClick: (note: Note) -> Unit) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            //Create card for each note
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp)
                        .clickable(onClick = { onNoteClick(note) })
                ) {
                    Column(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 30.sp
                        )
                        Text(
                            text = note.content,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }

    /*
        FloatingActionButton with Icon to Create Notes
     */
    @Composable
    fun CreateNoteBtn(onCreateNoteClick: () -> Unit) {
        //Sit at bottom of the screen
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                onClick = {
                    onCreateNoteClick()
                },
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    imageVector = Icons.Outlined.AddCircle,
                    contentDescription = "Create Note",

                )
            }
        }
    }
}
