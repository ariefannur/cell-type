package arief.com.celltype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arief.com.celltype.config.ImageUtils;
import arief.com.celltype.config.Service;
import arief.com.celltype.model.Item;
import arief.com.celltype.model.Result;
import arief.com.celltype.view.RvAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Item> lsReview = new ArrayList<>();
    private List<Item> lsSalary = new ArrayList<>();
    private List<Item> lsCompany = new ArrayList<>();
    private List<Item> lsHorizontal = new ArrayList<>();

    private LinearLayout layTheme;
    private RecyclerView rvCompany;
    private RvAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layTheme = (LinearLayout) findViewById(R.id.layTheme);
        rvCompany = (RecyclerView) findViewById(R.id.rvCompany);
        rvCompany.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    void getData(){
        Service.Api api = Service.create().create(Service.Api.class);
        api.getData().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.code() == 200){
                    Log.d("AF ", "List item "+response.body().items.size());
                    for (int i = 0; i < response.body().items.size(); i++) {
                        Item item = response.body().items.get(i);
                        if(item.cell_type.equals("CELL_TYPE_SALARY")){
                            lsSalary.add(item);
                        }else if(item.cell_type.equals("CELL_TYPE_HORIZONTAL_THEME")){
                            lsHorizontal.add(item);
                        }else if(item.cell_type.equals("CELL_TYPE_REVIEW")){
                            lsReview.add(item);
                        }else if(item.cell_type.equals("CELL_TYPE_COMPANY")){
                            lsCompany.add(item);
                        }

                    }
                    List<Item> ls = new ArrayList<Item>();
                    Item itmCompany = new Item();
                    itmCompany.cell_type = "TITLE";
                    itmCompany.name = "Company";
                    ls.add(itmCompany);

                    ls.addAll(lsCompany);

                    Item itmReview= new Item();
                    itmReview.cell_type = "TITLE";
                    itmReview.name = "Review";
                    ls.add(itmReview);

                    ls.addAll(lsReview);

                    Item itmSalary= new Item();
                    itmSalary.cell_type = "TITLE";
                    itmSalary.name = "Salary";
                    ls.add(itmSalary);

                    ls.addAll(lsSalary);

                    adapter = new RvAdapter(getApplicationContext(), ls);
                    rvCompany.setAdapter(adapter);

                    for (int i = 0; i < ls.size(); i++) {
                        Log.d("AF", "ini adalah :: "+ls.get(i).cell_type);
                    }

                    genereteTheme();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("AF ", "errr "+t.getMessage());
            }
        });
    }

    private void genereteTheme() {
        int size = lsHorizontal.get(0).themes.size();
        if(size > 0){
            for (int i = 0; i < size; i++) {
                Item.Theme tm = lsHorizontal.get(0).themes.get(i);
                View theme = LayoutInflater.from(this).inflate(R.layout.theme_item, null, false);
                TextView txtTitle = (TextView) theme.findViewById(R.id.txtTitle);
                txtTitle.setText(tm.title);
                ImageView img = (ImageView) theme.findViewById(R.id.imgTheme);
                ImageUtils.displayImage(getApplicationContext(), tm.cover_image, img);

                layTheme.addView(theme);
            }
        }
    }
}
