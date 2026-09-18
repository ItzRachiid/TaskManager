# TaskManager
1. Descripción del proyecto
TaskManager va a ser una aplicación móvil sencilla para organizar tareas pendientes. le permitiría al usuario agregar tareas, visualizar su lista, marcar tareas como completadas y eliminarlas.

El objetivo del proyecto es ofrecer una herramienta fácil de utilizar para ayudar a las personas a organizar sus actividades diarias.

2. Exposición del problema
Las personas pueden olvidar actividades o tener dificultades para organizar sus tareas diarias. TaskManager busca solucionar este problema brindando una lista sencilla donde el usuario pueda registrar y controlar sus tareas pendientes.


3. Plataforma
La aplicación será desarrollada para dispositivos Android. Se utilizarán herramientas de desarrollo de aplicaciones móviles adecuadas para Android.


4. Interfaz de usuario e interfaz de administrador
Interfaz de usuario

El usuario sería capáz de:

Agregar nuevas tareas.
Visualizar las tareas registradas.
Marcar tareas como completadas.
Eliminar tareas.

La aplicación tendrá una interfaz sencilla para que las funciones principales sean fáciles de encontrar y utilizar.


Interfaz de administrador
Para este proyecto no será necesario crear un sistema de administración separado, debido a que la aplicación será un proyecto sencillo y no tendrá cuentas de usuarios. Las tareas serán administradas directamente desde la aplicación.


5. Funcionalidad
Las principales funciones de TaskManager serán:

Agregar una tarea.
Mostrar las tareas existentes.
Marcar una tarea como completada.
Eliminar una tarea.

El objetivo es mantener las funciones simples y enfocadas en la organización de tareas.


6. Diseño
La aplicación tendrá un diseño sencillo compuesto por las siguientes pantallas:

Pantalla principal: mostrará la lista de tareas.
Agregar tarea: permitirá escribir y agregar una nueva tarea.
Lista de tareas: permitirá marcar tareas como completadas o eliminarlas.

Los diseños iniciales se representarán mediante wireframes sencillos antes de comenzar el desarrollo de la aplicación.


Mis wireframes se verían algo así:

┌─────────────────────────┐
│      TASKMANAGER        │
├─────────────────────────┤
│ Mis tareas              │
│                         │
│ ☐ Hacer tarea           │
│ ☑ Comprar comida        │
│ ☐ Estudiar              │
│                         │
│       [+ Agregar]       │
└─────────────────────────┘
┌─────────────────────────┐
│     NUEVA TAREA         │
├─────────────────────────┤
│                         │
│ Escribe una tarea:      │
│ ┌─────────────────────┐ │
│ │ Estudiar Android    │ │
│ └─────────────────────┘ │
│                         │
│       [Agregar]         │
└─────────────────────────┘
┌─────────────────────────┐
│   ADMINISTRACIÓN        │
├─────────────────────────┤
│ Tareas registradas      │
│                         │
│ Hacer tarea     [Borrar]│
│ Estudiar        [Borrar]│
│ Comprar comida  [Borrar]│
└─────────────────────────┘


Módulo #2

---

Estructura de Pantallas
Basado en los wireframes definidos, la aplicación contará con 3 vistas principales para gestionar el flujo del usuario:

1. **Pantalla Principal (`Mis tareas`):** 
   - Esta mostraría el listado actual de tareas pendientes y completadas con indicadores visuales (`☐` / `☑`).
   - Incluye un acceso directo (`[+ Agregar]`) para navegar hacia la creación de nuevas tareas.

2. **Pantalla de Nueva Tarea:** 
   - Contar;ia con una interfaz dedicada con un campo de texto para escribir la descripción de la actividad.
   - Botón de confirmación (`[Agregar]`) para registrar la tarea en la lista principal.

3. **Pantalla de Administración:** 
   - Contaría con una vista orientada al control y gestión de los elementos registrados.
   - Permitiría al usuario visualizar el listado completo acompañado de una acción directa de eliminación (`[Borrar]`) para cada tarea.


