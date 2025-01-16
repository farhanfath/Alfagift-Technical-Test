package gli.intern.technicaltestintern_2.presentation.student

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import gli.intern.technicaltestintern_2.databinding.ActivityStudentListBinding
import gli.intern.technicaltestintern_2.presentation.student.adapter.StudentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStudentListBinding
    private val studentViewModel : StudentListViewModel by viewModel()
    private val adapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        studentViewModel.students.observe(this) { students ->
            adapter.setStudents(students)
        }

        studentViewModel.loadStudents()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@StudentListActivity.adapter
            layoutManager = LinearLayoutManager(this@StudentListActivity)
        }
    }
}