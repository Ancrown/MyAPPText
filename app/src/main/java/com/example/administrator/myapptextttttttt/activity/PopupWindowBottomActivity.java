package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.utils.popupwindow.PopupWindowCheckChoose;
import com.example.administrator.myapptextttttttt.utils.popupwindow.PopupWindowTest;
import com.example.administrator.myapptextttttttt.utils.popupwindow.citylinkage.CityPicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/26
 * 描述:
 */

public class PopupWindowBottomActivity extends BaseActivity {
    @BindView(R.id.pp_address)
    TextView ppAddress;
    @BindView(R.id.pp_time)
    TextView ppTime;
    @BindView(R.id.pp_time_mo)
    TextView ppTimeMo;

    private TextView mTvContent;
    private PopupWindowCheckChoose mPopup;
    private ArrayList<String> mList = new ArrayList();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        mTvContent = (TextView) findViewById(R.id.tv_content);


        mList = getPopList();
        mPopup = new PopupWindowCheckChoose(this, mList);
        mPopup.setTagTxt(getString(R.string.choose_drawback_reason_of_refuse))//设置顶部title的内容
                .setButtomTxt(getString(R.string.cancel))//设置底部按钮内容
                .setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);//单选


        mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup.showPop(mTvContent);
            }
        });
        //单选
        mPopup.setOnEventLisenter(new PopupWindowCheckChoose.onEventLisenter() {
            @Override
            public void onItemClick(ArrayList<Integer> positionList) {
                mPopup.dismiss();
                mTvContent.setText(mList.get(positionList.get(0)));
            }

            @Override
            public void onClickButtom() {

            }
        });

        //-----------------------------------
//        //多选
//        mPopup.setOnEventLisenter(positionList -> {
//            StringBuffer buffer=new StringBuffer();
//            for (int i=0;i<positionList.size();i++){
//                buffer.append(mList.get(positionList.get(i))+",");
//            }
//            mTvContent.setText(buffer.toString());
//        });
    }

    /**
     * 数据
     *
     * @return
     */
    public ArrayList<String> getPopList() {
        ArrayList<String> popList = new ArrayList<>();
        popList.add(getString(R.string.reason_draw_back_buy_more));
        popList.add(getString(R.string.reason_draw_back_not_receive));
        popList.add(getString(R.string.reason_draw_back_its_fake));
        popList.add(getString(R.string.reason_draw_back_buy_error));
        popList.add(getString(R.string.reason_draw_back_type_error));
        popList.add(getString(R.string.reason_draw_back_quality_problem));
        popList.add(getString(R.string.reason_draw_back_deal));
        popList.add(getString(R.string.reason_draw_back_no_info_of_logistics));
        popList.add(getString(R.string.reason_draw_back_no_goods));
        popList.add(getString(R.string.reason_draw_back_another_reason));
        return popList;
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_popup_bottom;
    }


    //工作期望地
    private String address;
    private String cityCode;

    @OnClick({R.id.pp_address, R.id.pp_time, R.id.pp_time_mo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pp_address:
                CityPicker.Builder builder = new CityPicker.Builder(this).onlyShowProvinceAndCity(true)
                        .visibleItemsCount(7).title("期望工作地").titleTextColor("#000000").confirTextColor("#000000").cancelTextColor("#838383");
                if (!TextUtils.isEmpty(address)) {
                    builder.province(address.split(",")[0]).city(address.split(",")[1]);
                }
                CityPicker c = builder.build();

                c.show();
                c.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {

                        address = citySelected[0] + "," + citySelected[1];
                        cityCode = citySelected[3];
                        ppAddress.setText(citySelected[0] + citySelected[1]);

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onHide() {
                    }
                });
                break;
            case R.id.pp_time:
                PopupWindowTest test = new PopupWindowTest(this, 0);
                Log.e("eeeeee", "eeeeeeeeeeeeeeeeeeeeeeeee");
                break;
            case R.id.pp_time_mo:
                break;
        }
    }
}
