package com.govardhan.nytimesarticles.ui.nytarticles


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.EditText
import com.govardhan.nytimesarticles.R
import com.govardhan.nytimesarticles.common.events.RxBus
import com.govardhan.nytimesarticles.common.utils.showTextDialog
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import com.govardhan.nytimesarticles.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import com.govardhan.nytimesarticles.databinding.FragmentNytArticlesListBinding
import com.govardhan.nytimesarticles.ui.adapters.nytarticles.NYTArticlesAdapter
import kotlinx.android.synthetic.main.fragment_nyt_articles_details.*
import kotlinx.android.synthetic.main.fragment_nyt_articles_list.view.*

import android.view.*
import kotlinx.android.synthetic.main.fragment_nyt_articles_details.toolbar
import kotlinx.android.synthetic.main.fragment_nyt_articles_list.*
import kotlinx.android.synthetic.main.nytarticle_list_item.*


class NYTArticlesListFragment : BaseFragment<NYTArticlesViewState, NYTArticlesListFragmentViewModel>() {

    private lateinit var binding: FragmentNytArticlesListBinding
    private lateinit var nytArticlesAdapter: NYTArticlesAdapter

    override fun getFragmentViewModel(): NYTArticlesListFragmentViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(NYTArticlesListFragmentViewModel::class.java)

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var periodSelected = "1json"
        when (item.getItemId()) {
            R.id.menu_1 -> periodSelected = "1.json"
            R.id.menu_7 -> periodSelected = "7.json"
            R.id.menu_30 -> periodSelected = "30.json"
        }
        callArticlesList(periodSelected)
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nyt_articles_list, container, false)
        binding.viewModel = viewModel
        binding.activity=activity as AppCompatActivity
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setCustomView(toolbar)
        observeViewModel()
        initView()
    }

    private fun initView() {
        setRecyclerView(ArrayList<NYTArticleItem>())
        viewModel.getNYTArticlesList()
            .observe(this, Observer { it?.let { nytArticlesAdapter.setAppList(it?.results!!) } })
        callArticlesList("1.json")
    }

    private fun callArticlesList(period: String) {
        Log.d("Data", period)
        viewModel.getMostViewedArtices(period)
    }

    fun showMessage(message: String, edtText: EditText) = showTextDialog(activity as Context, message) {
        RxBus.publish(RxBus.Event.KeyboardEvent(true, edtText))
    }

    fun showMessage(message: String) = showTextDialog(activity as Context, message)

    override fun render(viewState: NYTArticlesViewState) {
        when (viewState) {
            is ShowMessageViewState -> {
                showMessage(viewState.message)
            }

        }
    }

    private fun setRecyclerView(dataList: ArrayList<NYTArticleItem>) {
        nytArticlesAdapter = NYTArticlesAdapter(activity!!)
        val categoryLinearLayoutManager = LinearLayoutManager(activity!!)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.article_recycler.layoutManager = categoryLinearLayoutManager
        nytArticlesAdapter.setAppList(dataList)
        binding.root.article_recycler.adapter = nytArticlesAdapter


    }
}