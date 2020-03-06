package com.example.diethelperapp.dietList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diethelperapp.databinding.FragmentDietplanBinding
import com.example.diethelperapp.dietplan.DietPlanAdapter
import com.example.diethelperapp.dietplan.DietPlanRepository
import com.example.diethelperapp.dietplan.DietPlanRepositoryMocked
import com.example.diethelperapp.dietplan.DietPlanViewModel
import kotlinx.android.synthetic.main.fragment_dietlist.*

class DietPlanFragment :  Fragment()  {
    private val viewModel: DietPlanViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                DietPlanViewModel("0", DietPlanRepositoryMocked()) as T
        }
    }

    private lateinit var dataBinding: FragmentDietplanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentDietplanBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.navigator = this
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        recyclerViewDiet.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


        val dietPlanObserver = Observer<List<DietPlanRepository.DietPlanDay>> { it ->
            recyclerViewDiet.adapter = DietPlanAdapter(it, viewModel)

        }
        viewModel.dietPlan.observe(viewLifecycleOwner, dietPlanObserver)
    }



}