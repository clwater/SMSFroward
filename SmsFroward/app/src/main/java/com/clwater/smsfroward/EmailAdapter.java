package com.clwater.smsfroward;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Create by clwater on 2019/4/20.
 */
public class EmailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public EmailAdapter(@Nullable List<String> data) {
        super(R.layout.emailitem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.email_text, item);
        helper.addOnClickListener(R.id.email_remove);
    }
}
