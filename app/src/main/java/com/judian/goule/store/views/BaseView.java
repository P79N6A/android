package com.judian.goule.store.views;

/**
 * 
 * @ClassName: BaseView
 * @Description: TODO(view 基类 )
 * @author yaodingding
 * @date 2015-9-24 上午10:28:56
 * 
 */
public interface  BaseView<T> {
	/**
	 * 对于请求错误的处理
	 * 
	 *
	 *            错误消
	 */

	void  result(T bean);
	 void error();// 获取数据失败
}
