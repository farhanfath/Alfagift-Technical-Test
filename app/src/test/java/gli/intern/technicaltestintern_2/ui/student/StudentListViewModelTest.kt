package gli.intern.technicaltestintern_2.ui.student

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import gli.intern.technicaltestintern_2.data.repository.StudentRepository
import gli.intern.technicaltestintern_2.domain.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class StudentListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repository: StudentRepository

    @Mock
    private lateinit var studentsObserver: Observer<List<Student>>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    private lateinit var viewModel: StudentListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = StudentListViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @DisplayName("fetchStudents should update students and loading states")
    fun fetchStudentTest() = runTest {
        // sample
        val mockStudents = listOf(
            Student(id = "1", name = "farhan", profilePicture = "https://example.com/profile.jpg", address = "Bekasi"),
            Student(id = "2", name = "fathur", profilePicture = "https://example.com/profile.jpg", address = "jakarta")
        )

        whenever(repository.getStudents()) doReturn Result.success(mockStudents)

        viewModel.students.observeForever(studentsObserver)
        viewModel.isLoading.observeForever(loadingObserver)

        // Act
        viewModel.fetchStudents()

        // Assert
        verify(loadingObserver).onChanged(true)
        verify(studentsObserver).onChanged(mockStudents)
        verify(loadingObserver).onChanged(false)
    }

    @Test
    @DisplayName("fetchStudents should handle error")
    fun fetchStudentErrorHandlingTest() = runTest {
        // sample
        val errorMessage = "Fetch error"
        val exception = Exception(errorMessage)

        whenever(repository.getStudents()) doReturn Result.failure(exception)

        viewModel.errorMessage.observeForever(errorObserver)
        viewModel.isLoading.observeForever(loadingObserver)

        // Act
        viewModel.fetchStudents()

        // Assert
        verify(loadingObserver).onChanged(true)
        verify(errorObserver).onChanged(errorMessage)
        verify(loadingObserver).onChanged(false)
    }

    @Test
    @DisplayName("addStudent should call repository and fetch students")
    fun addStudentTest() = runTest {
        // sample
        val name = "New Student"
        val address = "New Address"

        whenever(repository.addStudent(name, address)) doReturn Result.success(Unit)
        whenever(repository.getStudents()) doReturn Result.success(emptyList())

        // Act
        viewModel.addStudent(name, address)

        // Assert
        verify(repository).addStudent(name, address)
        verify(repository).getStudents()
    }

    @Test
    @DisplayName("deleteStudent should call repository and fetch students")
    fun deleteStudentTest() = runTest {
        // sample
        val studentId = "test-student-id"

        whenever(repository.deleteStudent(studentId)) doReturn Result.success(Unit)
        whenever(repository.getStudents()) doReturn Result.success(emptyList())

        // Act
        viewModel.deleteStudent(studentId)

        // Assert
        verify(repository).deleteStudent(studentId)
        verify(repository).getStudents()
    }
}