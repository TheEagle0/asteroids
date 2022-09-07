package com.predator.theeagle.astroids.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.predator.theeagle.astroids.R
import com.predator.theeagle.astroids.databinding.FragmentDetailBinding
import com.predator.theeagle.astroids.entities.Asteroid

class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding
    private val asteroid :Asteroid? by lazy { requireArguments().getParcelable("selectedAsteroid") }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

         binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        asteroid?.let {
            binding.asteroid = it
        }
        showInfoDialog()
    }

    private fun showInfoDialog() {
        binding.helpButton.setOnClickListener {
            informationDialog()
        }
    }

    private fun informationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomical_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
