package com.dnieln7.fake17.ui.home.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.CatDetailFragmentBinding
import com.dnieln7.fake17.ui.home.HomeActivity

class CatDetailFragment : Fragment() {
    private var _binding: CatDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: CatDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatDetailFragmentBinding.inflate(inflater, container, false)

        // Get cat instance provided with NavArgs
        val cat = args.cat

        // Create an Uri instance of the cat's image if not null
        val uri = cat.catImage?.url?.toUri()?.buildUpon()?.scheme("https")?.build()

        (activity as HomeActivity).supportActionBar?.title = cat.name

        with(binding) {
            image.load(uri) {
                crossfade(true) // An smooth transition to reveal the image when it's loaded
                error(R.drawable.ic_broken_image) // Icon to display if the image could not be loaded
            }
            name.text = cat.name

            // Set score based friendliness attributes, Of a score of 1 to 5, if the current cat
            // has at least 3 the attribute is displayed

            childFriendly.visibility = if(cat.childFriendly < 3) View.GONE else View.VISIBLE
            dogFriendly.visibility = if(cat.dogFriendly < 3) View.GONE else View.VISIBLE
            hypoallergenic.visibility = if(cat.hypoallergenic < 3) View.GONE else View.VISIBLE
            description.text = cat.description
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}