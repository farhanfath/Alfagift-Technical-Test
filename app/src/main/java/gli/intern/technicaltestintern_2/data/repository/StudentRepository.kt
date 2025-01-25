package gli.intern.technicaltestintern_2.data.repository

import gli.intern.technicaltestintern_2.domain.model.Student

interface StudentRepository {
    suspend fun getStudents(): Result<List<Student>>
    suspend fun addStudent(name: String, address: String): Result<Unit>
    suspend fun deleteStudent(studentId: String): Result<Unit>
}