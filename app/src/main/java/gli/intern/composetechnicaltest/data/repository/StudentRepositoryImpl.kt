package gli.intern.composetechnicaltest.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import gli.intern.composetechnicaltest.domain.data.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class StudentRepositoryImpl(
    firestore: FirebaseFirestore
) : StudentRepository {

    private val path = firestore.collection("students")

    override suspend fun getStudents(): Result<List<Student>> = withContext(Dispatchers.IO) {
        try {
            val snapshot = path.get().await()
            val students = snapshot.toObjects(Student::class.java)
            Result.success(students)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addStudent(name: String, address: String): Result<Unit> = withContext(
        Dispatchers.IO) {
        try {
            val data = Student(
                id = UUID.randomUUID().toString(),
                name = name,
                address = address,
                profilePicture = "https://cdn-icons-png.flaticon.com/512/1144/1144760.png"
            )
            path.document(data.id).set(data).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteStudent(studentId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            path.document(studentId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun editStudent (
        studentId: String,
        name: String,
        address: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        val updatedData = Student(
            id = studentId,
            name = name,
            address = address,
            profilePicture = "https://cdn-icons-png.flaticon.com/512/1144/1144760.png"
        )
        try {
            path.document(studentId).set(updatedData).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}