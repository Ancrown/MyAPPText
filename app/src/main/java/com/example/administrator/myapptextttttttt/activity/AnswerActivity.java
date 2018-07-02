package com.example.administrator.myapptextttttttt.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.AnswerAdapter;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.beans.AnswerBean;
import com.example.administrator.myapptextttttttt.beans.AnswerItemBean;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.countdown.CountDownTimerSupport;
import com.example.administrator.myapptextttttttt.utils.countdown.OnCountDownTimerListener;
import com.example.administrator.myapptextttttttt.views.NoSlideListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/14.
 */

public class AnswerActivity extends BaseActivity {
    //剩余时间
    @BindView(R.id.answer_time)
    TextView time;
    private boolean aBoolean;

    //答题下标
    @BindView(R.id.answer_index)
    TextView index;

//    private MyCountDownTimer myCountDownTimer;

    //倒计时
    private CountDownTimerSupport mTimer;


    //标题
    @BindView(R.id.answer_subject)
    TextView subject;
    //选项
    @BindView(R.id.answer_listview)
    NoSlideListView listview;
    //上一题
    @BindView(R.id.btn_previous)
    Button previous;
    //下一题
    @BindView(R.id.btn_next)
    Button next;
    //解释
    @BindView(R.id.answer_explaination)
    TextView explaination;
    //设置单选或者双选
    @BindView(R.id.item_answer_single_pair)
    Button singlePair;
    //最后一道题 点下一题 就不可再往前 默认可以
    private boolean mustNot;
    //
//    //设置单选0或双选1
//    private int singlePairText;
    //题
    private List<AnswerBean> answerBeanList;
    //错题
    private List<AnswerBean> answerBeanErrorList;
    //显示题 还是错题 true 题 false 错题
    private boolean wrongPage = true;
    //选项
    private List<AnswerItemBean> answerItemBeanList0;
    private List<AnswerItemBean> answerItemBeanList1;
    private List<AnswerItemBean> answerItemBeanList2;
    private List<AnswerItemBean> answerItemBeanList3;

    private int current;

    private AnswerAdapter answerAdapter;


    //提示框
    AlertDialog.Builder builderOne;
    AlertDialog.Builder builderTwo;

    @Override
    protected void initData() {
        answerBeanList = new ArrayList<>();
        answerBeanErrorList = new ArrayList<>();

        getData();

        showIndex();
    }

    private void getData() {

        answerItemBeanList0 = new ArrayList<>();
        answerItemBeanList0.add(new AnswerItemBean("0", "选项A"));
        answerItemBeanList0.add(new AnswerItemBean("1", "选项B"));
        answerItemBeanList0.add(new AnswerItemBean("2", "选项C"));
        answerItemBeanList0.add(new AnswerItemBean("3", "选项D"));
        answerBeanList.add(new AnswerBean("1000", "你选那个选项", "0", answerItemBeanList0, "这个题应该选C", "2"));

        answerItemBeanList1 = new ArrayList<>();
        answerItemBeanList1.add(new AnswerItemBean("0", "刘德华"));
        answerItemBeanList1.add(new AnswerItemBean("1", "刘晓伟"));
        answerBeanList.add(new AnswerBean("1001", "谁最高", "1", answerItemBeanList1, "刘晓伟最高", "1"));

        answerItemBeanList2 = new ArrayList<>();
        answerItemBeanList2.add(new AnswerItemBean("0", "秦朝"));
        answerItemBeanList2.add(new AnswerItemBean("1", "隋朝"));
        answerItemBeanList2.add(new AnswerItemBean("2", "西晋"));
        answerItemBeanList2.add(new AnswerItemBean("3", "元朝"));
        answerBeanList.add(new AnswerBean("1002", "下列王朝中统治时间最短的是： ", "0", answerItemBeanList2, "秦朝最短！！！", "0"));

        answerItemBeanList3 = new ArrayList<>();
        answerItemBeanList3.add(new AnswerItemBean("0", "1+9"));
        answerItemBeanList3.add(new AnswerItemBean("1", "2+8"));
        answerItemBeanList3.add(new AnswerItemBean("2", "4+4"));
        answerItemBeanList3.add(new AnswerItemBean("3", "5+5"));
        answerBeanList.add(new AnswerBean("1003", "那个等于10？", "1", answerItemBeanList3, "1+9 / 2+8 / 5+5", "0,1,3"));


        setData();
    }


