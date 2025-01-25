package gli.intern.technicaltestintern_2.di

import com.google.firebase.firestore.FirebaseFirestore
import gli.intern.technicaltestintern_2.data.repository.StudentRepository
import gli.intern.technicaltestintern_2.data.repository.StudentRepositoryImpl
import gli.intern.technicaltestintern_2.ui.login.LoginViewModel
import gli.intern.technicaltestintern_2.ui.student.StudentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }
    single<StudentRepository> { StudentRepositoryImpl(get()) }
    viewModel { StudentListViewModel(get()) }
    viewModel { LoginViewModel() }
}