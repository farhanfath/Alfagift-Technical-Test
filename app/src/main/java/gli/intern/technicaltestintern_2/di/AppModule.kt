package gli.intern.technicaltestintern_2.di

import gli.intern.technicaltestintern_2.data.repository.StudentRepository
import gli.intern.technicaltestintern_2.data.repository.StudentRepositoryImpl
import gli.intern.technicaltestintern_2.ui.login.LoginViewModel
import gli.intern.technicaltestintern_2.ui.student.StudentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<StudentRepository> { StudentRepositoryImpl() }
    viewModel { StudentListViewModel(get()) }
    viewModel { LoginViewModel() }
}