package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.aliyunoss.AliOss;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.main.adapter.HobbyAdapter;
import com.sports.limitsport.main.adapter.HobbySpaceItemDecoration;
import com.sports.limitsport.model.Hobby;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class SelectOwnHobbyActivity extends BaseActivity {
    @BindView(R.id.rl_hobby)
    RecyclerView rlHobby;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private HobbyAdapter adapter;
    private List<Hobby.DataBean> mData = new ArrayList<>();
    private String headPath;
    private String gender;
    private String name;
    private String city;
    private String birth;
    private String hobbys;
    private Subscription mSubscription;

    private String type; // 1:从个人资料页 进入

    private List<HashMap<String, String>> hobbysList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectownhabbits);
        ButterKnife.bind(this);
        getIntentData();
        initView();

        getHobbys();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            headPath = intent.getStringExtra("headPath");
            gender = intent.getStringExtra("gender");
            name = intent.getStringExtra("name");
            city = intent.getStringExtra("city");
            birth = intent.getStringExtra("birth");
            type = intent.getStringExtra("type");
        }
    }

    @OnClick({R.id.tv_skip, R.id.tv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                Intent intent1 = new Intent(SelectOwnHobbyActivity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
            case R.id.tv_done:
                if ("1".equals(type)) {
                    getHobby();
                    Intent intent = new Intent();
                    intent.putExtra("hobbys", (Serializable) hobbysList);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    doUploadPic();
                }
                break;
        }
    }

    private void initView() {
        if ("1".equals(type)) {
            tvSkip.setVisibility(View.GONE);
        }
        rlHobby.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new HobbyAdapter(mData);
        rlHobby.setAdapter(adapter);
        rlHobby.addItemDecoration(new HobbySpaceItemDecoration(UnitUtil.dip2px(this, 10)));
    }

    public void getHobbys() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getHobbys(), new LoadingNetSubscriber<Hobby>() {
            @Override
            public void response(Hobby response) {
                adapter.addData(response.getData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void getHobby() {
        hobbysList.clear();
        for (int i = 0; i < adapter.mSelectedPositions.size(); i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            int position = adapter.mSelectedPositions.get(i);
            Hobby.DataBean dataBean = adapter.getItem(position);
            hashMap.put("id", dataBean.getId() + "");
            hashMap.put("name", dataBean.getHobby());
            hobbysList.add(hashMap);
            if (TextViewUtil.isEmpty(hobbys)) {
                hobbys = dataBean.getId() + "";
            } else {
                hobbys = hobbys + "," + dataBean.getId() + "";
            }
        }
    }

    private void doUploadPic() {
        getHobby();
        mSubscription = Observable.just(headPath).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return AliOss.getInstance().putObjectFromByteArray(AliOss.DIR_HEAD_PORTRAIT, s);
            }
        }).flatMap(new Func1<String, Observable<BaseResponse>>() {
            @Override
            public Observable<BaseResponse> call(String s) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("headPortrait", s);
                hashMap.put("name", name);
                hashMap.put("hobby", hobbys);
                hashMap.put("sex", gender);
                hashMap.put("brithDate", birth);
                hashMap.put("coutry", "中国");
                hashMap.put("city", city);

                return ToolsUtil.createService(IpServices.class).updateUserInfo(hashMap);
            }
        }).compose(ToolsUtil.<BaseResponse>applayScheduers()).subscribe(new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {

                    UserInfo userInfo = SharedPrefsUtil.getUserInfo();
                    if (userInfo != null) {
                        userInfo.getData().setIsPerfect(0);
                    }
                    SharedPrefsUtil.saveUserInfo(userInfo);
                    Intent intent = new Intent(SelectOwnHobbyActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
