package com.kai.sdk.present

import com.kai.sdk.model.BaseModel
import com.kai.sdk.model.BaseModelImp
import com.kai.sdk.view.BaseView

object BasePresentImp {
    class MainPresent<T>(private var baseView: BaseView<T>) : BasePresent<T> {
        private var baseModel: BaseModel<T> = BaseModelImp.MainModel()

        override fun getData(page: Int) {
            baseModel.getModel(this, page)
        }

        override fun onSuccess(result: T) {
            baseView.getDataSuccess(result)
        }

        override fun onFailed(error: String) {
            baseView.getDataFailed(error)
        }
    }
}