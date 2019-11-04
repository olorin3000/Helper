package ua.olorin.helper

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject


class DefaultFragment : Fragment() {

    companion object {
        fun newInstance() = DefaultFragment()

        const val METHOD = "porter"
    }

    private val viewModel: DefaultViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.default_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, Observer {  })
        viewModel.getServerData(METHOD)
    }


}
