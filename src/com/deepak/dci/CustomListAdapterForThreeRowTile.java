package com.deepak.dci;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapterForThreeRowTile
  extends ArrayAdapter<String>
{
  private final Activity context;
  private final String[] row1;
  private final String[] row2;
  private final String[] row3;
  
  public CustomListAdapterForThreeRowTile(Activity paramActivity, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    super(paramActivity, R.layout.custom_tile_with_threerow, paramArrayOfString1);
    this.context = paramActivity;
    this.row1 = paramArrayOfString1;
    this.row2 = paramArrayOfString2;
    this.row3 = paramArrayOfString3;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = this.context.getLayoutInflater().inflate(R.layout.custom_tile_with_threerow, null, true);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.tVTileRow1);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.tVTileRow2);
    TextView localTextView3 = (TextView)localView.findViewById(R.id.tVTileRow3);
    localTextView1.setText(Html.fromHtml(paramInt + 1 + ". " + this.row1[paramInt]) );
    localTextView2.setText(Html.fromHtml(this.row2[paramInt]));
    localTextView3.setText(this.row3[paramInt]);
    
    localTextView1.setSelected(true);
    return localView;
  }
}


