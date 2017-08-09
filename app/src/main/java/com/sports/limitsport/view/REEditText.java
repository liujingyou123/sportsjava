package com.sports.limitsport.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.sports.limitsport.model.ReObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/8/9.
 */

public class REEditText extends EditText {

    private List<ReObject> reObjects = new ArrayList<>();

    public REEditText(Context context) {
        super(context);
        init();
    }

    public REEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public REEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {

                    int selectionStart = getSelectionStart();

                    int lastPos = 0;
                    // 遍历判断光标的位置
                    for (int i = 0; i < reObjects.size(); i++) {

                        String objectText = reObjects.get(i)
                                .getText();

                        lastPos = getText().toString().indexOf(objectText,
                                lastPos);
                        if (lastPos != -1) {
                            if (selectionStart != 0
                                    && selectionStart >= lastPos
                                    && selectionStart <= (lastPos + objectText
                                    .length())) {

                                getText().delete(lastPos,
                                        lastPos + objectText.length());

                                reObjects.remove(i);
                                return true;
                            }
                        }
                        lastPos += objectText.length();
                    }
                }
                return false;
            }
        });

        setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                int selectionStart = getSelectionStart();
                if (reObjects != null && reObjects.size() > 0) {
                    String objectLastOne = reObjects.get(reObjects.size() - 1).getText();
                    if (objectLastOne.equals(source.toString())) {
                        return null;
                    }
                    String objectText = reObjects.get(0)
                            .getText();
                    int indexPos = getText().toString().indexOf(objectText);
                    if (selectionStart > indexPos) {
                        return "";
                    }
                }
                return null;
            }
        }});
    }

    public void append(ReObject object) {
        reObjects.add(object);
        append(object.getText());
    }


    public void clearAt() {
        if (reObjects != null && reObjects.size() > 0) {
            int firstPos = getText().toString().indexOf(reObjects.get(0).getText());
            getText().delete(firstPos, getText().length());
            reObjects.clear();
        }
    }


    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (reObjects == null || reObjects.size() == 0) {
            return;
        }

        int startPosition = 0;
        int endPosition = 0;
        int lastPos = 0;
        String objectText = "";
        for (int i = 0; i < reObjects.size(); i++) {
            objectText = reObjects.get(i).getText();// 文本
            startPosition = getText().toString().indexOf(objectText, lastPos);// 获取文本开始下标
            endPosition = startPosition + objectText.length();
            lastPos = endPosition;
            if (startPosition != -1 && selStart > startPosition
                    && selStart <= endPosition) {// 若光标处于话题内容中间则移动光标到话题结束位置
                setSelection(endPosition);
                break;
            }
        }
    }
}
