package com.a4nt0n64r.contacts.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.contacts.APP_ACTIVITY
import com.a4nt0n64r.contacts.R
import com.a4nt0n64r.contacts.databinding.FragmentMainBinding
import com.a4nt0n64r.contacts.models.SimpleUser
import kotlinx.coroutines.CoroutineScope


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var vievModel: MainFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var observerOnList: Observer<List<SimpleUser>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return notNullBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        setHasOptionsMenu(true)

        adapter = MainAdapter()
        recyclerView = notNullBinding.recyclerView
        recyclerView.adapter = adapter
        observerOnList = Observer {
            val list = it.asReversed()
            adapter.setData(list)
        }

        vievModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        vievModel.usersLiveData.observe(this, observerOnList)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_action_update_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_update -> {
                vievModel.refresh()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        vievModel.usersLiveData.removeObserver(observerOnList)
        recyclerView.adapter = null
    }

    companion object {
        fun click(user: SimpleUser) {
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_profileFragment, bundle)
        }
    }
}