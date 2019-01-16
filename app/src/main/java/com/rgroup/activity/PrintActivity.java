package com.rgroup.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rgroup.R;
import com.rgroup.util.AidlUtil;
import com.rgroup.util.BluetoothUtil;
import com.rgroup.util.ESCUtil;

import java.io.IOException;

import sunmi.sunmiui.dialog.DialogCreater;
import sunmi.sunmiui.dialog.ListDialog;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PrintActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView mTextView1, mTextView2;
    private CheckBox mCheckBox1, mCheckBox2;
    private TextView mEditText;
    private LinearLayout mLayout, mLinearLayout;
    private int record;
    private boolean isBold, isUnderLine;
   private String print_msg;

    private String[] mStrings = new String[]{"CP437", "CP850", "CP860", "CP863", "CP865", "CP857", "CP737", "Windows-1252", "CP866", "CP852", "CP858", "CP874","CP855", "CP862", "CP864", "GB18030", "BIG5", "KSC5601", "utf-8"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priint_screen);
        print_msg = getIntent().getStringExtra("print_mesg");
        System.out.println("print message value is   "+print_msg);
        record = 17;
        isBold = false;
        isUnderLine = false;
        mTextView1 = (TextView) findViewById(R.id.text_text_character);
        mTextView2 = (TextView) findViewById(R.id.text_text_size);
        mCheckBox1 = (CheckBox) findViewById(R.id.text_bold);
        mCheckBox2 = (CheckBox) findViewById(R.id.text_underline);
        mEditText = (TextView) findViewById(R.id.text_text);
        if(print_msg != null){
            String[] data = print_msg.split("\\|");
            System.out.println("Vendor Id: " + data[0] +"\n"+ "Item Id: "+data[2] +"\n"+ "Voucher Serial: "+ data[3]+"\n"+"Expiry Date: "+data[4] +"\n"+
                    "Account Id: "+data[9]+"\n"+"Provider ID: "+data[8]+"\n"+"VAT : "+data[11]);
            String final_string = ("Vendor Id: " + data[0] +"\n"+ "Item Id: "+data[2] +"\n"+ "Voucher Serial: "+ data[3]+"\n"+"Expiry Date: "+data[4] +"\n"+
                    "Account Id: "+data[9]+"\n"+"Provider ID: "+data[8]+"\n"+"VAT : "+data[11]);
            mEditText.setText(final_string);
        }else{
            mEditText.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
        }


        mLinearLayout = (LinearLayout) findViewById(R.id.text_all);
        mLayout = (LinearLayout) findViewById(R.id.text_set);

        mLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mLinearLayout.getWindowVisibleDisplayFrame(r);
                if(r.bottom < 800){
                    mLayout.setVisibility(View.GONE);
                }else{
                    mLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        mCheckBox1.setOnCheckedChangeListener(this);
        mCheckBox2.setOnCheckedChangeListener(this);


        findViewById(R.id.text_character).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListDialog listDialog = DialogCreater.createListDialog(PrintActivity.this, getResources().getString(R.string.characterset), getResources().getString(R.string.cancel), mStrings);
                listDialog.setItemClickListener(new ListDialog.ItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        mTextView1.setText(mStrings[position]);
                        record = position;
                        listDialog.cancel();
                    }
                });
                listDialog.show();
            }
        });

        findViewById(R.id.text_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSeekBarDialog(PrintActivity.this, getResources().getString(R.string.size_text), 1, 36, mTextView2);
            }
        });
        AidlUtil.getInstance().initPrinter();
    }

    public void onClick(View view) {

        String content = mEditText.getText().toString();

        float size = Integer.parseInt(mTextView2.getText().toString());
        if (baseApp.isAidl()) {
            AidlUtil.getInstance().printText(content, size, isBold, isUnderLine);
            finish();
        } else {
            printByBluTooth(content);
        }
    }

    private void printByBluTooth(String content) {
        try {
            if (isBold) {
                BluetoothUtil.sendData(ESCUtil.boldOn());
            } else {
                BluetoothUtil.sendData(ESCUtil.boldOff());
            }

            if (isUnderLine) {
                BluetoothUtil.sendData(ESCUtil.underlineWithOneDotWidthOn());
            } else {
                BluetoothUtil.sendData(ESCUtil.underlineOff());
            }

            if (record < 17) {
                BluetoothUtil.sendData(ESCUtil.singleByte());
                BluetoothUtil.sendData(ESCUtil.setCodeSystemSingle(codeParse(record)));
            } else {
                BluetoothUtil.sendData(ESCUtil.singleByteOff());
                BluetoothUtil.sendData(ESCUtil.setCodeSystem(codeParse(record)));
            }

            BluetoothUtil.sendData(content.getBytes(mStrings[record]));
            BluetoothUtil.sendData(ESCUtil.nextLine(3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte codeParse(int value) {
        byte res = 0x00;
        switch (value) {
            case 0:
                res = 0x00;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                res = (byte) (value + 1);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                res = (byte) (value + 8);
                break;
            case 12:
                res = 21;
                break;
            case 13:
                res = 33;
                break;
            case 14:
                res = 34;
                break;
            case 15:
                res = 36;
                break;
            case 16:
                res = 37;
                break;
            case 17:
            case 18:
            case 19:
                res = (byte) (value - 17);
                break;
            case 20:
                res = (byte) 0xff;
                break;
        }
        return (byte) res;
    }

    /**
     * 自定义的seekbar dialog
     *
     * @param context
     * @param title
     * @param min
     * @param max
     * @param set
     */
    private void showSeekBarDialog(Context context, String title, final int min, final int max, final TextView set) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_seekbar, null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        TextView tv_title = (TextView) view.findViewById(R.id.sb_title);
        TextView tv_start = (TextView) view.findViewById(R.id.sb_start);
        TextView tv_end = (TextView) view.findViewById(R.id.sb_end);
        final TextView tv_result = (TextView) view.findViewById(R.id.sb_result);
        TextView tv_ok = (TextView) view.findViewById(R.id.sb_ok);
        TextView tv_cancel = (TextView) view.findViewById(R.id.sb_cancel);
        SeekBar sb = (SeekBar) view.findViewById(R.id.sb_seekbar);
        tv_title.setText(title);
        tv_start.setText(min + "");
        tv_end.setText(max + "");
        tv_result.setText(set.getText());
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.setText(tv_result.getText());
                dialog.cancel();
            }
        });
        sb.setMax(max - min);
        sb.setProgress(Integer.parseInt(set.getText().toString()) - min);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int rs = min + progress;
                tv_result.setText(rs + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.text_bold:
                isBold = isChecked;
                break;
            case R.id.text_underline:
                isUnderLine = isChecked;
                break;
            default:
                break;
        }
    }
}
