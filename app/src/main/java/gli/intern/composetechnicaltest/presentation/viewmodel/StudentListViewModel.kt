package gli.intern.composetechnicaltest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gli.intern.composetechnicaltest.data.repository.StudentRepository
import gli.intern.composetechnicaltest.domain.data.Student
import kotlinx.coroutines.launch

class StudentListViewModel(private val repository: StudentRepository) : ViewModel() {
    private val _students = MutableLiveData<List<Student>>()
    val students : LiveData<List<Student>> = _students

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    fun fetchStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getStudents()
                .onSuccess { data ->
                    _students.value = data
                }
                .onFailure { exception ->
                    _errorMessage.value = exception.message ?: "Error Fetching Students"
                }
            _isLoading.value = false
        }
    }

    fun addStudent(name: String, address: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.addStudent(name, address)
            _isLoading.value = false
            fetchStudents()
        }
    }

    fun deleteStudent(studentId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteStudent(studentId)
            _isLoading.value = false
            fetchStudents()
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.editStudent(student.id, student.name, student.address)
            _isLoading.value = false
            fetchStudents()
        }
    }
}