Módulo #3 y #4

---

Añado el código que he podido adaptar hasta ahora para este proyecto haciendo uso de Android Studio, personalmente elegí la versión de Android 8.0 con un API de 37.2 debido a que este es un trabajo sencillo y quiero que se adapte bien al emulador.

Este es mi trabajo hasta ahora:

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// --------------------------------------------------
// COLORES
// --------------------------------------------------

val Morado = Color(0xFF6750A4)
val Fondo = Color(0xFFF7F5FC)
val Texto = Color(0xFF202124)
val Gris = Color(0xFF777777)
val Verde = Color(0xFF35A853)
val Rojo = Color(0xFFE5484D)


// --------------------------------------------------
// MODELO DE TAREA
// --------------------------------------------------

data class Task(
    val id: Int,
    val name: String,
    val completed: Boolean = false
)


// --------------------------------------------------
// ACTIVIDAD PRINCIPAL
// --------------------------------------------------

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskManagerApp()
        }
    }
}


// --------------------------------------------------
// APLICACIÓN
// --------------------------------------------------

@Composable
fun TaskManagerApp() {

    var tasks by remember {
        mutableStateOf(
            listOf<Task>()
        )
    }

    var screen by remember {
        mutableStateOf("home")
    }

    var newTask by remember {
        mutableStateOf("")
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Fondo
    ) {

        AnimatedContent(
            targetState = screen,

            transitionSpec = {

                (
                        slideInHorizontally(
                            initialOffsetX = { it }
                        ) + fadeIn()
                        ).togetherWith(

                        slideOutHorizontally(
                            targetOffsetX = { -it }
                        ) + fadeOut()
                    )
            },

            label = "Transicion"
        ) { pantalla ->

            when (pantalla) {

                "home" -> {

                    HomeScreen(
                        tasks = tasks,

                        onAdd = {
                            newTask = ""
                            screen = "add"
                        },

                        onAdmin = {
                            screen = "admin"
                        },

                        onCheck = { id ->

                            tasks = tasks.map { task ->

                                if (task.id == id) {

                                    task.copy(
                                        completed = !task.completed
                                    )

                                } else {
                                    task
                                }
                            }
                        },

                        onDelete = { id ->

                            tasks = tasks.filter {
                                it.id != id
                            }
                        }
                    )
                }


                "add" -> {

                    AddTaskScreen(
                        task = newTask,

                        onChange = {
                            newTask = it
                        },

                        onAdd = {

                            if (newTask.trim().isNotEmpty()) {

                                val id = if (tasks.isEmpty()) {
                                    1
                                } else {
                                    tasks.maxOf { it.id } + 1
                                }

                                tasks = tasks + Task(
                                    id = id,
                                    name = newTask.trim()
                                )

                                newTask = ""

                                screen = "home"
                            }
                        },

                        onBack = {
                            screen = "home"
                        }
                    )
                }


                "admin" -> {

                    AdminScreen(
                        tasks = tasks,

                        onDelete = { id ->

                            tasks = tasks.filter {
                                it.id != id
                            }
                        },

                        onBack = {
                            screen = "home"
                        }
                    )
                }
            }
        }
    }
}


