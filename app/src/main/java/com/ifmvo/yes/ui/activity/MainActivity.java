package com.ifmvo.yes.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.presenter.impl.TestPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.ITestPresenter;
import com.ifmvo.yes.ui.custom.TitleBar;
import com.ifmvo.yes.ui.view.interfaces.ITestView;
import com.ifmvo.yes.vo.request.QueryRequest;
import com.ifmvo.yes.vo.response.ResultResponse;
import com.ifmvo.yes.vo.info.Result;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements ITestView {
    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.tv_result)
    TextView tvResult;

    ITestPresenter testPresenter;

    @Override
    public void initContentView() {
        // 设置布局文件
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        titleBar.setTitle("测试页面");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryRequest queryParameter = new QueryRequest();
                queryParameter.phone = etInput.getText().toString();

                testPresenter.attributionToInquiries(MainActivity.this, queryParameter);
            }
        });
    }

    @Override
    public void initPresenter() {
        testPresenter = new TestPresenterImpl();
    }

    @Override
    public void queryResult(ResultResponse result) {
        Result resultInfo = result.result;
        if (resultInfo != null) {
            showToast("您的手机归属地信息是:\n" + resultInfo.province + resultInfo.city + resultInfo.company + resultInfo.card);
        }
    }
}
