package gli.intern.technicaltestintern_2.ui.student

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import gli.intern.technicaltestintern_2.R
import gli.intern.technicaltestintern_2.databinding.ActivityStudentListBinding
import gli.intern.technicaltestintern_2.databinding.DialogAddStudentBinding
import gli.intern.technicaltestintern_2.ui.student.adapter.StudentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStudentListBinding
    private val studentViewModel : StudentListViewModel by viewModel()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupRvAdapter()
        setupObservers()

        binding.addFab.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun setupRvAdapter() {
        adapter = StudentAdapter( onDeleteClick = { studentId ->
            showDeleteDialog(studentId)
        })

        binding.recyclerView.apply {
            adapter = this@StudentListActivity.adapter
            layoutManager = LinearLayoutManager(this@StudentListActivity)
        }
    }

    private fun setupObservers() {
        studentViewModel.students.observe(this) { students ->
            adapter.setStudents(students)

            if (students.isEmpty()) {
                binding.emptyDataView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.emptyDataView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        studentViewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        studentViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }

        studentViewModel.fetchStudents()
    }

    private fun showAddStudentDialog() {
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.add_dialog_text))
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogBinding.editTextName.text.toString()
                val address = dialogBinding.editTextAddress.text.toString()

                studentViewModel.addStudent(name, address)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showDeleteDialog(selectedId: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.delete_dialog_text))
            .setMessage(getString(R.string.delete_confirmation_text))
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Delete") { _, _ ->
                studentViewModel.deleteStudent(selectedId)
            }
            .show()
    }
}