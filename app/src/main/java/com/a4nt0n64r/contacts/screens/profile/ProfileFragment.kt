package com.a4nt0n64r.contacts.screens.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a4nt0n64r.contacts.APP_ACTIVITY
import com.a4nt0n64r.contacts.R
import com.a4nt0n64r.contacts.databinding.FragmentProfileBinding
import com.a4nt0n64r.contacts.models.SimpleUser
import com.a4nt0n64r.contacts.showToast
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var vievModel: ProfileFragmentViewModel

    private lateinit var currentUser: SimpleUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        currentUser = arguments?.getSerializable("user") as SimpleUser
        return notNullBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        notNullBinding.fragmentProfileName.setText(currentUser.name)
        notNullBinding.fragmentProfileSurname.setText(currentUser.surname)
        notNullBinding.fragmentProfileEmail.setText(currentUser.email)
        Glide.with(this).load(currentUser.bigImgLink).into(notNullBinding.fragmentProfileImage)
        vievModel = ViewModelProvider(this).get(ProfileFragmentViewModel::class.java)

        notNullBinding.fragmentButtonSave.setOnClickListener {
            val newUser = SimpleUser(
                currentUser.id,
                notNullBinding.fragmentProfileName.text.toString(),
                notNullBinding.fragmentProfileSurname.text.toString(),
                notNullBinding.fragmentProfileEmail.text.toString(),
                currentUser.smallImgLink,
                currentUser.bigImgLink
            )
            vievModel.delete(currentUser)
            vievModel.save(newUser)
            showToast("Пользователь сохранён")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                vievModel.delete(currentUser)
                vievModel.navigateback()
                showToast("Пользователь удалён")
            }
            R.id.btn_exit -> {
                APP_ACTIVITY.navController.navigate(R.id.action_profileFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}