package com.whpe.review.missiondoneview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.whpe.review.missiondoneview.treegroup.TreeAdapterImp;
import com.whpe.review.missiondoneview.treegroup.TreeNode;
import com.whpe.review.missiondoneview.treegroup.TreeView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TreeView treeView = (TreeView) findViewById(R.id.treeView);
        TreeAdapterImp adapterImp = new TreeAdapterImp();
        TreeNode treeNode1 = new TreeNode("侄子");
        TreeNode treeNode2 = new TreeNode("侄女");
        ArrayList<TreeNode> list1 = new ArrayList<>();
        list1.add(treeNode1);
        list1.add(treeNode2);

        TreeNode treeNode3 = new TreeNode("外甥");
        TreeNode treeNode4 = new TreeNode("外甥女");
        ArrayList<TreeNode> list2 = new ArrayList<>();
        list2.add(treeNode3);
        list2.add(treeNode4);

        TreeNode treeNode5 = new TreeNode("哥哥");
        TreeNode treeNode6 = new TreeNode("姐姐");
        treeNode5.setChildren(list1);
        treeNode6.setChildren(list2);

        TreeNode treeNode7 = new TreeNode("曾孙");
        TreeNode treeNode8 = new TreeNode("曾孙女");
        ArrayList<TreeNode> list3 = new ArrayList<>();
        list3.add(treeNode7);
        list3.add(treeNode8);
        TreeNode treeNode9 = new TreeNode("外曾孙");
        TreeNode treeNode10 = new TreeNode("外曾孙女");
        ArrayList<TreeNode> list4 = new ArrayList<>();
        list4.add(treeNode9);
        list4.add(treeNode10);

        TreeNode treeNode11 = new TreeNode("孙子");
        TreeNode treeNode12 = new TreeNode("孙女");
        treeNode11.setChildren(list3);
        treeNode12.setChildren(list4);
        ArrayList<TreeNode> list5 = new ArrayList<>();
        list5.add(treeNode11);
        list5.add(treeNode12);

        TreeNode treeNode13 = new TreeNode("外孙");
        TreeNode treeNode14 = new TreeNode("外孙女");
        ArrayList<TreeNode> list6 = new ArrayList<>();
        list6.add(treeNode13);
        list6.add(treeNode14);

        TreeNode treeNode15 = new TreeNode("儿子");
        TreeNode treeNode16 = new TreeNode("女儿");
        treeNode15.setChildren(list5);
        treeNode16.setChildren(list6);
        ArrayList<TreeNode> list7 = new ArrayList<>();
        list7.add(treeNode15);
        list7.add(treeNode16);

        TreeNode treeNode17 = new TreeNode("自己");
        treeNode17.setChildren(list7);

        TreeNode treeNode18 = new TreeNode("侄子");
        TreeNode treeNode19 = new TreeNode("侄女");
        ArrayList<TreeNode> list8 = new ArrayList<>();
        list8.add(treeNode18);
        list8.add(treeNode19);

        TreeNode treeNode20 = new TreeNode("弟弟");
        treeNode20.setChildren(list8);

        TreeNode treeNode21 = new TreeNode("外甥");
        TreeNode treeNode22 = new TreeNode("外甥女");
        ArrayList<TreeNode> list9 = new ArrayList<>();
        list9.add(treeNode21);
        list9.add(treeNode22);

        TreeNode treeNode23 = new TreeNode("妹妹");
        treeNode23.setChildren(list9);

        ArrayList<TreeNode> list10 = new ArrayList<>();
        list10.add(treeNode5);
        list10.add(treeNode6);
        list10.add(treeNode17);
        list10.add(treeNode20);
        list10.add(treeNode23);

        TreeNode treeNode24 = new TreeNode("父亲");
        treeNode24.setChildren(list10);
        Gson gson = new Gson();
        String s = gson.toJson(treeNode24);
        Log.d("addView",s);
        adapterImp.setTreeNode(treeNode24);
        treeView.setAdapter(adapterImp);
    }
}