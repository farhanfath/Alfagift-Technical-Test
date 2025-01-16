package gli.intern.technicaltestintern_2.data.repository

import gli.intern.technicaltestintern_2.data.constants.StudentConstants
import gli.intern.technicaltestintern_2.domain.model.Student

class StudentRepositoryImpl : StudentRepository {
    override fun getStudents(): List<Student> {
        return StudentConstants.STUDENT_LIST
    }
}