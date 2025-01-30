package gli.intern.composetechnicaltest.di

import com.google.firebase.firestore.FirebaseFirestore
import gli.intern.composetechnicaltest.data.repository.StudentRepository
import gli.intern.composetechnicaltest.data.repository.StudentRepositoryImpl
import gli.intern.composetechnicaltest.presentation.viewmodel.LoginViewModel
import gli.intern.composetechnicaltest.presentation.viewmodel.StudentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }
    single<StudentRepository> { StudentRepositoryImpl(get()) }
    viewModel { StudentListViewModel(get()) }
    viewModel { LoginViewModel() }
}