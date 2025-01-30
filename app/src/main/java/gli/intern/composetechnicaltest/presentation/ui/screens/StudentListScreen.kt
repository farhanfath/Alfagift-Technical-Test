package gli.intern.composetechnicaltest.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import gli.intern.composetechnicaltest.domain.data.Student
import gli.intern.composetechnicaltest.navigation.AppScreens
import gli.intern.composetechnicaltest.presentation.ui.components.AddStudentDialog
import gli.intern.composetechnicaltest.presentation.ui.components.DeleteStudentDialog
import gli.intern.composetechnicaltest.presentation.ui.components.EditStudentDialog
import gli.intern.composetechnicaltest.presentation.ui.theme.PrimaryColor
import gli.intern.composetechnicaltest.presentation.viewmodel.StudentListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(navController: NavController, viewModel: StudentListViewModel = koinViewModel()) {
    val students by viewModel.students.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    var showEditDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedStudent by remember { mutableStateOf<Student?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchStudents()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("List Student") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(AppScreens.LoginScreen.name) {
                            popUpToRoute
                        }
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor, titleContentColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddDialog = true
                },
                containerColor = PrimaryColor
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student Button")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if(isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = PrimaryColor
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(students) { student ->
                    StudentItem(
                        student = student,
                        onButtonClick = {
                            selectedStudent = student
                            showEditDialog = true
                        }
                    )
                }
            }

            errorMessage?.let { error ->
                Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    if (showAddDialog) {
        AddStudentDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { name, address ->
                viewModel.addStudent(name, address)
                showAddDialog = false
            }
        )
    }

    if (showEditDialog) {
        selectedStudent?.let { student ->
            EditStudentDialog(
                student = student,
                onDismiss = {
                    showEditDialog = false
                    selectedStudent = null
                },
                onEdit = { updatedStudent ->
                    viewModel.updateStudent(updatedStudent)
                    showEditDialog = false
                    selectedStudent = null
                },
                onDelete = {
                    showDeleteDialog = true
                }
            )
        }
    }

    if (showDeleteDialog) {
        selectedStudent?.let { student ->
            DeleteStudentDialog(
                onDismiss = {
                    showDeleteDialog = false
                },
                onDelete = {
                    viewModel.deleteStudent(student.id)
                    showDeleteDialog = false
                    showEditDialog = false
                    selectedStudent = null
                }
            )
        }
    }
}

@Composable
fun StudentItem(
    student : Student,
    modifier: Modifier = Modifier,
    onButtonClick : () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Red.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = student.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            IconButton(onClick = onButtonClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options"
                )
            }
        }
    }
}