    @Override
    protected void initListener() {

        answerAdapter.setOnClickLL(new AnswerAdapter.OnClickLL() {
            @Override
            public void onLL(int position, String id) {
                if (!wrongPage)
                    return;

                Log.e("eeeeee", " 点击:" + answerBeanList.get(current).getOptionList().get(position).getText());


                if (answerBeanList.get(current).getSinglePair().equals("0")) {
                    //单选设置
                    for (int i = 0; i < answerBeanList.get(current).getOptionList().size(); i++) {
                        answerBeanList.get(current).getOptionList().get(i).setType(i == position ? (
                                //选中>取消 取消>选中
                                answerBeanList.get(current).getOptionList().get(position).getType() == 0 ? 1 : 0
                        ) : 0);
                    }
                } else {
                    //多选设置
                    answerBeanList.get(current).getOptionList().get(position).setType(
                            //选中>取消 取消>选中
                            answerBeanList.get(current).getOptionList().get(position).getType() == 0 ? 1 : 0);
                }


                answerAdapter.setRefreshList(answerBeanList.get(current).getOptionList());


                StringBuilder aa = new StringBuilder("");
                for (int j = 0; j < answerBeanList.get(current).getOptionList().size(); j++) {
                    if (answerBeanList.get(current).getOptionList().get(j).getType() == 1) {
                        aa.append(answerBeanList.get(current).getOptionList().get(j).getId() +
                                (answerBeanList.get(current).getSinglePair().equals("1") ? "," : ""));
                    }
                }

                answerBeanList.get(current).setMyOptions(aa.toString().equals("") ? "-1" : aa.toString());
                Log.e("eeeeee", " 我的选项：" + answerBeanList.get(current).getMyOptions());

            }
        });

    }

