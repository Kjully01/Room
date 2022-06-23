package br.com.room.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.room.R
import br.com.room.databinding.FragmentUpdateBinding
import br.com.room.model.Aluna
import br.com.room.viewModel.AlunaViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: AlunaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""

        viewModel = ViewModelProvider(this).get(AlunaViewModel::class.java)

        binding.apply {
            etName.setText(args.currentAluna.name)
            etLastName.setText(args.currentAluna.lastName)
            etAge.setText(args.currentAluna.age.toString())
            etEmail.setText(args.currentAluna.email)
        }

        listener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemList -> {
                navigateList()
                true
            }
            R.id.itemCadastrar -> {
                findNavController().navigate(
                    UpdateFragmentDirections.actionUpdateFragmentToAddFragment()
                )
                true
            }
            else -> false
        }
    }

    private fun listener() {
        binding.btnUpdate.setOnClickListener {
            if (etName.text.toString().isNotEmpty() &&
                etLastName.text.toString().isNotEmpty() &&
                etAge.text.toString().isNotEmpty() &&
                etEmail.text.toString().isNotEmpty()
            ) {
                val name = etName.text.toString()
                val lastName = etLastName.text.toString()
                val age = etAge.text.toString()
                val email = etEmail.text.toString()

                val aluna = Aluna(args.currentAluna.id, name, lastName, age.toInt(), email)
                //val aluna = Aluna(args.currentAluna.id, name, lastName, age.toInt())
                viewModel.updateAluna(aluna)

                navigateList()
            } else {
                Toast.makeText(requireContext(), "Preencha todos os dados", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteAluna(args.currentAluna)
            navigateList()
        }
    }

    private fun navigateList() {
        findNavController().navigate(
            UpdateFragmentDirections.actionUpdateFragmentToListFragment()
        )
    }

}