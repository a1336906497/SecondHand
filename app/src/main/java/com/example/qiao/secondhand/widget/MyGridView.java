package com.example.qiao.secondhand.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class MyGridView extends GridView{

	public MyGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyGridView(Context context, AttributeSet attrs){
		super(context,attrs);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int h=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, h);
	}
}
