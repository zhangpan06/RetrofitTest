package com.example.zhangpan.myretrofitdemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangpan.myretrofitdemo.bean.Issue;
import com.example.zhangpan.myretrofitdemo.R;
import com.example.zhangpan.myretrofitdemo.Service.RetrofitService;
import com.example.zhangpan.myretrofitdemo.Utils.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zhangpan on 16/8/22.
 */
public class ReNoAuFragment extends Fragment {
    private View rootView;
    private Button get;
    private Button get_query;
    private Button get_path;
    private Button get_map;
    private TextView tv_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_re_noau, null);

        get = (Button) rootView.findViewById(R.id.get);
        get_query = (Button) rootView.findViewById(R.id.get_query);
        get_path = (Button) rootView.findViewById(R.id.get_path);
        get_map = (Button) rootView.findViewById(R.id.get_map);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Issue>> call = RetrofitService.getInstance().getRetrofitInterface().getIssues();
                call.enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {

                        List<Issue> issues = response.body();
                        if (CollectionUtils.isEmpty(issues)) {
                            tv_content.setText("数据解析失败");
                        } else {
                            StringBuffer sb = new StringBuffer();
                            Iterator iterator = issues.iterator();
                            while (iterator.hasNext()) {
                                sb.append(((Issue) iterator.next()).getBody() + "\n");
                            }
                            tv_content.setText(sb.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });

            }
        });

        get_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Issue>> call = RetrofitService.getInstance().getRetrofitInterface().getIssueViaQuery("close");
                call.enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                        List<Issue> issues = response.body();
                        if (CollectionUtils.isEmpty(issues)) {
                            tv_content.setText("数据解析失败");
                        } else {
                            StringBuffer sb = new StringBuffer();
                            Iterator iterator = issues.iterator();
                            while (iterator.hasNext()) {
                                sb.append(((Issue) iterator.next()).getBody() + "\n");
                            }
                            tv_content.setText(sb.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });
        get_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Issue>> call = RetrofitService.getInstance().getRetrofitInterface()
                        .getIssuesViaPath("vmg", "redcarpet");
                call.enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {

                        List<Issue> issues = response.body();
                        if (CollectionUtils.isEmpty(issues)) {
                            tv_content.setText("数据解析失败");
                        } else {
                            StringBuffer sb = new StringBuffer();
                            Iterator iterator = issues.iterator();
                            while (iterator.hasNext()) {
                                sb.append(((Issue) iterator.next()).getBody() + "\n");
                            }
                            tv_content.setText(sb.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });
        get_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put("page", "2");
                params.put("per_page", "10");
                Call<List<Issue>> call = RetrofitService.getInstance().getRetrofitInterface()
                        .getIssueViaQueryMap(params);

                call.enqueue(new Callback<List<Issue>>() {
                    @Override
                    public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {

                        List<Issue> issues = response.body();
                        if (CollectionUtils.isEmpty(issues)) {
                            tv_content.setText("数据解析失败");
                        } else {
                            StringBuffer sb = new StringBuffer();
                            Iterator iterator = issues.iterator();
                            while (iterator.hasNext()) {
                                sb.append(((Issue) iterator.next()).getBody() + "\n");
                            }
                            tv_content.setText(sb.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Issue>> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });
        return rootView;
    }
}


