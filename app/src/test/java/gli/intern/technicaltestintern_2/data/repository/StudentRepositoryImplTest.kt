package gli.intern.technicaltestintern_2.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import gli.intern.technicaltestintern_2.domain.model.Student
import gli.intern.technicaltestintern_2.utils.mockTask
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*

class StudentRepositoryImplTest {

    @Mock
    private lateinit var firestore: FirebaseFirestore

    @Mock
    private lateinit var collectionReference: CollectionReference

    @Mock
    private lateinit var querySnapshot: QuerySnapshot

    private lateinit var repository: StudentRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        whenever(firestore.collection("students")) doReturn collectionReference
        repository = StudentRepositoryImpl(firestore)
    }

    @Test
    @DisplayName("getStudents should return success with list of students")
    fun getStudentsTest() = runTest {
        // Sample
        val mockStudents = listOf(
            Student(id = "1", name = "farhan", profilePicture = "https://example.com/profile.jpg", address = "Bekasi"),
            Student(id = "2", name = "fathur", profilePicture = "https://example.com/profile.jpg", address = "jakarta")
        )

        whenever(collectionReference.get()) doReturn mockTask(querySnapshot)
        whenever(querySnapshot.toObjects(Student::class.java)) doReturn mockStudents

        // Act
        val result = repository.getStudents()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(mockStudents, result.getOrNull())
        println(mockStudents)
    }

    @Test
    @DisplayName("addStudent should return success when student is added")
    fun addStudentTest() = runTest {
        // Sample
        val name = "Farhan Fathur"
        val address = "Cakung, Jakarta"

        val studentCaptor = argumentCaptor<Student>()
        val documentReference = mock<DocumentReference>()
        whenever(collectionReference.document(any())) doReturn documentReference
        whenever(documentReference.set(studentCaptor.capture())) doReturn mockTask<Void>(null)

        // Act
        val result = repository.addStudent(name, address)

        // Assert
        assertTrue(result.isSuccess)
        val capturedStudent = studentCaptor.firstValue
        assertEquals(name, capturedStudent.name)
        assertEquals(address, capturedStudent.address)
        assertNotNull(capturedStudent.id)
        assertNotNull(capturedStudent.profilePicture)
    }

    @Test
    @DisplayName("deleteStudent should return success when student is deleted")
    fun deleteStudentTest() = runTest {
        // Sample
        val studentId = "test-student-id"
        val documentReference = mock<DocumentReference>()

        whenever(collectionReference.document(studentId)) doReturn documentReference
        whenever(documentReference.delete()) doReturn mockTask<Void>(null)

        // Act
        val result = repository.deleteStudent(studentId)

        // Assert
        assertTrue(result.isSuccess)
        verify(documentReference).delete()
    }

    @Test
    @DisplayName("getStudents should return failure when exception occurs")
    fun failGetStudentsTest() = runTest {
        // Sample
        val exception = Exception("Network error")
        whenever(collectionReference.get()) doReturn mockTask<QuerySnapshot>(null, exception)

        // Act
        val result = repository.getStudents()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

