package com.boohee.customtagview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustomTagView extends android.support.v7.widget.AppCompatTextView {

    private StringBuffer content_buffer;

    private TextView tv_tag;

    private Context mContext;

    private int mWidth;
    private int mTagWidth;

    private String mContent;
    private String mTag;

    private int mLayoutId=0;

    public CustomTagView(Context context) {

        super(context);

        mContext = context;

    }


    public CustomTagView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mContext = context;

    }


    public CustomTagView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        mContext = context;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (TextUtils.isEmpty(mTag)) {
            return;
        }
        drawTag();
    }

    private void drawTag() {
        try {
            mWidth = getWidth();

            if (mWidth > 0) {
                String lastText = getLastLength();

                int index = 0;
                if (mWidth > 0 && ((getPaint().measureText(lastText)-mTagWidth) > (mWidth - mTagWidth))) {
                    while (true) {
                        if (mWidth > 0 && ((getPaint().measureText(lastText) -mTagWidth)> (mWidth - mTagWidth))) {
                            lastText = lastText.substring(0, lastText.length() - 1);
                            index++;
                        } else {
                            break;
                        }
                    }
                    if (mContent.length() - index > 0) {
                        this.mContent = mContent.substring(0, mContent.length() - index);
                    }
                }
                setContent(mContent, mTag);
            }
        } catch (Exception e) {

        }

    }

    private ImageSpan getImageSpan(String tag) {
        View view;
        if(mLayoutId==0){
            view= LayoutInflater.from(mContext).inflate(R.layout.cor_tag, null);
        }else{
            view= LayoutInflater.from(mContext).inflate(mLayoutId, null);
        }

        tv_tag = view.findViewById(R.id.tv_tag);
        tv_tag.setText(tag);
        Bitmap bitmap = convertViewToBitmap(view);
        Drawable d = new BitmapDrawable(bitmap);
        mTagWidth=view.getWidth();
        d.setBounds(0, 0, view.getWidth(),view.getHeight() );
        return new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
    }

    private void setContent(String content, String tag) {
        this.mContent = content;
        this.mTag = tag;
        content_buffer = new StringBuffer();
        content_buffer.append(mContent);
        content_buffer.append(tag);
        SpannableString spannableString = new SpannableString(content_buffer);
        int startIndex;
        int endIndex;
        startIndex = mContent.length();
        endIndex = startIndex + tag.length();
        spannableString.setSpan(getImageSpan(tag), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);

    }


    private static Bitmap convertViewToBitmap(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();


        return bitmap;

    }

    private String getLastLength() {
        Layout layout = getLayout();
        String text = getText().toString();
        String line = null;
        int start = 0;
        int end;
        for (int i = 0; i < getLineCount(); i++) {
            end = layout.getLineEnd(i);
            line = text.substring(start, end);
            start = end;
        }
        return line;
    }

    public void setLayoutResource(int layoutId) {
        this.mLayoutId = layoutId;
    }

    public void setContentAndTag(String content, String tag) {
        this.mContent = content;
        this.mTag = tag;

        if (TextUtils.isEmpty(tag)) {
            setText(content);
            return;
        }

        content_buffer = new StringBuffer();
        content_buffer.append(mContent);
        content_buffer.append(tag);
        SpannableString spannableString = new SpannableString(content_buffer);
        int startIndex;
        int endIndex;
        startIndex = mContent.length();
        endIndex = startIndex + tag.length();
        spannableString.setSpan(getImageSpan(tag), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(spannableString);

        setGravity(Gravity.CENTER_VERTICAL);
        if (mWidth > 0) {
            drawTag();
        }
    }

}
