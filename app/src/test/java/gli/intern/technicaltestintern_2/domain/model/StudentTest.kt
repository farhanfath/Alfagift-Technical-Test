package gli.intern.technicaltestintern_2.domain.model

import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class StudentTest {

    @Test
    @DisplayName("Student data should have correct properties")
    fun studentDataHasCorrectProperties() {
        val student = Student(
            id = "1",
            name = "John Doe",
            profilePicture = "https://example.com/profile.jpg",
            address = "123 Main St"
        )
        Assert.assertEquals("1", student.id)
        Assert.assertEquals("John Doe", student.name)
        Assert.assertEquals("https://example.com/profile.jpg", student.profilePicture)
        Assert.assertEquals("123 Main St", student.address)
    }
}