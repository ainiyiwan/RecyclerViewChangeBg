package zy.com.recyclerviewchangecolor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<UserBean> list = new ArrayList<>();
    private MyAdapter mAdapter;


    //uiHandler在主线程中创建，所以自动绑定主线程
    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MsgType.TYPE_LEFT:
                    System.out.println("handleMessage thread id " + Thread.currentThread().getId());
                    System.out.println("msg.arg1:" + msg.arg1);
                    int position = (int) msg.obj;
                    UserBean userBean = mAdapter.getItem(position);
                    userBean.setLeftShow(false);
                    mAdapter.setData(position, userBean);
                    Log.e("TAG", "收到消息 消失吧" + position);
                    break;
                case MsgType.TYPE_RIGHT:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        mAdapter = new MyAdapter(R.layout.item_recycler, list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            UserBean userBean = new UserBean();
            list.add(userBean);
        }
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        int position = 8;
        UserBean userBean = mAdapter.getItem(position);
        if (userBean != null) {
            userBean.setLeftShow(true);
            mAdapter.setData(position, userBean);
            countDown(position);
        }
    }

    private void countDown(int position) {
        if (uiHandler.hasMessages(MsgType.TYPE_LEFT, position)) {
            uiHandler.removeMessages(MsgType.TYPE_LEFT, position);
            Log.e("TAG", "取消消息" + position);
        }

        Message msg = uiHandler.obtainMessage();
        msg.what = MsgType.TYPE_LEFT;
        msg.obj = position;
        Log.e("TAG", "开始倒计时" + position);
        uiHandler.sendMessageDelayed(msg, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }
}