// --------------------------------------------------
// PANTALLA PRINCIPAL
// --------------------------------------------------

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onAdd: () -> Unit,
    onAdmin: () -> Unit,
    onCheck: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {

    Scaffold(
        containerColor = Fondo,

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAdd,
                containerColor = Morado,
                contentColor = Color.White
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar tarea"
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            // Encabezado

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

            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // Título

            Text(
                text = "Mis tareas",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Texto
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )


            val completadas = tasks.count {
                it.completed
            }

            val pendientes = tasks.size - completadas


            Text(
                text = if (tasks.isEmpty()) {
                    "Todavía no tienes tareas"
                } else {
                    "$pendientes pendientes · $completadas completadas"
                },

                color = Gris
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // Lista de tareas

            if (tasks.isEmpty()) {

                EmptyTasks()

            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),

                    verticalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {

                    items(
                        items = tasks,
                        key = { it.id }
                    ) { task ->

                        TaskCard(
                            task = task,

                            onCheck = {
                                onCheck(task.id)
                            },

                            onDelete = {
                                onDelete(task.id)
                            }
                        )
                    }

                    item {

                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            // Administración

            OutlinedButton(
                onClick = onAdmin,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text("Administración")
            }
        }
    }
}


// --------------------------------------------------
// TARJETA DE TAREA
// --------------------------------------------------

@Composable
fun TaskCard(
    task: Task,
    onCheck: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = task.completed,

                onCheckedChange = {
                    onCheck()
                },

                colors = CheckboxDefaults.colors(
                    checkedColor = Verde
                )
            )


            Spacer(
                modifier = Modifier.width(6.dp)
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = task.name,

                    fontSize = 16.sp,

                    fontWeight = FontWeight.Medium,

                    color = if (task.completed) {
                        Gris
                    } else {
                        Texto
                    },

                    textDecoration = if (task.completed) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = if (task.completed) {
                        "Completada"
                    } else {
                        "Pendiente"
                    },

                    fontSize = 12.sp,

                    color = if (task.completed) {
                        Verde
                    } else {
                        Morado
                    }
                )
            }


            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Rojo
                )
            }
        }
    }
}


// --------------------------------------------------
// AGREGAR TAREA
// --------------------------------------------------

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

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // Volver

        IconButton(
            onClick = onBack
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Texto
            )
        }


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        Text(
            text = "Nueva tarea",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Morado
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "¿Qué necesitas hacer?",
            fontSize = 15.sp,
            color = Gris
        )


        Spacer(
            modifier = Modifier.height(28.dp)
        )


        OutlinedTextField(
            value = task,

            onValueChange = onChange,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Nombre de la tarea")
            },

            placeholder = {
                Text("Ej. Estudiar Android")
            },

            singleLine = true,

            shape = RoundedCornerShape(14.dp)
        )


        Spacer(
            modifier = Modifier.height(18.dp)
        )


        Button(
            onClick = onAdd,

            enabled = task.trim().isNotEmpty(),

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Morado
            )
        ) {

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Agregar tarea",
                fontWeight = FontWeight.Bold
            )
        }
    }
}


// --------------------------------------------------
// ADMINISTRACIÓN
// --------------------------------------------------

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

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // Volver

        IconButton(
            onClick = onBack
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Texto
            )
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )


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


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        if (tasks.isEmpty()) {

            Text(
                text = "No hay tareas registradas.",
                color = Gris
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),

                verticalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {

                items(
                    items = tasks,
                    key = { it.id }
                ) { task ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(15.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = null,
                                tint = Morado
                            )

                            Spacer(
                                modifier = Modifier.width(10.dp)
                            )


                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = task.name,
                                    fontWeight = FontWeight.Medium
                                )

                                Text(
                                    text = if (task.completed) {
                                        "Completada"
                                    } else {
                                        "Pendiente"
                                    },

                                    fontSize = 12.sp,

                                    color = if (task.completed) {
                                        Verde
                                    } else {
                                        Morado
                                    }
                                )
                            }


                            OutlinedButton(
                                onClick = {
                                    onDelete(task.id)
                                }
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


// --------------------------------------------------
// PANTALLA VACÍA
// --------------------------------------------------

@Composable
fun EmptyTasks() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    Morado.copy(alpha = 0.10f)
                ),

            contentAlignment =
                Alignment.Center
        ) {

            Text(
                text = "✓",
                fontSize = 35.sp,
                color = Morado
            )
        }


        Spacer(
            modifier = Modifier.height(18.dp)
        )


        Text(
            text = "No tienes tareas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Texto
        )


        Spacer(
            modifier = Modifier.height(5.dp)
        )


        Text(
            text = "Presiona + para agregar una.",
            color = Gris
        )
    }
}








