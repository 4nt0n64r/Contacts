package com.a4nt0n64r.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.a4nt0n64r.contacts.databinding.ActivityMainBinding
import com.a4nt0n64r.notes.database.room.AppRoomDataBase
import com.a4nt0n64r.notes.database.room.AppRoomRepository
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null

    //Лайфхак!
    val notNullBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(notNullBinding.root)
        APP_ACTIVITY = this
        toolbar = notNullBinding.toolbar
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
        title = getString(R.string.app_name)
        initDataBase()
    }

    fun initDataBase() {
        val dao = AppRoomDataBase.getInstance(this).getAppRoomDao()
        REPOSITORY = AppRoomRepository(dao)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

fun showToast(message: String){
    Toast.makeText(APP_ACTIVITY,message, Toast.LENGTH_LONG).show()
}


//Хороший пример чтобы переделать позже:

//suspend fun DBClass.insert(note: AppNote) {
//    database.insert()  // тут может быть ексепшен
//}
//
//suspend fun ViewModelClass.insert(note: AppNote, onSuccess: () -> Unit) {
//    val succesLiveData = MutableLiveData(false)
//
//
//    viewModelScope.launch(Dispatchers.Main) {
//        try {
//            withContext(Dispatchers.IO) {
//                DBClass.insert(note)
//                successLiveData.setValue(true)
//            }
//            onSuccess()
//        } catch (Exception e) {
//            Log.e(e)
//        }
//    }
//}
//
//class Fragment {
//    addNoteViewModel.successLiveData.observe
//    {
//        // во вьюмодели триггернуть успешную лайвдвту и тогда ок
//    }
//
//    fun onViewCreated(...) {
//        addNoteViewModel.insert(Appnote(...))
//    }
//
//}