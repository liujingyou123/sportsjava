package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.NamesAdapter;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.MyTestData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class DongTaiDetialHeadView extends LinearLayout {

    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.rl_names)
    RecyclerView rlNames;
    private NamesAdapter namesAdapter; //他们也报名了


    public DongTaiDetialHeadView(Context context) {
        super(context);
        initView();
    }

    public DongTaiDetialHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DongTaiDetialHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_dongtaidetail, this);
        ButterKnife.bind(this, this);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);
        setNameRecy();
    }

    /**
     * 他们也报名了
     */
    private void setNameRecy() {
        rlNames.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        namesAdapter = new NamesAdapter(MyTestData.getData());
        rlNames.setAdapter(namesAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNames.addItemDecoration(decoration);
    }

    @OnClick({R.id.imv_pinglun, R.id.imv_share, R.id.imv_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_pinglun:
                break;
            case R.id.imv_share:
                ShareDialog shareDialog = new ShareDialog(getContext());
                shareDialog.show();
                break;
            case R.id.imv_report:
                ReportDialog reportDialog = new ReportDialog(getContext());
                reportDialog.show();
                break;
        }
    }

}
