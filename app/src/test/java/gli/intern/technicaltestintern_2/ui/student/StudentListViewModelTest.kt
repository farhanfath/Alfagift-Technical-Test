package gli.intern.technicaltestintern_2.ui.student

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gli.intern.technicaltestintern_2.data.constants.StudentConstants
import gli.intern.technicaltestintern_2.data.repository.StudentRepository
import gli.intern.technicaltestintern_2.utils.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class StudentListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StudentListViewModel

    @Test
    @DisplayName("loadStudents should update Livedata with mock data")
    fun loadStudents() {
        val mockRepository = Mockito.mock(StudentRepository::class.java)
        val mockStudentList = StudentConstants.STUDENT_LIST

        `when`(mockRepository.getStudents()).thenReturn(mockStudentList)

        viewModel = StudentListViewModel(mockRepository)

        viewModel.loadStudents()

        val students = viewModel.students.getOrAwaitValue()
        assertEquals(mockStudentList.size, students.size)
        assertEquals(mockStudentList[0].name, students[0].name)
    }
}