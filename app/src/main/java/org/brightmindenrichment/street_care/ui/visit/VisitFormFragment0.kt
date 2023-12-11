package org.brightmindenrichment.street_care.ui.visit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitBinding
import org.brightmindenrichment.street_care.ui.visit.visit_forms.VisitLogRecyclerAdapter
import org.brightmindenrichment.street_care.ui.visit.visit_forms.VisitViewModel

class VisitFormFragment0 : Fragment() {
    private var _binding: FragmentVisitBinding? = null
    val binding get() = _binding!!
    private val sharedVisitViewModel: VisitViewModel by activityViewModels()
    private val visitDataAdapter = VisitDataAdapter()
    companion object {
        fun newInstance() = VisitFormFragment0()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitBinding.inflate(inflater, container, false)
        return _binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddNew.setOnClickListener {
            // if user is submitting multiple visit log together, the view model field should reset
           sharedVisitViewModel.resetVisitLogPage()
            findNavController().navigate(R.id.action_nav_visit_to_visitFormFragment1)
        }
        if (Firebase.auth.currentUser != null) {

           updateUI()
        } else {
            Log.d("BME", "not logged in")
        }
    }
    private fun updateUI() {
        visitDataAdapter.refresh {
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_visit)
            recyclerView?.layoutManager = LinearLayoutManager(view?.context)
            recyclerView?.adapter = VisitLogRecyclerAdapter(requireContext(), visitDataAdapter)
            var totalItemsDonated = visitDataAdapter.getTotalItemsDonated
            var totalOutreaches = visitDataAdapter.size
            var totalPeopleHelped = visitDataAdapter.getTotalPeopleCount


            binding.txtItemDonate.text = totalItemsDonated.toString()
            binding.txtOutreaches.text = totalOutreaches.toString()
            binding.txtPplHelped.text = totalPeopleHelped.toString()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}