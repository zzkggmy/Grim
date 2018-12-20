package com.kai.grim.ui.activity

import android.util.Log
import com.kai.baseutils.base.BaseActivity
import com.kai.grim.R
import com.kai.grim.bean.ArticleBean
import com.kai.sdk.model.BaseModel
import com.kai.sdk.present.BasePresent
import com.kai.sdk.present.BasePresentImp
import com.kai.sdk.view.BaseView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : BaseActivity(), BaseView<ArticleBean> {

    private val basePresent: BasePresentImp.MainPresent<ArticleBean> by lazy { BasePresentImp.MainPresent(this) }
    override fun getDataSuccess(result: ArticleBean) {
        Log.d("resu_data", result.data.datas[0].apkLink)
        tv_main.text = result.data.datas[0].chapterName
    }

    override fun getDataFailed(error: String) {

    }

    private val list: ArrayList<ArticleBean.Data.Datas> = ArrayList()

    override fun initView() {
        setCenterTitle("Main")
        setRightTitle("Giao")
        basePresent.getData(0)
    }

    override fun bindLayout() = R.layout.activity_main
}
