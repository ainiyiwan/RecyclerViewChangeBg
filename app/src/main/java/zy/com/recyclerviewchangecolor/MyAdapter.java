package zy.com.recyclerviewchangecolor;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * ================================================
 * 作    者：Luffy（张阳）
 * 版    本：1.0
 * 创建日期：2018/5/12
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MyAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<UserBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        if (item.isLeftShow()) {
            helper.setVisible(R.id.left_image, true);
        } else {
            helper.setVisible(R.id.left_image, false);
        }

        if (item.isRightShow()) {
            helper.setVisible(R.id.right_image, true);
        } else {
            helper.setVisible(R.id.right_image, false);
        }
    }
}
