package com.example.proyectorachid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

val Morado = Color(0xFF6750A4)
val Fondo = Color(0xFFF7F5FC)
val Texto = Color(0xFF202124)
val Gris = Color(0xFF777777)
val Verde = Color(0xFF35A853)
val Rojo = Color(0xFFE5484D)

data class Task(
    val id: Int,
    val name: String,
    val completed: Boolean = false
)

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
        private set

    var screen by mutableStateOf("home")
    var newTaskText by mutableStateOf("")
    var filterType by mutableStateOf("Todas")

    fun addTask() {
        if (newTaskText.trim().isNotEmpty()) {
            val id = if (tasks.isEmpty()) 1 else tasks.maxOf { it.id } + 1
            tasks = tasks + Task(id = id, name = newTaskText.trim())
            newTaskText = ""
            screen = "home"
        }
    }

    fun toggleTask(id: Int) {
        tasks = tasks.map { task ->
            if (task.id == id) task.copy(completed = !task.completed) else task
        }
    }

    fun deleteTask(id: Int) {
        tasks = tasks.filter { it.id != id }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerApp()
        }
    }
}

@Composable
fun TaskManagerApp(viewModel: TaskViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Fondo
    ) {
        AnimatedContent(
            targetState = viewModel.screen,
            transitionSpec = {
                (slideInHorizontally(initialOffsetX = { it }) + fadeIn())
                    .togetherWith(slideOutHorizontally(targetOffsetX = { -it }) + fadeOut())
            },
            label = "Transicion"
        ) { pantalla ->
            when (pantalla) {
                "home" -> {
                    HomeScreen(
                        tasks = viewModel.tasks,
                        filterType = viewModel.filterType,
                        onFilterChanged = { viewModel.filterType = it },
                        onAddClick = {
                            viewModel.newTaskText = ""
                            viewModel.screen = "add"
                        },
                        onAdminClick = { viewModel.screen = "admin" },
                        onCheck = { viewModel.toggleTask(it) },
                        onDelete = { viewModel.deleteTask(it) }
                    )
                }
                "add" -> {
                    AddTaskScreen(
                        task = viewModel.newTaskText,
                        onChange = { viewModel.newTaskText = it },
                        onAdd = { viewModel.addTask() },
                        onBack = { viewModel.screen = "home" }
                    )
                }
                "admin" -> {
                    AdminScreen(
                        tasks = viewModel.tasks,
                        onDelete = { viewModel.deleteTask(it) },
                        onBack = { viewModel.screen = "home" }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    tasks: List<Task>,
    filterType: String,
    onFilterChanged: (String) -> Unit,
    onAddClick: () -> Unit,
    onAdminClick: () -> Unit,
    onCheck: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    Scaffold(
        containerColor = Fondo,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Morado,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Text(
                text = "TaskManager",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Morado
            )
            Text(
                text = "Organiza tus actividades",
                fontSize = 14.sp,
                color = Gris
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Mis tareas",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Texto
            )

            Spacer(modifier = Modifier.height(5.dp))

            val completadas = tasks.count { it.completed }
            val pendientes = tasks.size - completadas

            Text(
                text = if (tasks.isEmpty()) {
                    "Todavía no tienes tareas"
                } else {
                    "$pendientes pendientes · $completadas completadas"
                },
                color = Gris
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Todas", "Pendientes", "Completadas").forEach { filtro ->
                    FilterChip(
                        selected = filterType == filtro,
                        onClick = { onFilterChanged(filtro) },
                        label = { Text(filtro) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            val filteredTasks = when (filterType) {
                "Pendientes" -> tasks.filter { !it.completed }
                "Completadas" -> tasks.filter { it.completed }
                else -> tasks
            }

            if (filteredTasks.isEmpty()) {
                EmptyTasks()
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = filteredTasks,
                        key = { it.id }
                    ) { task ->
                        TaskCard(
                            task = task,
                            onCheck = { onCheck(task.id) },
                            onDelete = { onDelete(task.id) }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onAdminClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Administración")
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onCheck: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onCheck() },
                colors = CheckboxDefaults.colors(checkedColor = Verde)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (task.completed) Gris else Texto,
                    textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = if (task.completed) "Completada" else "Pendiente",
                    fontSize = 12.sp,
                    color = if (task.completed) Verde else Morado
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Rojo
                )
            }
        }
    }
}

@Composable
fun AddTaskScreen(
    task: String,
    onChange: (String) -> Unit,
    onAdd: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        IconButton(onClick = onBack) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Texto)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Nueva tarea",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Morado
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "¿Qué necesitas hacer?",
            fontSize = 15.sp,
            color = Gris
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = task,
            onValueChange = onChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre de la tarea") },
            placeholder = { Text("Ej. Estudiar Android") },
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = onAdd,
            enabled = task.trim().isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Morado)
        ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Agregar tarea", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AdminScreen(
    tasks: List<Task>,
    onDelete: (Int) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        IconButton(onClick = onBack) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Texto)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Administración",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Morado
        )

        Text(
            text = "${tasks.size} tareas registradas",
            color = Gris
        )

        Spacer(modifier = Modifier.height(25.dp))

        if (tasks.isEmpty()) {
            Text(
                text = "No hay tareas registradas.",
                color = Gris
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = tasks,
                    key = { task -> task.id }
                ) { task ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = null,
                                tint = Morado
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = task.name,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = if (task.completed) "Completada" else "Pendiente",
                                    fontSize = 12.sp,
                                    color = if (task.completed) Verde else Morado
                                )
                            }

                            OutlinedButton(
                                onClick = { onDelete(task.id) }
                            ) {
                                Text(
                                    text = "Borrar",
                                    color = Rojo
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyTasks() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Morado.copy(alpha = 0.10f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✓",
                fontSize = 35.sp,
                color = Morado
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "No tienes tareas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Texto
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Presiona + para agregar una.",
            color = Gris
        )
    }
}