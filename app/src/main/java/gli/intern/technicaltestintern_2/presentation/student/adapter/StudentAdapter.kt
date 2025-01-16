package gli.intern.technicaltestintern_2.presentation.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gli.intern.technicaltestintern_2.databinding.ItemStudentBinding
import gli.intern.technicaltestintern_2.domain.model.Student

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var students = listOf<Student>()

    fun setStudents(newStudents: List<Student>) {
        val diffCallback = StudentDiffCallback(students, newStudents)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        students = newStudents
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size

    class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.userName.text = student.name
            binding.userPhone.text = student.address
            Glide.with(binding.root.context)
                .load(student.profilePicture)
                .circleCrop()
                .into(binding.profileImage)
        }
    }

    class StudentDiffCallback(
        private val oldList: List<Student>,
        private val newList: List<Student>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}