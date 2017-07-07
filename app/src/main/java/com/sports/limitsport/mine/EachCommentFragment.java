package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.mine.adapter.EachCommentAdapter;
import com.sports.limitsport.mine.model.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 评论
 */

public class EachCommentFragment extends Fragment {
    @BindView(R.id.rl_comments)
    RecyclerView rlComments;
    Unbinder unbinder;
    private EachCommentAdapter adapter;
    private List<Comment> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eachcomment, null);
        unbinder = ButterKnife.bind(this, view);
        testData();
        initView();
        return view;
    }

    private void testData() {
        for (int i = 0; i < 5; i++) {
            Comment dongtai = new Comment();
            data.add(dongtai);
        }
    }

    private void initView() {

        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlComments.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new EachCommentAdapter(data);
        adapter.bindToRecyclerView(rlComments);

        adapter.setEmptyView(emptyView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.rl_comments)
    public void onViewClicked() {
    }
}
