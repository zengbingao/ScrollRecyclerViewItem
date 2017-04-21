package com.robin.SlideItem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<MyModel> listmodel=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listmodel=new ArrayList<MyModel>();
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_demo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//更新的时候防止闪动
        mRecyclerView.setAdapter(new MyAdapter(listmodel,this));
        
    }

    public void initData(){
        MyModel m=null;

        for(int i=1;i<=20;i++){
            m=new MyModel("左边"+i,"这是内容"+i,"删除"+i);
            listmodel.add(m);
        }
    }
    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private static final String TAG = "MyAdapter";
        private List<MyModel> model;
        private Context context;

        public MyAdapter(List<MyModel> model,Context context) {
            this.model = model;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,null);
            return new ModelHolder(mView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof ModelHolder){
                ((ModelHolder)holder).left.setText(model.get(position).getLeft());
                ((ModelHolder)holder).content.setText(model.get(position).getContent());
                ((ModelHolder)holder).right.setText(model.get(position).getRight());
                ((ModelHolder)holder).right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notifyItemRemoved(position);
                        model.remove(position);
                        notifyItemRangeChanged(position,model.size());
                        Log.i(TAG, "onClick: position=="+position+"----------------model.size=="+model.size());
                    }
                });
                
            }
        }

        @Override
        public int getItemCount() {
            return model.size();
        }
        class ModelHolder extends RecyclerView.ViewHolder{

            public TextView left;
            public TextView content;
            public TextView right;
            public ModelHolder(View itemView) {
                super(itemView);
                left=(TextView)itemView.findViewById(R.id.leftbutton);
                content=(TextView)itemView.findViewById(R.id.contentview);
                right=(TextView)itemView.findViewById(R.id.rightbutton);
            }
        }
    }
}
