package gli.intern.composetechnicaltest.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gli.intern.composetechnicaltest.domain.data.Student
import gli.intern.composetechnicaltest.presentation.ui.theme.GreyColor
import gli.intern.composetechnicaltest.presentation.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudentDialog(
    student: Student,
    onDismiss: () -> Unit = {},
    onEdit : (Student) -> Unit = {},
    onDelete : () -> Unit = {}
) {
    var name by remember { mutableStateOf(student.name) }
    var address by remember { mutableStateOf(student.address) }

    BasicAlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Manage Student",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onDelete) {
                    Text("Delete", color = Color.Red)
                }
                Row {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    TextButton(
                        onClick = {
                            onEdit(Student(student.id, name, student.profilePicture, address))
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddStudentDialog(
    onDismiss: () -> Unit = {},
    onAdd: (name: String, address: String) -> Unit = { _, _ -> }
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    BasicAlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }

                TextButton(
                    onClick = { onAdd(name, address) },
                    enabled = name.isNotBlank() && address.isNotBlank()
                ) {
                    Text("Add")
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeleteStudentDialog(
    onDismiss: () -> Unit = {},
    onDelete : () -> Unit = {}
) {
    BasicAlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text("Delete Student")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Are you sure you want to delete this student?", color = GreyColor)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = PrimaryColor, containerColor = Color.White)
                ) {
                    Text("Cancel")
                }
                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(contentColor = PrimaryColor, containerColor = Color.White)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}