    @Override
    protected void initView() {
        getTitleView().setData("答题", 0, 0, null, 0, null, null);
        answerAdapter = new AnswerAdapter(this);
        listview.setAdapter(answerAdapter);
        builderOne = new AlertDialog.Builder(AnswerActivity.this);
        builderTwo = new AlertDialog.Builder(AnswerActivity.this);


//        myCountDownTimer = new MyCountDownTimer(1000 * 10 * 1, 1000);
//        //开始倒计时
//        myCountDownTimer.start();
        mTimer = new CountDownTimerSupport(1000 * 60 * 30, 1000);
        mTimer.start();
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!AnswerActivity.this.isFinishing()) {
                    time.setText((millisUntilFinished / 1000) / 60 + "分" + (millisUntilFinished / 1000) % 60 + "秒");
                }
            }

            @Override
            public void onFinish() {
                if (!AnswerActivity.this.isFinishing()) {

                    if (!aBoolean) {
                        //操作
                        lookError();
                    }
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_answer;
    }


    @OnClick({R.id.btn_previous, R.id.btn_next, R.id.item_answer_single_pair})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_previous:
                if (mustNot) {
                    ToolUtils.showToast(AnswerActivity.this, "你已经提交！");
                    return;
                }
                current--;
                if (current < 0) {
                    current = 0;
                    ToolUtils.showToast(AnswerActivity.this, "不可往前！");
                    return;
                }

                showIndex();


                //判断显示 题 还是 错题
                if (wrongPage) {
                    setData();
                } else {
                    displayErrorData();
                }

                break;
            case R.id.btn_next:

                comparison();

                current++;
                if (current == (wrongPage ? answerBeanList.size() : answerBeanErrorList.size())) {
                    current = (wrongPage ? answerBeanList.size() : answerBeanErrorList.size()) - 1;
                    if (wrongPage) {
                        //在这显示是否提交~！
                        builderOne.setTitle("提示").setMessage("已经达到最后一题，是否确认交卷？ 交卷就不可修改！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (contrastiveProblem() == 0) {
                                            ToolUtils.showToast(AnswerActivity.this, "恭喜你全部都答对了！");
                                            finish();
                                        } else {
                                            mustNot = true;

                                            //计时停止
                                            mTimer.pause();
                                            lookError();
                                        }

                                    }
                                }).setNegativeButton("取消", null)
                                .show();

                    } else {
                        ToolUtils.showToast(AnswerActivity.this, "不可往后了！");
                    }
                    return;
                }
                showIndex();

                //判断显示 题 还是 错题
                if (wrongPage) {
                    setData();
                } else {
                    displayErrorData();
                }
                break;
            case R.id.item_answer_single_pair:
                if (!ttt) {
                    singlePair.setText("继续");
                    mTimer.pause();
                    ttt = true;
                } else {
                    singlePair.setText("停止");
                    mTimer.resume();
                    ttt = false;
                }


                break;

        }
    }

    private boolean ttt;

    //比对标记
    private void comparison() {
        Log.e("eeeeee", " TTTT: " + answerBeanList.get(current).getMyOptions() + "   " + answerBeanList.get(current).getCorrectOptions());
        if (Arrays.equals(answerBeanList.get(current).getMyOptions().split(","), answerBeanList.get(current).getCorrectOptions().split(","))) {
            answerBeanList.get(current).setErrorOptions(true);
            Log.e("eeeeee", " 你做的正确");
        } else {
            answerBeanList.get(current).setErrorOptions(false);
            Log.e("eeeeee", " 你做的错误！！！！");
        }
    }

    //对比错误个数
    private int contrastiveProblem() {
        int num = 0;
        for (int i = 0; i < answerBeanList.size(); i++) {
            if (!answerBeanList.get(i).isErrorOptions()) {
                num++;
            }
        }
        return num;
    }

    //查看错误题
    private void lookError() {
        builderTwo.setTitle("提示").setMessage("已经答完了！  你打错了" + contrastiveProblem() + "道题！！").setCancelable(false)
                .setPositiveButton("查看错题", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        current = 0;
                        mustNot = false;
                        wrongPage = false;

                        for (int i = 0; i < answerBeanList.size(); i++) {
                            if (!answerBeanList.get(i).isErrorOptions()) {
                                AnswerBean bean = answerBeanList.get(i);
                                for (int j = 0; j < bean.getOptionList().size(); j++) {

                                    bean.getOptionList().get(j).setOnClick(false);
                                }
                                answerBeanErrorList.add(bean);
                            }
                        }
                        displayErrorData();

                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    //题数据
    private void setData() {
        singlePair.setText("当前: " + current);
        subject.setText(answerBeanList.get(current).getTitle());
        explaination.setVisibility(View.INVISIBLE);
        answerAdapter.setRefreshList(answerBeanList.get(current).getOptionList());
    }


    //比对错题数据
    private void displayErrorData() {
        singlePair.setText("当前: " + current);
        subject.setText(answerBeanErrorList.get(current).getTitle());
        explaination.setVisibility(View.VISIBLE);
        explaination.setText(answerBeanErrorList.get(current).getAnalysis());
        answerAdapter.setRefreshList(answerBeanErrorList.get(current).getOptionList());

    }

    //显示当 当前题/总题数
    private void showIndex() {
        index.setText((current + 1) + "/" + answerBeanList.size());
    }

    //自定义一个类继承自CountDownTimer，实现多少秒后的逻辑
//    class MyCountDownTimer extends CountDownTimer {
//        private Context context;
//
//        /**
//         *    *
//         *    * @param millisInFuture
//         *    * 表示以毫秒为单位 倒计时的总数
//         *    *
//         *    * 例如 millisInFuture=1000 表示1秒
//         *    *
//         *    * @param countDownInterval
//         *    * 表示 间隔 多少微秒 调用一次 onTick 方法
//         *    *
//         *    * 例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
//         *    *
//         *    
//         */
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            if (!AnswerActivity.this.isFinishing()) {
//                time.setText((millisUntilFinished / 1000) / 60 + "分" + (millisUntilFinished / 1000) % 60 + "秒");
//            }
//
//        }
//
//        @Override
//        public void onFinish() {
//            if (!AnswerActivity.this.isFinishing()) {
//
//                if (!aBoolean) {
//                    //停止倒计时 
//                    cancel();
//                    //操作
//                    lookError();
//                }
//            }
//        }
//    }
}
