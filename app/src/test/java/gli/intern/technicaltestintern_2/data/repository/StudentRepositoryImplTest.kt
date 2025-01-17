package gli.intern.technicaltestintern_2.data.repository

import gli.intern.technicaltestintern_2.data.constants.StudentConstants
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class StudentRepositoryImplTest {
    private lateinit var repository: StudentRepositoryImpl
    
    @Before
    fun setup() {
        repository = StudentRepositoryImpl()
    }

    @Test
    fun `getStudentsFunction should return correct list from constants`() {

        val result = repository.getStudents()

        assertEquals(StudentConstants.STUDENT_LIST, result)
        assertEquals(10, result.size)

        with(result[0]) {
            assertEquals(1, id)
            assertEquals("Alexander Mitchell", name)
            assertEquals("https://cdn-icons-png.flaticon.com/512/1144/1144760.png", profilePicture)
            assertEquals("Jl. Sudirman No. 123, Jakarta Selatan", address)
        }
    }
}