package arief.com.celltype.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arief.com.celltype.R;
import arief.com.celltype.config.ImageUtils;
import arief.com.celltype.model.Item;

/**
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name Arief Maffrudin A N, 13/04/17
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Context mContext;
    private List<Item> lsItems;
    private final int REVIEW = 0;
    private final int COMPANY = 1;
    private final int SALARY = 2;
    private final int TITLE = 2;

    public RvAdapter(Context context, List<Item> lsItems){
        this.mContext = context;
        this.lsItems = lsItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == COMPANY){
            view = layoutInflater.inflate(R.layout.layout_company, null, false);
        }else if(viewType == SALARY){
            view = layoutInflater.inflate(R.layout.layout_salary, null, false);
        }else if(viewType == REVIEW){
            view = layoutInflater.inflate(R.layout.layout_review, null, false);
        }else{
            view = layoutInflater.inflate(R.layout.title_layout, null, false);
        }

        return new ViewHolder(view, viewType);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(lsItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        String type = lsItems.get(position).cell_type != null ? lsItems.get(position).cell_type : "";
        switch (type){
            case "CELL_TYPE_SALARY":
                return SALARY;
            case "CELL_TYPE_REVIEW":
                return REVIEW;
            case "CELL_TYPE_COMPANY":
                return COMPANY;
            case "TITLE":
                return TITLE;
            default:
                return COMPANY;

        }
    }

    @Override
    public int getItemCount() {
        return lsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtIndestri, txtAvg;
        private ImageView imgLogo;
        private int mType;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            this.mType = type;
            if(type == COMPANY){
                txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
                txtIndestri = (TextView) itemView.findViewById(R.id.txtIndustri);
                txtAvg = (TextView) itemView.findViewById(R.id.txtAvg);

                imgLogo = (ImageView) itemView.findViewById(R.id.imgList);
            }else if(type == TITLE){
                txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            }
        }

        public void bind(Item item){
            if(mType == COMPANY) {
                txtTitle.setText(item.name != null ? item.name : "");
                txtIndestri.setText("" + item.industry_name);
                txtAvg.setText("" + item.rate_total_avg);
                ImageUtils.displayImage(mContext, item.logo_path, imgLogo);
            }else if(mType == TITLE){
                txtTitle.setText(item.name);
            }

        }
    }
}
