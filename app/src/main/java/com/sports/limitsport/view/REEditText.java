package com.sports.limitsport.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.ReObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/8/9.
 */

public class REEditText extends EditText {

    private List<ReObject> reObjects = new ArrayList<>();
    private OnDelObjectListener mOnDelObjectListener;

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


    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    private void init() {
        setLongClickable(false);
        this.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        this.setLongClickable(false);

        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {

                    int selectionStart = getSelectionStart();
                    int selectionEnd = getSelectionEnd();

                    // 遍历判断光标的位置
                    for (int i = 0; i < reObjects.size(); i++) {

                        int index = reObjects.get(i).getIndex();
                        String objectText = reObjects.get(i)
                                .getText();

                        if (index != -1) {
                            if (selectionStart != 0 && selectionStart == index + objectText.length()) {
                                for (int j = 0; j < reObjects.size(); j++) {
                                    ReObject reObject = reObjects.get(j);
                                    int indexJ = reObject.getIndex();
                                    if (selectionEnd > 0 && selectionEnd <= indexJ) {
                                        if (selectionEnd == selectionStart) {
                                            reObject.setIndex(reObject.getIndex() - 1);
                                        } else {
                                            reObject.setIndex(reObject.getIndex() - (selectionEnd - selectionStart));
                                        }
                                    }
                                }
                                if (mOnDelObjectListener != null) {
                                    mOnDelObjectListener.onDeleteListener(reObjects.remove(i));
                                }
                                getText().delete(index,
                                        index + objectText.length());

                                return true;
                            }

                        }
                    }

                    for (int i = 0; i < reObjects.size(); i++) {
                        ReObject reObject = reObjects.get(i);
                        int index = reObject.getIndex();
                        if (selectionEnd > 0 && selectionEnd <= index) {
                            if (selectionEnd == selectionStart) {
                                reObject.setIndex(reObject.getIndex() - 1);
                            } else {
                                reObject.setIndex(reObject.getIndex() - (selectionEnd - selectionStart));
                            }
                        }
                    }
                }
                return false;
            }
        });

        setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                XLog.e("filter = " + source);
                int selectionStart = getSelectionStart();
                if (isSetSpan) {
                    XLog.e("filter selectionStart = " + selectionStart);
                    return null;
                }
                if (reObjects != null && reObjects.size() > 0) {
                    for (int i = 0; i < reObjects.size(); i++) {
                        ReObject reObject = reObjects.get(i);
                        if (reObject.getIndex() < 0) {
                            if ("2".equals(reObject.getType())) {
                                reObject.setIndex(dstart);
                            } else {
                                reObject.setIndex(selectionStart);
                            }
                        } else if (reObject.getIndex() >= selectionStart) {
                            reObject.setIndex(source.length() + reObject.getIndex());
                        }
                    }
                }
                if (reObjects != null && reObjects.size() > 0 && reObjects.get(reObjects.size() - 1).getType().equals("2") && selectionStart == getText().length()) {
                    String objectLastOne = reObjects.get(reObjects.size() - 1).getText();
                    if (objectLastOne.equals(source.toString())) {

                        return null;
                    }
                    return "";
                }


                return null;
            }
        }});

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isSetSpan) {
                    setTextCcc();
                } else {
                    isSetSpan = false;
                }
            }
        });

    }

    boolean isSetSpan;

    public void setTextCcc() {
        isSetSpan = true;
        Spannable spannable = new SpannableString(getText().toString());
        for (int i = 0; i < reObjects.size(); i++) {
            ReObject reObject = reObjects.get(i);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#4899ff")), reObject.getIndex(), reObject.getIndex() + reObject.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        int selectionStart = getSelectionStart();
        XLog.e("before selectionStart = " + selectionStart);
        setText(spannable);
        setSelection(selectionStart);
        int selectionStarta = getSelectionStart();
        XLog.e("after selectionStart = " + selectionStarta);
//        int selectionStart = getSelectionStart();
//        XLog.e("setTextCcc selectionStart = " + selectionStart);

    }

    public void appendInsert(ReObject object) {
        if (reObjects != null && reObjects.size() > 0) {
            if ("2".equals(reObjects.get(reObjects.size() - 1).getType())) {
                reObjects.add(reObjects.size() - 1, object);
            } else {
                reObjects.add(object);
            }
        } else if (reObjects != null) {
            reObjects.add(object);
        }
        int selectPosition = getSelectionStart();
        Editable editText = getEditableText();
        if (selectPosition < 0 || selectPosition >= editText.length()) {
            editText.append(object.getText());
        } else {
            editText.insert(selectPosition, object.getText());//光标所在位置插入文字
        }
    }

    public void append(ReObject object) {
        reObjects.add(object);
        append(object.getText());
    }

//    public String getContent() {
//        String str = null;
//        if (reObjects != null && reObjects.size() > 0) {
//            String objectText = reObjects.get(0).getText();
//            String content = getText().toString();
//            int lastPos = content.indexOf(objectText);
//            if (lastPos >= 0) {
//                str = getText().toString().substring(0, lastPos);
//            }
//        } else {
//            str = getText().toString();
//        }
//
//        return str;
//    }

    public String stringFormat(String name, String id) {
        return String.format("%s[AT]%s[UID]", name, id);
    }

    public String stringFormatId(String id) {
        return String.format("[AT]%s[UID]", id);
    }

    public String getUploadContent() {
        String content = getText().toString();
        StringBuilder sb = new StringBuilder(content);

        if (reObjects != null && reObjects.size() > 0) {
            List<ReObject> tmps = new ArrayList<>();
            ReObject lastOne = reObjects.get(reObjects.size() - 1);
            if ("2".equals(lastOne.getType())) {
                tmps.addAll(reObjects.subList(0, reObjects.size() - 1));
                sb.delete(lastOne.getIndex(), sb.length());
            } else {
                tmps.addAll(reObjects);
            }

            MyCompartor compartor = new MyCompartor();
            Collections.sort(tmps, compartor);

            int offset = 0;
            for (int i = 0; i < tmps.size(); i++) {
                ReObject currentO = tmps.get(i);
                String ids = stringFormatId(currentO.getId());
                sb.insert(currentO.getIndex() + currentO.getText().length() + offset, ids);
                offset += ids.length();
            }
        }

        return sb.toString();

    }

    public void clearActivity() {
        if (reObjects != null && reObjects.size() > 0) {

            for (int i = 0; i < reObjects.size(); i++) {
                ReObject reObject = reObjects.get(i);
                if (reObject != null && "2".equals(reObject.getType())) {
                    int index = reObject.getIndex();
                    reObjects.remove(i);
                    getText().delete(index,
                            index + reObject.getText().length());

                }

            }
        }
    }

    public List<ReObject> getTypeData(String type) {
        List<ReObject> retObjects = new ArrayList<>();
        for (int i = 0; i < reObjects.size(); i++) {
            ReObject reObject = reObjects.get(i);
            if (type.equals(reObject.getType())) {
                retObjects.add(reObject);
            }
        }
        return retObjects;
    }

    public void clear(String type) {
        if (reObjects != null && reObjects.size() > 0) {

            List<ReObject> mType = new ArrayList<>();
            for (int i = 0; i < reObjects.size(); i++) {
                String objectText = reObjects.get(i)
                        .getText();

                int lastPos = getText().toString().indexOf(objectText);

                if (type.equals(reObjects.get(i).getType())) {
                    mType.add(reObjects.get(i));
                    getText().delete(lastPos,
                            lastPos + objectText.length());
                }
            }
            for (int i = 0; i < mType.size(); i++) {
                reObjects.remove(mType.get(i));
            }
        }
    }


    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        XLog.e("selStart = " + selStart + " selEnd = " + selEnd);

        super.onSelectionChanged(selStart, selEnd);
        if (selStart != selEnd) {
            setSelection(selEnd);
            return;
        }
        if (reObjects == null || reObjects.size() == 0) {
            return;
        }

        int endPosition = 0;
        String objectText = "";
        for (int i = 0; i < reObjects.size(); i++) {
            objectText = reObjects.get(i).getText();// 文本
            int index = reObjects.get(i).getIndex();


            endPosition = index + objectText.length();

            if (index != -1 && selStart > index && selStart < endPosition) {
                XLog.e("setSelection = " + endPosition);
                setSelection(endPosition);
                break;
            }
        }
    }

    public void setOnDelObjectListener(OnDelObjectListener onDelObjectListener) {
        this.mOnDelObjectListener = onDelObjectListener;
    }

    public interface OnDelObjectListener {
        void onDeleteListener(ReObject object);
    }

    private class ActionModeCallbackInterceptor implements ActionMode.Callback {


        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }


        public void onDestroyActionMode(ActionMode mode) {
        }
    }

    static class MyCompartor implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {

            ReObject sdto1 = (ReObject) o1;

            ReObject sdto2 = (ReObject) o2;

            return sdto1.getIndex() - sdto2.getIndex();

        }
    }

}
