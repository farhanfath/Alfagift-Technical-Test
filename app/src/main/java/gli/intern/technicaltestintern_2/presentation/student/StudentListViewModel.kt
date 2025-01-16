package gli.intern.technicaltestintern_2.presentation.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gli.intern.technicaltestintern_2.data.repository.StudentRepository
import gli.intern.technicaltestintern_2.domain.model.Student

class StudentListViewModel(private val repository: StudentRepository) : ViewModel() {
    private val _students = MutableLiveData<List<Student>>()
    val students : LiveData<List<Student>> = _students

    fun loadStudents() {
        _students.value = repository.getStudents()
    }
}