package ua.olorin.helper

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.BundleCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.default_fragment.*
import org.koin.android.ext.android.inject
import ua.olorin.helper.data.Data
import ua.olorin.helper.databinding.DefaultFragmentBinding


class DefaultFragment : Fragment() {

    companion object {
        fun newInstance(path: String) = DefaultFragment().apply {
            val args = Bundle().apply {
                putString(ARG_PATH, path)
            }
            arguments = args
        }

        private const val ARG_PATH = "path"
    }

    private var methodPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            methodPath = it.getString(ARG_PATH)
        }
    }

    private val viewModel: DefaultViewModel by inject()
    private lateinit var viewDataBinding : DefaultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DefaultFragmentBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = viewModel
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.data.observe(viewLifecycleOwner, Observer { dataList ->
            val adapter = RecyclerViewAdapter(dataList)
            viewDataBinding.recyclerView.addItemDecoration(MarginItemDecoration(context = activity!!))
            viewDataBinding.recyclerView.adapter = adapter
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Snackbar.make(defaultFragmentLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.text_try_again){
                    methodPath?.let { viewModel.getServerData(it)}
                }.show()
        })

        methodPath?.let {
            viewModel.getServerData(it)
        }

    }


}
