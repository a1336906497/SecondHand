package com.example.qiao.secondhand.util;

/**
 * Created by qiao on 2017/4/15.
 */

public class URL {
    //登录验证
    public static final String SECONDHANDLOGIN="http://10.0.2.2:8080/SecondHand/LoginServlet";
    //获得用户信息
    public static final String GETUSERINFO="http://10.0.2.2:8080/SecondHand/GetUserInfoServlet";
    //获得所有学院
    public static final String GETCOLLEGES="http://10.0.2.2:8080/SecondHand/GetCollegeServlet";
    //获得系?classCollege=6
    public static final String GETCLASS="http://10.0.2.2:8080/SecondHand/GetClass";
    //注册是否重名?userName=李四
    public static final String ISSAMENAME="http://10.0.2.2:8080/SecondHand/IsSameNameServlet";
    //注册
    public static final String REGISTER="http://10.0.2.2:8080/SecondHand/RegisterServlet";
    //图片
    public static final String IMAGE="http://10.0.2.2/image/";
    //按日期顺序获得商品?index=0
    public static final String GETGOODSBYTIME="http://10.0.2.2:8080/SecondHand/GetGoodsByTime";
    //首页数据
    public static final String GETREOMMENDINFO="http://10.0.2.2:8080/SecondHand/GetRecommendInfoServlet";
    //获得某件商品的全部评论?goodsid=4
    public static final String GETCOMMENDBYGOODS="http://10.0.2.2:8080/SecondHand/GetCommentServlet";
    //商品添加评论
    public static final String ADDCOMMEND="http://10.0.2.2:8080/SecondHand/AddCommendServlet";
    //商品浏览数+1  ?goodsid=
    public static final String ADDGOODSTMES="http://10.0.2.2:8080/SecondHand/AddGoodsTimes";
    //获得商品一级分类
    public static final String GETALLTYPES="http://10.0.2.2:8080/SecondHand/GetAllTypeServlet";
    //根据一级分类 获得二级分类?typeid=
    public static final String GETBORDERBYTYPE="http://10.0.2.2:8080/SecondHand/GetAllBorderServlet";
    //保存图片?imagename=
    public static final String SAVEIMAGE="http://10.0.2.2:8080/SecondHand/SaveImageServlet";
    //发布商品
    public static final String releaseGoods="http://10.0.2.2:8080/SecondHand/ReleaseServlet";
    //根据typeid获取商品列表?typeid=
    public static final String GETGOODSBYTYPE="http://10.0.2.2:8080/SecondHand/GetGoodsByType";
    //模糊查询?name=
    public static final String GETGOODSBYNAME="http://10.0.2.2:8080/SecondHand/SearchGoodsByName";
    //添加收藏
    public static final String ADDCOLLECTION="http://10.0.2.2:8080/SecondHand/AddGoodsCollection";
    //取消收藏
    public static final String CANCLECOLLECTION="http://10.0.2.2:8080/SecondHand/CancleCollections";
    //判断是否收藏
    public static final String ISCOLLECTION="http://10.0.2.2:8080/SecondHand/IsCollection";

    //获得收藏的商品列表?userid=
    public static final String GETCOLLECTIONGOODS="http://10.0.2.2:8080/SecondHand/GetCollectionsGoods";

    //获得发布的商品列表?userid=
    public static final String GETRELEASEGOODS="http://10.0.2.2:8080/SecondHand/GetReleaseGoods";


    //取消发布
    public static final String CANCLERELEASE= "http://10.0.2.2:8080/SecondHand/CancleReleaseGoods";


    //修改个人信息
    public static final String CHANGEUSERINFO="http://10.0.2.2:8080/SecondHand/ChangeUserInfoServlet";
}
