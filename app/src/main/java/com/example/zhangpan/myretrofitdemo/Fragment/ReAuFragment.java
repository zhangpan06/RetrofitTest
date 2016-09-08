package com.example.zhangpan.myretrofitdemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangpan.myretrofitdemo.Service.RetrofitService;
import com.example.zhangpan.myretrofitdemo.bean.Comment;
import com.example.zhangpan.myretrofitdemo.bean.CommentBean;
import com.example.zhangpan.myretrofitdemo.R;
import com.example.zhangpan.myretrofitdemo.Service.RetrofitAuthService;
import com.example.zhangpan.myretrofitdemo.constants.RequestConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zhangpan on 16/8/22.
 */
public class ReAuFragment extends Fragment {
    private View rootView;
    private Button post_headers;
    private Button post_body_map;
    private Button post_body_bean;
    private Button delete;
    private TextView tv_content;
    private long comment_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_re, null);

        post_headers = (Button) rootView.findViewById(R.id.post_headers);
        post_body_map = (Button) rootView.findViewById(R.id.post_body_map);
        post_body_bean = (Button) rootView.findViewById(R.id.post_body_bean);
        delete = (Button) rootView.findViewById(R.id.delete);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);

        /**
         * 可以通过返回码判断是否成功评论，也可以通过网页查看，如下
         * https://gist.github.com/jonathanmoore/2640302
         */

        post_body_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("body", "Cool");
                Call<Comment> call = RetrofitAuthService.getInstance().getRetrofitInterface()
                        .postCommentViaBodyMap(map);
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        tv_content.setText(response.code() + "  " + response.body().toString());
                        Comment self_comment = response.body();
                        comment_id = self_comment.getId();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });

        post_body_bean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Comment> call = RetrofitAuthService.getInstance().getRetrofitInterface()
                        .postCommentViaBodyBean(new CommentBean("Cool2!!"));
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        tv_content.setText(response.code() + "  " + response.body().toString());
                        Comment self_comment = response.body();
                        comment_id = self_comment.getId();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<String> call = RetrofitAuthService.getInstance().getRetrofitInterface()
                        .callDelete(String.valueOf(comment_id));

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        tv_content.setText(String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });

            }
        });
        /**
         * 不通过RetrofitAuthService中的Interceptor加入认证信息
         * 使用Headers注解实现
         */
        post_headers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Comment> call = RetrofitService.getInstance().getRetrofitInterface()
                        .postCommentViaHeaders(new CommentBean("Cool3!!"), RequestConstants.auth_string);
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        tv_content.setText(response.code() + "  " + response.body().toString());
                        Comment self_comment = response.body();
                        comment_id = self_comment.getId();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        tv_content.setText("网络请求失败");
                    }
                });
            }
        });

        return rootView;
    }
}

