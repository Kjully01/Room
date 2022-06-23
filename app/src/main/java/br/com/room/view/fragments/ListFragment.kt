package br.com.room.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.room.R
import br.com.room.databinding.FragmentListBinding
import br.com.room.model.Aluna
import br.com.room.view.adapter.AlunaAdapter
import br.com.room.viewModel.AlunaViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var alunaAdapter: AlunaAdapter
    private lateinit var viewModel: AlunaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""

        viewModel = ViewModelProvider(this).get(AlunaViewModel::class.java)

        observer()
        startAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemList -> {
                Toast.makeText(requireContext(), "Listar", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.itemCadastrar -> {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToAddFragment()
                )
                true
            }
            else -> false
        }
    }

    private fun startAdapter() {
        alunaAdapter = AlunaAdapter()
        binding.rvAlunas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = alunaAdapter
        }
    }

    private fun setDataAdapter(alunaList: List<Aluna>) {
        alunaAdapter.setData(alunaList)
    }

    private fun observer() {
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { aluna ->
            setDataAdapter(aluna)
        })
    }

}