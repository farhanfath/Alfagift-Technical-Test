package gli.intern.composetechnicaltest.data.repository

import gli.intern.composetechnicaltest.domain.data.Student

interface StudentRepository {
    suspend fun getStudents(): Result<List<Student>>
    suspend fun addStudent(name: String, address: String): Result<Unit>
    suspend fun deleteStudent(studentId: String): Result<Unit>
    suspend fun editStudent(studentId: String, name: String, address: String): Result<Unit>
}