package br.com.room.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.room.R
import br.com.room.databinding.FragmentAddBinding
import br.com.room.model.Aluna
import br.com.room.viewModel.AlunaViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AlunaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""

        viewModel = ViewModelProvider(this).get(AlunaViewModel::class.java)

        listener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemList -> {
                findNavController().navigate(
                    AddFragmentDirections.actionAddFragmentToListFragment2()
                )
                true
            }
            R.id.itemCadastrar -> {
                Toast.makeText(requireContext(), "Cadastrar", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }

    private fun listener() {
        binding.btnCadastro.setOnClickListener {
            if (etName.text.toString().isNotEmpty() &&
                etLastName.text.toString().isNotEmpty() &&
                etAge.text.toString().isNotEmpty()
            ) {
                val name = etName.text.toString()
                val lastName = etLastName.text.toString()
                val age = etAge.text.toString()

                val aluna = Aluna(0, name, lastName, age.toInt())
                viewModel.addAluna(aluna)
            } else {
                Toast.makeText(requireContext(), "Preencha todos os dados", Toast.LENGTH_SHORT).show()
            }

        }
    }

}