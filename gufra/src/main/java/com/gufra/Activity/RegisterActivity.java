package com.gufra.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gufra.Adapters.MyItemTouchHelper;
import com.gufra.Adapters.MyRecyclerAdapter;
import com.gufra.gufra.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {

    private ListView mList;
    private List<String> list;
    private String[] arrs;
    private ArrayAdapter<String> adapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initData();
//        MyList();
        MyRecyclerView();
    }

    private void initData() {
        list = new ArrayList<>();
        arrs = new String[50];
        for (int i= 0;i< arrs.length;i++ ){
            arrs[i] = i+"";
            list.add("item of recyclerView"+i);
        }

    }

    private void initView(){
//        mList = (ListView)findViewById(R.id.list0);
        mRecyclerView = (RecyclerView)findViewById(R.id.myrecycler_view);
    }

    private void MyList(){
        adapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,arrs);
        mList.setAdapter(adapter);
    }

    private void MyRecyclerView(){
        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(list);
        //LayoutManager:线性：LinearLayoutManager 宫格：GirdLayoutManager 瀑布：StaggeredGridLayoutManager
        // 参考：https://www.jianshu.com/p/1cfca4d34402
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ///分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //1.自定义的ItemTouchHeloer.Callback
        MyItemTouchHelper helper = new MyItemTouchHelper(recyclerAdapter);
        //2.利用这个Callback构造ItemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        //3.把ItemTouchHelper和RecyclerView关联起来.
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(recyclerAdapter);

    }
}
