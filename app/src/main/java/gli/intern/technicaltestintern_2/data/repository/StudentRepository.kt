package gli.intern.technicaltestintern_2.data.repository

import gli.intern.technicaltestintern_2.domain.model.Student

interface StudentRepository {
    fun getStudents() : List<Student>
}