package com.example.androidtrialoform.utilities;

import com.example.androidtrialoform.MainActivity;
import com.example.androidtrialoform.R;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowDialog {
	private Context mContext;
	private String mId;
	private String mSearchString;
	
	public ShowDialog(Context context) {
		mContext = context;
	}
	
	public void showCustomDialog() {
		final Dialog dialog = new Dialog(mContext);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_row, null);
		dialog.setContentView(view);
		Button btnOK = (Button)view.findViewById(R.id.btnOK);
		Button btnCancel = (Button)view.findViewById(R.id.btnCancel);
		final EditText editId = (EditText)view.findViewById(R.id.editId);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(editId.getText().toString())) {
					Toast.makeText(mContext, "Please enter an id", Toast.LENGTH_SHORT).show();
				}else {
					mId = editId.getText().toString().trim();
					((MainActivity)mContext).receiveId(mId);
					dialog.dismiss();
				}
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	
	public void showStringSearchDialog() {
		final Dialog dialog = new Dialog(mContext);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_row, null);
		dialog.setContentView(view);
		Button btnOK = (Button)view.findViewById(R.id.btnOK);
		Button btnCancel = (Button)view.findViewById(R.id.btnCancel);
		final EditText editId = (EditText)view.findViewById(R.id.editId);
		editId.setInputType(InputType.TYPE_CLASS_TEXT);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(editId.getText().toString())) {
					mSearchString = "";
				}else {
					mSearchString = editId.getText().toString().trim();
					((MainActivity)mContext).receiveString(mSearchString);
					dialog.dismiss();
				}
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
}
