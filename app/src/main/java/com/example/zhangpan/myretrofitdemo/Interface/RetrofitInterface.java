package com.example.zhangpan.myretrofitdemo.Interface;

import com.example.zhangpan.myretrofitdemo.bean.Comment;
import com.example.zhangpan.myretrofitdemo.bean.Issue;
import com.example.zhangpan.myretrofitdemo.bean.CommentBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zhangpan on 16/8/22.
 */
public interface RetrofitInterface {

    /******************************************* 异步执行 ***********************************************************/
    /************************** 下面的方法为测试get方法，传递值以及获取callback的方式 ***********************************/

    //直接get访问一个地址
    @GET("/repos/vmg/redcarpet/issues")
    Call<List<Issue>> getIssues();

    //get方式拼接地址
    @GET("/repos/{user_name}/{repos_name}/issues")
    Call<List<Issue>> getIssuesViaPath(@Path("user_name") String user_name, @Path("repos_name") String repos_name);

    //在get地址后面添加参数
    @GET("/repos/vmg/redcarpet/issues")
    Call<List<Issue>> getIssueViaQuery(@Query("state") String state);

    //在get地址后面添加参数,使用QueryMap来添加多个参数，当然也可以使用多个Query来替代
    @GET("/repos/vmg/redcarpet/issues")
    Call<List<Issue>> getIssueViaQueryMap(@QueryMap Map<String,String> params);

    /************************** 下面的方法为测试post方法，传递值以及获取callback的方式 ************************************/

    //直接访问一个post地址，使用Body传递参数,
    //Map方式提交
    @POST("/gists/2640302/comments")
    Call<Comment> postCommentViaBodyMap(@Body HashMap<String,String> body);

    //Map方式提交
    @POST("/gists/2640302/comments")
    Call<Comment> postCommentViaBodyBean(@Body CommentBean body);

    //直接访问一个post地址，使用Field传递参数
    @POST("/postParams.php")
    @FormUrlEncoded
    void postViaField(@Field("name") String name, @Field("age") int age);

    //直接访问一个post地址，使用FieldMap传递参数
    @POST("/postParams.php")
    @FormUrlEncoded
    void postViaFieldMap(@FieldMap Map<String,String> params);

    //直接访问一个post地址，使用Part传递参数
    @POST("/postParams.php")
    @Multipart
    //注意阅读Part的注释，int等其他类型，需要特殊处理
    void postViaPart(@Part("name") String name, @Part("age") String age);

    //直接访问一个post地址，使用PartMap传递参数
    @POST("/postParams.php")
    @Multipart
    void postViaPartMap(@PartMap Map<String,String> params);

    /**************************** 下面的方法是测试添加header的方法 ************************************************************/
    @Headers({
            "Cache-Control: max-age=640000",
            "Accept: application/json"
    })
    @POST("/gists/2640302/comments")
    Call<Comment> postCommentViaHeaders(@Body CommentBean body,
                                        @Header("Authorization") String auth);

    /**************************** 下面的方法是测试delete的方法 ************************************************************/
    @DELETE("/gists/2640302/comments/{comment_id}")
    Call<String> callDelete(@Path("comment_id") String comment_id);

}
